package admin.dessert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaquaDAO {
	private static final String JDBC_URL = 
	"jdbc:mysql://localhost:3306/snack_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "1234";
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Daqua> getAllDaqua() {
      List<Daqua> list = new ArrayList<>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
    	   	
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection
            		(JDBC_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT id, name, price FROM daqua";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Daqua d = new Daqua();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setCategory(rs.getString("category"));
                d.setStock(rs.getInt("stock"));
                list.add(d);
            }
               
        } catch (Exception e) {
            e.printStackTrace();
        
    }finally {
    	//자원 정리
    	try { if (rs != null) rs.close(); } 
    	catch (SQLException e) { e.printStackTrace();}
    	try { if (pstmt != null) pstmt.close(); }
    	catch (SQLException e) {e.printStackTrace();}
    	try { if(conn != null) conn.close(); }
    	catch (SQLException e) {e.printStackTrace();}
    }
        
        List<Daqua> daqualist = null;
		return daqualist;
    }
}
