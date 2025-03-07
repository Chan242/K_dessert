package user.basket.controller;

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

import admin.member.MemberDto;
import admin.product.ProductDao;
import admin.product.ProductDto;

/**
 * Servlet implementation class UserBasketListController
 */
@WebServlet("/page/member/mypage/basket")
public class UserBasketListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBasketListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
			HttpSession session = req.getSession();
			MemberDto memberDto = new MemberDto();

			memberDto = (MemberDto) session.getAttribute("member");

			int memIndexInt = memberDto.getMemIndexInt();
			
			conn = (Connection)sc.getAttribute("conn");
			
			BasketDao basketDao = new BasketDao();
			
			basketDao.setConnection(conn);
			
			ArrayList<BasketDto> basketList = null;
			basketList = (ArrayList<BasketDto>)basketDao.basketList(memIndexInt);
			
			req.setAttribute("basketList", basketList);
			
			RequestDispatcher rd = req.getRequestDispatcher("./MypageBasketView.jsp");
			
			rd.forward(req, res);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();

			RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");

			rd.forward(req, res);
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
