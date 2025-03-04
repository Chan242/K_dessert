package member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {

	private Connection connection;
	
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	//로그인
	public MemberDto memberExist(String id, String pwd)
		throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		sql += "SELECT M_NAME";
		sql += " FROM MEMBER";
		sql += " WHERE M_ID = ? AND M_PASSWORD = ?";

		String name = "";

		try {
			pstmt = connection.prepareStatement(sql);
			
			int colIndex = 1;
			pstmt.setString(colIndex++, id);
			pstmt.setString(colIndex, pwd);

			rs = pstmt.executeQuery();
			
			MemberDto memberDto = new MemberDto();
			
			if (rs.next()) {
				
				//확인된 회원의 이름
				name = rs.getString("M_NAME");
				memberDto.setMemNameStr(name);
				
				// 회원 정보 조회 확인됨
				return memberDto;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // finally 종료

		// 회원이 조회가 안된다면
		return null;
	}
	
	// 회원 등록
	public int memberSignUp(MemberDto memberDto) throws Exception{
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String id = "";
		String pwd = "";
		String name = "";
		String tel = "";
		String email = "";
		String address = "";
		String addressSec = "";
		Date birth = null; 
		
		String sql = "";
		sql += "INSERT INTO MEMBER";
		sql += " (M_INDEX, M_ID, M_PASSWORD, M_NAME, M_TEL,";
		sql += " M_EMAIL, M_ADDRESS, M_ADDRESS_SEC, M_BIRTH,";
		sql += " M_SIGN_TIME, M_POINT, M_NOTE)";
		sql += " VALUES(M_INDEX_SEQ.NEXTVAL, ?, ?, ?, ?,";
		sql += " ?, ?, ?, ?, SYSDATE, 0, '')";

		try {
			
			pstmt = connection.prepareStatement(sql);

			//회원가입에서 입력된 정보
			id = memberDto.getMemIdStr();
			pwd = memberDto.getMemPasswordStr();
			name = memberDto.getMemNameStr();
			tel = memberDto.getMemTelStr();
			email = memberDto.getMemEmailStr();
			address = memberDto.getMemAddressStr();
			addressSec = memberDto.getMemAddressSecStr();
			birth = memberDto.getMemBirthDate();
			
			//를 ?에 집어넣음
			int colIndex = 1;
			pstmt.setString(colIndex++, id);
			pstmt.setString(colIndex++, pwd);
			pstmt.setString(colIndex++, name);
			pstmt.setString(colIndex++, tel);
			pstmt.setString(colIndex++, email);
			pstmt.setString(colIndex++, address);
			pstmt.setString(colIndex++, addressSec);
			pstmt.setDate(colIndex, birth);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} // finally 종료
		
		return result;
	}
	
	public MemberDto findId(String name, String email) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String id = "";
		
		String sql = "";
		sql += "SELECT M_ID";
		sql += " FROM MEMBER";
		sql += " WHERE M_NAME = ? AND M_EMAIL = ?";

		try {
			
			pstmt = connection.prepareStatement(sql);
			
			int colIndex = 1;
			pstmt.setString(colIndex++, name);
			pstmt.setString(colIndex, email);

			rs = pstmt.executeQuery();
			
			MemberDto memberDto = new MemberDto();
			
			if (rs.next()) {
				
				//확인된 회원의 아이디
				id = rs.getString("M_ID");
				memberDto.setMemIdStr(id);
				
				// 회원 정보 조회 확인됨
				return memberDto;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // finally 종료

		return null;
		
	}
		
		
}
