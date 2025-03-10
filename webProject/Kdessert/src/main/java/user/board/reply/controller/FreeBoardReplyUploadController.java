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


public class FreeBoardReplyUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeBoardReplyUploadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("댓글 doget");
		
		//세션 객체 가져오기
		HttpSession session = req.getSession();
		
		
		//페이지 준비
		RequestDispatcher dispatcher = 
			req.getRequestDispatcher("/page/member/board/ReplyView.jsp");
		
		dispatcher.forward(req, res);

	}

	// 글쓰기 버튼 누를 시 doPost 실행
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("게시판글쓰기 doPost");

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
	}
}
