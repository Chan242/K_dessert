package product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher rd = req.getRequestDispatcher("/page/admin/product/ProductAddView.jsp");
		
		rd.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // 클라이언트로부터 POST 요청을 처리하는 메소드
	    
	    System.out.println("doPost 수행함");
	    // 요청이 수행됨을 콘솔에 출력
	    
	    Connection conn = null;
	    // 데이터베이스 연결 객체 선언 (아직 초기화되지 않음)
	    
	    String pNameStr = req.getParameter("pName");
	    // 요청으로부터 "pName" 파라미터 값을 가져와서 문자열로 저장
	    
	    int pPriceInt = Integer.parseInt(req.getParameter("pPrice"));
	    // 요청으로부터 "pPrice" 파라미터 값을 가져와서 정수로 변환하여 저장
	    
	    int pStockInt = Integer.parseInt(req.getParameter("pStock"));
	    // 요청으로부터 "pStock" 파라미터 값을 가져와서 정수로 변환하여 저장
	    
	    int pOpenInt = (req.getParameter("pOpen").equals("공개")) ? 0 : 1;
	    // 요청으로부터 "pOpen" 파라미터 값을 가져와서 "공개"이면 0, 그렇지 않으면 1로 설정
	}


}
