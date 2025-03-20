package admin.dessert;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/page/admin/DaquaList/modify")
public class DaquaModifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		// 응답 한글 깨짐 방지 설정
		res.setContentType("text/html; charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		Connection conn = null;
		
		DaquaDAO dao = new DaquaDAO();
		List<Daqua> daquaList = null;
//		List<Daqua> daquaList = dao.getAllDaqua();

		
			
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			dao.setConnection(conn);
			
			
			// DB에서 다과 리스트 가져오기
			daquaList = dao.getAllDaqua();
			req.setAttribute("daquaList", daquaList);
			
			
			//다과 리스트 JSP로 포워딩

			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("/page/admin/dessert/dagwagwanli_sujeonghagi.jsp");
				//dispatcher를 통해 링크 화면으로 이어짐.
				dispatcher.include(req, res);
		
		} catch (Exception e) {
			e.printStackTrace();
			// 오류 발생 시, 에러 페이지로 리다이렉트
			res.sendRedirect("error.jsp");
			return;
						
		
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		
		
	}
	  
}
