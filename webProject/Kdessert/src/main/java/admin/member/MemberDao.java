package admin.member;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

	private Connection connection;
	
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	// 로그인
	public MemberDto memberExist(String id, String pwd)
		throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		sql += "SELECT M_NAME, M_INDEX, M_ID, M_ADM_CHECK";
		sql += " FROM MEMBER";
		sql += " WHERE M_ID = ? AND M_PASSWORD = ?";

		String name = "";
		int index = 0;
		int admCheck = 0;

		try {
			pstmt = connection.prepareStatement(sql);
			
			int colIndex = 1;
			pstmt.setString(colIndex++, id);
			pstmt.setString(colIndex, pwd);

			rs = pstmt.executeQuery();
			
			MemberDto memberDto = new MemberDto();
			
			if (rs.next()) {
				
				//확인된 회원의 정보들
				name = rs.getString("M_NAME");
				memberDto.setMemNameStr(name);
				
				index = rs.getInt("M_INDEX");
				memberDto.setMemIndexInt(index);
				
				admCheck = rs.getInt("M_ADM_CHECK");
				memberDto.setMemAdmCheckInt(admCheck);
				
				id = rs.getString("M_ID");
				memberDto.setMemIdStr(id);
				
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
		java.sql.Date birth = null; 
		
		String sql = "";
		sql += "INSERT INTO MEMBER";
		sql += " (M_INDEX, M_ID, M_PASSWORD, M_NAME, M_TEL,";
		sql += " M_EMAIL, M_ADDRESS, M_ADDRESS_SEC, M_BIRTH,";
		sql += " M_SIGN_TIME, M_NOTE)";
		sql += " VALUES(M_INDEX_SEQ.NEXTVAL, ?, ?, ?, ?,";
		sql += " ?, ?, ?, ?, SYSDATE, '')";

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
			birth = (java.sql.Date) memberDto.getMemBirthDate();
			
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

	// email 중복 확인
	public boolean memberEmailCheck(String email) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT M_EMAIL";
		sql += " FROM MEMBER";
		sql += " WHERE M_EMAIL = ?";

		try {
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			
			//이메일 존재함
			if (rs.next()) {
				return true;
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

		return false;
		
	}
	
	// id 중복 확인
	public boolean memberIdCheck(String id) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT M_ID";
		sql += " FROM MEMBER";
		sql += " WHERE M_ID = ?";

		try {
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			
			//아이디 존재함
			if (rs.next()) {
				return true;
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

		return false;
	}
	
	// id 찾기
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
 	
 	
 	// pwd 발급
 	public int memberTempPwd(String id, String tempPwd) throws SQLException {
 		
 		int result = 0;

 		PreparedStatement pstmt = null;

 		String sql = "";
 		sql = "UPDATE MEMBER";
 		sql += " SET M_PASSWORD=?";
 		sql += " WHERE M_ID =?";
 		
 		try {
 			
 			pstmt = connection.prepareStatement(sql);

 			pstmt.setString(1, tempPwd);
 			pstmt.setString(2, id);

 			result = pstmt.executeUpdate(); // 이놈이 범인 .. 
 			
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			
 		} finally {
 			try {
 				if (pstmt != null) {
 					pstmt.close();
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}

 		} // finally 종료
 		return result;
 	}

 	// 회원 전체 조회에서 전체 데이터의 수를 가져오는 메소드
 	public int getTotalCount() {
 		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		String sql = "";
		
		sql += "SELECT COUNT(M_INDEX) FROM MEMBER";

		try {
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			totalCount = rs.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end
		return totalCount;
	}
 	
	// 전체 회원 조회 (페이징)	
	public List<MemberDto> selectList(int pageNum, int pageSize) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();
		
		String sql = "";
		
		//페이징 이전 sql
//		sql += "SELECT M_INDEX, M_NAME, M_ID, M_EMAIL, M_BIRTH, M_SIGN_TIME";
//		sql += " FROM (";
//		sql += " SELECT m.M_INDEX, m.M_NAME, m.M_ID, m.M_EMAIL, m.M_BIRTH, m.M_SIGN_TIME, ROWNUM rnum";
//		sql += " FROM MEMBER m";
//		sql += " WHERE ROWNUM <= ?)";
//		sql += " WHERE rnum >= ?";
//		sql += " ORDER BY M_INDEX DESC";
		
		//페이징 적용을 위한 sql
		sql += "SELECT * FROM (";
		sql += " SELECT A.*, ROWNUM rnum FROM (";
		sql += " SELECT m.M_INDEX, m.M_NAME, m.M_ID, m.M_EMAIL, m.M_BIRTH, m.M_SIGN_TIME";
		sql += " FROM MEMBER m ORDER BY m.M_INDEX DESC";
		sql += " ) A WHERE ROWNUM <= ?";
		sql += " ) WHERE rnum >= ?";
		
		//pageNum에 따라 startRow 부터 endRow 까지의 값을 보여준다
		int startRow = (pageNum - 1) * pageSize + 1; // 조회한 테이블에서 첫번째로 보여줄 행
		int endRow = pageNum * pageSize; // 조회한 테이블에서 마지막으로 보여줄 행
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, endRow);  // ROWNUM <= endRow 마지막 행보다 행보다 작거나 같을 때
			pstmt.setInt(2, startRow); // rnum >= startRow 첫번째 행보다 크거나 같을 때
			
			rs = pstmt.executeQuery();
			
			int index = 0;
			String name = "";
			String id = "";
			String email = "";
			Date birth = null;
			Date signTime = null;
			
			while (rs.next()) {
				
				index = rs.getInt("M_INDEX");
				name = rs.getString("M_NAME");
				id = rs.getString("M_ID");
				email = rs.getString("M_EMAIL");
				birth = rs.getDate("M_BIRTH");
				signTime = rs.getDate("M_SIGN_TIME");
				
				MemberDto memberDto = new MemberDto(index, name, id, email, birth, signTime);
				
				memberList.add(memberDto);
						
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end
		
		return memberList;
		
	}
	
	// 단일 회원 조회
	public MemberDto memberSelectOne(int no) {
		
		MemberDto memberDto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		sql += "SELECT M_INDEX, M_NAME, M_ID, M_EMAIL, M_BIRTH, M_TEL, ";
		sql += "M_ADDRESS, M_ADDRESS_SEC, M_SIGN_TIME, M_CORR_DATE, M_ADM_CHECK, M_NOTE";
		sql += " FROM MEMBER M ";
		sql += " WHERE M_INDEX =?";

		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			int index = 0;
			String name = "";
			String id = "";
			String email = "";
			Date birth = null;
			String tel = "";
			String address = "";
			String addressSec ="";
			Date signTime = null;
			Date corrDate = null;
			int admCheck = 0;
			String note = "";

			if (rs.next()) {
				index = rs.getInt("M_INDEX");
				name = rs.getString("M_NAME");
				id = rs.getString("M_ID");
				email = rs.getString("M_EMAIL");
				birth = rs.getDate("M_BIRTH");
				tel = rs.getString("M_TEL");
				address = rs.getString("M_ADDRESS");
				addressSec = rs.getString("M_ADDRESS_SEC");
				signTime = rs.getTimestamp("M_SIGN_TIME");
				corrDate = rs.getTimestamp("M_CORR_DATE");
				admCheck = rs.getInt("M_ADM_CHECK");
				note = rs.getString("M_NOTE");

				memberDto = new MemberDto();

				memberDto.setMemIndexInt(index);
				memberDto.setMemNameStr(name);
				memberDto.setMemIdStr(id);
				memberDto.setMemEmailStr(email);
				memberDto.setMemBirthDate(birth);
				memberDto.setMemTelStr(tel);
				memberDto.setMemAddressStr(address);
				memberDto.setMemAddressSecStr(addressSec);
				memberDto.setMemSignTimeDate(signTime);
				memberDto.setMemCorrDate(corrDate);
				memberDto.setMemAdmCheckInt(admCheck);
				memberDto.setMemNoteStr(note);
				
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료
		return memberDto;
	}
	
	//회원 검색 (페이징)
	public List<MemberDto> searchList(String searchText, int pageNum, int pageSize) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();
		
		String sql = "";
		
		String namePattern = "%" + searchText + "%";
		String idPattern = "%" + searchText + "%";
		
		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;
		
		try {
			
			if (searchText.matches("\\d+")) {  // 숫자만 포함된 경우
				int indexSearch = Integer.parseInt(searchText);
				
				sql += "SELECT * FROM (";
				sql += " SELECT A.*, ROWNUM rnum FROM (";
				sql += " SELECT m.M_INDEX, m.M_NAME, m.M_ID, m.M_EMAIL, m.M_BIRTH, m.M_SIGN_TIME";
				sql += " FROM MEMBER m";
				sql += " WHERE M_INDEX=? OR M_NAME LIKE ? OR M_ID LIKE ?";
				sql += " ORDER BY m.M_INDEX DESC";
				sql += " ) A WHERE ROWNUM <= ?";
				sql += " ) WHERE rnum >= ?";
				
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1, indexSearch);
				pstmt.setString(2, namePattern);
				pstmt.setString(3, idPattern);
				pstmt.setInt(4, endRow);
				pstmt.setInt(5, startRow);
				
			} else {
				
				sql += "SELECT * FROM (";
				sql += " SELECT A.*, ROWNUM rnum FROM (";
				sql += " SELECT m.M_INDEX, m.M_NAME, m.M_ID, m.M_EMAIL, m.M_BIRTH, m.M_SIGN_TIME";
				sql += " FROM MEMBER m";
				sql += " WHERE M_NAME LIKE ? OR M_ID LIKE ?";
				sql += " ORDER BY m.M_INDEX DESC";
				sql += " ) A WHERE ROWNUM <= ?";
				sql += " ) WHERE rnum >= ?";
				
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, namePattern);
				pstmt.setString(2, idPattern);
				pstmt.setInt(3, endRow);
				pstmt.setInt(4, startRow);
				
			}
			
			rs = pstmt.executeQuery();
			
			int index = 0;
			String name = "";
			String id = "";
			String email = "";
			Date birth = null;
			Date signTime = null;
			
			while (rs.next()) {
				index = rs.getInt("M_INDEX");
				name = rs.getString("M_NAME");
				id = rs.getString("M_ID");
				email = rs.getString("M_EMAIL");
				birth = rs.getDate("M_BIRTH");
				signTime = rs.getDate("M_SIGN_TIME");
				
				MemberDto memberDto = new MemberDto(index, name, id, email, birth, signTime);
				
				memberList.add(memberDto);
			}
			
			if(memberList.isEmpty()) {
				//리스트에 값이 존재하지 않음
				return null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end
		
		return memberList;
		
	}
	
	// 회원 검색 조회에서 전체 데이터의 수를 가져오는 메소드
	 	public int getSearchTotalCount(String searchText) {
	 		
	 		PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "";
			
			String namePattern = "%" + searchText + "%";
			String idPattern = "%" + searchText + "%";
	 		
			int totalCount = 0;

			try {
				
				if (searchText.matches("\\d+")) { // 숫자만 포함된 경우
					int indexSearch = Integer.parseInt(searchText);
					sql = "";
					sql += "SELECT COUNT(M_INDEX)";
					sql += " FROM MEMBER";
					sql += " WHERE M_INDEX=? OR M_NAME LIKE ? OR M_ID LIKE ?";
					
					pstmt = connection.prepareStatement(sql);
					
					pstmt.setInt(1, indexSearch);
					pstmt.setString(2, namePattern);
					pstmt.setString(3, idPattern);
					
				} else {
					sql = "";
					sql += "SELECT COUNT(M_INDEX)";
					sql += " FROM MEMBER";
					sql += " WHERE M_NAME LIKE ? OR M_ID LIKE ?";
					
					pstmt = connection.prepareStatement(sql);
					
					pstmt.setString(1, namePattern);
					pstmt.setString(2, idPattern);
					
				}
				
				rs = pstmt.executeQuery();
				
				rs.next();
				totalCount = rs.getInt(1);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) {
						rs.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				try {
					if(pstmt != null) {
						pstmt.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} // finally end
			return totalCount;
		}
	
	// 회원 정보 수정 /관리자용
	public int memberUpdate(MemberDto memberDto) throws SQLException {
		
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = "";
		sql = "UPDATE MEMBER";
		sql += " SET M_NOTE=?, M_CORR_DATE=SYSDATE, M_ADM_CHECK=?";
		sql += " WHERE M_INDEX =?";

		try {
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, memberDto.getMemNoteStr());
			pstmt.setInt(2, memberDto.getMemAdmCheckInt());
			pstmt.setInt(3, memberDto.getMemIndexInt());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료
		return result;
	}
	
	// 회원 정보 수정 /회원용
	public int memberInfoUpdate(MemberDto memberDto) throws SQLException {
		
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = "";
		sql = "UPDATE MEMBER";
		sql += " SET M_PASSWORD=?, M_EMAIL=?, M_TEL=?, M_ADDRESS=?, M_ADDRESS_SEC=?, M_name=?";
		sql += " WHERE M_INDEX =?";
		
		try {
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, memberDto.getMemPasswordStr());
			pstmt.setString(2, memberDto.getMemEmailStr());
			pstmt.setString(3, memberDto.getMemTelStr());
			pstmt.setString(4, memberDto.getMemAddressStr());
			pstmt.setString(5, memberDto.getMemAddressSecStr());
			pstmt.setString(6, memberDto.getMemNameStr());
			pstmt.setInt(7, memberDto.getMemIndexInt());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료
		return result;
	}
	
	// 회원 포인트 확인 메소드
	public MemberDto memberPointGet(int no) {
		
		MemberDto memberDto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		
		sql += "SELECT m.M_INDEX, po.m_point, po.m_index ";
		sql += " FROM member m inner join (select sum(point_point) m_point,m_index from Point group by m_index) po";
		sql += " ON m.m_index= po.m_index";
		sql += " WHERE m.M_index = ?";
		
		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			int point = 0;

			memberDto = new MemberDto();
			
			//포인트 충전 기록이 있을 때
			if (rs.next()) {
				point = rs.getInt("m_point");
				memberDto.setMemPointInt(point);
				
			} else { //포인트 충전 기록이 없을 때 POINT = 0
				memberDto = new MemberDto();
				memberDto.setMemPointInt(point);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료
		return memberDto;
		
	}
	
	
	// 회원 포인트 충전
	public int memberPointCharge(MemberDto memberDto) throws SQLException {
		
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = "";
		sql = "INSERT INTO POINT";
		sql += " (POINT_INDEX, M_INDEX, POINT_DATE, POINT_POINT) ";
		sql += " VALUES(POINT_INDEX_SEQ.NEXTVAL, ?, SYSDATE, ?)";
		
		
		try {
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, memberDto.getMemIndexInt());
			pstmt.setInt(2, memberDto.getMemPointInt());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // finally 종료
		return result;
		
	}
	
	//회원 포인트 내역 전체 데이터의 수를 가져오는 메소드
	public int getPointListTotalCount(int index) {
 		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		String sql = "";

		sql += "SELECT COUNT(POINT_POINT)";
		sql += " FROM POINT WHERE M_INDEX = ?";
	

		try {
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, index);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			totalCount = rs.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end
		return totalCount;
	}
 	
	
	// 회원 포인트 내역 리스트
	public List<MemberDto> memberPointHistory(int index, int pageNum, int pageSize)  {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<MemberDto> memberList = null;
		MemberDto memberDto = null;
		
		int point = 0;
		int balancePoint = 0;
		Date pointDate = null;

		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;
		
		String sql = "";
		
//		sql += "SELECT POINT_DATE, POINT_POINT";
//		sql += " FROM POINT WHERE M_INDEX = ? ORDER BY POINT_DATE DESC";
		
		sql += "SELECT * FROM (";
		sql += " SELECT A.*, ROWNUM rnum FROM (";
		sql += "  SELECT p.POINT_DATE, p.POINT_POINT, SUM(p.POINT_POINT) OVER (ORDER BY p.POINT_DATE) AS ACCUMULATED_POINT";
		sql += " FROM POINT p WHERE p.M_INDEX = ? ORDER BY p.POINT_DATE DESC";
		sql += " ) A WHERE ROWNUM <= ?)";
		sql += " WHERE rnum >= ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, index);
			pstmt.setInt(2, endRow);  // ROWNUM <= endRow
			pstmt.setInt(3, startRow); // rnum >= startRow
			
			rs = pstmt.executeQuery();
			
			memberList = new ArrayList<MemberDto>();
			
			while(rs.next()) {
				point = rs.getInt("POINT_POINT");
				pointDate = rs.getTimestamp("POINT_DATE");
				balancePoint = rs.getInt("ACCUMULATED_POINT");
				
				memberDto = new MemberDto();
				memberDto.setMemPointInt(point);
				memberDto.setMemPointDate(pointDate);
				memberDto.setMemBalancePointInt(balancePoint);
				
				memberList.add(memberDto);
			}
			return memberList;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	// 회원 삭제
	public int memberDelete(int no) throws SQLException {
			
			int result = 0;

			PreparedStatement pstmt = null;

			String sql = "";
			
			sql += "DELETE FROM MEMBER";
			sql += " WHERE M_INDEX = ?";

			try {
				
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setInt(1, no);

				result = pstmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			} finally {

				try {
					if (pstmt != null) {
						pstmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} // finally 종료

			return result;
		}
		
	
}
