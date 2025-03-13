package user.board.main.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.board.main.FreeBoardDao;
import user.board.main.FreeBoardDto;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import admin.member.MemberDto;

/**
 * Servlet implementation class FreeBoard
 */
@WebServlet("/board/search")
public class FreeBoardSearchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardSearchListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("검색용 리스트 doget");
		ArrayList<FreeBoardDto> boardList = null;
		Connection conn = null;
		int totalCount = 0;
		int pageSize = 0;
		int pageNum = 0;
		
		pageSize = 10; // 한 페이지에 10개
		pageNum = 1;  // 기본값 1페이지
		
		String searchStr = "";
		
		try {
			
			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			
			FreeBoardDao boardDao = new FreeBoardDao();
			//주입
			boardDao.setConnection(conn);
			
			
			
			//입력한 검색어 가져오기
			searchStr = req.getParameter("searchStr");
			//검색어를 request에 저장

			
			boardList = (ArrayList<FreeBoardDto>)boardDao.freeboardSearch(searchStr,pageNum, pageSize);

			req.setAttribute("boardList", boardList);
			
			
			/* 페이징관련 */

			//게시글의 총 데이터 수 가져오기
			totalCount = boardDao.freeBoardListSearchTo(searchStr);
			// 페이지의 총 개수 계산
	        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
	        // 요청에 필요한 정보 저장
	        req.setAttribute("totalPage", totalPage);
	        req.setAttribute("pageNum", pageNum);
	        req.setAttribute("pageSize", pageSize);
	        
			//페이지 준비
			RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/page/member/board/FreeBoardListView.jsp");
			//dispatcher를 통해 링크 화면으로 이어짐.
			dispatcher.include(req, res);
			
			
			
			
			
		} catch (Exception e) {
//			throw new ServletException(e);
			System.out.println("게시판 목록에서 예외 발생");
			e.printStackTrace();
			
			req.setAttribute("error", e);
			
			RequestDispatcher dispatcher =
					req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, res);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("검리스트 doPost");
		
	}

}
