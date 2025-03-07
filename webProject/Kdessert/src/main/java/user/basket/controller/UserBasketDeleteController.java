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
import java.io.PrintWriter;
import java.sql.Connection;

import admin.member.MemberDto;

/**
 * Servlet implementation class UserBasketDelete
 */
@WebServlet("/page/member/mypage/basket/delete")
public class UserBasketDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBasketDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,res);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		int result = 0;
		Connection conn = null;

		
		try {
			
			HttpSession session = req.getSession();
			MemberDto memberDto = new MemberDto();
			
			memberDto = (MemberDto) session.getAttribute("member");
			
			int memIndexInt = memberDto.getMemIndexInt();
			int no = Integer.parseInt(req.getParameter("no"));
			
			BasketDto basketDto = new BasketDto();
			basketDto.setMemIndexInt(memIndexInt);
			basketDto.setProIndexInt(no);
			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			
			BasketDao basketDao = new BasketDao();
			basketDao.setConnection(conn);
			
			result = basketDao.deleteBasketItem(basketDto);
			
			if(result == 1) {
		        res.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = res.getWriter();
		        out.println("<script>");
		        out.println("alert('제품이 삭제되었습니다.');");
		        out.println("window.location.href = document.referrer;");
		        out.println("</script>");
			}else if(result == 0){
		        res.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = res.getWriter();
		        out.println("<script>");
		        out.println("alert('제품이 없습니다');");
		        out.println("window.location.href = document.referrer;");
		        out.println("</script>");
			}else {
		        res.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = res.getWriter();
		        out.println("<script>");
		        out.println("alert('오류가 발생했습니다.');");
		        out.println("window.location.href = document.referrer;");
		        out.println("</script>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");

			rd.forward(req, res);
		}
		

		
		
	}

}
