package user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import admin.member.MemberDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/page/member/mypage/info/delete")

public class MypageInfoDeleteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;

		int no = Integer.parseInt(req.getParameter("no"));

		try {
			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);

			int result = memberDao.memberDelete(no);

			if (result == 0) {
				System.out.println("회원 삭제가 정상처리 되지 않았습니다");
				PrintWriter writer = res.getWriter();
				writer.println("<script> alert('탈퇴에 실패하였습니다.'); location.href='" + "../info" + "'; </script>"); 
				writer.close();
				return;
			}
			
			System.out.println("마이페이지에서 회원 탈퇴 완료됨");
			
			// 응답의 Content-Type을 HTML로 설정
			res.setContentType("text/html;charset=UTF-8");
			
			// PrintWriter로 HTML 출력
			PrintWriter writer = res.getWriter();
			writer.println("<script>"
			              + "alert('탈퇴가 진행되었습니다.\\n그동안 이용해주셔서 감사합니다.'); "
			              + "location.href='/Kdessert/auth/logout';"
			              + "</script>");
			writer.close();
			
			
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
		
		
	}
	
	
	
	
}
