<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>다과 리스트</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: center; }
        th { background-color: #f4a261; color: white; }
    </style>
</head>
<body>

<h2>🍪 다과 리스트 🍩</h2>

<table>
    <tr>
        <th>번호</th>
        <th>이름</th>
        <th>가격</th>
    </tr>
    
    <%
        // DB 연결 정보
        String jdbcUrl = "jdbc:mysql://localhost:3306/snack_db";
        String dbUser = "root";
        String dbPassword = "1234";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            String sql = "SELECT id, name, price FROM daqua";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getInt("price") %>원</td>
    </tr>
    <%
            }
        } catch (Exception e) {
            out.println("<tr><td colspan='3'>데이터를 불러오는 중 오류 발생</td></tr>");
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
    %>
</table>

</body>
</html>
