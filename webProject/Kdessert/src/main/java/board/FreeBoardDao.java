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
	public List<FreeBoardDto> boardList() throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FreeBoardDto> boardList = new ArrayList<FreeBoardDto>();

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
			String brdTextStr = "";
			String brdImageStr = "";
			Date brdCreDate = null;

			
			while (rs.next()) {
				brdIndexInt = rs.getInt("F_index");
				brdViewInt = rs.getInt("F_VIEW");
				brdNoticeInt = rs.getInt("F_NOTICE");
				brdIdStr = rs.getString("M_ID");
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdTextStr = rs.getString("");
				brdCreDate = rs.getDate("CREATE_DATE");

				FreeBoardDto boardDto = new FreeBoardDto(brdIndexInt, brdIdStr, brdSubjectStr
						, brdCreDate, brdViewInt, brdNoticeInt);

				boardList.add(boardDto);
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
		return boardList;
	}
	

	//게시판 상세 정보 보이기
	public FreeBoardDto boardDetail(int no) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FreeBoardDto boardDto = new FreeBoardDto();
		String sql = "";

		sql = "SELECT ?, FREE_BOARD_TITLE, FREE_BOARD_CONTENT, "
				+ "CREATE_DATE, MEMBER_NO, UPDATE_DATE "
				+ "FROM FREE_BOARD";

		pstmt = connection.prepareStatement(sql);

		try {

			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			String title = "";
			String content = "";
			Date creDate = null;


			if (rs.next()) {
				title = rs.getString("FREE_BOARD_TITLE");
				content = rs.getString("FREE_BOARD_CONTENT");
				creDate = rs.getDate("CREATE_DATE");

				boardDto.setNo(no);
				boardDto.setTitle(title);
				boardDto.setContent(content);
				boardDto.setCreatedDate(creDate);
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
		return boardDto;
	}
}
