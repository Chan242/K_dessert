package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/auth/signup")
public class SignUpController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("./signUpForm.jsp");
		rd.forward(req, res);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		
		try {

			//회원가입에서 입력받은 값
			String name = req.getParameter("mname");
			String id = req.getParameter("id");
			String password = req.getParameter("password");
//			String passwordCheck = req.getParameter("passwordCheck");
			String email = req.getParameter("email");
			String birthStr = req.getParameter("birth");
			String tel = req.getParameter("tel");
			String address = req.getParameter("address");
			String addressSec = req.getParameter("addressSec");
			
			//문자열로 받은 생년월일을 date 타입으로 변환
			java.sql.Date birth = java.sql.Date.valueOf(birthStr);
			
			//입력받은 정보를 회원객체에 저장
			MemberDto memberDto = new MemberDto();
			
			memberDto.setmNameStr(name);
			memberDto.setmIdStr(id);
			memberDto.setmPasswordStr(password);
			memberDto.setmEmailStr(email);
			memberDto.setmBirthDate(birth);
			memberDto.setmTelStr(tel);
			memberDto.setmAddressStr(address);
			memberDto.setmAddressSecStr(addressSec);
			
			//DB 연결
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			int result = 0;
			
			result = memberDao.memberSignUp(memberDto);
			
			//0이면 SQL 실패, 0이외에는 성공
			if(result == 0) {
				System.out.println("회원가입 실패");
				
				RequestDispatcher rd = req.getRequestDispatcher("./signUpFail.jsp");
				
				rd.forward(req, res);
				return;
			}
			
			//성공페이지로 이동
			res.sendRedirect("./signUpSuccess.jsp");
			
		} catch (Exception e) {

			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, res);
		}
	
	}
	
}
