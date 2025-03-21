package admin.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import admin.member.MemberDto;

public class EventDao {

	private Connection connection;
		
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	// 전체 행사 조회 (페이징)	
	public List<EventDto> selectList(int pageNum, int pageSize) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<EventDto> eventList = new ArrayList<EventDto>();
		
		String sql = "";
		
		//페이징 적용을 위한 sql
		//행사 번호, 행사명, 행사일, 공개여부, 생성일, 수정일
		sql += "SELECT * FROM (";
		sql += " SELECT A.*, ROWNUM rnum FROM (";
		sql += " SELECT e.E_INDEX, e.E_NAME, e.E_EVENT_DATE, e.E_OPEN, e.E_CRE_DATE, e.E_CORR_DATE";
		sql += " FROM EVENT e ORDER BY e.E_INDEX DESC";
		sql += " ) A WHERE ROWNUM <= ?";
		sql += " ) WHERE rnum >= ?";
		
		//pageNum에 따라 startRow 부터 endRow 까지의 값을 보여준다
		int startRow = (pageNum - 1) * pageSize + 1; // 조회한 테이블에서 첫번째로 보여줄 행
		int endRow = pageNum * pageSize; // 조회한 테이블에서 마지막으로 보여줄 행
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, endRow);  // ROWNUM <= endRow 마지막 행보다 행보다 작거나 같을 때
			pstmt.setInt(2, startRow); // rnum >= startRow 첫번째 행보다 크거나 같을 때
			
			rs = pstmt.executeQuery();
			
			int index = 0;
			String name = "";
			Date eveDate = null;
			int open = 0;
			Date creDate = null;
			Date corrDate = null;
			
			while (rs.next()) {
				
				index = rs.getInt("E_INDEX");
				name = rs.getString("E_NAME");
				eveDate = rs.getTimestamp("E_EVENT_DATE");
				open = rs.getInt("E_OPEN");
				creDate = rs.getDate("E_CRE_DATE");
				corrDate = rs.getDate("E_CORR_DATE");
				
				EventDto eventDto = new EventDto();
				
				eventDto.setEveIndexInt(index);
				eventDto.setEveNameStr(name);
				eventDto.setEveEventDate(eveDate);
				eventDto.setEveOpenInt(open);
				eventDto.setEveCreDate(creDate);
				eventDto.setEveCorrDate(corrDate);
				
				eventList.add(eventDto);
						
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
		} // finally end
		
		return eventList;
		
	}
	
	// 행사 전체 조회에서 전체 데이터의 수를 가져오는 메소드
 	public int getTotalCount() {
 		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		String sql = "";
		
		sql += "SELECT COUNT(E_INDEX) FROM EVENT";

		try {
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			totalCount = rs.getInt(1);
			
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
		} // finally end
		return totalCount;
	}	

 	// 단일 행사 조회
 	public EventDto eventSelectOne(int no) {
 		
 		EventDto eventDto = null;

 		PreparedStatement pstmt = null;
 		ResultSet rs = null;

 		String sql = "";

 		sql += "SELECT E_INDEX, E_NAME, E_IMAGE, E_EVENT_DATE, E_EXPLAIN, ";
 		sql += "E_OPEN, E_CRE_DATE, E_CORR_DATE, E_NOTE";
 		sql += " FROM EVENT";
 		sql += " WHERE E_INDEX =?";
 		
 		try {
 			pstmt = connection.prepareStatement(sql);

 			pstmt.setInt(1, no);

 			rs = pstmt.executeQuery();
 			
 			
 			int index = 0;
			String name = "";
			String image = "";
			Date eveDate = null;
			String explain = "";
			int open = 0;
			Date creDate = null;
			Date corrDate = null;
 			String note = "";

 			if (rs.next()) {
 				index = rs.getInt("E_INDEX");
 				name = rs.getString("E_NAME");
 				image = rs.getString("E_IMAGE");
 				eveDate = rs.getTimestamp("E_EVENT_DATE");
 				explain = rs.getString("E_EXPLAIN");
 				open = rs.getInt("E_OPEN");
 				creDate = rs.getTimestamp("E_CRE_DATE");
 				corrDate = rs.getTimestamp("E_CORR_DATE");
 				note = rs.getString("E_NOTE");

 				eventDto = new EventDto();

 				eventDto.setEveIndexInt(index);
 				eventDto.setEveNameStr(name);
 				eventDto.setEveImageStr(image);
 				eventDto.setEveEventDate(eveDate);
 				eventDto.setEveExplainStr(explain);
 				eventDto.setEveOpenInt(open);
 				eventDto.setEveCreDate(creDate);
 				eventDto.setEveCorrDate(corrDate);
 				eventDto.setEveNoteStr(note);
 				
 			} else {
 				throw new Exception("해당 번호의 행사를 찾을 수 없습니다.");
 			}

 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} finally {

 			try {
 				if (rs != null) {
 					rs.close();
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}

 			try {
 				if (pstmt != null) {
 					pstmt.close();
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}

 		} // finally 종료
 		return eventDto;
 	}
 	
 // 행사 등록
  	public int eventAdd(EventDto eventDto) throws SQLException {
  		
  		int result = 0;

  		PreparedStatement pstmt = null;

  		String sql = "";
  		sql = "INSERT INTO EVENT";
  		sql += " (E_INDEX,E_NAME,E_IMAGE,E_EVENT_DATE, E_EXPLAIN, E_OPEN,E_CRE_DATE,E_CORR_DATE,E_NOTE)";
  		sql	+= " VALUES(E_INDEX_SEQ.NEXTVAL,?, '이미지', ?, ?, ?, sysdate, sysdate, ?)";
  		
  		
  		try {
  			
  			pstmt = connection.prepareStatement(sql);

  			pstmt.setString(1, eventDto.getEveNameStr());
  			
  			java.util.Date utilDate = eventDto.getEveEventDate();
  			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
  			pstmt.setDate(2, sqlDate);
  			pstmt.setString(3, eventDto.getEveExplainStr());
  			pstmt.setInt(4, eventDto.getEveOpenInt());
  			pstmt.setString(5, eventDto.getEveNoteStr());

  			result = pstmt.executeUpdate();
  			
  		} catch (Exception e) {
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

  		} // finally 종료
  		return result;
  	}
 	
 	// 행사 정보 수정
 	public int eventUpdate(EventDto eventDto) throws SQLException {
 		
 		int result = 0;

 		PreparedStatement pstmt = null;

 		String sql = "";
 		sql = "UPDATE EVENT";
 		sql += " SET E_NAME=?, E_EVENT_DATE=?, E_EXPLAIN=?,";
 		sql	+= " E_OPEN=?, E_CORR_DATE=SYSDATE, E_NOTE=?";
 		sql += " WHERE E_INDEX =?";
 		
 		
 		try {
 			
 			pstmt = connection.prepareStatement(sql);

 			pstmt.setString(1, eventDto.getEveNameStr());
 			java.util.Date utilDate = eventDto.getEveEventDate();
 			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
 			pstmt.setTimestamp(2, sqlTimestamp);
 			pstmt.setString(3, eventDto.getEveExplainStr());
 			pstmt.setInt(4, eventDto.getEveOpenInt());
 			pstmt.setString(5, eventDto.getEveNoteStr());
 			pstmt.setInt(6, eventDto.getEveIndexInt());

 			result = pstmt.executeUpdate();
 			
 		} catch (Exception e) {
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

 		} // finally 종료
 		return result;
 	}
 	
 	// 행사 삭제
 	public int eventDelete(int no) throws SQLException {
 			
 			int result = 0;

 			PreparedStatement pstmt = null;

 			String sql = "";
 			
 			sql += "DELETE FROM EVENT";
 			sql += " WHERE E_INDEX = ?";

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
 	
 	
 // 달력에서 행사 조회	
 	public List<EventDto> eventCalendarCheck(String eventDate) {
 	
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		
 		ArrayList<EventDto> eventList = null;
 		
 		String sql = "";
 		
 		sql += " SELECT E_INDEX, E_NAME, E_EVENT_DATE, E_OPEN, E_EXPLAIN";
 		sql += " FROM EVENT";
 		sql += " WHERE e_event_date BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND LAST_DAY(TO_DATE(?))";
 		
 		try {
 			pstmt = connection.prepareStatement(sql);
 			
 			pstmt.setString(1,eventDate); 
 			pstmt.setString(2,eventDate); 
 			
 			rs = pstmt.executeQuery();
 			
 			int index = 0;
 			String name = "";
 			Date eveDate = null;
 			int open = 0;
 			
 			eventList = new ArrayList<EventDto>();
 			while (rs.next()) {
 				index = rs.getInt("E_INDEX");
 				name = rs.getString("E_NAME");
 				eveDate = rs.getTimestamp("E_EVENT_DATE");
 				open = rs.getInt("E_OPEN");
 				
 				EventDto eventDto = new EventDto();
 				
 				eventDto.setEveIndexInt(index);
 				eventDto.setEveNameStr(name);
 				eventDto.setEveEventDate(eveDate);
 				eventDto.setEveOpenInt(open);
 				
 				eventList.add(eventDto);
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
 		} // finally end
 		
 		return eventList;
 		
 	}
 	
 	
	// 선택된 달력에서 행사 조회 (페이징)	
	public List<EventDto> eventCalendarList(int pageNum, int pageSize, String eventDate) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<EventDto> eventList = null;
		
		String sql = "";
		
		//페이징 적용을 위한 sql
		//행사 번호, 행사명, 행사일, 공개여부, 생성일, 수정일
		sql += "SELECT * FROM (";
		sql += " SELECT A.*, ROWNUM rnum FROM (";
		sql += " SELECT e.E_INDEX, e.E_NAME, e.E_EVENT_DATE, e.E_OPEN, e.E_EXPLAIN";
		sql += " FROM EVENT e WHERE TRUNC(e_event_date) = TO_DATE(?, 'YYYY-MM-DD') ORDER BY e.E_INDEX DESC";
		sql += " ) A WHERE ROWNUM <= ?";
		sql += " ) WHERE rnum >= ?";
		
		//pageNum에 따라 startRow 부터 endRow 까지의 값을 보여준다
		int startRow = (pageNum - 1) * pageSize + 1; // 조회한 테이블에서 첫번째로 보여줄 행
		int endRow = pageNum * pageSize; // 조회한 테이블에서 마지막으로 보여줄 행
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, eventDate); 
			pstmt.setInt(2, endRow);  // ROWNUM <= endRow 마지막 행보다 행보다 작거나 같을 때
			pstmt.setInt(3, startRow); // rnum >= startRow 첫번째 행보다 크거나 같을 때
			
			rs = pstmt.executeQuery();
			
			int index = 0;
			String name = "";
			Date eveDate = null;
			int open = 0;
			String eveExplain = "";
			
			eventList = new ArrayList<EventDto>();
			while (rs.next()) {
				
				index = rs.getInt("E_INDEX");
				name = rs.getString("E_NAME");
				eveDate = rs.getTimestamp("E_EVENT_DATE");
				open = rs.getInt("E_OPEN");
				eveExplain = rs.getString("E_EXPLAIN");
				
				EventDto eventDto = new EventDto();
				
				eventDto.setEveIndexInt(index);
				eventDto.setEveNameStr(name);
				eventDto.setEveEventDate(eveDate);
				eventDto.setEveOpenInt(open);
				eventDto.setEveExplainStr(eveExplain);
				
				eventList.add(eventDto);
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
		} // finally end
		
		return eventList;
		
	}
		
	// 달력 행사 조회에서 전체 데이터의 수를 가져오는 메소드
	 	public int getTotalCountCalendar(String eventDate) {
	 		
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalCount = 0;
			String sql = "";
			
			
			sql += "SELECT COUNT(E_INDEX) FROM EVENT";
			sql += " WHERE TRUNC(E_EVENT_DATE) = TO_DATE(?, 'YYYY-MM-DD')";
			
			
			try {
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, eventDate); 
				
				rs = pstmt.executeQuery();
				
				rs.next();
				totalCount = rs.getInt(1);
				
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
			} // finally end
			return totalCount;
		}	
}
