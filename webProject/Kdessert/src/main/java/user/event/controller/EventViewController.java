package user.event.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import admin.event.EventDao;
import admin.event.EventDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/event/list")

public class EventViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;
		ArrayList<EventDto> eventMonthList = null;
		ArrayList<EventDto> eventDateList = null;
		int pageNum = 1;  // 기본값 1페이지
		int pageSize = 3; // 한 페이지에 5개
		int totalCount = 0; // 전체 페이지 수
		boolean eventCheck = false;
		
		int year = 0;
		String yearStr = "";
		
		int month = 0;
		String monthStr = "";
		
		int date = 0;
		String dateStr = "";
		
		try {
			// 달력
				//현재 날짜 구하기 (년 월 일)
				Calendar todayCal = Calendar.getInstance();
				
				year = todayCal.get(Calendar.YEAR);
				yearStr = Integer.toString(year);
				
				month = todayCal.get(Calendar.MONTH)+1;
				monthStr = Integer.toString(month);
				
				date = todayCal.get(Calendar.DATE);
				dateStr = Integer.toString(date);
				
				// 사용자로부터 날짜 값을 받음, 없으면 오늘 날짜를 반영
		        year = Integer.parseInt(req.getParameter("year") != null ? req.getParameter("year") : yearStr);
		        month = Integer.parseInt(req.getParameter("month") != null ? req.getParameter("month") : monthStr);
		        date = Integer.parseInt(req.getParameter("date") != null ? req.getParameter("date") : dateStr);
		        
		        // Calendar 객체 생성. 위에서 할당된 년, 월의 시작일로 날짜 지정
		        Calendar cal = Calendar.getInstance();
		        cal.set(year, month - 1, 1); // 월을 0부터 시작하므로 month - 1
		        
		        // 1일의 요일이 몇 번째인지 구함
		        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1=일요일, 7=토요일
		        // 해당 월의 마지막 날짜
		        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
		        
		        // 요일 이름
		        String[] daysOfWeek = {"일", "월", "화", "수", "목", "금", "토"};
		        
		        // 달력을 모델로 전달
		        req.setAttribute("year", year);
		        req.setAttribute("month", month);
		        req.setAttribute("date", date);
		        req.setAttribute("firstDayOfWeek", firstDayOfWeek);
		        req.setAttribute("lastDay", lastDay);
		        req.setAttribute("daysOfWeek", daysOfWeek);
	
		        // 달력의 날짜를 계산하기 위한 배열
		        int[][] calendar = new int[6][7]; // 최대 6주, 7일
		        int day = 1;
		        for (int i = 0; i < 6; i++) {
		            for (int j = 0; j < 7; j++) {
		                if (i == 0 && j < firstDayOfWeek - 1) {
		                    calendar[i][j] = 0; // 첫 번째 주의 빈 공간
		                } else if (day <= lastDay) {
		                    calendar[i][j] = day++; // 날짜 채우기
		                } else {
		                    calendar[i][j] = 0; // 마지막 날짜 이후 빈 공간
		                }
		            }
		        }
		        req.setAttribute("calendar", calendar);
			//
		        
		    //DB 연결
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			//사용자로부터 받은 달의 1일
			String eventCheckDateStr = year + "-" + String.format("%02d", month) + "-" + "1";
		    
		    //선택된 달의 행사목록
			eventMonthList = (ArrayList<EventDto>) eventDao.eventCalendarCheck(eventCheckDateStr);
			
			//사용자로부터 받은 날짜
			String eventDateStr = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", date);
		    
			//선택된 페이지 넘버
			if (req.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(req.getParameter("pageNum"));
			}
			
		    //선택된 날짜의 행사목록
			eventDateList = (ArrayList<EventDto>) eventDao.eventCalendarList(pageNum, pageSize, eventDateStr);

			//총 데이터 수 가져오기
			totalCount = eventDao.getTotalCountCalendar(eventDateStr);
			// 전체 페이지 수 계산
	        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
			
	        req.setAttribute("totalPage", totalPage);
	        req.setAttribute("pageNum", pageNum);
	        req.setAttribute("pageSize", pageSize);
			
	        req.setAttribute("date", date);
	        req.setAttribute("eventMonthList", eventMonthList);
			req.setAttribute("eventDateList", eventDateList);
		        
			RequestDispatcher dispatcher = req.getRequestDispatcher("/page/member/event/EventView.jsp");
			
			dispatcher.include(req, res);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	
}
