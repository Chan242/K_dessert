package member;

import java.io.IOException;
import java.sql.Connection;

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

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			MemberDto memberDto = memberDao.memberSelectOne(mIndex);
			
			if (memberDto == null) {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			
			req.setAttribute("memberDto", memberDto);
			
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
		super.doPost(req, res);
	}
	
	
}
