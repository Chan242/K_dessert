package admin.order.controller;

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

import admin.order.OrderDao;
import admin.order.OrderDto;
import admin.order.OrderProductDto;

/**
 * Servlet implementation class OrderDetailController
 */
@WebServlet("/admin/order/detail")
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderDetailController() {
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
		int no = Integer.parseInt(req.getParameter("no"));

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			OrderDao orderDao = new OrderDao();

			OrderDto orderDto = null;
			ArrayList<OrderProductDto> orderProductList = null;

			orderDao.setConnection(conn);
			orderDto = orderDao.selectOne(no);
			orderProductList = orderDao.orderProductList(no);
			
//			orderProductDto = orderDao.orderDetail()

			req.setAttribute("orderDto", orderDto);
			req.setAttribute("orderProductList", orderProductList);
			
			
			
			

			RequestDispatcher rd = req.getRequestDispatcher("/page/admin/order/OrderDetailView.jsp");

			rd.forward(req, res);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
