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

@WebServlet("/board/write/reply")
public class FreeBoardReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeBoardReplyController() {
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
			req.getRequestDispatcher("/page/member/board/FreeBoardWriteView.jsp");
		
		dispatcher.forward(req, res);

	}

	// 글쓰기 버튼 누를 시 doPost 실행
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("게시판글쓰기 doPost");

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		MemberDto memberDtoSelect = new MemberDto();

		//해당 회원의 인덱스 값을 세션에서 구함
		HttpSession session = req.getSession();
		memberDtoSelect = (MemberDto) session.getAttribute("member");
		System.out.println("글쓰기 작성자 가져옴");
		
		
		String memIdStr = memberDtoSelect.getMemIdStr();// 유저 아이디-작성자명 조인해서 가져올 재료
		String brdSubjectStr = req.getParameter("brdSubjectStr");// 제목
		String brdTextStr = req.getParameter("brdTextStr");// 텍스트(내용)
		String memNameStr = req.getParameter("memNameStr");// 유저명

		FreeBoardDto boardDto = new FreeBoardDto();
		
		boardDto.setBrdIdStr(memIdStr);//유저아이디 저장
		boardDto.setBrdSubjectStr(brdSubjectStr);//제목 저장
		boardDto.setBrdTextStr(brdTextStr);//내용 저장

		
		memNameStr = memberDtoSelect.getMemNameStr();// 현재 로그인중인 유저명
		
		try {
			System.out.println("서블릿디비 연결");
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			FreeBoardDao boardDao = new FreeBoardDao();
			boardDao.setConnection(conn);
			
			//Dao의 freeBoardNew 메서드 불러와서 불러온 값을 넣음
			boardDao.freeBoardNew(boardDto);
			
			
			res.sendRedirect("/Kdessert/board");
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			
			
		}
	}
}
