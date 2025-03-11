package admin.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class OrderDao {
	
	private Connection connection;
	

	public void setConnection(Connection conn) {
		this.connection = conn;
	}


	public ArrayList<OrderDto> selectList() {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		ArrayList<OrderDto> orderList = new ArrayList<OrderDto>();
		
		
		String sql = "";
		
try {
			
			sql += "SELECT O_INDEX, STA_STATUS, O_TIME, O_TOTAL, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL";
			sql += " FROM S_ORDER";
			
			pstmt = connection.prepareStatement(sql);
			
			
			rs = pstmt.executeQuery();
			
			int ordIndexint = 0;//주문번호
			String staStatStr = "";//주문상태
			Date ordTime = null;//주문일자
			String memAdd1Str = "";
			String memAdd2Str = "";
			String memTelStr = "";
			int memIndexInt = 0;//주문자 멤버 인덱스
			String memNameStr = "";//주문자명
			int totalPriceInt = 0;//총주문액
			
			while(rs.next()) {
				
				ordIndexint = rs.getInt("O_INDEX");
				staStatStr = rs.getString("STA_STATUS");
				ordTime = rs.getTimestamp("O_TIME");
				totalPriceInt = rs.getInt("O_TOTAL");
				memIndexInt = rs.getInt("M_INDEX");
				memNameStr = rs.getString("O_NAME");
				memAdd1Str = rs.getString("O_ADDRESS");
				memAdd2Str = rs.getString("O_ADDRESS_SEC");
				memTelStr = rs.getString("O_TEL");
							
				
				OrderDto orderDto = new OrderDto();
				
				orderDto.setOrdIndexint(ordIndexint);
				orderDto.setStaStatStr(staStatStr);
				orderDto.setOrdTime(ordTime);
				orderDto.setTotalPriceInt(totalPriceInt);
				orderDto.setMemIndexInt(memIndexInt);
				orderDto.setMemNameStr(memNameStr);
				orderDto.setMemAdd1Str(memAdd1Str);
				orderDto.setMemAdd2Str(memAdd2Str);
				orderDto.setMemTelStr(memTelStr);
				
				
				orderList.add(orderDto);
				
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
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
		}return orderList;
		
		
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
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, ordIndexInt);
			
			rs = pstmt.executeQuery();
			
			int proIndexInt = 0;
			int proOrdStockInt = 0;
			int proOrderPriceInt = 0;
			
			while(rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proOrdStockInt = rs.getInt("PO_STOCK");
				proOrderPriceInt = rs.getInt("PO_PRICE");
				
				OrderProductDto orderProductDto = new OrderProductDto
						(proIndexInt, proOrdStockInt, proOrderPriceInt);
				
				orderProduct.add(orderProductDto);
				
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
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
		}return orderProduct;
		
		
		
	}


	public OrderDto selectOne() {
		// TODO Auto-generated method stub
		return null;
	}


}
