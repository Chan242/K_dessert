package admin.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import admin.product.ProductDto;
import user.basket.BasketDto;

public class OrderDao {

	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}

	public ArrayList<OrderDto> selectList(int no, int divRowInt) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<OrderDto> orderList = new ArrayList<OrderDto>();

		String sql = "";

		try {

			sql = 
				    "SELECT rn, O_INDEX, STA_STATUS, O_TIME, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL, O_TOTAL " +
				    "FROM ( " +
				    "    SELECT ROWNUM AS rn, O_INDEX, STA_STATUS, O_TIME, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL, O_TOTAL " +
				    "    FROM ( " +
				    "        SELECT O_INDEX, STA_STATUS, O_TIME, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL, O_TOTAL " +
				    "        FROM s_order " +
				    "        ORDER BY O_INDEX DESC " +
				    "    ) " +
				    "    WHERE ROWNUM <= ? " +
				    ") " +
				    "WHERE rn >= ?";

				pstmt = connection.prepareStatement(sql);

				pstmt.setInt(1, no * divRowInt); // 끝 범위
				pstmt.setInt(2, (no - 1) * divRowInt + 1); // 시작 범위


			rs = pstmt.executeQuery();

			int ordIndexint = 0;// 주문번호
			String staStatStr = "";// 주문상태
			Date ordTime = null;// 주문일자
			String memAdd1Str = "";
			String memAdd2Str = "";
			String memTelStr = "";
			int memIndexInt = 0;// 주문자 멤버 인덱스
			String memNameStr = "";// 주문자명
			int totalPriceInt = 0;// 총주문액

			while (rs.next()) {

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
		return orderList;

	}

	public ArrayList<OrderProductDto> orderProductList(int ordIndexInt) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<OrderProductDto> orderProductList = new ArrayList<OrderProductDto>();

		String sql = "";

		try {

			sql += "SELECT P_INDEX, P_NAME, PO_STOCK, PO_PRICE";
			sql += " FROM PRODUCT_ORDER";
			sql += " WHERE O_INDEX = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, ordIndexInt);

			rs = pstmt.executeQuery();

			int proIndexInt = 0;
			String proNameStr = "";
			int proOrdStockInt = 0;
			int proOrderPriceInt = 0;

			while (rs.next()) {
				proIndexInt = rs.getInt("P_INDEX");
				proNameStr = rs.getString("P_NAME");
				proOrdStockInt = rs.getInt("PO_STOCK");
				proOrderPriceInt = rs.getInt("PO_PRICE");

				OrderProductDto orderProductDto = new OrderProductDto(proIndexInt, proNameStr, proOrdStockInt,
						proOrderPriceInt);

				orderProductList.add(orderProductDto);

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
		return orderProductList;

	}

	public OrderDto selectOne(int no) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		OrderDto orderDto = new OrderDto();

		String sql = "";

		try {

			sql += "SELECT O_INDEX, STA_STATUS, O_TIME, O_TOTAL, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL";
			sql += " FROM S_ORDER";
			sql += " WHERE O_INDEX = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			int ordIndexint = 0;// 주문번호
			String staStatStr = "";// 주문상태
			Date ordTime = null;// 주문일자
			String memAdd1Str = "";
			String memAdd2Str = "";
			String memTelStr = "";
			int memIndexInt = 0;// 주문자 멤버 인덱스
			String memNameStr = "";// 주문자명
			int totalPriceInt = 0;// 총주문액

			if (rs.next()) {

				ordIndexint = rs.getInt("O_INDEX");
				staStatStr = rs.getString("STA_STATUS");
				ordTime = rs.getTimestamp("O_TIME");
				totalPriceInt = rs.getInt("O_TOTAL");
				memIndexInt = rs.getInt("M_INDEX");
				memNameStr = rs.getString("O_NAME");
				memAdd1Str = rs.getString("O_ADDRESS");
				memAdd2Str = rs.getString("O_ADDRESS_SEC");
				memTelStr = rs.getString("O_TEL");

				orderDto.setOrdIndexint(ordIndexint);
				orderDto.setStaStatStr(staStatStr);
				orderDto.setOrdTime(ordTime);
				orderDto.setTotalPriceInt(totalPriceInt);
				orderDto.setMemIndexInt(memIndexInt);
				orderDto.setMemNameStr(memNameStr);
				orderDto.setMemAdd1Str(memAdd1Str);
				orderDto.setMemAdd2Str(memAdd2Str);
				orderDto.setMemTelStr(memTelStr);

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
		return orderDto;

	}

	public ArrayList<OrderDto> userSelectList(int memIndexInt) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<OrderDto> orderList = new ArrayList<OrderDto>();

		String sql = "";

		try {

			sql += "SELECT ROWNUM, O_INDEX, STA_STATUS, O_TIME, O_TOTAL, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL";
			sql += " FROM S_ORDER";
			sql += " WHERE M_INDEX = ?";
			sql += " ORDER BY O_INDEX DESC";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, memIndexInt);

			rs = pstmt.executeQuery();

			int ordIndexint = 0;// 주문번호
			String staStatStr = "";// 주문상태
			Date ordTime = null;// 주문일자
			String memAdd1Str = "";
			String memAdd2Str = "";
			String memTelStr = "";
//			int memIndexInt = 0;//주문자 멤버 인덱스
			String memNameStr = "";// 주문자명
			int totalPriceInt = 0;// 총주문액

			while (rs.next()) {

				ordIndexint = rs.getInt("O_INDEX");
				staStatStr = rs.getString("STA_STATUS");
				ordTime = rs.getTimestamp("O_TIME");
				totalPriceInt = rs.getInt("O_TOTAL");
//				memIndexInt = rs.getInt("M_INDEX");
				memNameStr = rs.getString("O_NAME");
				memAdd1Str = rs.getString("O_ADDRESS");
				memAdd2Str = rs.getString("O_ADDRESS_SEC");
				memTelStr = rs.getString("O_TEL");

				OrderDto orderDto = new OrderDto();

				orderDto.setOrdIndexint(ordIndexint);
				orderDto.setStaStatStr(staStatStr);
				orderDto.setOrdTime(ordTime);
				orderDto.setTotalPriceInt(totalPriceInt);
//				orderDto.setMemIndexInt(memIndexInt);
				orderDto.setMemNameStr(memNameStr);
				orderDto.setMemAdd1Str(memAdd1Str);
				orderDto.setMemAdd2Str(memAdd2Str);
				orderDto.setMemTelStr(memTelStr);

				orderList.add(orderDto);

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
		return orderList;

	}

	public int orderProcess(OrderDto orderDto, ArrayList<BasketDto> basketList) throws SQLException {
	    int result = 0;

	    // PreparedStatement 객체 선언        
	    PreparedStatement pstmtStockCheck = null; // 주문 재고 점검용
	    String sqlStockCheck = ""; // 주문 재고 체크 SQL

	    PreparedStatement pstmtOrder = null; // 주문 테이블 삽입용
	    String sqlOrder = ""; // 주문 테이블 삽입 SQL

	    PreparedStatement pstmtOrderProduct = null; // 주문 제품 테이블 삽입용
	    String sqlOrderProduct = ""; // 주문 제품 테이블 삽입 SQL

	    PreparedStatement pstmtDecreaseStock = null; // 재고 감소용
	    String sqlDecreaseStock = ""; // 재고 감소 SQL

	    PreparedStatement pstmtDecreasePoint = null; // 포인트 감소용
	    String sqlDecreasePoint = ""; // 포인트 감소 SQL

	    PreparedStatement pstmtClearBasket = null; // 장바구니 비우기용
	    String sqlClearBasket = ""; // 장바구니 비우기 SQL

	    ResultSet rsStockCheck = null; // 재고 조회용 ResultSet

	    try {
	        System.out.println(connection.isClosed()); // 연결 상태 확인 (디버깅용)

	        // 트랜잭션 시작
	        connection.setAutoCommit(false);

	        // 주문 정보를 DTO에서 가져옴
	        int memIndexInt = orderDto.getMemIndexInt();
	        String nameStr = orderDto.getMemNameStr();
	        String addressOneStr = orderDto.getMemAdd1Str();
	        String addressTwoStr = orderDto.getMemAdd2Str();
	        String telStr = orderDto.getMemTelStr();
	        int totalPriceInt = orderDto.getTotalPriceInt();

	        // 주문 테이블 삽입 SQL 생성 및 실행
	        sqlOrder += "INSERT INTO S_ORDER";
	        sqlOrder += " (O_INDEX, STA_STATUS, O_TIME, O_TOTAL, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL)";
	        sqlOrder += " VALUES(O_INDEX_SEQ.NEXTVAL, '접수대기', SYSDATE, ?, ?, ?, ?, ?, ?)";
	        pstmtOrder = connection.prepareStatement(sqlOrder);

	        // 주문 데이터 바인딩 및 배치에 추가
	        pstmtOrder.setInt(1, totalPriceInt); // 총 가격
	        pstmtOrder.setInt(2, memIndexInt); // 회원 인덱스
	        pstmtOrder.setString(3, nameStr); // 주문자 이름
	        pstmtOrder.setString(4, addressOneStr); // 주소 1
	        pstmtOrder.setString(5, addressTwoStr); // 주소 2
	        pstmtOrder.setString(6, telStr); // 연락처
	        pstmtOrder.addBatch(); // 배치 실행 준비

	        // 주문 제품 테이블 삽입 SQL 생성
	        sqlOrderProduct += "INSERT INTO PRODUCT_ORDER";
	        sqlOrderProduct += " (O_INDEX, P_INDEX, P_NAME, PO_STOCK, PO_PRICE)";
	        sqlOrderProduct += " VALUES(O_INDEX_SEQ.CURRVAL, ?, ?, ?, ?)";
	        pstmtOrderProduct = connection.prepareStatement(sqlOrderProduct);

	        // 재고 점검용 SQL 생성
	        sqlStockCheck += "SELECT P_STOCK FROM PRODUCT WHERE P_INDEX = ?";
	        pstmtStockCheck = connection.prepareStatement(sqlStockCheck);

	        // 장바구니에 있는 각 항목 처리
	        for (BasketDto basketDto : basketList) {
	            // 장바구니에 담긴 상품의 재고 점검
	            pstmtStockCheck.setInt(1, basketDto.getProIndexInt()); // 상품 ID
	            rsStockCheck = pstmtStockCheck.executeQuery(); // 결과셋 조회

	            if (rsStockCheck.next()) {
	                int stockAvailable = rsStockCheck.getInt("P_STOCK"); // 재고 수량
	                int requestedQuantity = basketDto.getBasStockInt(); // 요청한 수량

	                if (stockAvailable < requestedQuantity) {
	                    // 재고 부족 시 예외 처리
	                    throw new SQLException("상품 [" + basketDto.getProNameStr() + "]의 재고가 부족합니다.");
	                }
	            } else {
	                // 재고 정보가 없는 경우 처리
	                throw new SQLException("상품 [" + basketDto.getProNameStr() + "]을(를) 찾을 수 없습니다.");
	            }

	            // 주문 제품 데이터 바인딩 및 배치에 추가
	            pstmtOrderProduct.setInt(1, basketDto.getProIndexInt()); // 상품 ID
	            pstmtOrderProduct.setString(2, basketDto.getProNameStr()); // 상품 이름
	            pstmtOrderProduct.setInt(3, basketDto.getBasStockInt()); // 주문 수량
	            pstmtOrderProduct.setInt(4, basketDto.getProPriceInt()); // 상품 가격
	            pstmtOrderProduct.addBatch(); // 배치 실행 준비

	            // 재고 감소 SQL 생성
	            sqlDecreaseStock += "UPDATE PRODUCT";
	            sqlDecreaseStock += " SET P_STOCK = P_STOCK - ?";
	            sqlDecreaseStock += " WHERE P_INDEX = ?";
	            pstmtDecreaseStock = connection.prepareStatement(sqlDecreaseStock);

	            pstmtDecreaseStock.setInt(1, basketDto.getBasStockInt()); // 감소할 수량
	            pstmtDecreaseStock.setInt(2, basketDto.getProIndexInt()); // 상품 ID
	            pstmtDecreaseStock.addBatch(); // 배치 실행 준비
	        }

	        // 포인트 감소 SQL 생성
	        sqlDecreasePoint += "INSERT INTO POINT";
	        sqlDecreasePoint += " (POINT_INDEX, M_INDEX, POINT_DATE, POINT_POINT)";
	        sqlDecreasePoint += " VALUES(POINT_INDEX_SEQ.NEXTVAL, ?, SYSDATE, ?)";
	        pstmtDecreasePoint = connection.prepareStatement(sqlDecreasePoint);

	        // 포인트 감소 데이터 삽입
	        pstmtDecreasePoint.setInt(1, memIndexInt); // 회원 ID
	        pstmtDecreasePoint.setInt(2, -totalPriceInt); // 차감할 포인트 (음수 값)
	        pstmtDecreasePoint.addBatch(); // 배치 실행 준비

	        // 장바구니 비우기 SQL 생성
	        sqlClearBasket += "DELETE FROM BASKET";
	        sqlClearBasket += " WHERE M_INDEX = ?";
	        pstmtClearBasket = connection.prepareStatement(sqlClearBasket);

	        // 장바구니 비우기 데이터 바인딩
	        pstmtClearBasket.setInt(1, memIndexInt); // 회원 ID
	        pstmtClearBasket.addBatch(); // 배치 실행 준비

	        // 배치 실행
	        int[] order = pstmtOrder.executeBatch(); // 주문 데이터 실행
	        int[] orderProduct = pstmtOrderProduct.executeBatch(); // 주문 상품 데이터 실행
	        int[] decreaseStock = pstmtDecreaseStock.executeBatch(); // 재고 감소 실행
	        int[] decreasePoint = pstmtDecreasePoint.executeBatch(); // 포인트 감소 실행
	        int[] clearBasket = pstmtClearBasket.executeBatch(); // 장바구니 비우기 실행

	        // 모든 작업 성공 시 커밋
	        connection.commit();
	        connection.setAutoCommit(true);

	    } catch (Exception e) {
	        // 예외 발생 시 롤백
	        e.printStackTrace();
	        connection.rollback();

	    } finally {
	        // 리소스 정리
	        try {
	            if (rsStockCheck != null) {
	                rsStockCheck.close(); // ResultSet 닫기
	            }
	            if (pstmtClearBasket != null) {
	                pstmtClearBasket.close();
	            }
	            if (pstmtDecreaseStock != null) {
	                pstmtDecreaseStock.close();
	            }
	            if (pstmtOrderProduct != null) {
	                pstmtOrderProduct.close();
	            }
	            if (pstmtOrder != null) {
	                pstmtOrder.close();
	            }
	            if (pstmtStockCheck != null) {
	                pstmtStockCheck.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return result; // 결과 반환
	}

	public int orderCount() {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			
			sql += "SELECT COUNT(O_INDEX)";
			sql += " FROM S_ORDER";

			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				result = rs.getInt("COUNT(O_INDEX)");
				}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return result;
		
	}

	public int updateStat(int no, String status) {
		// TODO Auto-generated method stub
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "";
		
		try {
			sql += "UPDATE S_ORDER";
			sql += " SET STA_STATUS=?";
			sql += " WHERE O_INDEX=?";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return result;
	}

	public ArrayList<OrderDto> selectList(int no, int divRowInt, String filter) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<OrderDto> orderList = new ArrayList<OrderDto>();

		String sql = "";

		try {

			sql = 
				    "SELECT rn, O_INDEX, STA_STATUS, O_TIME, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL, O_TOTAL " +
				    "FROM ( " +
				    "    SELECT ROWNUM AS rn, O_INDEX, STA_STATUS, O_TIME, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL, O_TOTAL " +
				    "    FROM ( " +
				    "        SELECT O_INDEX, STA_STATUS, O_TIME, M_INDEX, O_NAME, O_ADDRESS, O_ADDRESS_SEC, O_TEL, O_TOTAL " +
				    "        FROM s_order " +
				    "        WHERE STA_STATUS = ?" +
				    "        ORDER BY O_INDEX DESC " +
				    "    ) " +
				    "    WHERE ROWNUM <= ? " +
				    ") " +
				    "WHERE rn >= ?";

				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, filter);
				pstmt.setInt(2, no * divRowInt); // 끝 범위
				pstmt.setInt(3, (no - 1) * divRowInt + 1); // 시작 범위
				


			rs = pstmt.executeQuery();

			int ordIndexint = 0;// 주문번호
			String staStatStr = "";// 주문상태
			Date ordTime = null;// 주문일자
			String memAdd1Str = "";
			String memAdd2Str = "";
			String memTelStr = "";
			int memIndexInt = 0;// 주문자 멤버 인덱스
			String memNameStr = "";// 주문자명
			int totalPriceInt = 0;// 총주문액

			while (rs.next()) {

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
		return orderList;
	}

	public int orderCount(String filter) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			
			sql += "SELECT COUNT(O_INDEX)";
			sql += " FROM S_ORDER";
			sql += " WHERE STA_STATUS = ?";
			sql += " GROUP BY STA_STATUS";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, filter);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				result = rs.getInt("COUNT(O_INDEX)");
				}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return result;
	}


}
