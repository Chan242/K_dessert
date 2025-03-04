package member;

import java.sql.Date;

public class MemberDto {

	private int mIndexInt = 0;
	private String mIdStr = "";
	private String mPasswordStr = "";
	private String mNameStr = "";
	private String mTelStr = "";
	private String mEmailStr = "";
	private String mAddressStr = "";
	private String mAddressSecStr = "";
	private Date mBirthDate = null;
	private Date mSignTimeDate = null;
	private int mPointInt = 0;
	private String mNoteStr = "";
	
	public MemberDto() {
		super();
	}
	
	public MemberDto(int mIndexInt, String mIdStr, String mPasswordStr, 
		String mNameStr, String mTelStr, String mEmailStr, String mAddressStr, 
		String mAddressSecStr, Date mBirthDate, Date mSignTimeDate, 
		int mPointInt, String mNoteStr) {
		super();
		this.mIndexInt = mIndexInt;
		this.mIdStr = mIdStr;
		this.mPasswordStr = mPasswordStr;
		this.mNameStr = mNameStr;
		this.mTelStr = mTelStr;
		this.mEmailStr = mEmailStr;
		this.mAddressStr = mAddressStr;
		this.mAddressSecStr = mAddressSecStr;
		this.mBirthDate = mBirthDate;
		this.mSignTimeDate = mSignTimeDate;
		this.mPointInt = mPointInt;
		this.mNoteStr = mNoteStr;
	}

	public int getmIndexInt() {
		return mIndexInt;
	}

	public void setmIndexInt(int mIndexInt) {
		this.mIndexInt = mIndexInt;
	}

	public String getmIdStr() {
		return mIdStr;
	}

	public void setmIdStr(String mIdStr) {
		this.mIdStr = mIdStr;
	}

	public String getmPasswordStr() {
		return mPasswordStr;
	}

	public void setmPasswordStr(String mPasswordStr) {
		this.mPasswordStr = mPasswordStr;
	}

	public String getmNameStr() {
		return mNameStr;
	}

	public void setmNameStr(String mNameStr) {
		this.mNameStr = mNameStr;
	}

	public String getmTelStr() {
		return mTelStr;
	}

	public void setmTelStr(String mTelStr) {
		this.mTelStr = mTelStr;
	}

	public String getmEmailStr() {
		return mEmailStr;
	}

	public void setmEmailStr(String mEmailStr) {
		this.mEmailStr = mEmailStr;
	}

	public String getmAddressStr() {
		return mAddressStr;
	}

	public void setmAddressStr(String mAddressStr) {
		this.mAddressStr = mAddressStr;
	}

	public String getmAddressSecStr() {
		return mAddressSecStr;
	}

	public void setmAddressSecStr(String mAddressSecStr) {
		this.mAddressSecStr = mAddressSecStr;
	}

	public Date getmBirthDate() {
		return mBirthDate;
	}

	public void setmBirthDate(Date mBirthDate) {
		this.mBirthDate = mBirthDate;
	}

	public Date getmSignTimeDate() {
		return mSignTimeDate;
	}

	public void setmSignTimeDate(Date mSignTimeDate) {
		this.mSignTimeDate = mSignTimeDate;
	}

	public int getmPointInt() {
		return mPointInt;
	}

	public void setmPointInt(int mPointInt) {
		this.mPointInt = mPointInt;
	}

	public String getmNoteStr() {
		return mNoteStr;
	}

	public void setmNoteStr(String mNoteStr) {
		this.mNoteStr = mNoteStr;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return	"MemberDto [mIndexInt =" + mIndexInt + 
				"mIdStr = " + mIdStr + 
				"mPasswordStr = " + mPasswordStr + 
				"mNameStr = " + mNameStr + 
				"mTelStr = " + mTelStr + 
				"mEmailStr = " + mEmailStr + 
				"mAddressStr = " + mAddressStr + 
				"mAddressSecStr = " + mAddressSecStr + 
				"mBirthDate = " + mBirthDate + 
				"mSignTimeDate = " + mSignTimeDate + 
				"mPointInt = " + mPointInt + 
				"mNoteStr = " + mNoteStr + 
				"]";
	}
	
}
