package admin.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDao {
	
	private Connection connection;
	

	public void setConnection(Connection conn) {
		this.connection = conn;
	}


	public ArrayList<OrderDto> selectList() {
		// TODO Auto-generated method stub
		
		
		
		
		return null;
	}
	
	
	public ArrayList<OrderProductDto> orderProduct(int ordIndexInt){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<OrderProductDto> orderProduct = new ArrayList<OrderProductDto>();
		
		
		String sql = "";
		
		
		try {
			
			sql += "SELECT P_INDEX, PO_STOCK, PO_PRICE";
			sql += " FROM PRODUCT_ORDER";
			sql += " WHERE O_INDEX = ?";
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
		
		
		
	}


}
