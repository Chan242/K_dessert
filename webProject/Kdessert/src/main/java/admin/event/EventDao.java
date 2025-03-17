package admin.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				eveDate = rs.getDate("E_EVENT_DATE");
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
}
