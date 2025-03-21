package admin.event.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

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

@WebServlet ("/page/admin/event/update")
public class EventUpdateController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		RequestDispatcher rd = null;

		String eveIndexStr = "";
		try {
			eveIndexStr = req.getParameter("no");
			int eveIndex = Integer.parseInt(eveIndexStr);

			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			EventDto eventDto = eventDao.eventSelectOne(eveIndex);
			
			if (eventDto == null) {
				throw new Exception("해당 번호의 행사를 찾을 수 없습니다.");
			}
			
			req.setAttribute("eventDto", eventDto);
			
			rd = req.getRequestDispatcher("./EventUpdateForm.jsp");
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
		
		EventDto eventDto = null;
		
		Connection conn = null;
		
		try {
			
			String eveIndexStr = req.getParameter("no");
			int eveIndex = Integer.parseInt(eveIndexStr);
			
            String name = req.getParameter("name");
			
			String eventDateStr = req.getParameter("date");
			System.out.println("이벤트 업데이트 컨트롤 행사날짜값 받기: "+eventDateStr);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date eventDate = format.parse(eventDateStr);
			
			String explan = req.getParameter("explain");
			
			String openStr = req.getParameter("openCheck");
			int open = Integer.parseInt(openStr);
			String note = req.getParameter("note");
			
			eventDto = new EventDto();
			eventDto.setEveIndexInt(eveIndex);
			eventDto.setEveNameStr(name);
			eventDto.setEveEventDate(eventDate);
			eventDto.setEveExplainStr(explan);
			eventDto.setEveOpenInt(open);
			eventDto.setEveNoteStr(note);
			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			int result = eventDao.eventUpdate(eventDto);

			if(result == 0){
				System.out.println(" 행사 정보 수정에 실패하였습니다.");
			}
			
			res.sendRedirect("./list");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}
	}
	

}
