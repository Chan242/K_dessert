package user.board.main;

import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.member.MemberDao;
import admin.member.MemberDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.board.main.FreeBoardDto;


/*게시판정보 전부 삽입*/
public class FreeBoardDao {
	private Connection connection;
	
	public void setConnection(Connection conn) {
		this.connection = conn;
	}
	
	//게시판 정보 조회-메인 화면에 보여질 리스트
	public List<FreeBoardDto> freeBoardList(int pageNum, int pageSize) 
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FreeBoardDto> freeBoardList = new ArrayList<FreeBoardDto>();

		String sql = "";

		//pageNum~pageSize만큼의 목록 불러옴

		
		sql =  "SELECT * "
	               + "FROM ( SELECT "
	               + "	F.F_INDEX, F.M_INDEX, F.F_SUBJECT, F.F_TEXT, F.F_IMAGE, "
	               + "  F.F_VIEW, F.F_CRE_DATE, F.F_NOTICE, "
	               + "  ROWNUM AS rnum"
	               + "  FROM FREE_BOARD F "
	               + "  WHERE F.F_NOTICE=0"
	               + "  ORDER BY F.F_INDEX DESC )"
	               + " WHERE rnum BETWEEN ? AND ?"
	               + " ORDER BY F.F_INDEX DESC";
		

		int startRow = (pageNum - 1) * pageSize + 1; // 조회한 테이블에서 첫번째로 보여줄 행
		int endRow = pageNum * pageSize; // 조회한 테이블에서 마지막으로 보여줄 행

		try {
			/* sql 연결 */
			pstmt = connection.prepareStatement(sql);


			 pstmt.setInt(1, startRow);
			 pstmt.setInt(2, endRow); 
			
			
			rs = pstmt.executeQuery();

			int brdIndexInt = 0;
			int brdViewInt = 0;
			int brdNoticeInt = 0;
			int memIndexInt = 0;
			String brdSubjectStr = "";
			Date brdCreDate = null;

			
			while (rs.next()) {
				brdIndexInt = rs.getInt("F_INDEX");
				brdViewInt = rs.getInt("F_VIEW");
				brdNoticeInt = rs.getInt("F_NOTICE");
				memIndexInt = rs.getInt("M_INDEX");
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdCreDate = rs.getDate("F_CRE_DATE");

				FreeBoardDto freeBoardDto = new FreeBoardDto(brdIndexInt, memIndexInt, brdSubjectStr
						, brdCreDate, brdViewInt, brdNoticeInt);

				//여기부터 추가
				

				MemberDto memberDto = freeboardWriter(memIndexInt);
				
				freeBoardDto.setMemberDto(memberDto); // MemberDto를 FreeBoardDto에 설정

				
				//이건 원래 있던거
				freeBoardList.add(freeBoardDto);
			}

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end
		return freeBoardList;
	}
	
	//게시판 정보 조회-메인 화면에 보여질 리스트
		public List<FreeBoardDto> freeBoardNotiList() 
				throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<FreeBoardDto> freeBoardList = new ArrayList<FreeBoardDto>();

			String sql = "";

			/*원본 리스트 보여줌
			 * sql = "SELECT F_index, M_INDEX, F_SUBJECT, F_TEXT, " +
			 * "F_IMAGE, F_VIEW, F_CRE_DATE, F_NOTICE " + "FROM FREE_BOARD " +
			 * "ORDER BY F_index DESC";
			 */
			//pageNum~pageSize만큼의 목록 불러옴
			sql =  "SELECT * "
		               + "FROM ( SELECT "
		               + "	F.F_INDEX, F.M_INDEX, F.F_SUBJECT, F.F_TEXT, F.F_IMAGE, "
		               + "  F.F_VIEW, F.F_CRE_DATE, F.F_NOTICE, "
		               + "  ROWNUM AS rnum"
		               + "  FROM FREE_BOARD F "
		               + "  WHERE F.F_NOTICE=1"
		               + "  ORDER BY F.F_INDEX DESC ) ";
			

