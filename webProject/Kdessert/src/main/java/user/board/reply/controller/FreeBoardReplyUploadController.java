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


public class FreeBoardReplyUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeBoardReplyUploadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("댓글 doget");
		res.sendRedirect("/page/member/board/ReplyView.jsp");

	}

	// 글쓰기 버튼 누를 시 doPost 실행
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("댓글쓰기 doPost 수행");

		Connection conn = null;
		PreparedStatement pstmt = null;
		String  brdIndexInt= "";
		
		try {
			brdIndexInt = req.getParameter("brdIndexInt");
			int brdIndex = Integer.parseInt(brdIndexInt);
			
			ServletContext sc = this.getServletContext();
			//conn 연결
			conn = (Connection)sc.getAttribute("conn");
			
			//BoardReplyDao 객체 생성
			BoardReplyDao boardreplyDao = new BoardReplyDao();
			
			boardreplyDao.setConnection(conn);
			
			BoardReplyDto boardReplyDto = new BoardReplyDto();
			
			boardreplyDao.relpyAdd(brdIndex);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
