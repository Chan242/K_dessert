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
	
	public ArrayList<UserProductDto> userSelectList(int no, int divRowInt) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<UserProductDto> userProductList = new ArrayList<UserProductDto>();

		String sql = "";

		sql += "SELECT rn, P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN, P_IMAGE " +
	             "FROM ( " +
	             "    SELECT ROWNUM AS rn, P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN, P_IMAGE " +
	             "    FROM ( " +
	             "        SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN, P_IMAGE " +
	             "        FROM PRODUCT " +
	             "        WHERE P_OPEN = 0 " +
	             "        ORDER BY P_INDEX DESC " +
	             "    ) " +
	             "    WHERE ROWNUM <= ? " +
	             ") " +
	             "WHERE rn >= ?";

		try {

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, no * divRowInt); // 끝 범위
			pstmt.setInt(2, (no - 1) * divRowInt + 1); // 시작 범위
			

			rs = pstmt.executeQuery();

			System.out.println(rs.isClosed());

			int proIndexInt = 0;
			String proNameStr = "";
			int proStockInt = 0;
			int proPriceInt = 0;
			String proImageStr = "";

			while (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");
				proImageStr = rs.getString("P_IMAGE");

				UserProductDto userProductDto = new UserProductDto(proIndexInt, proNameStr, proPriceInt, proStockInt);
				userProductDto.setProImageStr(proImageStr);

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

		sql += "SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_INTRO, P_IMAGE";
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
			String proImageStr = "";

			if (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");
				proIntroStr =rs.getString("P_INTRO");
				proImageStr = rs.getString("P_IMAGE");

				userProductDto.setProIndexInt(proIndexInt);
				userProductDto.setProNameStr(proNameStr);
				userProductDto.setProPriceInt(proPriceInt);
				userProductDto.setProStockInt(proStockInt);
				userProductDto.setProIntroStr(proIntroStr);
				userProductDto.setProImageStr(proImageStr);
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

	
	public ArrayList<UserProductDto> userSearchList(String queryStr, int no, int divRowInt) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<UserProductDto> productList = new ArrayList<UserProductDto>();

		String sql = "";

		sql += "SELECT rn, P_INDEX, P_NAME, P_INTRO, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE, P_IMAGE ";
		sql += " FROM ( ";
		sql += "    SELECT ROWNUM AS rn, P_INDEX, P_NAME, P_INTRO, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE, P_IMAGE ";
		sql += "    FROM ( ";
		sql += "        SELECT P.P_INDEX, P_NAME, P_INTRO, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE, T_NAME, P_IMAGE";
		sql += "        FROM PRODUCT P LEFT JOIN PRODUCT_TAG PT ON P.P_INDEX = PT.P_INDEX  ";
		sql += "        WHERE (P_NAME LIKE ? OR P_INTRO LIKE ? OR T_NAME LIKE ?) AND P_OPEN = 0";
		sql += "        ORDER BY P_INDEX DESC ";
		sql += "    ) ";
		sql += "    WHERE ROWNUM <= ? ";
		sql += " ) ";
		sql += " WHERE rn >= ?";

		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, "%" + queryStr + "%");
			pstmt.setString(2, "%" + queryStr + "%");
			pstmt.setString(3, "%" + queryStr + "%");
			pstmt.setInt(4, no * divRowInt); // 끝 범위
			pstmt.setInt(5, (no - 1) * divRowInt + 1); // 시작 범위
			

			
			rs = pstmt.executeQuery();

			int proIndexInt = 0;
			String proNameStr = "";
			int proStockInt = 0;
			int proPriceInt = 0;
			int proOpenInt = 0;
			Date proCreDateDate = null;
			Date proChanDateDate = null;
			String proImageStr = "";

			while (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");
				proImageStr = rs.getString("P_IMAGE");
				
				UserProductDto productDto = new UserProductDto(proIndexInt, proNameStr, proPriceInt, proStockInt);
				productDto.setProImageStr(proImageStr);
				
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

	public int queryProductCount(String queryStr) {
		// TODO Auto-generated method stub
		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		try {
			sql += "SELECT COUNT(P_INDEX)";
			sql += " FROM PRODUCT";
			sql += " WHERE P_NAME LIKE ? OR P_INTRO LIKE ? AND P_OPEN = 0";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, "%" + queryStr + "%");
			pstmt.setString(2, "%" + queryStr + "%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("COUNT(P_INDEX)");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
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

		return result;
	}
	
	
}
