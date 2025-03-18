package admin.event.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
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

@WebServlet("/page/admin/event/add")
public class EventAddController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("./EventAddForm.jsp");
		rd.forward(req, res);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		EventDto eventDto = null;
		
		Connection conn = null;
		
		try {
			
            String name = req.getParameter("name");
			
			String eventDateStr = req.getParameter("date");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date eventDate = format.parse(eventDateStr);
			
			String explan = req.getParameter("explain");
			
			String openStr = req.getParameter("openCheck");
			int open = Integer.parseInt(openStr);
			
			String note = req.getParameter("note");
			
			eventDto = new EventDto();
			eventDto.setEveNameStr(name);
			eventDto.setEveEventDate(eventDate);
			eventDto.setEveExplainStr(explan);
			eventDto.setEveOpenInt(open);
			eventDto.setEveNoteStr(note);
			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			int result = eventDao.eventAdd(eventDto);

			if(result == 0){
				System.out.println("행사 등록에 실패하였습니다.");
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
