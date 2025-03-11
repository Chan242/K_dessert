package user.member.controller;

import java.io.IOException;
import java.sql.Connection;

import admin.member.MemberDao;
import admin.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/page/member/mypage/point")
public class MypagePointViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		MemberDto memberDto = null;
		int index = 0;
		
		try {
			MemberDto memberDtoindex = new MemberDto();
			
			//해당 회원의 인덱스 값을 세션에서 구함
			HttpSession session = req.getSession();
			memberDtoindex = (MemberDto) session.getAttribute("member");
			index = memberDtoindex.getMemIndexInt();
			
			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			//dao로 받아온 dto 담기
			memberDto = memberDao.memberPointGet(index);
			
			req.setAttribute("memberDto", memberDto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("./MypagePointView.jsp");
			
			dispatcher.include(req, res);
			
		} catch (Exception e) {
//			throw new ServletException(e);
			System.out.println("회원 목록에서 예외 발생");
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
