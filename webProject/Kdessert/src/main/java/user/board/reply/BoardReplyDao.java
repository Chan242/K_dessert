package user.board.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
					+ " R_CRE_DATE, R_CHAN_DATE"
					+ " FROM REPLY"
					+ " WHERE F_INDEX = ?";

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
			
			System.out.println(rs.next());
			// 다음값이 존재하는 동안
			while (rs.next()) {
				System.out.println("댓글리스트 while문 시작");
				replyIndexInt = rs.getInt("R_INDEX");
				memIndexInt = rs.getInt("M_INDEX");
				replyTextStr = rs.getString("R_TEXT");
				replyCreDate = rs.getDate("R_CRE_DATE");
				replyCorrDate = rs.getDate("R_CHAN_DATE");

				BoardReplyDto boardReplyDto = new BoardReplyDto(replyIndexInt, memIndexInt, replyTextStr, 
						brdIndexInt, replyCreDate, replyCorrDate);
				
				System.out.println(replyIndexInt+" / "+memIndexInt+" / "+replyTextStr+" / "+replyCreDate+" / "+replyCorrDate+" / ");

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
}