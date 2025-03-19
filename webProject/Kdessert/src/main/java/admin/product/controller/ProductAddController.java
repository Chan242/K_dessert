package admin.product.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import admin.product.ProductDao;
import admin.product.ProductDto;

/**
 * Servlet implementation class ProductAddController
 */
@MultipartConfig
@WebServlet("/admin/product/add")
public class ProductAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "image";

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
	    System.out.println("doPost 수행함");

	    Connection conn = null;

	    // 파일 업로드 처리
	    String fileName = null;
	    Part filePart = req.getPart("proImage");  // 'proImage'는 HTML 폼에서 전송되는 파일 파라미터 이름입니다.

	    if (filePart != null) {
	        // 파일이 존재하면 파일 이름을 추출하고 저장 경로 설정
	        fileName = filePart.getSubmittedFileName();

	        // 웹 애플리케이션의 실제 경로를 가져옵니다.
	        String filePath = "C:\\semiPj\\K_dessert\\webProject\\Kdessert\\src\\main\\webapp\\image\\"+ fileName;
//	        String appPath = req.getServletContext().getRealPath("/");  // 애플리케이션 루트 경로
//	        String filePath = appPath + "image" + File.separator + fileName;  // 이미지 폴더에 파일 저장
	        System.out.println("파일 경로: " + filePath);  // 경로 확인

	        // 파일명 중복 검사
	        File fileToCheck = new File(filePath);
	        if (fileToCheck.exists()) {
	            // 파일명이 중복되면 업로드 취소하고 오류 메시지 반환
	        	
	            res.sendRedirect("./error.jsp");
	            return;  // 업로드 취소
	        }

	        // 파일을 서버에 저장
	        filePart.write(filePath);
	        System.out.println("파일 업로드 성공: " + filePath);
	    }

	    // 나머지 요청 파라미터 처리
	    String proNameStr = req.getParameter("proName");
	    int proPriceInt = Integer.parseInt(req.getParameter("proPrice"));
	    int proStockInt = Integer.parseInt(req.getParameter("proStock"));
	    int proOpenInt = Integer.parseInt(req.getParameter("proOpen"));
	    String proIntroStr = req.getParameter("proIntro");
	    String[] tagArray = req.getParameter("tag").split(",");

	    ProductDto productDto = new ProductDto();
	    productDto.setProNameStr(proNameStr);
	    productDto.setProPriceInt(proPriceInt);
	    productDto.setProStockInt(proStockInt);
	    productDto.setProOpenInt(proOpenInt);
	    productDto.setProIntroStr(proIntroStr);
	    productDto.setProImageStr(fileName);

	    // 업로드된 파일의 경로를 ProductDto에 추가
	    if (fileName != null) {
	        productDto.setProImageStr(fileName);  // 이미지 파일 경로를 저장
	        System.out.println("업로드된 파일 경로: " + productDto.getProImageStr());
	    }

	    try {
	        ServletContext sc = this.getServletContext();
	        conn = (Connection) sc.getAttribute("conn");
	        ProductDao productDao = new ProductDao();
	        productDao.setConnection(conn);

	        // 제품 추가 처리
	        if (tagArray == null) {
	            result = productDao.productAdd(productDto);
	        } else if (tagArray != null) {
	            result = productDao.productAdd(productDto, tagArray);
	        }

	        if (result == 0) {
	            System.out.println("제품 입력 실패");
	            res.sendRedirect("./error.jsp");
	        }

	        res.sendRedirect("/Kdessert/admin/product/list?no=1");

	    } catch (Exception e) {
	        e.printStackTrace();
	        res.sendRedirect("./error.jsp");
	    }
	}


}
