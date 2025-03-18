package admin.order.controller;

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
import admin.product.ProductDao;
import admin.product.ProductDto;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/admin/order/list")
public class OrderListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		int divRowInt = 5;//한 화면에 보여질 컬럼 개수
		String filter = req.getParameter("filter");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		try {
			ServletContext sc = this.getServletContext();
						
			conn = (Connection)sc.getAttribute("conn");
			
			OrderDao orderDao = new OrderDao();
			
			
			int orderCountInt = 0;
			ArrayList<OrderDto> orderList = null;
			
			int totalPageInt = 0;
			
			orderDao.setConnection(conn);
			
			if(filter==null||filter == ""||filter.equals("all")) {
				orderList = orderDao.selectList(no,divRowInt);
				orderCountInt = orderDao.orderCount();
				totalPageInt = (int) Math.ceil(orderCountInt / (divRowInt*1.0));//총 페이지
				
			}else {
				orderList = orderDao.selectList(no,divRowInt,filter);
				orderCountInt = orderDao.orderCount(filter);
				totalPageInt = (int) Math.ceil(orderCountInt / (divRowInt*1.0));//총 페이지
			}
			

			
			
			
			
			int start = ((no-1)/5*5)+1;
			System.out.println("start: " + start);
			int end = start+4;
			System.out.println("end: " + end);
			int maxEnd = end > totalPageInt ? totalPageInt : end;
			System.out.println("maxEnd: " + maxEnd);
			req.setAttribute("totalPageInt", totalPageInt);//총 페이지 수
			req.setAttribute("orderList", orderList);//주문 실 목록
			
			ArrayList<OrderStatusDto> orderStatusList = null;
			OrderStatusDao orderStatusDao = new OrderStatusDao();
			orderStatusDao.setConnection(conn);
			orderStatusList = orderStatusDao.orderStatusList();
			
			
			req.setAttribute("no", no);//현재 페이지
			req.setAttribute("start", start);
			req.setAttribute("maxEnd", maxEnd);
			req.setAttribute("orderStatusList", orderStatusList);//주문 상태
			req.setAttribute("filter", filter);
			
			RequestDispatcher rd = req.getRequestDispatcher("/page/admin/order/OrderListView.jsp");
			
			rd.forward(req, res);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
