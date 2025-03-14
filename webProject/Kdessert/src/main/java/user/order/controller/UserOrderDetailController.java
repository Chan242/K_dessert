package user.order.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import admin.member.MemberDto;
import admin.order.OrderDao;
import admin.order.OrderDto;
import admin.order.OrderProductDto;

/**
 * Servlet implementation class UserOrderDetailController
 */
@WebServlet("/page/member/mypage/order/detail")
public class UserOrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserOrderDetailController() {
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

		try {

			HttpSession session = req.getSession();
			MemberDto memberDto = (MemberDto) session.getAttribute("member");

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			int memIndexInt = memberDto.getMemIndexInt();
			int ordIndexInt = Integer.parseInt(req.getParameter("no"));
			
			System.out.println(memIndexInt);
			System.out.println(ordIndexInt);

			OrderDto orderDto = new OrderDto();
			OrderDao orderDao = new OrderDao();

			orderDao.setConnection(conn);

			orderDto = orderDao.selectOne(ordIndexInt);

			if (memIndexInt == orderDto.getMemIndexInt()) {

				ArrayList<OrderProductDto> orderProductList = null;

				orderProductList = orderDao.orderProductList(ordIndexInt);

				req.setAttribute("orderDto", orderDto);
				req.setAttribute("orderProductList", orderProductList);

				RequestDispatcher rd = req.getRequestDispatcher("/page/member/mypage/order/MypageOrderDetailView.jsp");
				rd.forward(req, res);

			} else if (memIndexInt != orderDto.getMemIndexInt()) {
				RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
				rd.forward(req, res);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
