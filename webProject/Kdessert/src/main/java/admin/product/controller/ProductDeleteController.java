package admin.product.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

import admin.product.ProductDao;

/**
 * Servlet implementation class ProductDeleteController
 */
@WebServlet("/admin/product/delete")
public class ProductDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDeleteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(req,res);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;

		int no = Integer.parseInt(req.getParameter("no"));
		int result = 0;

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			ProductDao productDao = new ProductDao();
			productDao.setConnection(conn);

			result = productDao.productDelete(no);
			
			if (result == 0) {
				System.out.println("제품 삭제가 정상처리 되지 않았습니다");
			}

			res.sendRedirect("./list?no=1");
			
			
		} catch (Exception e) {
			// TODO: handle exception;
			e.printStackTrace();

			RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");

			rd.forward(req, res);
		}

	}

}
