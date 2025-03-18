package admin.event.controller;

import java.io.IOException;
import java.sql.Connection;

import admin.event.EventDao;
import admin.member.MemberDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/page/admin/event/delete")
public class EventDeleteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;

		int no = Integer.parseInt(req.getParameter("no"));

		try {
			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			//Dao 메서드 실행
			int result = eventDao.eventDelete(no);

			if (result == 0) {
				System.out.println("행사 삭제가 정상처리 되지 않았습니다");
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
}
