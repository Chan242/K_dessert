package user.member.controller;

import java.io.IOException;

import admin.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
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
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("./MypagePointView.jsp");
		
		dispatcher.include(req, res);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
}
