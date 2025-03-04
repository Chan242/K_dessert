package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public List<ProductDto> selectList() throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ProductDto> productList = new ArrayList<ProductDto>();

		String sql = "";

		sql += "SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN";
		sql += " FROM PRODUCT";
		sql += " ORDER BY P_INDEX DESC";

		try {

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			System.out.println(rs.isClosed());

			int pIndexInt = 0;
			String pNameStr = "";
			int pStockInt = 0;
			int pPriceInt = 0;
			int pOpenInt = 0;

			while (rs.next()) {
				pIndexInt = rs.getInt("P_INDEX");
				pNameStr = rs.getString("P_NAME");
				pPriceInt = rs.getInt("P_PRICE");
				pStockInt = rs.getInt("P_STOCK");
				pOpenInt = rs.getInt("P_OPEN");

				ProductDto productDto = new ProductDto(pIndexInt, pNameStr, pPriceInt, pStockInt, pOpenInt);

				productList.add(productDto);

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
		return productList;

	}

	public int productAdd(ProductDto productDto) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String pNameStr = productDto.getpNameStr();
			int pPriceInt = productDto.getpPriceInt();
			int pStockInt = productDto.getpStockInt();
			int pOpenInt = productDto.getpOpenInt();
			String pIntroStr = productDto.getpIntroStr();

			String sql = "";

			sql += "INSERT INTO PRODUCT";
			sql += " (P_INDEX,P_NAME,P_PRICE,P_INTRO,P_STOCK,P_OPEN)";
			sql += " VALUES(P_INDEX_SEQ.NEXTVAL,?,?,?,?,?)";// 스트링,인트,스트링,인트,인트

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, pNameStr);
			pstmt.setInt(2, pPriceInt);
			pstmt.setString(3, pIntroStr);
			pstmt.setInt(4, pStockInt);
			pstmt.setInt(5, pOpenInt);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}

		return result;
	}
	public int productDelete(int no) throws SQLException {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = "";
		sql = "DELETE FROM PRODUCT";
		sql += " WHERE P_INDEX = ?";

		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, no);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료

		return result;
	}

	public ProductDto selectOne(int no) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

}
