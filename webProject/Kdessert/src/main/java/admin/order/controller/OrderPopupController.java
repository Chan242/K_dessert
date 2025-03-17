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

import admin.order.OrderDao;

/**
 * Servlet implementation class OrderPopupController
 */
@WebServlet("/admin/order/popup")
public class OrderPopupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPopupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		res.getWriter().write("wrong offer.sir.");
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // 요청 파라미터 받아오기
		
		Connection conn = null;
	    int no = Integer.parseInt(req.getParameter("no"));
	    String status = req.getParameter("status");
	    int result = 0;

	    try {
		    ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

		    OrderDao orderDao = new OrderDao();
		    
		    orderDao.setConnection(conn);
		    
		    result = orderDao.updateStat(no, status); // 상태 업데이트

		    // 요청 처리 결과에 따라 메시지 설정
		    String message = "";
		    if (result != 1) {
		        message = "요청에 실패하였습니다. 다시 확인해 주세요.";
		    } else {
		        message = "요청이 성공하였습니다.";
		    }

		    // 메시지를 request 객체에 저장
		    req.setAttribute("message", message);

		    // 팝업 페이지로 포워딩
		    RequestDispatcher rd = req.getRequestDispatcher("/page/admin/order/OrderPopupView.jsp");
		    rd.forward(req, res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}


}
