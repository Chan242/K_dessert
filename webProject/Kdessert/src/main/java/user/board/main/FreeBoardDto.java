package user.board.main;

import java.util.Date;

import admin.member.MemberDto;

public class FreeBoardDto {

	private int brdIndexInt = 0;// 게시판 번호
	private int memIndexInt = 0;// 작성자 번호-외래키
	private String brdSubjectStr = "";// 제목
	private String brdTextStr = "";// 텍스트(내용)
	private String brdImageStr = "";// 이미지(주소)
	private Date brdCreDate = null;// 작성일
	private int brdViewInt = 0;// 조회수
	private int brdNoticeInt = 0;// 공지여부(0:일반, 1:공지)
	//멤버테이블 끌고옴
	private MemberDto memberDto = null; // 게시글의 작성자 정보

	public FreeBoardDto() {
		super();
	}
	
	public FreeBoardDto(int brdIndexInt, int memIndexInt, String brdSubjectStr
			, Date brdCreDate, int brdViewInt, int brdNoticeInt) {
		super();
		this.brdIndexInt = brdIndexInt;
		this.memIndexInt = memIndexInt;
		this.brdSubjectStr = brdSubjectStr;
		this.brdCreDate = brdCreDate;
		this.brdViewInt = brdViewInt;
		this.brdNoticeInt = brdNoticeInt;
	}

	public FreeBoardDto(int brdIndexInt, int memIndexInt, String brdSubjectStr, String brdTextStr
			, String brdImageStr, Date brdCreDate, int brdViewInt, int brdNoticeInt) {
		super();
		this.brdIndexInt = brdIndexInt;
		this.memIndexInt = memIndexInt;
		this.brdSubjectStr = brdSubjectStr;
		this.brdTextStr = brdTextStr;
		this.brdImageStr = brdImageStr;
		this.brdCreDate = brdCreDate;
		this.brdViewInt = brdViewInt;
		this.brdNoticeInt = brdNoticeInt;
	}
	
    public MemberDto getMemberDto() {
        return memberDto;
    }

    public void setMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
    }
	

	public int getBrdIndexInt() {
		return brdIndexInt;
	}

	public void setBrdIndexInt(int brdIndexInt) {
		this.brdIndexInt = brdIndexInt;
	}

	public int getMemIndexInt() {
		return memIndexInt;
	}

	public void setMemIndexInt(int memIndexInt) {
		this.memIndexInt = memIndexInt;
	}

	public String getBrdSubjectStr() {
		return brdSubjectStr;
	}

	public void setBrdSubjectStr(String brdSubjectStr) {
		this.brdSubjectStr = brdSubjectStr;
	}

	public String getBrdTextStr() {
		return brdTextStr;
	}

	public void setBrdTextStr(String brdTextStr) {
		this.brdTextStr = brdTextStr;
	}

	public String getBrdImageStr() {
		return brdImageStr;
	}

	public void setBrdImageStr(String brdImageStr) {
		this.brdImageStr = brdImageStr;
	}

	public Date getBrdCreDate() {
		return brdCreDate;
	}

	public void setBrdCreDate(Date brdCreDate) {
		this.brdCreDate = brdCreDate;
	}

	public int getBrdViewInt() {
		return brdViewInt;
	}

	public void setBrdViewInt(int brdViewInt) {
		this.brdViewInt = brdViewInt;
	}

	public int getBrdNoticeInt() {
		return brdNoticeInt;
	}

	public void setBrdNoticeInt(int brdNoticeInt) {
		this.brdNoticeInt = brdNoticeInt;
	}

	@Override
	public String toString() {

		return	"FreeBoardDto [brdIndexInt =" + brdIndexInt + 
				"memIndexInt = " + memIndexInt + 
				"brdSubjectStr = " + brdSubjectStr + 
				"brdTextStr = " + brdTextStr + 
				"brdImageStr = " + brdImageStr + 
				"brdCreDate = " + brdCreDate + 
				"brdViewInt = " + brdViewInt + 
				"brdNoticeInt = " + brdNoticeInt + 
				"]";
	}
}
