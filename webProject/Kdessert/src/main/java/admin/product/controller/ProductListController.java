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
import java.util.ArrayList;

import admin.product.ProductDao;
import admin.product.ProductDto;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/admin/product/list")
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		
		
		try {
			ServletContext sc = this.getServletContext();
			
			conn = (Connection)sc.getAttribute("conn");
			
			int productCountInt = 0;
			int divRowInt = 5;//한 화면에 보여질 컬럼 개수
			ProductDao productDao = new ProductDao();
			
			productDao.setConnection(conn);
			
			productCountInt = productDao.productCount();
			
			ArrayList<ProductDto> productList = null;
			productList = (ArrayList<ProductDto>)productDao.selectList(no,divRowInt);
			
			
			
			int totalPageInt = (int) Math.ceil(productCountInt / (divRowInt*1.0));//총 페이지
			
			
			int start = ((no-1)/5*5)+1;
			System.out.println("start: " + start);
			int end = start+4;
			System.out.println("end: " + end);
			int maxEnd = end > totalPageInt ? totalPageInt : end;
			System.out.println("maxEnd: " + maxEnd);
			
			req.setAttribute("start", start);
			req.setAttribute("maxEnd", maxEnd);
			req.setAttribute("productList", productList);
			req.setAttribute("no", no);//현재 페이지
			req.setAttribute("totalPageInt", totalPageInt);
			
			
			
			
			
			
			RequestDispatcher rd = req.getRequestDispatcher("/page/admin/product/ProductListView.jsp");
			
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
