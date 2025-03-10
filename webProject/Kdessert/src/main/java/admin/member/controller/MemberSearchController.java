package admin.member.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import admin.member.MemberDao;
import admin.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/page/admin/member/search")
public class MemberSearchController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		String searchText = req.getParameter("searchText");
		
		try {
			
			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			//페이지로 보낼 정보 준비
			ArrayList<MemberDto> memberList = null;
			
			memberList = (ArrayList<MemberDto>)memberDao.searchList(searchText);
			
			req.setAttribute("memberList", memberList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("./MemberSearchView.jsp");
			
			dispatcher.include(req, res);
			
		} catch (Exception e) {
//			throw new ServletException(e);
			System.out.println("회원 검색에서 예외 발생");
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
