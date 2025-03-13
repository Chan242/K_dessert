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

@WebServlet("/board/reply")
public class FreeBoardReplyListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeBoardReplyListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//Db 연결
		Connection conn = null;
		String  brdIndexInt= "";
		
		try {
			
			brdIndexInt = req.getParameter("brdIndexInt");
			int brdIndex = Integer.parseInt(brdIndexInt);
			
			//로그인중인 현재 서블릿 객체 준비
			ServletContext sc = this.getServletContext();
			//conn 연결
			conn = (Connection)sc.getAttribute("conn");
			
			//BoardReplyDao 객체 생성
			BoardReplyDao boardreplyDao = new BoardReplyDao();
			
			//BoardReplyDao와 DB 연결
			boardreplyDao.setConnection(conn);
			
			//댓글 데이터들이 들어갈 리스트 생성
			ArrayList<BoardReplyDto> boardreplyList = null;
			
			boardreplyList = (ArrayList<BoardReplyDto>)boardreplyDao.replyList(brdIndex);
			
			req.setAttribute("boardreplyList", boardreplyList);
			
			
			//페이지 준비
			RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/page/member/board/ReplyView.jsp");
			
			dispatcher.forward(req, res);
			
			
		} catch (Exception e) {
			System.out.println("댓글목록에서 오류상황 발생");
			e.printStackTrace();
		}


	}

	// 댓글란 등록 버튼 누를 시 doPost 실행
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("댓글 입력 doPost시작");
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
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//댓글 작성
		
		

	}
}
