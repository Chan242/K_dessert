package admin.event.controller;

import java.io.IOException;
import java.sql.Connection;

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

@WebServlet ("/page/admin/event/details")
public class EventDetailsController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		RequestDispatcher rd = null;

		String eIndexStr = "";
		
		try {
			eIndexStr = req.getParameter("no");
			int eIndex = Integer.parseInt(eIndexStr);

			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			//특정 행사 조회
			EventDto eventDto = eventDao.eventSelectOne(eIndex); // 회원의 기본 정보 조회
			
			if (eventDto == null) {
				throw new Exception("EventDetailsController : 해당 번호의 행사 찾을 수 없습니다.");
			}
			
			req.setAttribute("eventDto", eventDto);
			
			rd = req.getRequestDispatcher("./EventDetailsView.jsp");
			rd.forward(req, res);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
