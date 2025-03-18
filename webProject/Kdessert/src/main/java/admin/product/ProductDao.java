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

	public List<ProductDto> selectList(int no, int divRowInt) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ProductDto> productList = new ArrayList<ProductDto>();

		String sql = "SELECT rn, P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE " + "FROM ( "
				+ "    SELECT ROWNUM AS rn, P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE "
				+ "    FROM ( " + "        SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE "
				+ "        FROM PRODUCT " + "        ORDER BY P_INDEX DESC " + "    ) " + "    WHERE ROWNUM <= ? "
				+ ") " + "WHERE rn >= ?";

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

				ProductDto productDto = new ProductDto(proIndexInt, proNameStr, proPriceInt, proStockInt, proOpenInt,
						proCreDateDate, proChanDateDate);

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
			String proImageStr = productDto.getProImageStr();

			String sql = "";

			sql += "INSERT INTO PRODUCT";
			sql += " (P_INDEX,P_NAME,P_PRICE,P_INTRO,P_STOCK,P_OPEN,P_IMAGE)";
			sql += " VALUES(P_INDEX_SEQ.NEXTVAL,?,?,?,?,?)";// 스트링,인트,스트링,인트,인트

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, proNameStr);
			pstmt.setInt(2, proPriceInt);
			pstmt.setString(3, proIntroStr);
			pstmt.setInt(4, proStockInt);
			pstmt.setInt(5, proOpenInt);
			pstmt.setString(6, proImageStr);

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

	public ProductDto selectOne(int no) throws Exception {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		PreparedStatement pstmtTagList = null;
		ResultSet rsTag = null;
		
		String sqlTag = "";

		ProductDto productDto = new ProductDto();

		

		sql += "SELECT P_INDEX, P_NAME, P_STOCK, P_PRICE, P_OPEN, P_INTRO, P_CRE_DATE, P_CORR_DATE, P_IMAGE";
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
			String proImageStr = "";

			if (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proPriceInt = rs.getInt("P_PRICE");
				proStockInt = rs.getInt("P_STOCK");
				proOpenInt = rs.getInt("P_OPEN");
				proIntroStr = rs.getString("P_INTRO");
				proCreDateDate = rs.getTimestamp("P_CRE_DATE");
				proChanDateDate = rs.getTimestamp("P_CORR_DATE");
				proImageStr = rs.getString("P_IMAGE");

				productDto.setProIndexInt(proIndexInt);
				productDto.setProNameStr(proNameStr);
				productDto.setProPriceInt(proPriceInt);
				productDto.setProStockInt(proStockInt);
				productDto.setProOpenInt(proOpenInt);
				productDto.setProIntroStr(proIntroStr);
				productDto.setProCreDateDate(proCreDateDate);
				productDto.setProChanDateDate(proChanDateDate);
				productDto.setProImageStr(proImageStr);

			}
			
			ArrayList<String> tagList = new ArrayList<String>();
			
			sqlTag += "SELECT P_INDEX, T_NAME";
			sqlTag += " FROM PRODUCT_TAG";
			sqlTag += " WHERE P_INDEX=?";
			
			pstmtTagList = connection.prepareStatement(sqlTag);
			
			pstmtTagList.setInt(1, no);
			
			rsTag = pstmtTagList.executeQuery();
			String tagNameStr = "";
			while(rsTag.next()) {
				tagNameStr = rsTag.getString("T_NAME");
				tagList.add(tagNameStr);
				
			}
			productDto.setProTagList(tagList);
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rsTag != null) {
					rsTag.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				if(pstmtTagList != null) {
					pstmtTagList.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
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

	public int productUpdate(ProductDto productDto) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;

		PreparedStatement pstmtProTagDel = null;
		String sqlProTag = "";
		
		PreparedStatement pstmtProTag = null;
		String proTagSql = "";
		
		String sql = "";

		sql = "UPDATE PRODUCT";
		sql += " SET P_NAME = ?, P_PRICE = ?, P_STOCK = ?, P_OPEN = ?, P_INTRO = ?, P_CORR_DATE = SYSDATE";
		sql += " WHERE P_INDEX = ?";

		try {
			connection.setAutoCommit(false);
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, productDto.getProNameStr());
			pstmt.setInt(2, productDto.getProPriceInt());
			pstmt.setInt(3, productDto.getProStockInt());
			pstmt.setInt(4, productDto.getProOpenInt());
			pstmt.setString(5, productDto.getProIntroStr());
			pstmt.setInt(6, productDto.getProIndexInt());
			
			pstmt.addBatch();
			
			
			sqlProTag += "DELETE PRODUCT_TAG WHERE P_INDEX = ?";
			
			pstmtProTagDel = connection.prepareStatement(sqlProTag);
			
			pstmtProTagDel.setInt(1, productDto.getProIndexInt());
			
			pstmtProTagDel.addBatch();
			
				
			proTagSql += "INSERT INTO PRODUCT_TAG";
			proTagSql += " (P_INDEX, T_NAME)";
			proTagSql += " VALUES(?, trim(?))";
			
			pstmtProTag = connection.prepareStatement(proTagSql);
			
			for (String str : productDto.getProTagList()) {
				pstmtProTag.setInt(1, productDto.getProIndexInt());
				pstmtProTag.setString(2, str);
				System.out.println(str);
				pstmtProTag.addBatch();
			}
			
			int[] result1 = pstmtProTagDel.executeBatch();
			int[] result2 = pstmtProTag.executeBatch();
			int[] result3 = pstmt.executeBatch();
			
			
			result = result3[0];

			
			
	        // 모든 작업 성공 시 커밋
	        connection.commit();
	        connection.setAutoCommit(true);
			
			

			

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
				connection.rollback();
			}

		}

		return result;
	}

	public ArrayList<ProductDto> searchList(String queryStr, int no, int divRowInt) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ProductDto> productList = new ArrayList<ProductDto>();

		String sql = "";
		sql += "SELECT rn, P_INDEX, P_NAME, P_INTRO, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE ";
		sql += "FROM ( ";
		sql += "    SELECT ROWNUM AS rn, P_INDEX, P_NAME, P_INTRO, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE ";
		sql += "    FROM ( ";
		sql += "        SELECT P.P_INDEX, P_NAME, P_INTRO, P_STOCK, P_PRICE, P_OPEN, P_CRE_DATE, P_CORR_DATE, T_NAME";
		sql += "        FROM PRODUCT P LEFT JOIN PRODUCT_TAG PT ON P.P_INDEX = PT.P_INDEX  ";
		sql += "        WHERE P_NAME LIKE ? OR P_INTRO LIKE ? OR T_NAME LIKE ?";
		sql += "        ORDER BY P_INDEX DESC ";
		sql += "    ) ";
		sql += "    WHERE ROWNUM <= ? ";
		sql += ") ";
		sql += "WHERE rn >= ?";

		try {

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, "%" + queryStr + "%");
			pstmt.setString(2, "%" + queryStr + "%");
			pstmt.setString(3, "%" + queryStr + "%");
			pstmt.setInt(4, no * divRowInt); // 끝 범위
			pstmt.setInt(5, (no - 1) * divRowInt + 1); // 시작 범위

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

				ProductDto productDto = new ProductDto(proIndexInt, proNameStr, proPriceInt, proStockInt, proOpenInt,
						proCreDateDate, proChanDateDate);

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

	public int productCount() throws Exception{
		// TODO Auto-generated method stub
		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		try {

			sql += "SELECT COUNT(P_INDEX)";
			sql += " FROM PRODUCT";

			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("COUNT(P_INDEX)");
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
		return result;
	}

	public int queryProductCount(String queryStr) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		try {
			sql += "SELECT COUNT(P_INDEX)";
			sql += " FROM PRODUCT";
			sql += " WHERE P_NAME LIKE ? OR P_INTRO LIKE ?";
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

	public int productAdd(ProductDto productDto, String[] tagArray) throws SQLException {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;

		PreparedStatement pstmtProTag = null;
		String proTagSql = "";
		

		try {
			
			connection.setAutoCommit(false);
			
			String proNameStr = productDto.getProNameStr();
			int proPriceInt = productDto.getProPriceInt();
			int proStockInt = productDto.getProStockInt();
			int proOpenInt = productDto.getProOpenInt();
			String proIntroStr = productDto.getProIntroStr();
			String proImageStr = productDto.getProImageStr();

			String sql = "";

			sql += "INSERT INTO PRODUCT";
			sql += " (P_INDEX,P_NAME,P_PRICE,P_INTRO,P_STOCK,P_OPEN,P_IMAGE)";
			sql += " VALUES(P_INDEX_SEQ.NEXTVAL,?,?,?,?,?,?)";// 스트링,인트,스트링,인트,인트

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, proNameStr);
			pstmt.setInt(2, proPriceInt);
			pstmt.setString(3, proIntroStr);
			pstmt.setInt(4, proStockInt);
			pstmt.setInt(5, proOpenInt);
			pstmt.setString(6, proImageStr);

			pstmt.addBatch();
			
			
			proTagSql += "INSERT INTO PRODUCT_TAG";
			proTagSql += " (P_INDEX, T_NAME)";
			proTagSql += " VALUES(P_INDEX_SEQ.CURRVAL, ?)";
			
			pstmtProTag = connection.prepareStatement(proTagSql);
			
			for (String str : tagArray) {
				pstmtProTag.setString(1, str);
				System.out.println(str);
				pstmtProTag.addBatch();
			}
			
			
			
			int[] result2 = pstmt.executeBatch();
			int[] result1 = pstmtProTag.executeBatch();
			
			result = result2[0];

			
			
	        // 모든 작업 성공 시 커밋
	        connection.commit();
	        connection.setAutoCommit(true);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.rollback();
		} finally {
			try {
				if(pstmtProTag != null) {
					pstmtProTag.close();
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
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
