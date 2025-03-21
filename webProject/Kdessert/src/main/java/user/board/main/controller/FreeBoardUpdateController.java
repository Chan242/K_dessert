package user.board.main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

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

@WebServlet("/board/update")
public class FreeBoardUpdateController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		RequestDispatcher rd = null;

		String brdIndexStr = "";
		
		try {
			brdIndexStr = req.getParameter("brdIndexInt");
			int brdIndexInt = Integer.parseInt(brdIndexStr);

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			FreeBoardDao boardDao = new FreeBoardDao();
			boardDao.setConnection(conn);
			
			FreeBoardDto boardDto = boardDao.freeBoardWritedInfo(brdIndexInt);
			
			HttpSession session = req.getSession();
			
			MemberDto memberDto = (MemberDto)session.getAttribute("member");
			
			int memIndex = memberDto.getMemIndexInt();
			
			 // 세션에 로그인 정보가 없다면 게시판을 볼 수 없음
	        if (session == null ||boardDto.getMemIndexInt() != memIndex) {
	
	            res.setContentType("text/html; charset=UTF-8");
	            PrintWriter writer = res.getWriter();//알림창이 뜬 후 로그인 페이지로 리다이렉트
	            writer.println("<script> alert('권한이 없습니다. 메인 페이지로 이동합니다.'); location.href='" 
	            				+ "/Kdessert" 
	            				+ "'; </script>"); 
	            writer.close();
	            return;  // 더 이상 코드 실행하지 않도록 종료
	        }
	        
			
			
			req.setAttribute("boardDto", boardDto);
			rd = req.getRequestDispatcher("/page/member/board/FreeBoardUpdateView.jsp");
			rd.forward(req, res);
			
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
		FreeBoardDto boardDto = null;
		
		Connection conn = null;
		
		
		try {
			
			String brdSubjectStr  = req.getParameter("brdSubjectStr");
			String brdTextStr = req.getParameter("brdTextStr");
			String brdIndexStr = req.getParameter("brdIndexInt");
			int brdIndexInt = Integer.parseInt(brdIndexStr);

//			String brdImageStr = req.getParameter("brdImageStr");


			
			boardDto = new FreeBoardDto();
			boardDto.setBrdSubjectStr(brdSubjectStr);
			boardDto.setBrdTextStr(brdTextStr);
			boardDto.setBrdIndexInt(brdIndexInt);
			System.out.println(brdIndexInt);
			//현재 접속중인 서블릿 객체 가져옴
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			FreeBoardDao boardDao = new FreeBoardDao();
			boardDao.setConnection(conn);
			
			boardDao.freeBoardUpdate(boardDto);
			
			res.sendRedirect("/Kdessert/board");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/page/Error.jsp");
			rd.forward(req, res);
		}
	}
}
