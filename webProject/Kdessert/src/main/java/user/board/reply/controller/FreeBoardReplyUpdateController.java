package user.board.reply.controller;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.board.reply.BoardReplyDao;
import user.board.reply.BoardReplyDto;

@WebServlet("/board/updateReply")
public class FreeBoardReplyUpdateController extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		RequestDispatcher rd = null;

		String replyIndexStr = "";
		
		try {
			replyIndexStr = req.getParameter("replyIndexInt");
			int replyIndexInt = Integer.parseInt(replyIndexStr);

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			BoardReplyDao replyDao = new BoardReplyDao();
			replyDao.setConnection(conn);
			
			BoardReplyDto replyDto = replyDao.replyWritedInfo(replyIndexInt);
			
			
			req.setAttribute("replyDto", replyDto);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			rd = req.getRequestDispatcher("/page/Error.jsp");
			rd.forward(req, res);
		}
	}
	//업데이트 정보 보냄
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardReplyDto replyDto = null;
		
		Connection conn = null;
		
		
		try {
			String brdIndexStr = req.getParameter("brdIndexInt");
			int brdIndexInt = Integer.parseInt(brdIndexStr);
			String replyTextStr  = req.getParameter("replyEditStr");
			String replyIndexStr = req.getParameter("replyIndexInt");
			int replyIndexInt = Integer.parseInt(replyIndexStr);


			
			replyDto = new BoardReplyDto();
			replyDto.setReplyIndexInt(replyIndexInt);
			replyDto.setReplyTextStr(replyTextStr);

			//현재 접속중인 서블릿 객체 가져옴
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			BoardReplyDao replyDao = new BoardReplyDao();
			replyDao.setConnection(conn);
			
			replyDao.replyUpdate(replyDto, replyIndexInt);
			
			req.setAttribute("replyDto", replyDto);
			
//			res.sendRedirect("/Kdessert/board");
			res.sendRedirect("/Kdessert/board/freeboarddetail?brdIndexInt=" + brdIndexInt);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/page/Error.jsp");
			rd.forward(req, res);
		}
	}
}
