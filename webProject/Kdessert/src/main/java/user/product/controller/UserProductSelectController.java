package user.product.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.product.UserProductDao;
import user.product.UserProductDto;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Servlet implementation class MemProductListController
 */
@WebServlet("/product/select")
public class UserProductSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProductSelectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		String proIndex = "";
		
		try {
			ServletContext sc = this.getServletContext();
			proIndex = req.getParameter("no");
			int no = Integer.parseInt(proIndex);
			
			conn = (Connection)sc.getAttribute("conn");
			
			UserProductDao userProductDao = new UserProductDao();
			
			userProductDao.setConnection(conn);
			
			UserProductDto userProductDto = new UserProductDto();
			
			userProductDto = userProductDao.userSelectOne(no);
			
			req.setAttribute("userProductDto", userProductDto);
			RequestDispatcher rd = req.getRequestDispatcher("/page/member/product/MemProductSelectView.jsp");
			
			rd.forward(req, res);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			res.sendRedirect("./error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
