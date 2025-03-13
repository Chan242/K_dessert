package user.board.main.controller;

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

@WebServlet("/board/write")
public class FreeBoardNewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeBoardNewController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		System.out.println("게시판글쓰기 doget");
		
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
		
		
		int memIndexInt = memberDtoSelect.getMemIndexInt();//유저인덱스-작성자명 조인해서 가져올 재료
		String brdSubjectStr = req.getParameter("brdSubjectStr");// 제목
		String brdTextStr = req.getParameter("brdTextStr");// 텍스트(내용)
		String memNameStr = req.getParameter("memNameStr");// 유저명
		int memAdmCheckInt = memberDtoSelect.getMemAdmCheckInt();//관리자인지 아닌지(0,1)
		
		int brdNoticeInt = 0;// 게시물이 공지인가 아닌가

		FreeBoardDto boardDto = new FreeBoardDto();
		
		
		if(brdSubjectStr =="" || brdTextStr=="") {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = res.getWriter();//알림창이 뜬 후 로그인 페이지로 리다이렉트
            writer.println("<script> alert('내용이 비었습니다.'); location.href='" 
            				+ "/Kdessert/board" + "'; </script>"); 
            writer.close();
			return;
		}
		
		
		
		boardDto.setMemIndexInt(memIndexInt);//유저인덱스 저장
		boardDto.setBrdSubjectStr(brdSubjectStr);//제목 저장
		boardDto.setBrdTextStr(brdTextStr);//내용 저장
		
		if(memAdmCheckInt==1) {
			brdNoticeInt = 1;
		}else if(memAdmCheckInt==1) {
			brdNoticeInt = 0;
			
		}
		boardDto.setBrdNoticeInt(brdNoticeInt);//공지여부 저장
		
		memNameStr = memberDtoSelect.getMemNameStr();// 현재 로그인중인 유저명
		
		try {
			System.out.println("서블릿디비 연결");
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			FreeBoardDao boardDao = new FreeBoardDao();
			boardDao.setConnection(conn);
			
			//Dao의 freeBoardNew 메서드 불러와서 불러온 값을 넣음
			boardDao.freeBoardNew(boardDto, res);
			
			
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
