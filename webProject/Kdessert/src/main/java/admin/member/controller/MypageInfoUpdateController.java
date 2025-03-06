package admin.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import admin.member.MemberDao;
import admin.member.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet ("/page/member/mypage/info/update")

public class MypageInfoUpdateController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;
		String mIndexStr = "";
		
		try {
			
			mIndexStr = req.getParameter("no");
			int mIndex = Integer.parseInt(mIndexStr);
			
			//DB연결
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			//새 dto 객체에 dao로 받아온 dto 담기
			MemberDto memberDto = new MemberDto();
			memberDto = memberDao.memberSelectOne(mIndex);
			
			req.setAttribute("memberDto", memberDto);
			
			RequestDispatcher rd = req.getRequestDispatcher("../MypageInfoUpdateForm.jsp");
			rd.forward(req, res);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;
		MemberDto memberDto = null;
		int index = 0;
		
		try {
			
			//해당 회원의 인덱스 값을 세션에서 구함
			MemberDto memberDtoSelect = new MemberDto();
			HttpSession session = req.getSession();
			memberDtoSelect = (MemberDto) session.getAttribute("member");
			index = memberDtoSelect.getMemIndexInt();
			
			//DB 연결
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);

			//수정 값 받아오기
			String pwd = req.getParameter("password");
			String email = req.getParameter("email");
			String tel = req.getParameter("tel");
			String address = req.getParameter("address");
			String addressSec = req.getParameter("addressSec");
			
			//회원객체에 수정정보 담기
			memberDto = new MemberDto();
			
			memberDto.setMemIndexInt(index);
			memberDto.setMemPasswordStr(pwd);
			memberDto.setMemEmailStr(email);
			memberDto.setMemTelStr(tel);
			memberDto.setMemAddressStr(address);
			memberDto.setMemAddressSecStr(addressSec);
			
			//업데이트 메소드 실행
			int result = memberDao.memberInfoUpdate(memberDto);

			if(result == 0){
				System.out.println("회원 정보 수정에 실패하였습니다.");
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = res.getWriter();
				writer.println("<script> alert('수정에 실패하였습니다.'); location.href='" + "../info" + "'; </script>"); 
				writer.close();
				return;
			}
			
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = res.getWriter();
			writer.println("<script> alert('개인정보가 수정되었습니다.'); location.href='" + "../info" + "'; </script>"); 
			writer.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		}
	
	}
	
}
