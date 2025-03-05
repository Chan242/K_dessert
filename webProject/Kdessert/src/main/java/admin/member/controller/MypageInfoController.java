package admin.member.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

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

@WebServlet ("/page/member/mypage")

public class MypageInfoController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		int index = 0;
		
		try {
			MemberDto memberDtoSelect = new MemberDto();
			
			//해당 회원의 인덱스 값을 세션에서 구함
			HttpSession session = req.getSession();
			memberDtoSelect = (MemberDto) session.getAttribute("member");
			index = memberDtoSelect.getMemIndexInt();
			
			System.out.println("index: " + index);
			
			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			//새 dto 객체에 dao로 받아온 dto 담기
			MemberDto memberDto = new MemberDto();
			memberDto = memberDao.memberSelectOne(index);
			
			System.out.println(memberDto.getMemNameStr());
			System.out.println(memberDto.getMemAddressStr());
			
			req.setAttribute("memberDto", memberDto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("./mypage/MypageInfoView.jsp");
			
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
		// TODO Auto-generated method stub
		
	}
	
	
}
