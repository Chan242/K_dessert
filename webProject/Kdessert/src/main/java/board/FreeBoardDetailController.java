package board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;


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
	        
			
			brdIndexInt = req.getParameter("brdindexint");
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


	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		doGet(req, res);
	}

}
