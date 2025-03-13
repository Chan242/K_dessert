package admin.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

		sql += "SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE";
		sql += " FROM PRODUCT";
		sql += " ORDER BY P_INDEX DESC";

		try {

			pstmt = connection.prepareStatement(sql);

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
				proOpenInt = rs.getInt("P_OPEN");
				proCreDateDate = rs.getTimestamp("P_CRE_DATE");
				proChanDateDate = rs.getTimestamp("P_CORR_DATE");
				
				ProductDto productDto = new ProductDto(proIndexInt, proNameStr, proPriceInt, proStockInt, proOpenInt, proCreDateDate, proChanDateDate);

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
			String proNameStr = productDto.getProNameStr();
			int proPriceInt = productDto.getProPriceInt();
			int proStockInt = productDto.getProStockInt();
			int proOpenInt = productDto.getProOpenInt();
			String proIntroStr = productDto.getProIntroStr();

			String sql = "";

			sql += "INSERT INTO PRODUCT";
			sql += " (P_INDEX,P_NAME,P_PRICE,P_INTRO,P_STOCK,P_OPEN)";
			sql += " VALUES(P_INDEX_SEQ.NEXTVAL,?,?,?,?,?)";// 스트링,인트,스트링,인트,인트

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, proNameStr);
			pstmt.setInt(2, proPriceInt);
			pstmt.setString(3, proIntroStr);
			pstmt.setInt(4, proStockInt);
			pstmt.setInt(5, proOpenInt);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ProductDto productDto = new ProductDto();

		String sql = "";

		sql += "SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN, P_INTRO, P_CRE_DATE, P_CORR_DATE";
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
			int proOpenInt = 0;
			String proIntroStr = "";
			Date proCreDateDate = null;
			Date proChanDateDate = null;

			if (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");
				proOpenInt = rs.getInt("P_OPEN");
				proIntroStr = rs.getString("P_INTRO");
				proCreDateDate = rs.getTimestamp("P_CRE_DATE");
				proChanDateDate = rs.getTimestamp("P_CORR_DATE");

				productDto.setProIndexInt(proIndexInt);
				productDto.setProNameStr(proNameStr);
				productDto.setProPriceInt(proPriceInt);
				productDto.setProStockInt(proStockInt);
				productDto.setProOpenInt(proOpenInt);
				productDto.setProIntroStr(proIntroStr);
				productDto.setProCreDateDate(proCreDateDate);
				productDto.setProChanDateDate(proChanDateDate);

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

		return productDto;
	}

	public int productUpdate(ProductDto productDto) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = "";

		sql = "UPDATE PRODUCT";
		sql += " SET P_NAME = ?, P_PRICE = ?, P_STOCK = ?, P_OPEN = ?, P_INTRO = ?, P_CORR_DATE = SYSDATE";
		sql += " WHERE P_INDEX = ?";

		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, productDto.getProNameStr());
			pstmt.setInt(2, productDto.getProPriceInt());
			pstmt.setInt(3, productDto.getProStockInt());
			pstmt.setInt(4, productDto.getProOpenInt());
			pstmt.setString(5, productDto.getProIntroStr());
			pstmt.setInt(6, productDto.getProIndexInt());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return result;
	}

	public ArrayList<ProductDto> searchList(String queryStr) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ProductDto> productList = new ArrayList<ProductDto>();

		String sql = "";

		sql += "SELECT P_INDEX, P_NAME, P_INTRO, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE";
		sql += " FROM PRODUCT";
		sql += " WHERE P_NAME LIKE ? OR P_INTRO LIKE ?";
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
				proOpenInt = rs.getInt("P_OPEN");
				proCreDateDate = rs.getTimestamp("P_CRE_DATE");
				proChanDateDate = rs.getTimestamp("P_CORR_DATE");
				
				ProductDto productDto = new ProductDto(proIndexInt, proNameStr, proPriceInt, proStockInt, proOpenInt, proCreDateDate, proChanDateDate);

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
