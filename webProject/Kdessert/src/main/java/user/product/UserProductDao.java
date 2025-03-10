package user.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import admin.product.ProductDto;

public class UserProductDao {
	
	Connection connection;

	public void setConnection(Connection conn) {
		// TODO Auto-generated method stub
		this.connection = conn;
	}
	
	public ArrayList<UserProductDto> userSelectList() throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<UserProductDto> userProductList = new ArrayList<UserProductDto>();

		String sql = "";

		sql += "SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN";
		sql += " FROM PRODUCT";
		sql += " WHERE P_OPEN = 0";
		sql += " ORDER BY P_INDEX DESC";

		try {

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			System.out.println(rs.isClosed());

			int proIndexInt = 0;
			String proNameStr = "";
			int proStockInt = 0;
			int proPriceInt = 0;

			while (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");

				UserProductDto userProductDto = new UserProductDto(proIndexInt, proNameStr, proPriceInt, proStockInt);

				userProductList.add(userProductDto);

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
		return userProductList;

	}

	public UserProductDto userSelectOne(int no) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UserProductDto userProductDto = new UserProductDto();

		String sql = "";

		sql += "SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_INTRO";
		sql += " FROM PRODUCT";
		sql += " WHERE P_INDEX = ?";

		try {

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			System.out.println(rs.isClosed());

			int proIndexInt = 0;
			String proNameStr = "";
			int proStockInt = 0;
			int proPriceInt = 0;
			String proIntroStr = "";

			if (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");
				proIntroStr =rs.getString("P_INTRO");

				userProductDto.setProIndexInt(proIndexInt);
				userProductDto.setProNameStr(proNameStr);
				userProductDto.setProPriceInt(proPriceInt);
				userProductDto.setProStockInt(proStockInt);
				userProductDto.setProIntroStr(proIntroStr);
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

		
		return userProductDto;
	}

	
	public ArrayList<UserProductDto> userSearchList(String queryStr) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<UserProductDto> productList = new ArrayList<UserProductDto>();

		String sql = "";

		sql += "SELECT P_INDEX, P_NAME, P_INTRO, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE";
		sql += " FROM PRODUCT";
		sql += " WHERE P_NAME LIKE ? OR P_INTRO LIKE ?";
		sql += " AND P_OPEN = 0";
		sql += " ORDER BY P_INDEX DESC";

		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, "%" + queryStr + "%");
			pstmt.setString(2, "%" + queryStr + "%");
			
			
			rs = pstmt.executeQuery();

			System.out.println(rs.isClosed());

			int proIndexInt = 0;
			String proNameStr = "";
			int proStockInt = 0;
			int proPriceInt = 0;
			int proOpenInt = 0;
			Date proCreDateDate = null;
			Date proChanDateDate = null;

			while (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");
				
				UserProductDto productDto = new UserProductDto(proIndexInt, proNameStr, proPriceInt, proStockInt);

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
	
	
}
