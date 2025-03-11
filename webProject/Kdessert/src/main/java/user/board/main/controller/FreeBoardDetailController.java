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
import user.board.reply.BoardReplyDao;
import user.board.reply.BoardReplyDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;


/**
 * Servlet implementation class FreeBoard
 */
@WebServlet("/board/freeboarddetail")
public class FreeBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

    

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("보드디테일 doget 실행");
		
		Connection conn = null;
		String  brdIndexInt= "";
		
		try {
			//세션 객체 가져오기
			HttpSession session = req.getSession();
			
			 // 세션에 로그인 정보가 없다면 게시판을 볼 수 없음
	        if (session == null || session.getAttribute("member") == null) {
	
	            res.setContentType("text/html; charset=UTF-8");
	            PrintWriter writer = res.getWriter();//알림창이 뜬 후 로그인 페이지로 리다이렉트
	            writer.println("<script> alert('회원 전용 페이지입니다. 로그인을 해주세요.'); location.href='" 
	            				+ "/Kdessert/auth/login" + "'; </script>"); 
	            writer.close();
	            return;  // 더 이상 코드 실행하지 않도록 종료
	        }
	        
			
			brdIndexInt = req.getParameter("brdIndexInt");
			int brdIndex = Integer.parseInt(brdIndexInt);
			
			//this는 ServletContext
			ServletContext sc = this.getServletContext();
			
			//또 new해서 객체 생성하지 않게 (appinitservlet에서 만든)conn재활용.
			conn = (Connection) sc.getAttribute("conn");

			//멤버객체 생성
			FreeBoardDao boardDao = new FreeBoardDao();
			boardDao.setConnection(conn);//윗줄에서 만든 conn을 여러곳(boardDao)에서 재사용하게 연결
		
			//boardDto객체에 boardDao.selectOne(no)값을 넣음(반환값이 BoardDto이기 때문에 BoardDto타입)
			FreeBoardDto boardDto = boardDao.freeBoardDetail(brdIndex);
			
			//----------------------------------------------------------------//
			//댓글부분
			
			//BoardReplyDao 객체 생성
			BoardReplyDao boardreplyDao = new BoardReplyDao();
			
			//BoardReplyDao와 DB 연결
			boardreplyDao.setConnection(conn);
			
			//댓글 데이터들이 들어갈 리스트 생성
			ArrayList<BoardReplyDto> boardreplyList = null;
			
			boardreplyList = (ArrayList<BoardReplyDto>)boardreplyDao.replyList(brdIndex);
			
			req.setAttribute("boardreplyList", boardreplyList);
		
			//-------------------리퀘부분-------------------//
			//BoardUpdateForm.jsp에서 <jsp:useBean id="boardDto"...의 id를 가져올거라 "boardDto", 
			//boardDto가 위에 선언한 boardDto이다.
			req.setAttribute("boardDto", boardDto);
			
			
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("../page/member/board/FreeBoardDetailView.jsp");
			dispatcher.forward(req, res);

		}catch (Exception e) {

			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, res);
		}
	}

	//댓글 작성
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

	BoardReplyDto boardReplyDto = null;
		
		Connection conn =null;
		
		try {
			String memIndexStr = req.getParameter("memIndexInt");
			int memIndexInt = Integer.parseInt(memIndexStr);
			String replyTextStr = req.getParameter("replyTextStr");
			String brdIndexStr = req.getParameter("brdIndexInt");
			int brdIndexInt = Integer.parseInt(brdIndexStr);
			
			boardReplyDto =new BoardReplyDto();
			//dto에 값 저장
			boardReplyDto.setMemIndexInt(memIndexInt);
			boardReplyDto.setReplyTextStr(replyTextStr);
			boardReplyDto.setBrdIndexInt(brdIndexInt);
			
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			BoardReplyDao boardReplyDao = new BoardReplyDao();
			boardReplyDao.setConnection(conn);
			
			boardReplyDao.relpyNew(boardReplyDto);
			
			res.sendRedirect("/Kdessert/board/freeboarddetail?brdIndexInt=" + brdIndexInt);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
			dispatcher.forward(req, res);
		}
		
		
	}

}
