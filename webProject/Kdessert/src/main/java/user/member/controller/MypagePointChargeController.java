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
		Connection conn = null;
		int index = 0;
		
		try {
			MemberDto memberDtoSelect = new MemberDto();
			
			//해당 회원의 인덱스 값을 세션에서 구함
			HttpSession session = req.getSession();
			memberDtoSelect = (MemberDto) session.getAttribute("member");
			index = memberDtoSelect.getMemIndexInt();
			
			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			//새 dto 객체에 dao로 받아온 dto 담기
			MemberDto memberDto = new MemberDto();
			memberDto = memberDao.memberSelectOne(index);
			
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
			
			
			//새 dto 객체에 dao로 받아온 dto 담기
			MemberDto memberDto = new MemberDto(); //memberDto에는 대부분의 회원 정보가 저장되어 있음
			memberDto = memberDao.memberSelectOne(index);
			
			memPoint = memberDto.getMemPointInt(); //로그인 중인 회원의 현재 포인트 값
			
			// 충전하려는 금액 값
			chargePointStr = req.getParameter("point");
			chargePoint = Integer.parseInt(chargePointStr);
			
			// 충전 후 포인트 값
			memPoint += chargePoint;
			
			//Dao 메서드에 보낼 memberDto 객체 생성
			MemberDto memberDtoForDao = new MemberDto(); //memberDtoForDao에는 충전 후 포인트 값과 회원의 인덱스가 존재함
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
