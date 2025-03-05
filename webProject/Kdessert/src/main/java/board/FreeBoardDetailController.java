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
			RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(req, res);
		}
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		doGet(req, res);
	}

}
