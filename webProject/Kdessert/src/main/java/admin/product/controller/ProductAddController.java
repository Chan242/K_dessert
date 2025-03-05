package admin.product.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

import admin.product.ProductDao;
import admin.product.ProductDto;

/**
 * Servlet implementation class ProductAddController
 */
@WebServlet("/admin/product/add")
public class ProductAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductAddController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher rd = req.getRequestDispatcher("/page/admin/product/ProductAddView.jsp");

		rd.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		int result = 0;

		// 클라이언트로부터 POST 요청을 처리하는 메소드

		System.out.println("doPost 수행함");
		// 요청이 수행됨을 콘솔에 출력

		Connection conn = null;
		// 데이터베이스 연결 객체 선언 (아직 초기화되지 않음)

		String proNameStr = req.getParameter("proName");
		// 요청으로부터 "proName" 파라미터 값을 가져와서 문자열로 저장

		int proPriceInt = Integer.parseInt(req.getParameter("proPrice"));
		// 요청으로부터 "proPrice" 파라미터 값을 가져와서 정수로 변환하여 저장

		int proStockInt = Integer.parseInt(req.getParameter("proStock"));
		// 요청으로부터 "proStock" 파라미터 값을 가져와서 정수로 변환하여 저장
				

		int proOpenInt = Integer.parseInt(req.getParameter("proOpen"));
		
		String proIntroStr = req.getParameter("proIntro");


		ProductDto productDto = new ProductDto();

		productDto.setproNameStr(proNameStr);
		productDto.setproPriceInt(proPriceInt);
		productDto.setproStockInt(proStockInt);
		productDto.setproOpenInt(proOpenInt);
		
		productDto.setproIntroStr(proIntroStr);

		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			ProductDao productDao = new ProductDao();
			productDao.setConnection(conn);

			result = productDao.productAdd(productDto);

			if (result == 0) {

				System.out.println("제품 입력 실패");
				res.sendRedirect("./error.jsp");
			}

			res.sendRedirect("./list");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			res.sendRedirect("./error.jsp");
		}

	}

}
