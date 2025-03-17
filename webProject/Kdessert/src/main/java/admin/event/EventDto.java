package admin.event;

import java.util.Date;

public class EventDto {

	private int eveIndexInt = 0;
	private String eveNameStr = "";
	private String eveImageStr = "";
	private Date eveEventDate = null;
	private String eveExplanStr = "";
	private int eveOpenInt = 0;
	private Date eveCreDate = null;
	private Date eveCorrDate = null;
	private String eveNoteStr = "";
	
	
	
	
	
	
	public EventDto() {
		super();
	}
	
	public EventDto(int eveIndexInt, String eveNameStr, String eveImageStr, Date eveEventDate, String eveExplanStr, 
		int eveOpenInt, Date eveCreDate, Date eveCorrDate , String eveNoteStr) {
		super();
		this.eveIndexInt = eveIndexInt;
		this.eveNameStr = eveNameStr;
		this.eveImageStr = eveImageStr;
		this.eveEventDate = eveEventDate;
		this.eveExplanStr = eveExplanStr;
		this.eveOpenInt = eveOpenInt;
		this.eveCreDate = eveCreDate;
		this.eveCorrDate = eveCorrDate;
		this.eveNoteStr = eveNoteStr;
	}
	
	
	public int getEveIndexInt() {
		return eveIndexInt;
	}
	public void setEveIndexInt(int eveIndexInt) {
		this.eveIndexInt = eveIndexInt;
	}
	public String getEveNameStr() {
		return eveNameStr;
	}
	public void setEveNameStr(String eveNameStr) {
		this.eveNameStr = eveNameStr;
	}
	public String getEveImageStr() {
		return eveImageStr;
	}
	public void setEveImageStr(String eveImageStr) {
		this.eveImageStr = eveImageStr;
	}
	public Date getEveEventDate() {
		return eveEventDate;
	}
	public void setEveEventDate(Date eveEventDate) {
		this.eveEventDate = eveEventDate;
	}
	public String getEveExplanStr() {
		return eveExplanStr;
	}
	public void setEveExplanStr(String eveExplanStr) {
		this.eveExplanStr = eveExplanStr;
	}
	public int getEveOpenInt() {
		return eveOpenInt;
	}
	public void setEveOpenInt(int eveOpenInt) {
		this.eveOpenInt = eveOpenInt;
	}
	public Date getEveCreDate() {
		return eveCreDate;
	}
	public void setEveCreDate(Date eveCreDate) {
		this.eveCreDate = eveCreDate;
	}
	public Date getEveCorrDate() {
		return eveCorrDate;
	}
	public void setEveCorrDate(Date eveCorrDate) {
		this.eveCorrDate = eveCorrDate;
	}
	public String getEveNoteStr() {
		return eveNoteStr;
	}
	public void setEveNoteStr(String eveNoteStr) {
		this.eveNoteStr = eveNoteStr;
	}

	
	
}
