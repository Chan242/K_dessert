package user.order.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import admin.member.MemberDto;
import admin.order.OrderDao;
import admin.order.OrderDto;

/**
 * Servlet implementation class UserOrderSucessController
 */
@WebServlet("/page/member/mypage/order/sucess")
public class UserOrderSucessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserOrderSucessController() {
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
			MemberDto memberDto = new MemberDto();// 빈 객체 생성
			memberDto = (MemberDto) session.getAttribute("member");// 객체에 정보 담음
			System.out.println(memberDto.getMemNameStr());// 멤버 이름 확인

			int memIndexInt = memberDto.getMemIndexInt();// 멤버 인덱스 확인
			
			member

			OrderDao orderDao = new OrderDao();
			
			orderDao.setConnection(conn);

			

			result = orderDao.orderProcess(memIndexInt);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
