package user.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BasketDao {

	private Connection connection = null;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public ArrayList<BasketDto> basketList(int no) {

		ArrayList<BasketDto> basketList = new ArrayList<BasketDto>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "";

			sql += "SELECT M_INDEX,B.P_INDEX,P.P_NAME,P.P_PRICE,B.B_STOCK";
			sql += " FROM BASKET B INNER JOIN PRODUCT P";
			sql += " ON B.P_INDEX = P.P_INDEX";
			sql += " WHERE M_INDEX = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			int proIndexInt = 0;
			int memIndexInt = 0;
			int basStockInt = 0;
			String proNameStr = "";
			int proPriceInt = 0;

			while (rs.next()) {

				proIndexInt = rs.getInt("P_INDEX");
				memIndexInt = rs.getInt("M_INDEX");
				basStockInt = rs.getInt("B_STOCK");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");

				BasketDto basketDto = new BasketDto();

				basketDto.setProIndexInt(proIndexInt);
				basketDto.setMemIndexInt(memIndexInt);
				basketDto.setBasStockInt(basStockInt);
				basketDto.setProNameStr(proNameStr);
				basketDto.setProPriceInt(proPriceInt);

				basketList.add(basketDto);

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
		return basketList;

	}

	public int addProduct(BasketDto basketdto) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			int memIndexInt = basketdto.getMemIndexInt();
			int proIndexInt = basketdto.getProIndexInt();
			int basStockInt = basketdto.getBasStockInt();

//			

			String sql = "";

			sql += "INSERT INTO BASKET";
			sql += " (M_INDEX,P_INDEX,B_STOCK)";
			sql += " VALUES(?,?,?)";
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, memIndexInt);
			pstmt.setInt(2, proIndexInt);
			pstmt.setInt(3, basStockInt);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
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

		return result;

	}

	public int updateProduct(BasketDto basketdto) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			int memIndexInt = basketdto.getMemIndexInt();
			int proIndexInt = basketdto.getProIndexInt();
			int basStockInt = basketdto.getBasStockInt();

//			

			String sql = "";

			sql += "UPDATE BASKET";
			sql += " SET B_STOCK = ?";
			sql += " WHERE M_INDEX = ? AND P_INDEX = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, basStockInt);
			pstmt.setInt(2, memIndexInt);
			pstmt.setInt(3, proIndexInt);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
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

		return result;

	}

}
