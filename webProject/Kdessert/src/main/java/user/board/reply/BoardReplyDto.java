package user.board.reply;

import java.util.Date;

import admin.member.MemberDto;
import jakarta.servlet.annotation.WebServlet;
import user.board.main.FreeBoardDto;
@WebServlet("/board/freeboarddetail/reply")
public class BoardReplyDto {

	private int replyIndexInt = 0;// 댓글 번호
	private int memIndexInt = 0;// 작성자 번호-외래키
	private String replyTextStr = "";// 텍스트(내용)
	private int brdIndexInt = 0;// 작성한 게시글 번호-외래키
	private Date replyCreDate = null;// 작성일
	private Date replyCorrDate = null;// 수정일

	// 멤버테이블 끌고옴
	private MemberDto memberDto; // 게시글의 작성자 정보
	private FreeBoardDto freeboardDto;

	public BoardReplyDto() {
		super();
	}

	public BoardReplyDto(int replyIndexInt, int memIndexInt, String replyTextStr, 
			int brdIndexInt, Date replyCreDate, Date replyCorrDate) {
		this.replyIndexInt = replyIndexInt;
		this.memIndexInt = memIndexInt;
		this.replyTextStr = replyTextStr;
		this.brdIndexInt = brdIndexInt;
		this.replyCreDate = replyCreDate;
		this.replyCorrDate = replyCorrDate;

	}
	
	/* 생성자 나중에 필요하면 추가하는 걸로 */
	
	public FreeBoardDto getFreeboardDto() {
		return freeboardDto;
	}
	
	public void setFreeboardDto(FreeBoardDto freeboardDto) {
		this.freeboardDto = freeboardDto;
	}
	
	public MemberDto getMemberDto() {
		return memberDto;
	}
	
	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}
	
	public int getReplyIndexInt() {
		return replyIndexInt;
	}
	
	public void setReplyIndexInt(int replyIndexInt) {
		this.replyIndexInt = replyIndexInt;
	}
	
	public String getReplyTextStr() {
		return replyTextStr;
	}
	
	public int getMemIndexInt() {
		return memIndexInt;
	}
	
	public void setMemIndexInt(int memIndexInt) {
		this.memIndexInt = memIndexInt;
	}
	
	public void setReplyTextStr(String replyTextStr) {
		this.replyTextStr = replyTextStr;
	}
	
	public int getBrdIndexInt() {
		return brdIndexInt;
	}
	
	public void setBrdIndexInt(int brdIndexInt) {
		this.brdIndexInt = brdIndexInt;
	}
	
	public Date getReplyCreDate() {
		return replyCreDate;
	}
	
	public void setReplyCreDate(Date replyCreDate) {
		this.replyCreDate = replyCreDate;
	}
	
	public Date getReplyCorrDate() {
		return replyCorrDate;
	}
	
	public void setReplyCorrDate(Date replyCorrDate) {
		this.replyCorrDate = replyCorrDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BoardReplyDto [" + 
				"replyIndexInt = " + replyIndexInt + 
				"memIndexInt = " + memIndexInt + 
				"replyTextStr = " + replyTextStr + 
				"brdIndexInt = " + brdIndexInt + 
				"replyCreDate = " + replyCreDate + 
				"replyCorrDate = " + replyCorrDate + 
				"]";
	}

}
