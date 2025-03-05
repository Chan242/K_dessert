package admin.member;

import java.sql.Date;

public class MemberDto {

	private int memIndexInt = 0;
	private String memIdStr = "";
	private String memPasswordStr = "";
	private String memNameStr = "";
	private String memTelStr = "";
	private String memEmailStr = "";
	private String memAddressStr = "";
	private String memAddressSecStr = "";
	private Date memBirthDate = null;
	private Date memSignTimeDate = null;
	private int memPointInt = 0;
	private String memNoteStr = "";
	
	public MemberDto() {
		super();
	}
	
	public MemberDto(int memIndexInt, String memIdStr, String memPasswordStr, 
		String memNameStr, String memTelStr, String memEmailStr, String memAddressStr, 
		String memAddressSecStr, Date memBirthDate, Date memSignTimeDate, 
		int memPointInt, String memNoteStr) {
		super();
		this.memIndexInt = memIndexInt;
		this.memIdStr = memIdStr;
		this.memPasswordStr = memPasswordStr;
		this.memNameStr = memNameStr;
		this.memTelStr = memTelStr;
		this.memEmailStr = memEmailStr;
		this.memAddressStr = memAddressStr;
		this.memAddressSecStr = memAddressSecStr;
		this.memBirthDate = memBirthDate;
		this.memSignTimeDate = memSignTimeDate;
		this.memPointInt = memPointInt;
		this.memNoteStr = memNoteStr;
	}

	public MemberDto(int memIndexInt, String memNameStr, String memIdStr, 
			String memEmailStr,Date memBirthDate, Date memSignTimeDate) {
			super();
			this.memIndexInt = memIndexInt;
			this.memNameStr = memNameStr;
			this.memIdStr = memIdStr;
			this.memEmailStr = memEmailStr;
			this.memBirthDate = memBirthDate;
			this.memSignTimeDate = memSignTimeDate;
		}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return	"MemberDto [memIndexInt =" + memIndexInt + 
				"memIdStr = " + memIdStr + 
				"memPasswordStr = " + memPasswordStr + 
				"memNameStr = " + memNameStr + 
				"memTelStr = " + memTelStr + 
				"memEmailStr = " + memEmailStr + 
				"memAddressStr = " + memAddressStr + 
				"memAddressSecStr = " + memAddressSecStr + 
				"memBirthDate = " + memBirthDate + 
				"memSignTimeDate = " + memSignTimeDate + 
				"memPointInt = " + memPointInt + 
				"memNoteStr = " + memNoteStr + 
				"]";
	}

	public int getMemIndexInt() {
		return memIndexInt;
	}

	public void setMemIndexInt(int memIndexInt) {
		this.memIndexInt = memIndexInt;
	}

	public String getMemIdStr() {
		return memIdStr;
	}

	public void setMemIdStr(String memIdStr) {
		this.memIdStr = memIdStr;
	}

	public String getMemPasswordStr() {
		return memPasswordStr;
	}

	public void setMemPasswordStr(String memPasswordStr) {
		this.memPasswordStr = memPasswordStr;
	}

	public String getMemNameStr() {
		return memNameStr;
	}

	public void setMemNameStr(String memNameStr) {
		this.memNameStr = memNameStr;
	}

	public String getMemTelStr() {
		return memTelStr;
	}

	public void setMemTelStr(String memTelStr) {
		this.memTelStr = memTelStr;
	}

	public String getMemEmailStr() {
		return memEmailStr;
	}

	public void setMemEmailStr(String memEmailStr) {
		this.memEmailStr = memEmailStr;
	}

	public String getMemAddressStr() {
		return memAddressStr;
	}

	public void setMemAddressStr(String memAddressStr) {
		this.memAddressStr = memAddressStr;
	}

	public String getMemAddressSecStr() {
		return memAddressSecStr;
	}

	public void setMemAddressSecStr(String memAddressSecStr) {
		this.memAddressSecStr = memAddressSecStr;
	}

	public Date getMemBirthDate() {
		return memBirthDate;
	}

	public void setMemBirthDate(Date memBirthDate) {
		this.memBirthDate = memBirthDate;
	}

	public Date getMemSignTimeDate() {
		return memSignTimeDate;
	}

	public void setMemSignTimeDate(Date memSignTimeDate) {
		this.memSignTimeDate = memSignTimeDate;
	}

	public int getMemPointInt() {
		return memPointInt;
	}

	public void setMemPointInt(int memPointInt) {
		this.memPointInt = memPointInt;
	}

	public String getMemNoteStr() {
		return memNoteStr;
	}

	public void setMemNoteStr(String memNoteStr) {
		this.memNoteStr = memNoteStr;
	}
	
}
