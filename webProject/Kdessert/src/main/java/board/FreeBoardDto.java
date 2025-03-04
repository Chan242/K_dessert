package board;

import java.util.Date;

public class FreeBoardDto {

	private int brdIndexInt = 0;// 게시판 번호
	private String brdIdStr = "";// 작성자 ID-외래키
	private String brdSubjectStr = "";// 제목
	private String brdTextStr = "";// 텍스트(내용)
	private String brdImageStr = "";// 이미지(주소)
	private Date brdCreDate = null;// 작성일
	private int brdViewInt = 0;// 조회수
	private int brdNoticeInt = 0;// 공지여부(0:일반, 1:공지)

	public FreeBoardDto() {
		super();
	}
	
	public FreeBoardDto(int brdIndexInt, String brdIdStr, String brdSubjectStr
			, Date brdCreDate, int brdViewInt, int brdNoticeInt) {
		super();
		this.brdIndexInt = brdIndexInt;
		this.brdIdStr = brdIdStr;
		this.brdSubjectStr = brdSubjectStr;
		this.brdCreDate = brdCreDate;
		this.brdViewInt = brdViewInt;
		this.brdNoticeInt = brdNoticeInt;
	}

	public FreeBoardDto(int brdIndexInt, String brdIdStr, String brdSubjectStr, String brdTextStr
			, String brdImageStr, Date brdCreDate, int brdViewInt, int brdNoticeInt) {
		super();
		this.brdIndexInt = brdIndexInt;
		this.brdIdStr = brdIdStr;
		this.brdSubjectStr = brdSubjectStr;
		this.brdTextStr = brdTextStr;
		this.brdImageStr = brdImageStr;
		this.brdCreDate = brdCreDate;
		this.brdViewInt = brdViewInt;
		this.brdNoticeInt = brdNoticeInt;
	}

	public int getBrdIndexInt() {
		return brdIndexInt;
	}

	public void setBrdIndexInt(int brdIndexInt) {
		this.brdIndexInt = brdIndexInt;
	}

	public String getBrdIdStr() {
		return brdIdStr;
	}

	public void setBrdIdStr(String brdIdStr) {
		this.brdIdStr = brdIdStr;
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
				"brdIdStr = " + brdIdStr + 
				"brdSubjectStr = " + brdSubjectStr + 
				"brdTextStr = " + brdTextStr + 
				"brdImageStr = " + brdImageStr + 
				"brdCreDate = " + brdCreDate + 
				"brdViewInt = " + brdViewInt + 
				"brdNoticeInt = " + brdNoticeInt + 
				"]";
	}
}
