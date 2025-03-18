package admin.dessert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DaquaDAO {
	private Connection connection;

	public void setConnection(Connection conn) {
		this.connection = conn;
	}
    
    // 다과 리스트 가져오기
    public List<Daqua> getAllDaqua() throws Exception {
        List<Daqua> list = new ArrayList<>();
        String sql = "SELECT * FROM dessert";
        
        

        try (
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Daqua daqua = new Daqua();
                daqua.setId(rs.getInt("id"));
                daqua.setName(rs.getString("name"));
                daqua.setDescription(rs.getString("description"));
                daqua.setPrice(rs.getDouble("price"));
                daqua.setImagUrl(rs.getString("image_url"));

                list.add(daqua);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list; // 수정된 부분
    
}

// 다과 추가하기
	public boolean inserDaqua(Daqua daqua) throws Exception {
	String sql = "INSERT INTO daqua (name, description, price, image_url) "
			+ "VALUES (?, ?, ?, ?)";
	try (
		PreparedStatement pstmt = connection.prepareStatement(sql)) {
		
		pstmt.setString(1, daqua.getName());
		pstmt.setString(2, daqua.getDescription());
		pstmt.setDouble(3, daqua.getPrice());
		pstmt.setString(4, daqua.getImagUrl());
		
		int rowsAffected = pstmt.executeUpdate();
		return rowsAffected > 0;
		
	}
		
}
	// 다과 정보 수정하기
	public boolean updateDaqua(Daqua daqua) throws Exception {
	String sql = "UPDATE daqua SET name=?, description=?, price=?, image_url=? WHERE id=?";	
		
	try (
		 PreparedStatement pstmt = connection.prepareStatement(sql)) {
		
		pstmt.setString(1, daqua.getName());
		pstmt.setString(2, daqua.getDescription());
		pstmt.setDouble(3, daqua.getPrice());
		pstmt.setString(4, daqua.getImagUrl());
		pstmt.setInt(5, daqua.getID());
		
		int rowsAffected = pstmt.executeUpdate();
		return rowsAffected > 0;
		
	}	
}
	
	// 다과 삭제하기
	public boolean deletDaqua(int id) throws Exception {
	String sql = "DELETE FROM daqua WHERE id=?";
	
	try (
		PreparedStatement pstmt = connection.prepareStatement(sql)) {
		
		pstmt.setInt(1, id);
		int rowsAffected = pstmt.executeUpdate();
		return rowsAffected > 0;
	  }
	}
}