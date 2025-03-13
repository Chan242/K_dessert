package user.order.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.basket.BasketDao;
import user.basket.BasketDto;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import admin.member.MemberDao;
import admin.member.MemberDto;
import admin.order.OrderDao;
import admin.order.OrderDto;

/**
 * Servlet implementation class UserOrderSucessController
 */
@WebServlet("/page/member/mypage/order/success")
public class UserOrderSuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserOrderSuccessController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection conn = null;

		try {

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			int result = 0;

			HttpSession session = req.getSession();// 세션정보획득
			MemberDto sessionMemberDto = new MemberDto();// 빈 객체 생성
			sessionMemberDto = (MemberDto) session.getAttribute("member");// 객체에 정보 담음
			System.out.println(sessionMemberDto.getMemNameStr());// 멤버 이름 확인

			int memIndexInt = sessionMemberDto.getMemIndexInt();
			
			String recipientStr = req.getParameter("recipient");
			String addressOneStr = req.getParameter("addressOne");
			String addressTwoStr = req.getParameter("addressTwo");
			String telStr = req.getParameter("tel").toString();
			int totalPriceInt = Integer.parseInt(req.getParameter("total"));
			
			OrderDto orderDto = new OrderDto();
			
			orderDto.setMemIndexInt(memIndexInt);
			orderDto.setMemNameStr(recipientStr);
			orderDto.setMemAdd1Str(addressOneStr);
			orderDto.setMemAdd2Str(addressTwoStr);
			orderDto.setMemTelStr(telStr);
			orderDto.setTotalPriceInt(totalPriceInt);
			
			BasketDao basketDao = new BasketDao();
			
			basketDao.setConnection(conn);
			
			ArrayList<BasketDto> basketList = (ArrayList<BasketDto>)basketDao.basketList(memIndexInt);
			
			System.out.println("장바구니리스트는 비었는가?: " + basketList.isEmpty());
			
			OrderDao orderDao = new OrderDao();
			
			orderDao.setConnection(conn);
			
			result = orderDao.orderProcess(orderDto, basketList);
			
			RequestDispatcher rd = req.getRequestDispatcher("/page/member/mypage/order/MypageOrderSuccessView.jsp");
			
			rd.forward(req, res);
			
			
			


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

}