			try {
				/* sql 연결 */
				pstmt = connection.prepareStatement(sql);


				
				rs = pstmt.executeQuery();

				int brdIndexInt = 0;
				int brdViewInt = 0;
				int brdNoticeInt = 0;
				int memIndexInt = 0;
				String brdSubjectStr = "";
				Date brdCreDate = null;

				
				while (rs.next()) {
					brdIndexInt = rs.getInt("F_INDEX");
					brdViewInt = rs.getInt("F_VIEW");
					brdNoticeInt = rs.getInt("F_NOTICE");
					memIndexInt = rs.getInt("M_INDEX");
					brdSubjectStr = rs.getString("F_SUBJECT");
					brdCreDate = rs.getDate("F_CRE_DATE");

					FreeBoardDto freeBoardDto = new FreeBoardDto(brdIndexInt, memIndexInt, brdSubjectStr
							, brdCreDate, brdViewInt, brdNoticeInt);

					//여기부터 추가
					

					MemberDto memberDto = freeboardWriter(memIndexInt);
					
					freeBoardDto.setMemberDto(memberDto); // MemberDto를 FreeBoardDto에 설정

					
					//이건 원래 있던거
					freeBoardList.add(freeBoardDto);
				}

			} catch (Exception e) {
				// TODO: handle exception

				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					if (pstmt != null) {
						pstmt.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} // finally end
			return freeBoardList;
		}
	
	//일반회원 게시글 총 개수
	public int freeBoardListTotal() 
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
	

		String sql = "";

		sql = "SELECT F_index, M_INDEX, F_SUBJECT, F_TEXT, "
				+ "F_IMAGE, F_VIEW, F_CRE_DATE, F_NOTICE "
				+ "FROM FREE_BOARD "
	            + "  WHERE F_NOTICE=0"
				+ "ORDER BY F_index DESC";

		try {
			/* sql 연결 */
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				totalCount += 1;
			}

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end
		
