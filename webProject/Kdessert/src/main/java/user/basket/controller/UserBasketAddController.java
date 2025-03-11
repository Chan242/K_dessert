package user.basket.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import oracle.net.aso.e;
import user.basket.BasketDao;
import user.basket.BasketDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import admin.member.MemberDto;

/**
 * Servlet implementation class UserBasketController
 */
@WebServlet("/basket")
public class UserBasketAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserBasketAddController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int result = 0;

		boolean productIsExist = false;

		Connection conn = null;

		// session.setAttribute("memberDto", memberDto);

		try {

			HttpSession session = req.getSession();
			MemberDto memberDto = new MemberDto();

			memberDto = (MemberDto) session.getAttribute("member");

			int memIndexInt = memberDto.getMemIndexInt();

			BasketDto basketDto = new BasketDto();
			basketDto.setMemIndexInt(memIndexInt);

			int proIndexNum = Integer.parseInt(req.getParameter("proIndex"));
			basketDto.setProIndexInt(proIndexNum);

			int basStockInt = Integer.parseInt(req.getParameter("basStock"));
			basketDto.setBasStockInt(basStockInt);

			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			BasketDao basketdao = new BasketDao();
			basketdao.setConnection(conn);

			ArrayList<BasketDto> basketList = new ArrayList<BasketDto>();

			basketList = basketdao.basketList(memIndexInt);

			for (int i = 0; i < basketList.size(); i++) {
				if (basketList.get(i).getProIndexInt() == basketDto.getProIndexInt()) {
					basketDto.setBasStockInt(basketList.get(i).getBasStockInt() + basketDto.getBasStockInt());
					productIsExist = true;
					break;
				}

			}
			if (basketDto.getBasStockInt() > Integer.parseInt(req.getParameter("maxStock"))) {
				res.setContentType("text/html;charset=UTF-8");
//				res.getWriter().println("장바구니에 있는 재고가 주문할 수 있는 양을 넘어섰습니다.");
				PrintWriter out = res.getWriter();
				out.println("<script>");
				out.println("alert('장바구니에 있는 재고가 주문할 수 있는 양을 넘어섰습니다.');");
				out.println("history.back();");
				out.println("</script>");
				return;

			}

			if (productIsExist == false) {

				result = basketdao.addProduct(basketDto);

			} else if (productIsExist == true) {

				result = basketdao.updateProduct(basketDto);
			}

			if (result != 1) {
				RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");

				rd.forward(req, res);

			}
			
            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('장바구니에 수량이 담겼습니다.');");
            out.println("window.location.href = document.referrer;");
            out.println("</script>");
			

		} 	catch (NullPointerException ne) {
			// TODO: handle exception
			ne.printStackTrace();
			
		    res.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = res.getWriter();
		    out.println("<script>alert('로그인이 필요합니다.'); location.href='/Kdessert/auth/login';</script>");
		    out.close();
			
		}
			catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");

			rd.forward(req, res);

		}

	}

}
