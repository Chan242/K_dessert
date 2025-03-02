package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
	
	private Connection connection;
	
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	public List<ProductDto> selectList() throws Exception{
		PreparedStatement  pstmt = null;
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
			
			while(rs.next()) {
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
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}return productList;
		
	}

}