		return totalCount;

	}
	
	//검색창에 의한 정보 조회(일반)
	public List<FreeBoardDto> freeboardSearch(String searchWord, int pageNum, int pageSize) 
			throws SQLException{
		ResultSet rs = null;
		ArrayList<FreeBoardDto> freeBoardList = new ArrayList<FreeBoardDto>();
		
		String sql = "";
		
		PreparedStatement pstmt = null;
		
		int startRow = (pageNum - 1) * pageSize + 1; // 조회한 테이블에서 첫번째로 보여줄 행
		int endRow = pageNum * pageSize; // 조회한 테이블에서 마지막으로 보여줄 행
		
		try {
			//ROWNUM 이름 지정 필수(다른 ROWNUM으로 인식해버리기 때문)
			sql = "SELECT F_INDEX, M_INDEX, F_SUBJECT, F_TEXT, F_IMAGE,"
					+ "        F_VIEW, F_CRE_DATE, F_NOTICE\r\n"
					+ " FROM ( SELECT F.F_INDEX, F.M_INDEX, F.F_SUBJECT, F.F_TEXT, F.F_IMAGE,"
					+ "        	F.F_VIEW, F.F_CRE_DATE, F.F_NOTICE, ROWNUM AS rnum"
					+ "			FROM FREE_BOARD F"
					+ "			WHERE F_SUBJECT LIKE ?"
					+ "			AND F_NOTICE=0"
					+ "        	ORDER BY F_INDEX DESC )"
					+ " WHERE rnum BETWEEN ? AND ?";
			
			pstmt = connection.prepareStatement(sql);

			searchWord = '%' + searchWord +  '%';
			pstmt.setString(1, searchWord);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			

			rs = pstmt.executeQuery();

			int brdIndexInt = 0;
			int brdViewInt = 0;
			int brdNoticeInt = 0;
			int memIndexInt = 0;
			String brdSubjectStr = "";
			Date brdCreDate = null;

			
			while (rs.next()) {
				brdIndexInt = rs.getInt("F_index");
				brdViewInt = rs.getInt("F_VIEW");
				brdNoticeInt = rs.getInt("F_NOTICE");
				memIndexInt = rs.getInt("M_INDEX");
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdCreDate = rs.getDate("F_CRE_DATE");

				FreeBoardDto freeBoardDto = new FreeBoardDto(brdIndexInt, memIndexInt, brdSubjectStr
						, brdCreDate, brdViewInt, brdNoticeInt);


				MemberDto memberDto = freeboardWriter(memIndexInt);
				
				freeBoardDto.setMemberDto(memberDto); // MemberDto를 FreeBoardDto에 설정


				freeBoardList.add(freeBoardDto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return freeBoardList;
		
	}
	
	//검색창에 의한 정보 조회(공지)
		public List<FreeBoardDto> freeboardSearchNoti(String searchWord, int pageNum, int pageSize) 
				throws SQLException{
			ResultSet rs = null;
			ArrayList<FreeBoardDto> freeBoardList = new ArrayList<FreeBoardDto>();
			
			String sql = "";
			
			PreparedStatement pstmt = null;
			
			int startRow = (pageNum - 1) * pageSize + 1; // 조회한 테이블에서 첫번째로 보여줄 행
			int endRow = pageNum * pageSize; // 조회한 테이블에서 마지막으로 보여줄 행
			
			try {
				//ROWNUM 이름 지정 필수(다른 ROWNUM으로 인식해버리기 때문)
				sql = "SELECT F_INDEX, M_INDEX, F_SUBJECT, F_TEXT, F_IMAGE,"
						+ "        F_VIEW, F_CRE_DATE, F_NOTICE\r\n"
						+ " FROM ( SELECT F.F_INDEX, F.M_INDEX, F.F_SUBJECT, F.F_TEXT, F.F_IMAGE,"
						+ "        	F.F_VIEW, F.F_CRE_DATE, F.F_NOTICE, ROWNUM AS rnum"
						+ "			FROM FREE_BOARD F"
						+ "			WHERE F_SUBJECT LIKE ?"
						+ "			AND F_NOTICE=1"
						+ "        	ORDER BY F_INDEX DESC )"
						+ " WHERE rnum BETWEEN ? AND ?";
				
				pstmt = connection.prepareStatement(sql);

				searchWord = '%' + searchWord +  '%';
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				

				rs = pstmt.executeQuery();

				int brdIndexInt = 0;
				int brdViewInt = 0;
				int brdNoticeInt = 0;
				int memIndexInt = 0;
				String brdSubjectStr = "";
				Date brdCreDate = null;

				
				while (rs.next()) {
					brdIndexInt = rs.getInt("F_index");
					brdViewInt = rs.getInt("F_VIEW");
					brdNoticeInt = rs.getInt("F_NOTICE");
					memIndexInt = rs.getInt("M_INDEX");
					brdSubjectStr = rs.getString("F_SUBJECT");
					brdCreDate = rs.getDate("F_CRE_DATE");

					FreeBoardDto freeBoardDto = new FreeBoardDto(brdIndexInt, memIndexInt, brdSubjectStr
							, brdCreDate, brdViewInt, brdNoticeInt);


					MemberDto memberDto = freeboardWriter(memIndexInt);
					
					freeBoardDto.setMemberDto(memberDto); // MemberDto를 FreeBoardDto에 설정


					freeBoardList.add(freeBoardDto);
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if (rs != null) {
						rs.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					if (pstmt != null) {
						pstmt.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			return freeBoardList;
			
		}
	
	
	
	//검색한 게시글 총 개수(일반)
		public int freeBoardListSearchTo(String searchWord) 
				throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalCount = 0;
		

			String sql = "";

			sql = "SELECT F_index, M_INDEX, F_SUBJECT, F_TEXT, "
					+ " F_IMAGE, F_VIEW, F_CRE_DATE, F_NOTICE "
					+ " FROM FREE_BOARD"
					+ " WHERE F_SUBJECT LIKE ?"
					+ " AND F_NOTICE=0"
					+ " ORDER BY F_index DESC";

			try {
				/* sql 연결 */
				pstmt = connection.prepareStatement(sql);
				
				searchWord = '%' + searchWord +  '%';
				pstmt.setString(1, searchWord);

				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					totalCount += 1;
				}

			} catch (Exception e) {
				// TODO: handle exception

				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				try {
					if (pstmt != null) {
						pstmt.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} // finally end
			
			return totalCount;

		}
		
		//검색한 게시글 총 개수(공지)
				public int freeBoardListSearchNoti(String searchWord) 
						throws Exception {
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					int totalCount = 0;
				

					String sql = "";

					sql = "SELECT F_index, M_INDEX, F_SUBJECT, F_TEXT, "
							+ " F_IMAGE, F_VIEW, F_CRE_DATE, F_NOTICE "
							+ " FROM FREE_BOARD"
							+ " WHERE F_SUBJECT LIKE ?"
							+ " AND F_NOTICE=1"
							+ " ORDER BY F_index DESC";

					try {
						/* sql 연결 */
						pstmt = connection.prepareStatement(sql);
						
						searchWord = '%' + searchWord +  '%';
						pstmt.setString(1, searchWord);

						rs = pstmt.executeQuery();
						
						while (rs.next()) {
							
							totalCount += 1;
						}

					} catch (Exception e) {
						// TODO: handle exception

						e.printStackTrace();
					} finally {
						try {
							if (rs != null) {
								rs.close();
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

						try {
							if (pstmt != null) {
								pstmt.close();
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					} // finally end
					
					return totalCount;

				}		
	
	
	
	//게시판 상세 내용 보이기
	public FreeBoardDto freeBoardDetail(int brdIndexInt) 
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FreeBoardDto freeboardDto = new FreeBoardDto();
		String sql = "";

		sql = "SELECT F_INDEX, F_SUBJECT, F_TEXT,"
				+ " F_IMAGE, M_INDEX, F_VIEW, F_NOTICE,"
				+ " F_CRE_DATE, F_CORR_DATE"
				+ " FROM FREE_BOARD"
				+ " WHERE F_INDEX = ?"
				+ " ORDER BY F_INDEX DESC";

		pstmt = connection.prepareStatement(sql);

		try {

			pstmt.setInt(1, brdIndexInt);
			rs = pstmt.executeQuery();
			

			String brdSubjectStr = "";//제목
			String brdTextStr = "";//내용
			int memIndexInt = 0;//작성자
			Date brdCreDate = null;//작성일
			Date brdCorrDate = null;//수정일
			int brdViewInt = 0;// 조회수
			

			if (rs.next()) {
				brdIndexInt = rs.getInt("F_INDEX");
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdTextStr = rs.getString("F_TEXT");
				memIndexInt = rs.getInt("M_INDEX");
				brdCreDate = rs.getDate("F_CRE_DATE");
				brdCorrDate = rs.getDate("F_CORR_DATE");
				brdViewInt = rs.getInt("F_VIEW");
				

				freeboardDto.setBrdIndexInt(brdIndexInt);
				freeboardDto.setBrdSubjectStr(brdSubjectStr);
				freeboardDto.setBrdTextStr(brdTextStr);
				freeboardDto.setMemIndexInt(memIndexInt);
				freeboardDto.setBrdCreDate(brdCreDate);
				freeboardDto.setBrdCorrDate(brdCorrDate);
				freeboardDto.setBrdViewInt(brdViewInt);
				
			
				// freeboardWriter가 반환하는 리스트에서 첫 번째 MemberDto 객체를 가져옴
				MemberDto memberDto = freeboardWriter(memIndexInt);
				freeboardDto.setMemberDto(memberDto); 

			} else {
				throw new Exception("해당 게시물은 존재하지 않습니다.");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally 종료
		
		return freeboardDto;
	}
	
	//게시물 새로 작성하기
	public void freeBoardNew(FreeBoardDto freeBoardDto) 
			throws Exception {
		
		PreparedStatement pstmt = null;
		String sql=" ";
		
		// SQL 객체준비
		try {
			
			int memIndexInt = freeBoardDto.getMemIndexInt();
			String brdSubjectStr = freeBoardDto.getBrdSubjectStr();
			String brdTextStr = freeBoardDto.getBrdTextStr();
			int brdNoticeInt = freeBoardDto.getBrdNoticeInt();
//			String brdImageStr = freeBoardDto.getBrdImageStr();
			

			
			sql = "INSERT INTO FREE_BOARD"
					+ " (F_INDEX, M_INDEX, F_SUBJECT, F_TEXT, F_CRE_DATE, F_CORR_DATE, F_VIEW , F_NOTICE)"
					+ " VALUES(F_INDEX_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, null, 0, ?)";
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, memIndexInt);
			pstmt.setString(2, brdSubjectStr);
			pstmt.setString(3, brdTextStr);
			pstmt.setInt(4, brdNoticeInt);
			
			MemberDto memberDto = freeboardWriter(memIndexInt);
			
			freeBoardDto.setMemberDto(memberDto); // MemberDto를 FreeBoardDto에 설정
		

			pstmt.executeUpdate();



		}catch (Exception e) {
			connection.rollback(); //오류 시 롤백
			e.printStackTrace();
		}finally {
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				connection.setAutoCommit(true); // 원래 상태로 되돌림
			}
			
		} // finally 종료
	}
	
	//삭제
	public int deleteBoard(int brdIndexInt) 
			throws SQLException {
		// TODO Auto-generated method stub
		int result = 0;
		// sql 실행을 위한 PreparedStatement 객체를 선언
		PreparedStatement pstmt = null;
		
		System.out.println("brdIndexInt 삭제 Dao에 넘어왂나? "+brdIndexInt);
		
		//sql 삭제문 생성
		String sql = "";
		
		sql = "DELETE FROM FREE_BOARD WHERE F_INDEX = ?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			//첫 번째 ?(placeholder)에 brdIndexInt 값을 바인딩
			pstmt.setInt(1, brdIndexInt);
			
			result = pstmt.executeUpdate();
			
			if(result==0) {
				
				throw new SQLException("게시글 삭제 실패! 존재하지 않는 게시글일 가능성 있음");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	
	//게시물 업데이트(수정)
	
	public void freeBoardUpdate(FreeBoardDto boardDto) 
			throws SQLException {
		PreparedStatement pstmt = null;
		
		String sql = "";
	
		sql = "UPDATE FREE_BOARD"
				+ " SET F_SUBJECT = ?, F_TEXT = ?, F_CORR_DATE = SYSDATE"
				+ " WHERE F_INDEX = ?";
		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, boardDto.getBrdSubjectStr());
			pstmt.setString(2, boardDto.getBrdTextStr());
			pstmt.setInt(3, boardDto.getBrdIndexInt());

	
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
			    connection.commit(); 
			} else {
			    connection.rollback(); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
		
		
	}
	
	// 업데이트 페이지에 정보를 넣을 Dao
	
	public FreeBoardDto freeBoardWritedInfo(int brdIndexInt) 
			throws SQLException{
		// TODO Auto-generated method stub
		FreeBoardDto boardDto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";


		try {
			sql = "SELECT F_INDEX, F_SUBJECT, F_TEXT, M_INDEX"
					+ " FROM FREE_BOARD"
					+ " WHERE F_INDEX = ?";
			
			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, brdIndexInt);

			rs = pstmt.executeQuery();

			String brdSubjectStr = "";
			String brdTextStr = "";


			if (rs.next()) {
				brdSubjectStr = rs.getString("F_SUBJECT");
				brdTextStr = rs.getString("F_TEXT");
				int memIndexInt = rs.getInt("M_INDEX");

				boardDto = new FreeBoardDto();

				boardDto.setBrdIndexInt(brdIndexInt);
				boardDto.setBrdSubjectStr(brdSubjectStr);
				boardDto.setBrdTextStr(brdTextStr);
				boardDto.setMemIndexInt(memIndexInt);

			} else {
				throw new Exception("해당 게시물을 찾을 수 없습니다.");
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
		return boardDto;
	}
	

	/* 조회수 카운트하는 로직*/
	
	public void freeBoardView(int brdIndexInt) 
			throws SQLException {
//		System.out.println("뷰 카운트 시작");
		PreparedStatement pstmt = null;
		String sql = "";
		
		
		try {

			sql = "UPDATE FREE_BOARD"
					+ " SET F_VIEW = F_VIEW+1"
					+ " WHERE F_INDEX = ?";
			
			/* index에 따른 view값 찾기 */
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, brdIndexInt);
	
			// 쿼리 실행 및 반환값 확인
	        int rowsUpdated = pstmt.executeUpdate();
	        /*
	        if (rowsUpdated > 0) {
	            System.out.println("조회수 증가 성공: F_INDEX = " + brdIndexInt);
	        } else {
	            System.out.println("조회수 증가 실패: F_INDEX = " + brdIndexInt);
	        }
			*/
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
		
		
	}
	
	
	
	
	//멤버와 게시판 join-글쓴이명 가져오기용 메서드
	
	public MemberDto freeboardWriter(int memIndex) 
			throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberDto memberDto = null;

		String sql = "";

		//멤버 인덱스로 해당 게시물 글쓴이 찾기
		sql = "SELECT F.F_INDEX, M.M_NAME, M.M_ID, M_ADM_CHECK"
				+ " FROM FREE_BOARD F INNER JOIN MEMBER M"
				+ " ON M.M_INDEX = F.M_INDEX"
				+ " WHERE M.M_INDEX = ?"
				+ " ORDER BY F_INDEX DESC";

		try {
			
			/* sql 연결 */
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, memIndex);

			rs = pstmt.executeQuery();


			String memNameStr = "";
			String memIdStr = "";
			int memAdmCheckInt = 0;

			
			
			while (rs.next()) {

				memNameStr = rs.getString("M_NAME");
				memIdStr = rs.getString("M_ID");
				memAdmCheckInt = rs.getInt("M_ADM_CHECK");
				
				

				memberDto = new MemberDto(memIndex, memNameStr, memIdStr, memAdmCheckInt);

			}
			/*
			 * System.out.println(memAdmCheckInt); System.out.println(memberDto);
			 */
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // finally end
		
		return memberDto;
	}
	

	
}
