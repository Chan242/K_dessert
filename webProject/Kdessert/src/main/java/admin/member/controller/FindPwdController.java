package admin.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Random;

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

@WebServlet ("/auth/findpwd")
public class FindPwdController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;
		HttpSession session = req.getSession();
		
	    
		try {
			// 회원이 입력한 아이디
			String id = req.getParameter("id");
			
			//DB 연결
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			//조회된 id
			boolean idChek = memberDao.memberIdCheck(id);
			
			// 아이디가 존재하지 않음 알림
			if(!idChek){
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = res.getWriter();
				writer.println("<script> alert('해당 아이디는 현재 존재하는 계정이 아닙니다.\\n입력한 정보를 다시 확인바랍니다.'); location.href='" + "./FindAccount.jsp" + "'; </script>"); 
				writer.close();
				return;
			}
			
			// 세션에서 임시 비밀번호가 이미 생성되었는지 확인
            String tempPwd = (String) session.getAttribute("tempPwd");
            
            // 임시 비밀번호가 없으면 새로 생성
            if (tempPwd == null) {
                tempPwd = generateTempPassword();
                session.setAttribute("tempPwd", tempPwd); // 임시 비밀번호를 세션에 저장
            }
			System.out.println("임시비밀번호: " + tempPwd);
			
		    req.setAttribute("tempPwd", tempPwd);
			int result = memberDao.memberTempPwd(id, tempPwd);
			
			//DB 업데이트 실패
			if(result == 0){
				System.out.println("비밀번호 발급에 실패하였습니다.");
			}
			
			// 비밀번호 발급페이지로 안내
			RequestDispatcher dispatcher = req.getRequestDispatcher("./TempPasswordInfo.jsp");
			dispatcher.forward(req, res);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	private String generateTempPassword() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	
}
