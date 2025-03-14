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
@WebServlet("/board")
public class FreeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("게시판리스트 doget");
		Connection conn = null;
		int totalCount = 0;
		int pageSize = 0;
		int pageNum = 0;
		
		pageSize = 10; // 한 페이지에 10개
		pageNum = 1;  // 기본값 1페이지
		
		
		try {
			
			//선택한 페이지 넘버 받아오기
			if (req.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(req.getParameter("pageNum"));
			}

			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			
			FreeBoardDao boardDao = new FreeBoardDao();
			//주입
			boardDao.setConnection(conn);
			
			
			ArrayList<FreeBoardDto> boardList = null;
			ArrayList<FreeBoardDto> boardNotiList = null;
			//boardDao는 DB에 관한 로직만 존재해야함
			//회원목록 가져옴
			boardList = (ArrayList<FreeBoardDto>)boardDao.freeBoardList(pageNum, pageSize);
			boardNotiList = (ArrayList<FreeBoardDto>)boardDao.freeBoardNotiList();
			
			//게시물목록 정보 준비
			req.setAttribute("boardList", boardList);	
			req.setAttribute("boardNotiList", boardNotiList);	

			
			/* 페이징관련 */

			//게시글의 총 데이터 수 가져오기
			totalCount = boardDao.freeBoardListTotal();
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
		doGet(req, res);
	}

}
