package user.board.main;

import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.member.MemberDao;
import admin.member.MemberDto;
import jakarta.servlet.ServletContext;
import user.board.main.FreeBoardDto;


/*게시판정보 전부 삽입*/
public class FreeBoardDao {
	private Connection connection;
	
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	//게시판 정보 조회-메인 화면에 보여질 리스트
	public List<FreeBoardDto> freeBoardList() 
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FreeBoardDto> freeBoardList = new ArrayList<FreeBoardDto>();

		String sql = "";

		sql = "SELECT F_index, M_INDEX, F_SUBJECT, F_TEXT, "
				+ "F_IMAGE, F_VIEW, F_CRE_DATE, F_NOTICE "
				+ "FROM FREE_BOARD "
				+ "ORDER BY F_index DESC";

		try {
			/* sql 연결 */
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			int brdIndexInt = 0;
			int brdViewInt = 0;
			int brdNoticeInt = 0;
			int memIndexInt = 0;
			String brdSubjectStr = "";
			Date brdCreDate = null;

			
			while (rs.next()) {
				brdIndexInt = rs.getInt("F_index");
				brdViewInt = rs.getInt("F_VIEW");
				brdNoticeInt = rs.getInt("F_NOTICE");
				memIndexInt = rs.getInt("M_INDEX");
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdCreDate = rs.getDate("F_CRE_DATE");

				FreeBoardDto freeBoardDto = new FreeBoardDto(brdIndexInt, memIndexInt, brdSubjectStr
						, brdCreDate, brdViewInt, brdNoticeInt);

				//여기부터 추가
				

				MemberDto memberDto = freeboardWriter(memIndexInt);
				
				freeBoardDto.setMemberDto(memberDto); // MemberDto를 FreeBoardDto에 설정

				
				//이건 원래 있던거
				freeBoardList.add(freeBoardDto);
			}

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end
		return freeBoardList;
	}
	
	//게시판 상세 내용 보이기
	public FreeBoardDto freeBoardDetail(int brdIndexInt) 
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FreeBoardDto freeboardDto = new FreeBoardDto();
		String sql = "";

		sql = "SELECT F_INDEX, F_SUBJECT, F_TEXT,"
				+ " F_IMAGE, M_INDEX, F_VIEW, F_NOTICE,"
				+ " F_CRE_DATE"
				+ " FROM FREE_BOARD"
				+ " WHERE F_INDEX=?"
				+ " ORDER BY F_INDEX DESC";

		pstmt = connection.prepareStatement(sql);

		try {

			pstmt.setInt(1, brdIndexInt);
			rs = pstmt.executeQuery();
			

			String brdSubjectStr = "";//제목
			String brdTextStr = "";//내용
			int memIndexInt = 0;//작성자
			Date brdCreDate = null;//작성일
			int brdViewInt = 0;// 조회수


			if (rs.next()) {
				brdIndexInt = rs.getInt("F_INDEX");
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdTextStr = rs.getString("F_TEXT");
				memIndexInt = rs.getInt("M_INDEX");
				brdCreDate = rs.getDate("F_CRE_DATE");
				brdViewInt = rs.getInt("F_VIEW");

				freeboardDto.setBrdIndexInt(brdIndexInt);
				freeboardDto.setBrdSubjectStr(brdSubjectStr);
				freeboardDto.setBrdTextStr(brdTextStr);
				freeboardDto.setMemIndexInt(memIndexInt);
				freeboardDto.setBrdCreDate(brdCreDate);
				freeboardDto.setBrdViewInt(brdViewInt);
				
			
				// freeboardWriter가 반환하는 리스트에서 첫 번째 MemberDto 객체를 가져옴
				MemberDto memberDto = freeboardWriter(memIndexInt);
				freeboardDto.setMemberDto(memberDto); 
				

			} else {
				throw new Exception("해당 게시물은 존재하지 않습니다.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally 종료
		
		return freeboardDto;
	}
	
	//게시물 새로 작성하기
	public void freeBoardNew(FreeBoardDto freeBoardDto) 
			throws Exception {
		
		PreparedStatement pstmt = null;
		// SQL 객체준비
		try {
			
			int memIndexInt = freeBoardDto.getMemIndexInt();
			String brdSubjectStr = freeBoardDto.getBrdSubjectStr();
			String brdTextStr = freeBoardDto.getBrdTextStr();
//			String brdImageStr = freeBoardDto.getBrdImageStr();
			
			String sql=" ";
			
			sql = "INSERT INTO FREE_BOARD"
					+ " (F_INDEX, M_INDEX, F_SUBJECT, F_TEXT, F_CRE_DATE, F_VIEW , F_NOTICE)"
					+ " VALUES(F_INDEX_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, 3, 0)";
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, memIndexInt);
			pstmt.setString(2, brdSubjectStr);
			pstmt.setString(3, brdTextStr);
//			pstmt.setString(4, brdImageStr);

			pstmt.executeUpdate();
			
			connection.commit();


		}catch (Exception e) {
			connection.rollback(); //오류 시 롤백
			e.printStackTrace();
		}finally {
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				connection.setAutoCommit(true); // 원래 상태로 되돌림
			}
			
		} // finally 종료
	}
	
