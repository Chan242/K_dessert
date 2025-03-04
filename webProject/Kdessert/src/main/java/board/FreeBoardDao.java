package board;

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
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	//게시판 정보 조회-메인 화면에 보여질 리스트
	public List<FreeBoardDto> freeBoardList() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FreeBoardDto> freeBoardList = new ArrayList<FreeBoardDto>();

		String sql = "";

		sql = "SELECT F_index, M_ID, F_SUBJECT, F_TEXT, "
				+ "F_IMAGE, F_VIEW, CREATE_DATE, F_NOTICE "
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
				brdCreDate = rs.getDate("CREATE_DATE");

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
	

	
}
