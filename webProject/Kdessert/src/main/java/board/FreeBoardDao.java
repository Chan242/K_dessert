package board;

import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.FreeBoardDto;


/*게시판정보 전부 삽입*/
public class FreeBoardDao {
	private Connection connection;
	
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	//게시판 정보 조회-메인 화면에 보여질 리스트
	public List<FreeBoardDto> freeBoardList() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FreeBoardDto> freeBoardList = new ArrayList<FreeBoardDto>();

		String sql = "";

		sql = "SELECT F_index, M_ID, F_SUBJECT, F_TEXT, "
				+ "F_IMAGE, F_VIEW, F_CRE_DATE, F_NOTICE "
				+ "FROM FREE_BOARD "
				+ "ORDER BY F_index ASC";

		try {
			/* sql 연결 */
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			int brdIndexInt = 0;
			int brdViewInt = 0;
			int brdNoticeInt = 0;
			String brdIdStr = "";
			String brdSubjectStr = "";
			Date brdCreDate = null;

			
			while (rs.next()) {
				brdIndexInt = rs.getInt("F_index");
				brdViewInt = rs.getInt("F_VIEW");
				brdNoticeInt = rs.getInt("F_NOTICE");
				brdIdStr = rs.getString("M_ID");
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdCreDate = rs.getDate("F_CRE_DATE");

				FreeBoardDto freeBoardDto = new FreeBoardDto(brdIndexInt, brdIdStr, brdSubjectStr
						, brdCreDate, brdViewInt, brdNoticeInt);

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
	public FreeBoardDto freeBoardDetail(int brdindexint) 
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FreeBoardDto freeboardDto = new FreeBoardDto();
		String sql = "";

		sql = "SELECT F_INDEX, F_SUBJECT, F_TEXT,"
				+ " F_IMAGE, M_ID, F_VIEW, F_NOTICE,"
				+ " F_CRE_DATE"
				+ " FROM FREE_BOARD"
				+ " WHERE F_INDEX=?"
				+ " ORDER BY F_INDEX DESC";

		pstmt = connection.prepareStatement(sql);

		try {

			pstmt.setInt(1, brdindexint);
			rs = pstmt.executeQuery();

			String brdSubjectStr = "";//제목
			String brdTextStr = "";//내용
			String brdIdStr = "";//작성자
			Date brdCreDate = null;//작성일
			int brdViewInt = 0;// 조회수


			if (rs.next()) {
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdTextStr = rs.getString("F_TEXT");
				brdIdStr = rs.getString("M_ID");
				brdCreDate = rs.getDate("F_CRE_DATE");
				brdViewInt = rs.getInt("F_VIEW");

				freeboardDto.setBrdSubjectStr(brdSubjectStr);
				freeboardDto.setBrdTextStr(brdTextStr);
				freeboardDto.setBrdIdStr(brdIdStr);
				freeboardDto.setBrdCreDate(brdCreDate);
				freeboardDto.setBrdViewInt(brdViewInt);

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
	
	public void freeBoardNew(FreeBoardDto freeBoardDto) 
			throws Exception {
		
		PreparedStatement pstmt = null;
		// SQL 객체준비
		try {
			
			String brdIdStr = freeBoardDto.getBrdIdStr();
			String brdSubjectStr = freeBoardDto.getBrdSubjectStr();
			String brdTextStr = freeBoardDto.getBrdTextStr();
//			String brdImageStr = freeBoardDto.getBrdImageStr();
			
			String sql=" ";
			
			sql = "INSERT INTO FREE_BOARD"
					+ " (F_INDEX, M_ID, F_SUBJECT, F_TEXT, F_CRE_DATE, F_VIEW , F_NOTICE)"
					+ " VALUES(F_INDEX_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, 3, 0)";
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, brdIdStr);
			pstmt.setString(2, brdSubjectStr);
			pstmt.setString(3, brdTextStr);
//			pstmt.setString(4, brdImageStr);

			pstmt.executeUpdate();


		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} // finally 종료
	}

	
}
