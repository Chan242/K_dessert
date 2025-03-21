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

@WebServlet(value = "/board/delete")
public class FreeBoardDeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		// Db 연결
		Connection conn = null;
		
		String brdIndexStr = req.getParameter("brdIndexInt");

		//brdIndexInt값이 없을 경우 오류 발생
		if (brdIndexStr == null || brdIndexStr.isEmpty()) {
		    throw new IllegalArgumentException("brdIndexInt 값이 없습니다!");
		}

		
		try {
			
			//인덱스 번호의 게시물 주소로 가기위한 값
			int brdIndexInt = Integer.parseInt(brdIndexStr);
	        
			//현재 서블릿(this)이 속한 웹 애플리케이션의 ServletContext를 가져옴-sc변수에 저장
			ServletContext sc = this.getServletContext();
			
			//저장된 ServletContext sc에서 "conn"이라는 이름으로 저장된 DB 연결(Connection) 객체를 가져옴.
			conn = (Connection)sc.getAttribute("conn");
			
			
			//delete dao 가져옴: 1. boardDao DB에 연결
			FreeBoardDao boardDao = new FreeBoardDao();
			boardDao.setConnection(conn);
			FreeBoardDto boardDto = boardDao.freeBoardDetail(brdIndexInt);
			
			
			HttpSession session = req.getSession();
			
			MemberDto memberDto = (MemberDto)session.getAttribute("member");
			
			int memIndex = memberDto.getMemIndexInt();
			
			 // 로그인한 사람 혹은 관리자가 아닐 경우 삭제 불가
	        if (boardDto.getMemIndexInt() != memIndex || boardDto.getMemberDto().getMemAdmCheckInt() == 1) {
	        	
	        
		        int result = boardDao.deleteBoard(brdIndexInt);
	
		        boardDao.deleteBoard(brdIndexInt);
		        
		        
				if (result == 0) {
					System.out.println("회원 삭제가 정상처리 되지 않았습니다");
				}
				System.out.println("brdIndexInt: " + brdIndexInt);
				//삭제 후 절대경로로 게시글 목록으로 리다이렉트
				res.sendRedirect(req.getContextPath() +"/board");
	        }else {

	            res.setContentType("text/html; charset=UTF-8");
	            PrintWriter writer = res.getWriter();//알림창이 뜬 후 로그인 페이지로 리다이렉트
	            writer.println("<script> alert('권한이 없습니다. 메인 페이지로 이동합니다.'); location.href='" 
	            				+ "/Kdessert" 
	            				+ "'; </script>"); 
	            writer.close();
	            return;  // 더 이상 코드 실행하지 않도록 종료
	        }

			
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
