package admin.order;

import java.util.Date;

public class OrderDto {

	int ordIndexint = 0;
	String staStatStr = "";
	Date ordTime = null;
	String memAdd1Str = "";
	String memAdd2Str = "";
	String memTelStr = "";
	
	public OrderDto() {
		super();
	}

	
	
	
	public OrderDto(int ordIndexint, String staStatStr, Date ordTime, String memAdd1Str, String memAdd2Str,
			String memTelStr) {
		super();
		this.ordIndexint = ordIndexint;
		this.staStatStr = staStatStr;
		this.ordTime = ordTime;
		this.memAdd1Str = memAdd1Str;
		this.memAdd2Str = memAdd2Str;
		this.memTelStr = memTelStr;
	}




	public int getOrdIndexint() {
		return ordIndexint;
	}

	public void setOrdIndexint(int ordIndexint) {
		this.ordIndexint = ordIndexint;
	}
	public String getStaStatStr() {
		return staStatStr;
	}
	public void setStaStatStr(String staStatStr) {
		this.staStatStr = staStatStr;
	}
	public Date getOrdTime() {
		return ordTime;
	}
	public void setOrdTime(Date ordTime) {
		this.ordTime = ordTime;
	}
	public String getMemAdd1Str() {
		return memAdd1Str;
	}
	public void setMemAdd1Str(String memAdd1Str) {
		this.memAdd1Str = memAdd1Str;
	}
	public String getMemAdd2Str() {
		return memAdd2Str;
	}
	public void setMemAdd2Str(String memAdd2Str) {
		this.memAdd2Str = memAdd2Str;
	}
	public String getMemTelStr() {
		return memTelStr;
	}
	public void setMemTelStr(String memTelStr) {
		this.memTelStr = memTelStr;
	}
	@Override
	public String toString() {
		return "OrderDto [ordIndexint=" + ordIndexint + ", staStatStr=" + staStatStr + ", ordTime=" + ordTime
				+ ", memAdd1Str=" + memAdd1Str + ", memAdd2Str=" + memAdd2Str + ", memTelStr=" + memTelStr + "]";
	}
	
	
	
}
