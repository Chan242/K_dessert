package user.board.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import admin.member.MemberDto;
import user.board.main.FreeBoardDto;

public class BoardReplyDao {

	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	// 댓 리스트 Dao
	public List<BoardReplyDto> replyList(int brdIndexInt) 
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<BoardReplyDto> boardreplyList = new ArrayList<BoardReplyDto>();

		System.out.println("댓글리스트 Dao 시작");
		try {
			sql = "SELECT R_INDEX, M_INDEX, R_TEXT, F_INDEX,"
					+ " R_CRE_DATE, R_CORR_DATE"
					+ " FROM REPLY"
					+ " WHERE F_INDEX = ?"
					+ " ORDER BY R_INDEX DESC";

			// sql문 연결
			pstmt = connection.prepareStatement(sql);
			System.out.println(brdIndexInt);
			
			pstmt.setInt(1, brdIndexInt);
			
			// sql쿼리문 실행
			rs = pstmt.executeQuery();
			
			

			int replyIndexInt = 0;
			int memIndexInt = 0;
			String replyTextStr = "";
			Date replyCreDate = null;
			Date replyCorrDate = null;
			
			// 다음값이 존재하는 동안
			while (rs.next()) {
				System.out.println("댓글리스트 while문 시작");
				replyIndexInt = rs.getInt("R_INDEX");
				memIndexInt = rs.getInt("M_INDEX");
				replyTextStr = rs.getString("R_TEXT");
				replyCreDate = rs.getDate("R_CRE_DATE");
				replyCorrDate = rs.getDate("R_CORR_DATE");

				BoardReplyDto boardReplyDto = new BoardReplyDto(replyIndexInt, memIndexInt, replyTextStr, 
						brdIndexInt, replyCreDate, replyCorrDate);
				
				//replyWriter메서드 실행하는 MemberDto객체 생성
				MemberDto memberDto = replyWriter(memIndexInt, brdIndexInt);
				//실행: 댓글 정보(BoardReplyDto)에 작성자 정보(MemberDto)를 저장
				boardReplyDto.setMemberDto(memberDto);
				
				
				boardreplyList.add(boardReplyDto);
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

		}
		return boardreplyList;

	}
	
	//댓글 추가
	public void relpyNew(BoardReplyDto boardReplyDto) 
			throws Exception {
		
		String sql = "";
		PreparedStatement pstmt = null;
			
		try {
			//입력된 내용을 변수에 저장
			int memIndexInt = boardReplyDto.getMemIndexInt();
			int brdIndexInt = boardReplyDto.getBrdIndexInt();
			String replyTextStr = boardReplyDto.getReplyTextStr();
			
			//sql문 저장
			sql = "INSERT INTO REPLY(R_INDEX, M_INDEX, R_TEXT,"
					+ " F_INDEX, R_CRE_DATE, R_CORR_DATE)"
					+ " VALUES (R_INDEX_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)";
			
			pstmt = connection.prepareStatement(sql);
			
			//Db에 입력받은 데이터 삽입
			pstmt.setInt(1, memIndexInt);
			pstmt.setString(2, replyTextStr);
			pstmt.setInt(3, brdIndexInt);
			
			//업데이트
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			connection.rollback(); //오류 시 롤백
			e.printStackTrace();
		}finally {
			//정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				connection.setAutoCommit(true); // 원래 상태로 되돌림
			}
			
		}
		
	}
	
	//댓글 삭제
	public void replyDelete(int replyIndexInt) 
			throws SQLException{
		PreparedStatement pstmt = null;

		String sql = "";
		
		try {
			sql = "DELETE FROM REPLY"
					+ " WHERE R_INDEX = ?";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, replyIndexInt);

			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	// 댓글 업데이트 페이지에 정보를 넣을 Dao
	
		public BoardReplyDto replyWritedInfo(int replyIndexInt) 
				throws SQLException{
			// TODO Auto-generated method stub
			BoardReplyDto replyDto = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String sql = "";


			try {
				sql = "SELECT R_TEXT"
						+ " FROM REPLY"
						+ " WHERE R_INDEX = ?";
				
				pstmt = connection.prepareStatement(sql);

				pstmt.setInt(1, replyIndexInt);

				rs = pstmt.executeQuery();

				String replyTextStr = "";


				if (rs.next()) {
					replyTextStr = rs.getString("R_TEXT");

					replyDto = new BoardReplyDto();
					
					replyDto.setReplyIndexInt(replyIndexInt);
					replyDto.setReplyTextStr(replyTextStr);


				} else {
					throw new Exception("해당 댓글을 찾을 수 없습니다.");
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
			return replyDto;
		}
	
	//댓글 업데이트(수정)
	
		public void replyUpdate(BoardReplyDto replyDto, int replyIndexInt) 
				throws SQLException {
			PreparedStatement pstmt = null;
			
			String sql = "";
		
			sql = "UPDATE REPLY SET R_TEXT=?, R_CORR_DATE =SYSDATE"
				+	" WHERE R_INDEX = ?";
			try {
				pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, replyDto.getReplyTextStr());
				pstmt.setInt(2, replyIndexInt);

		
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
	
	
	//멤버와 댓글 테이블 join-글쓴이명 가져오기용 메서드
	
		public MemberDto replyWriter(int memIndexInt, int brdIndexInt) 
				throws SQLException{
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			MemberDto memberDto = new MemberDto();

			String sql = "";

			//멤버 인덱스로 해당 댓글 글쓴이 찾기+댓글 달린 게시물 찾기
			sql = "SELECT R.R_INDEX, M.M_NAME, M.M_ID, M.M_INDEX"
					+ " FROM  REPLY R INNER JOIN MEMBER M"
					+ " ON M.M_INDEX = ?"
					+ " WHERE R.F_INDEX =?"
					+ " ORDER BY R_INDEX DESC";

			try {
				/* sql 연결 */
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1, memIndexInt);
				pstmt.setInt(2, brdIndexInt);

				rs = pstmt.executeQuery();

				
				String memNameStr = "";
				String memIdStr = "";

				
				
				while (rs.next()) {
					memNameStr = rs.getString("M_NAME");
					memIdStr = rs.getString("M_ID");
				//멤버 Dto에 가져온 정보 저장
					memberDto = new MemberDto(memIndexInt, memNameStr, memIdStr);

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