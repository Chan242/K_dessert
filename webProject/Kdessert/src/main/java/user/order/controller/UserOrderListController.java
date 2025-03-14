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
import admin.order.status.OrderStatusDao;
import admin.order.status.OrderStatusDto;

/**
 * Servlet implementation class UserOrderListController
 */
@WebServlet("/page/member/mypage/orderlist")
public class UserOrderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOrderListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = req.getSession();
		MemberDto memberDto = new MemberDto();
		
		memberDto = (MemberDto) session.getAttribute("member");

		int memIndexInt = memberDto.getMemIndexInt();
		
		Connection conn = null;
		
		
		try {
			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			
			OrderDao orderDao = new OrderDao();
			OrderStatusDao orderStatusDao = new OrderStatusDao();
			
			ArrayList<OrderDto> orderList = null;
			ArrayList<OrderStatusDto> orderStatusList = null;
			
			orderDao.setConnection(conn);
			
			orderList = orderDao.userSelectList(memIndexInt);
			
			orderStatusDao.setConnection(conn);
			
			orderStatusList = orderStatusDao.orderStatusList();
			
			req.setAttribute("orderList", orderList);
			req.setAttribute("orderStatusList", orderStatusList);
			
			
			RequestDispatcher rd = req.getRequestDispatcher("/page/member/mypage/order/MypageOrderListView.jsp");
			
			rd.forward(req, res);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
