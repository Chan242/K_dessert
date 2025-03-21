package admin.dessert;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/page/admin/RegisterDessert")
@MultipartConfig
public class RegisterDessertServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // DB 연결 정보 (MySQL)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kdessert?useSSL=false&serverTimezone=Asia/Seoul"; // 서버의 시간대에 맞추어 수정
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 신규등록 폼을 보여주는 페이지로 포워딩
        RequestDispatcher dispatcher = req.getRequestDispatcher("/page/admin/product/ProductListView.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 데이터 인코딩 설정
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        // 폼 데이터 받기
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String publicStatus = req.getParameter("public");
        String tag = req.getParameter("tag");

        // 이미지 파일 처리
        Part filePart = req.getPart("image");
        String fileName = extractFileName(filePart);

        // 업로드 디렉토리 설정 (절대경로 사용)
        String uploadDirPath = getServletContext().getRealPath("/") + "uploads";
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) uploadDir.mkdir();  // 폴더가 없으면 생성

        // 파일 저장
        if (fileName != null && !fileName.isEmpty()) {
            filePart.write(uploadDirPath + File.separator + fileName);
        } else {
            fileName = "default.png"; // 파일이 없으면 기본 이미지 설정
        }

        // DB 연결 및 데이터 삽입
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // MySQL 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로드

            // DB 연결
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL 쿼리 실행
            String sql = "INSERT INTO desserts (name, description, image, public_status, tag) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, desc);
            pstmt.setString(3, fileName);
            pstmt.setString(4, publicStatus);
            pstmt.setString(5, tag);

            // 실행 후 결과 처리
            int result = pstmt.executeUpdate();
            if (result > 0) {
                // 성공 시 다과 리스트 페이지로 리다이렉트
                res.sendRedirect("/Kdessert/page/admin/RegisterDessertServlet");
            } else {
                // 실패 시 에러 메시지 출력
                res.getWriter().println("등록 실패. 다시 시도하세요.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("오류 발생: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 파일명 추출 메서드
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "default.png"; // 파일명 없으면 기본 이미지
    }
}
