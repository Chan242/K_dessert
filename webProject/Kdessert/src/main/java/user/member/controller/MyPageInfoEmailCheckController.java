package user.member.controller;

import java.io.IOException;
import java.sql.Connection;

import admin.member.MemberDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/page/member/mypage/info/emailcheck")

public class MyPageInfoEmailCheckController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		boolean isEmailAvailable = false;
		String email = req.getParameter("email");
		
		try {
			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
	        // DB에서 아이디 중복 체크. 아이디가 비어있으면(false) 가능(true) 
	        isEmailAvailable = !memberDao.memberEmailCheck(email);

	        // 응답 유형 설정
	        res.setContentType("text/html; charset=UTF-8");

	        // 결과에 따라 메시지 반환
	        if (isEmailAvailable) {
	            res.getWriter().write("<span style='color: green;'>사용 가능한 이메일입니다.</span>");
	        } else {
	            res.getWriter().write("<span style='color: red;'>이미 사용 중인 이메일입니다</span>");
	        }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
}
