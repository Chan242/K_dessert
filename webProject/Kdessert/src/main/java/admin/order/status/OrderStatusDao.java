package admin.order.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderStatusDao {

	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public ArrayList<OrderStatusDto> orderStatusList() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<OrderStatusDto> orderStatusList = new ArrayList<OrderStatusDto>();

		String sql = "";

		try {

			sql += "SELECT STA_INDEX, STA_STATUS, STA_NOTICE";
			sql += " FROM STATUS";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int staIndexInt = 0;
				String staStatusStr = "";
				String staNoticeStr = "";

				staIndexInt = rs.getInt("STA_INDEX");
				staStatusStr = rs.getString("STA_STATUS");
				staNoticeStr = rs.getString("STA_NOTICE");

				OrderStatusDto orderStatusDto = new OrderStatusDto();

				orderStatusDto.setStaIndexInt(staIndexInt);
				orderStatusDto.setStaStatusStr(staStatusStr);
				orderStatusDto.setStaNoticeStr(staNoticeStr);

				orderStatusList.add(orderStatusDto);

			}

		} catch (Exception e) {
			// TODO: handle exception
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
		return orderStatusList;

	}
}
