package product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Servlet implementation class PruductUpdateControllere
 */
@WebServlet("/admin/product/update")
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		RequestDispatcher rd = null;

		String proIndex = "";
		
		try {
			proIndex = req.getParameter("no");
			int no = Integer.parseInt(proIndex);

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			ProductDao productDao = new ProductDao();
			productDao.setConnection(conn);
			
			ProductDto productDto = productDao.selectOne(no);//selectOne작성중
			
			if (productDto == null) {
				throw new Exception("잘못된 제품번호입니다.");
			}
			
			req.setAttribute("productDto", productDto);
			rd = req.getRequestDispatcher("../../page/admin/product/ProductUpdateForm.jsp");
			rd.forward(req, res);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ProductDto productDto = null;
		
		Connection conn = null;
		
		try {
			
			int proIndexInt = Integer.parseInt(req.getParameter("proIndex"));
			String proNameStr = req.getParameter("proName");
			int proPriceInt = Integer.parseInt(req.getParameter("proPrice"));
			int proStockInt = Integer.parseInt(req.getParameter("proStock"));
			int proOpenInt = Integer.parseInt(req.getParameter("proOpen"));
			String proIntroStr = req.getParameter("proIntro");
			

			
			productDto = new ProductDto();
			
			productDto.setproIndexInt(proIndexInt);
			productDto.setproNameStr(proNameStr);
			productDto.setproPriceInt(proPriceInt);
			productDto.setproStockInt(proStockInt);
			productDto.setproOpenInt(proOpenInt);
			productDto.setproIntroStr(proIntroStr);

			
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			ProductDao productDao = new ProductDao();
			productDao.setConnection(conn);
			
			int result = productDao.productUpdate(productDto);

			if(result == 0){
				System.out.println("회원 정보 조회가 실패하였습니다.");
			}
			
			res.sendRedirect("./list");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}
		
		
		
		
		
		
		
		
	}

}
