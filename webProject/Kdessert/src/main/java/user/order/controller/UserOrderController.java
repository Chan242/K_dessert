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

/**
 * Servlet implementation class UserOrderController
 */
@WebServlet("/page/member/mypage/order")
public class UserOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserOrderController() {
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
		RequestDispatcher rd = null;

		try {
			ServletContext sc = this.getServletContext();
			
			HttpSession session = req.getSession();
			MemberDto memberDto = new MemberDto();
			MemberDto pointInfo = new MemberDto();
			MemberDao memberDao = new MemberDao();
			MemberDto orderInfo = new MemberDto();

			memberDto = (MemberDto) session.getAttribute("member");

			int memIndexInt = memberDto.getMemIndexInt();

			conn = (Connection) sc.getAttribute("conn");

			BasketDao basketDao = new BasketDao();


			ArrayList<BasketDto> basketList = null;
			
			basketDao.setConnection(conn);
			
			basketList = (ArrayList<BasketDto>) basketDao.basketList(memIndexInt);

			memberDao.setConnection(conn);

			orderInfo = memberDao.memberSelectOne(memIndexInt);

			pointInfo = memberDao.memberPointGet(memIndexInt);

			req.setAttribute("orderInfo", orderInfo);
			req.setAttribute("pointInfo", pointInfo);
			req.setAttribute("basketList", basketList);

			rd = req.getRequestDispatcher("/page/member/mypage/order/MypageOrderView.jsp");

			rd.forward(req, res);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			req.setAttribute("error", e);
			rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
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
