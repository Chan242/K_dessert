package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet ("/auth/findid")
public class FindIdController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;
		
		// 회원이 입력한 이름과 이메일 값
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		try {
			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			MemberDto memberDto = memberDao.findId(name, email);
			
			// 아이디가 존재하지 않음 알림
			if(memberDto == null){
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = res.getWriter();
				writer.println("<script> alert('아이디 조회에 실패했습니다. 입력한 정보를 다시 확인바랍니다.'); location.href='" + "./FindAccount.jsp" + "'; </script>"); 
				writer.close();
				return;
			}
			
			//조회된 id
			String id = memberDto.getMemIdStr();
			
			// 아이디가 존재한다면 알림창으로 안내
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = res.getWriter();
			writer.println("<script> alert('회원님의 아이디는 " + id + " 입니다.'); location.href='" + "./FindAccount.jsp" + "'; </script>"); 
			writer.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	
}
