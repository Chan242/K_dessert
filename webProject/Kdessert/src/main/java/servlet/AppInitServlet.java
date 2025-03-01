package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet{
// 기능 분리
   @Override
   public void init(ServletConfig config) throws ServletException {
      // TODO Auto-generated method stub
      System.out.println("AppInitServlet 준비...");
      super.init(config);
      
      ServletContext sc = this.getServletContext();
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      
      String driver = "";
      String url = "";
      String user = "";
      String password = "";
      
//      JDBC 드라이버 등록
      try {
         
         driver = sc.getInitParameter("driver");
         url = sc.getInitParameter("url");
         user = sc.getInitParameter("user");
         password = sc.getInitParameter("password");
         

         
         Class.forName(driver);
//         DB 연결
         conn = DriverManager.getConnection(url, user, password);
//         sql 실행객체 준비

         
         

         
//         throw new Exception();
         sc.setAttribute("conn", conn);
         System.out.println("DB 연결 성공");
      } catch (ClassNotFoundException e) {
         System.out.println("JDBC API 오류 발생");
         e.printStackTrace();
         // TODO Auto-generated catch block
//         throw new ServletException(e);
      
         
         
      }catch (SQLException e) {
      // TODO: handle exception
         System.out.println("DB연결 오류 발생");
         e.printStackTrace();
   }
      
  }  
   
   @Override
   public void destroy() {
      // TODO Auto-generated method stub
      System.out.println("AppInitServlet 마무리...");
      super.destroy();
      
      ServletContext sc = this.getServletContext();
            
   
       try {
          
          Connection conn = (Connection)sc.getAttribute("conn");
          
        if(conn != null) {
          conn.close();
          System.out.println("DB 연결 해제");
        }
       } catch (SQLException e) {
          // TODO: handle exception
          e.printStackTrace();
       }
    }
 }

