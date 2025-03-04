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

			int proIndexInt = 0;
			String proNameStr = "";
			int proStockInt = 0;
			int proPriceInt = 0;
			int proOpenInt = 0;

			while (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");
				proOpenInt = rs.getInt("P_OPEN");

				ProductDto productDto = new ProductDto(proIndexInt, proNameStr, proPriceInt, proStockInt, proOpenInt);

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
			String proNameStr = productDto.getproNameStr();
			int proPriceInt = productDto.getproPriceInt();
			int proStockInt = productDto.getproStockInt();
			int proOpenInt = productDto.getproOpenInt();
			String proIntroStr = productDto.getproIntroStr();

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
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ProductDto productDto = new ProductDto();

		String sql = "";

		sql += "SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN,P_INTRO";
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

			if (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");
				proOpenInt = rs.getInt("P_OPEN");
				proIntroStr =rs.getString("P_INTRO");

				productDto.setproIndexInt(proIndexInt);
				productDto.setproNameStr(proNameStr);
				productDto.setproPriceInt(proPriceInt);
				productDto.setproStockInt(proStockInt);
				productDto.setproOpenInt(proOpenInt);
				productDto.setproIntroStr(proIntroStr);
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
		sql += " SET P_NAME = ?, P_PRICE = ?, P_STOCK = ?, P_OPEN = ?, P_INTRO = ?";
		sql += " WHERE P_INDEX = ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, productDto.getproNameStr());
			pstmt.setInt(2, productDto.getproPriceInt());
			pstmt.setInt(3, productDto.getproStockInt());
			pstmt.setInt(4, productDto.getproOpenInt());
			pstmt.setString(5, productDto.getproIntroStr());
			pstmt.setInt(6, productDto.getproIndexInt());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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

}
