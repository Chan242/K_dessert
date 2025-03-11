package admin.member.controller;

import java.io.IOException;
import java.sql.Connection;

import admin.member.MemberDao;
import admin.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet ("/page/admin/member/details")
public class MemberDetailsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		RequestDispatcher rd = null;

		String mIndexStr = "";
		
		try {
			mIndexStr = req.getParameter("no");
			int mIndex = Integer.parseInt(mIndexStr);

			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			//특정 멤버 조회
			MemberDto memberDto = memberDao.memberSelectOne(mIndex); // 회원의 기본 정보 조회
			MemberDto memberDtoPoint = memberDao.memberPointGet(mIndex); // 회원의 포인트 정보 조회
			
			if (memberDto == null) {
				throw new Exception("MemberDetailsController : 해당 번호의 회원을 찾을 수 없습니다.");
			}
			
			req.setAttribute("memberDto", memberDto);
			req.setAttribute("memberDtoPoint", memberDtoPoint);
			
			rd = req.getRequestDispatcher("./MemberDetailsView.jsp");
			rd.forward(req, res);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
}
