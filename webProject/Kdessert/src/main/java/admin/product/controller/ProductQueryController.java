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
import java.util.ArrayList;

import admin.product.ProductDao;
import admin.product.ProductDto;

/**
 * Servlet implementation class ProductQueryController
 */
@WebServlet("/admin/product/query")
public class ProductQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductQueryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String queryStr = "";
		queryStr = req.getParameter("search");

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			ProductDao productDao = new ProductDao();

			productDao.setConnection(conn);

			ArrayList<ProductDto> productList = null;
			productList = (ArrayList<ProductDto>) productDao.searchList(queryStr);

			req.setAttribute("productList", productList);

			RequestDispatcher rd = req.getRequestDispatcher("/page/admin/product/ProductQueryView.jsp");

			rd.forward(req, res);

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();

			RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");

			rd.forward(req, res);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
