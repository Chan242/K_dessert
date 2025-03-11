package user.board.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import admin.member.MemberDto;
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

@WebServlet("/board/deleteReply")
public class FreeBoardReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeBoardReplyDeleteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("댓글삭제 doget");
		
		//세션 객체 가져오기
		Connection conn = null;
		
		int replyIndexInt = Integer.parseInt(req.getParameter("replyIndexInt"));
		int brdIndexInt = Integer.parseInt(req.getParameter("brdIndexInt"));

		
		try {

			
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			BoardReplyDao boardReplyDao = new BoardReplyDao();
			boardReplyDao.setConnection(conn);
			
			boardReplyDao.replyDelete(replyIndexInt);
	
			
			res.sendRedirect("/Kdessert/board/freeboarddetail?brdIndexInt=" + brdIndexInt);
			
		} catch (Exception e) {
	
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
			rd.forward(req, res);
		}

	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
