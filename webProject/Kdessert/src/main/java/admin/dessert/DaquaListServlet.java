package admin.dessert;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DaquaListServlet")
public class DaquaListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 응답 한글 깨짐 방지 설정
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		DaquaDAO dao = new DaquaDAO();
		List<Daqua> daquaList = null;
//		List<Daqua> daquaList = dao.getAllDaqua();

		
		try {
			// DB에서 다과 리스트 가져오기
			daquaList = dao.getAllDaqua();
			request.setAttribute("daquaList", daquaList);
			
			
			//다과 리스트 JSP로 포워딩
			RequestDispatcher dispatcher = 
			request.getRequestDispatcher("daquaList.jsp");
			dispatcher.forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			// 오류 발생 시, 에러 페이지로 리다이렉트
			response.sendRedirect("error.jsp");
			return;
			
			
		}
		
		
	}
	  
}
