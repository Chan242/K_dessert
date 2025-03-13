package user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DecimalFormat;

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
		Connection conn = null;
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
			MemberDto memberDto = null;
			memberDto = memberDao.memberPointGet(index);
			
			req.setAttribute("memberDto", memberDto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("../MypagePointCharge.jsp");
			
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
			
			//해당 회원의 인덱스 값을 세션에서 구함
			HttpSession session = req.getSession();
			MemberDto memberDtoIndex = (MemberDto) session.getAttribute("member"); //memberDtoIndex에는 회원의 인덱스, 아이디, 이름, 관리자권한 이 담겨있음
			index = memberDtoIndex.getMemIndexInt();
			
			// 충전하려는 금액 값
			chargePointStr = req.getParameter("point");
			chargePoint = Integer.parseInt(chargePointStr);
			
			//Dao 메서드에 보낼 memberDto 객체 생성
			MemberDto memberDtoForDao = new MemberDto(); //memberDtoForDao에는 충전 후 포인트 값과 회원의 인덱스가 존재함
			memberDtoForDao.setMemPointInt(chargePoint); // 충전하려는 금액 값
			memberDtoForDao.setMemIndexInt(index); // 로그인 중인 회원의 인덱스
			
			//Dao 메서드 실행
			int result = memberDao.memberPointCharge(memberDtoForDao);
			
			MemberDto memberDtoPoint = memberDao.memberPointGet(index);
			memPoint = memberDtoPoint.getMemPointInt();
			
			if(result == 0){
				System.out.println("마이포인트 충전 실패");
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = res.getWriter();
				writer.println("<script> alert('마이포인트 충전에 실패하였습니다.'); location.href='" + "../MypagePointCharge.jsp" + "'; </script>"); 
				writer.close();
				return;
			} else {
				
				System.out.println("마이포인트 충전 성공");
				
				// 🔹 숫자 포맷 설정 (천 단위 콤마 추가)
				DecimalFormat df = new DecimalFormat("#,###");
				String formattedMemPoint = df.format(memPoint); // 포맷 적용
				
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = res.getWriter();
				writer.println("<script> alert('포인트가 충전되었습니다. \\n현재 마이포인트 "+ formattedMemPoint + "P'); "
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
