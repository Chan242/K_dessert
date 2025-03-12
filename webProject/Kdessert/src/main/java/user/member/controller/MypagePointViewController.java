package user.member.controller;

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

@WebServlet("/page/member/mypage/point")
public class MypagePointViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		MemberDto memberDto = null;
		MemberDto memberDtoindex = null;
		int index = 0;
		
		int pageNum = 1;  // 기본값 1페이지
		int pageSize = 8; // 한 페이지에 8개
		int totalCount = 0;
		
		try {
			//선택된 페이지 넘버
			if (req.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(req.getParameter("pageNum"));
			}
			
			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);


			//해당 회원의 인덱스 값을 세션에서 구함
			HttpSession session = req.getSession();
			memberDtoindex = new MemberDto();
			memberDtoindex = (MemberDto) session.getAttribute("member");
			index = memberDtoindex.getMemIndexInt();
			
			//현재 포인트 정보
			memberDto = memberDao.memberPointGet(index);
			req.setAttribute("memberDto", memberDto);
			
			//포인트 내역 정보(페이징)
			ArrayList<MemberDto> memberPointList = new ArrayList<MemberDto>();
			memberPointList = (ArrayList<MemberDto>) memberDao.memberPointHistory(index, pageNum, pageSize);
			
			req.setAttribute("memberPoint", memberPointList);
			
				
			//페이징관련
				//총 데이터 수 가져오기
				totalCount = memberDao.getPointListTotalCount(index);
				// 전체 페이지 수 계산
		        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		        // 요청에 필요한 정보 저장
		        req.setAttribute("totalPage", totalPage);
		        req.setAttribute("pageNum", pageNum);
		        req.setAttribute("pageSize", pageSize);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("./MypagePointView.jsp");
			
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
		
	}
	
}
