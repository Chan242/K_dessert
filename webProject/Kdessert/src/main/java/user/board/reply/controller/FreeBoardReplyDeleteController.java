package user.board.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			BoardReplyDto boardReplyDto = boardReplyDao.replyInfo(replyIndexInt);
			
			
			HttpSession session = req.getSession();
			
			MemberDto memberDto = (MemberDto)session.getAttribute("member");
			
			int memIndex = memberDto.getMemIndexInt();
			
			 // 작성한 사람 혹은 관리자가 아닐 경우 삭제 불가
			System.out.println(boardReplyDto.getMemIndexInt()+ "/" + memIndex);
	        if (boardReplyDto.getMemIndexInt() == memIndex || memberDto.getMemAdmCheckInt() == 1) {
	        	
			
			boardReplyDao.replyDelete(replyIndexInt);
	
			
			res.sendRedirect("/Kdessert/board/freeboarddetail?brdIndexInt=" + brdIndexInt);
	        }else {
	        	 res.setContentType("text/html; charset=UTF-8");
		            PrintWriter writer = res.getWriter();//알림창이 뜬 후 로그인 페이지로 리다이렉트
		            writer.println("<script> alert('권한이 없습니다. 다시 시도해주세요.'); location.href='" 
		            				+ "/Kdessert/board/freeboarddetail?brdIndexInt=" + brdIndexInt
		            				+ "'; </script>"); 
		            writer.close();
		            return;  // 더 이상 코드 실행하지 않도록 종료
	        }
			
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