	public int deleteBoard(int brdIndexInt) 
			throws SQLException {
		// TODO Auto-generated method stub
		int result = 0;
		// sql 실행을 위한 PreparedStatement 객체를 선언
		PreparedStatement pstmt = null;
		
		System.out.println("brdIndexInt 삭제 Dao에 넘어왂나? "+brdIndexInt);
		
		//sql 삭제문 생성
		String sql = "";
		
		sql = "DELETE FROM FREE_BOARD WHERE F_INDEX = ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			//첫 번째 ?(placeholder)에 brdIndexInt 값을 바인딩
			pstmt.setInt(1, brdIndexInt);
			
			result = pstmt.executeUpdate();
			
			if(result==0) {
				
				throw new SQLException("게시글 삭제 실패! 존재하지 않는 게시글일 가능성 있음");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	
	//게시물 업데이트(수정)
	
	public void freeBoardUpdate(FreeBoardDto boardDto) 
			throws SQLException {
		PreparedStatement pstmt = null;
		
		String sql = "";
	
		sql = "UPDATE FREE_BOARD"
				+ " SET F_SUBJECT = ?, F_TEXT = ?"
				+ " WHERE F_INDEX = ?";
		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, boardDto.getBrdSubjectStr());
			pstmt.setString(2, boardDto.getBrdTextStr());
			pstmt.setInt(3, boardDto.getBrdIndexInt());

	
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
			    connection.commit(); 
			} else {
			    connection.rollback(); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
		
		
	}
	
	// 업데이트 페이지에 정보를 넣을 Dao
	
	public FreeBoardDto freeBoardWritedInfo(int brdIndexInt) 
			throws SQLException{
		// TODO Auto-generated method stub
		FreeBoardDto boardDto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		sql = "SELECT F_INDEX, F_SUBJECT, F_TEXT"
				+ " FROM FREE_BOARD"
				+ " WHERE F_INDEX = ?";

		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, brdIndexInt);

			rs = pstmt.executeQuery();

			String brdSubjectStr = "";
			String brdTextStr = "";


			if (rs.next()) {
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdTextStr = rs.getString("F_TEXT");

				boardDto = new FreeBoardDto();

				boardDto.setBrdIndexInt(brdIndexInt);
				boardDto.setBrdSubjectStr(brdSubjectStr);
				boardDto.setBrdTextStr(brdTextStr);

			} else {
				throw new Exception("해당 게시물을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료
		return boardDto;
	}
	
	
	//멤버와 게시판 join-글쓴이명 가져오기용 메서드
	
	public MemberDto freeboardWriter(int memIndex) 
			throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberDto memberDto = null;

		String sql = "";

		//멤버 인덱스로 해당 게시물 글쓴이 찾기
		sql = "SELECT F.F_INDEX, M.M_NAME, M.M_ID"
				+ " FROM FREE_BOARD F INNER JOIN MEMBER M"
				+ " ON M.M_INDEX = F.M_INDEX"
				+ " where M.M_INDEX = ?"
				+ " ORDER BY F_INDEX DESC";

		try {
			
			/* sql 연결 */
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, memIndex);

			rs = pstmt.executeQuery();


			String memNameStr = "";
			String memIdStr = "";

			
			
			while (rs.next()) {

				memNameStr = rs.getString("M_NAME");
				memIdStr = rs.getString("M_ID");

				if(memNameStr==null || memNameStr.equals("") ) {
					System.out.println(memNameStr);
					memNameStr="탈퇴한 회원";
					memIdStr = "탈퇴한 회원";
					System.out.println(memNameStr);
				}
				memberDto = new MemberDto(memIndex, memNameStr, memIdStr);

			}

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end
		return memberDto;
	}
	

	
}
