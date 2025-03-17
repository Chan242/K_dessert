package admin.event.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import admin.event.EventDao;
import admin.event.EventDto;
import admin.member.MemberDao;
import admin.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/page/admin/event/list")
public class EventListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		ArrayList<EventDto> eventList = null;
		int pageNum = 1;  // 기본값 1페이지
		int pageSize = 5; // 한 페이지에 5개
		int totalCount = 0; // 전체 페이지 수
		
		try {
			//선택된 페이지 넘버
			if (req.getParameter("pageNum") != null) {
		        pageNum = Integer.parseInt(req.getParameter("pageNum"));
		    }
			
			//DB 연결
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			//조회 정보 (페이징) 가져오기
			eventList = (ArrayList<EventDto>)eventDao.selectList(pageNum, pageSize);
			//총 데이터 수 가져오기
			totalCount = eventDao.getTotalCount();
			// 전체 페이지 수 계산
	        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
			
	        // 요청에 필요한 정보 저장
	        req.setAttribute("eventList", eventList);
	        
	        req.setAttribute("totalPage", totalPage);
	        req.setAttribute("pageNum", pageNum);
	        req.setAttribute("pageSize", pageSize);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/page/admin/event/EventListView.jsp");
			
			dispatcher.include(req, res);
			
		} catch (Exception e) {
//			throw new ServletException(e);
			System.out.println("행사 목록에서 예외 발생");
			e.printStackTrace();
			
			req.setAttribute("error", e);
			
			RequestDispatcher dispatcher =
				req.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(req, res);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	

}
