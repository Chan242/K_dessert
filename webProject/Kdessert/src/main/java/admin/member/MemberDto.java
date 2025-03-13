package admin.member;

import java.util.Date;

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
	private Date memCorrDate = null;
	private int memAdmCheckInt = 0;
	private String memNoteStr = "";
	
	//member 테이블에 없는 컬럼
	private int memPointInt = 0;
	private int memBalancePointInt = 0;
	private Date memPointDate = null;


	public MemberDto() {
		super();
	}
	
	public MemberDto(int memIndexInt, String memIdStr, String memPasswordStr, 
		String memNameStr, String memTelStr, String memEmailStr, String memAddressStr, 
		String memAddressSecStr, Date memBirthDate, Date memSignTimeDate, Date memCorrDate, 
		int memAdmCheckInt, String memNoteStr) {
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
		this.memCorrDate = memCorrDate;
		this.memAdmCheckInt = memAdmCheckInt;
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

		/* 게시물에서 멤버변수를 가져오기 위해 추가했어요 */
	public MemberDto(int memIndexInt, String memNameStr, String memIdStr, int memAdmCheckInt) {
			super();
			this.memIndexInt = memIndexInt;
			this.memNameStr = memNameStr;
			this.memIdStr = memIdStr;
			this.memAdmCheckInt = memAdmCheckInt;
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
				"memCorrDate = " + memCorrDate + 
				"memPointInt = " + memPointInt +
				"memAdmCheckInt = " + memAdmCheckInt +
				"memNoteStr = " + memNoteStr + 
				"]";
	}
	
	public int getMemBalancePointInt() {
		return memBalancePointInt;
	}

	public void setMemBalancePointInt(int memBalancePointInt) {
		this.memBalancePointInt = memBalancePointInt;
	}


	public Date getMemPointDate() {
		return memPointDate;
	}

	public void setMemPointDate(Date memPointDate) {
		this.memPointDate = memPointDate;
	}

	public int getMemPointInt() {
		return memPointInt;
	}

	public void setMemPointInt(int memPointInt) {
		this.memPointInt = memPointInt;
	}

	public Date getMemCorrDate() {
		return memCorrDate;
	}

	public void setMemCorrDate(Date memCorrDate) {
		this.memCorrDate = memCorrDate;
	}
	
	public int getMemAdmCheckInt() {
		return memAdmCheckInt;
	}

	public void setMemAdmCheckInt(int memAdmCheckInt) {
		this.memAdmCheckInt = memAdmCheckInt;
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


	public String getMemNoteStr() {
		return memNoteStr;
	}

	public void setMemNoteStr(String memNoteStr) {
		this.memNoteStr = memNoteStr;
	}
	
}
