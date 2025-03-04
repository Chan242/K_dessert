package member;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet ("/auth/login")
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
	
		RequestDispatcher rd = req.getRequestDispatcher("./LoginForm.jsp");
		rd.forward(req, res);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		Connection conn = null;
		
		// 회원이 입력한 아이디와 비밀번호 값
		String id = req.getParameter("id");
		String pwd = req.getParameter("password");
		
		try {

			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			MemberDto memberDto = memberDao.memberExist(id, pwd);
			
			// 회원이 없다면 로그인 실패 페이지로 이동
			if(memberDto == null){
				RequestDispatcher rd = req.getRequestDispatcher("./LoginFail.jsp");
	
				rd.forward(req, res);
				return;
			}
			
			// 회원이 존재한다면 세션에 담고 메인 페이지로 이동 
			HttpSession session = req.getSession();
			session.setAttribute("member", memberDto);
	
			res.sendRedirect("../index.jsp"); 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
