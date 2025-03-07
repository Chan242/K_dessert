package user.board.main.controller;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.board.main.FreeBoardDao;

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

		//인덱스 번호의 게시물 주소로 가기위한 값
		int brdIndexInt = Integer.parseInt(brdIndexStr);
		
		try {
			//현재 서블릿(this)이 속한 웹 애플리케이션의 ServletContext를 가져옴-sc변수에 저장
			ServletContext sc = this.getServletContext();
			
			//저장된 ServletContext sc에서 "conn"이라는 이름으로 저장된 DB 연결(Connection) 객체를 가져옴.
			conn = (Connection)sc.getAttribute("conn");
			
			//delete dao 가져옴: 1. boardDao DB에 연결
			FreeBoardDao boardDao = new FreeBoardDao();
			boardDao.setConnection(conn);
			
			boardDao.deleteBoard(brdIndexInt);
			
			int result = boardDao.deleteBoard(brdIndexInt);

			if (result == 0) {
				System.out.println("회원 삭제가 정상처리 되지 않았습니다");
			}
			System.out.println("brdIndexInt: " + brdIndexInt);
			//삭제 후 절대경로로 게시글 목록으로 리다이렉트
			res.sendRedirect(req.getContextPath() +"/board");
			
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
