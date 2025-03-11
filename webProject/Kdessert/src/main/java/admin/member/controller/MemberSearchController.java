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

@WebServlet ("/page/admin/member/search")
public class MemberSearchController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		ArrayList<MemberDto> memberList = null;
		Connection conn = null;
		String searchText = req.getParameter("searchText");
		int pageNum = 1;  // 기본값 1페이지
		int pageSize = 10; // 한 페이지에 10개
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
			
			//조회 정보 (페이징) 가져오기
			memberList = (ArrayList<MemberDto>)memberDao.searchList(searchText, pageNum, pageSize);
			
			if(memberList == null) {
				System.out.println("searchController에서 memberList null");
				boolean searchResult = false;
				req.setAttribute("searchResult", searchResult);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("./MemberSearchView.jsp");
				dispatcher.include(req, res);
				return;
			}
			
			
			//총 데이터 수 가져오기
			totalCount = memberDao.getSearchTotalCount(searchText);
			// 전체 페이지 수 계산
	        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
			
	        // 요청에 필요한 정보 저장
	        req.setAttribute("memberList", memberList);
	        req.setAttribute("totalPage", totalPage);
	        req.setAttribute("pageNum", pageNum);
	        req.setAttribute("pageSize", pageSize);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("./MemberSearchView.jsp");
			
			dispatcher.include(req, res);
			
		} catch (Exception e) {
//			throw new ServletException(e);
			System.out.println("회원 검색에서 예외 발생");
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
