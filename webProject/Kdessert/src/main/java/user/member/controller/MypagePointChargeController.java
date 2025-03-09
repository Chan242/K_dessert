package user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet ("/page/member/mypage/point/charge")
public class MypagePointChargeController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("../MypagePointCharge.jsp");
		
		dispatcher.include(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		String chargePointStr = "";
		int chargePoint = 0;
		int memPoint = 0;
		int index = 0;
		
		try {
			
			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			//해당 회원의 인덱스와 현재 포인트 값을 세션에서 구함
			HttpSession session = req.getSession(); // 세션에서 값 가져올 준비
			MemberDto memberDto = new MemberDto(); // 세션에서 가져온 값을 담을 객체
			memberDto = (MemberDto) session.getAttribute("member"); // 세션에서 member 값을 가져와 위에서 생성된 객체에 담음
			
			memPoint = memberDto.getMemPointInt(); //로그인 중인 회원의 현재 포인트 값
			index = memberDto.getMemIndexInt();
			
			// 충전하려는 금액 값
			chargePointStr = req.getParameter("point");
			chargePoint = Integer.parseInt(chargePointStr);
			
			// 충전 후 포인트 값
			memPoint += chargePoint;
			
			//Dao 메서드에 보낼 memberDto 객체 생성
			MemberDto memberDtoForDao = new MemberDto();
			memberDtoForDao.setMemPointInt(memPoint); // 충전 후 포인트 값
			memberDtoForDao.setMemIndexInt(index); // 로그인 중인 회원의 인덱스
			
			//Dao 메서드 실행
			int result = memberDao.memberPointCharge(memberDtoForDao);

			//변경된 포인트 값을 다시 넣어서 세션에 재저장
			memberDto.setMemPointInt(memPoint);
			session.setAttribute("member", memberDto);
			
			memberDto = (MemberDto) session.getAttribute("member");

			
			if(result == 0){
				System.out.println("마이포인트 충전 실패");
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = res.getWriter();
				writer.println("<script> alert('마이포인트 충전에 실패하였습니다.'); location.href='" + "./MypagePointCharge.jsp" + "'; </script>"); 
				writer.close();
				return;
			} else {
				
				System.out.println("마이포인트 충전 성공");
				
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = res.getWriter();
				writer.println("<script> alert('포인트가 충전되었습니다. \\n현재 마이포인트 "+ memPoint + "P'); "
						+ "window.close(); </script>"); 
				writer.close();
				return;
			}
			
		} catch (Exception e) {
//			throw new ServletException(e);
			System.out.println("포인트 충전에서 예외 발생");
			e.printStackTrace();
			
			req.setAttribute("error", e);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(req, res);
		}
	}
		
}
