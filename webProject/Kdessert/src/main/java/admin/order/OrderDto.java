package admin.order;

import java.util.ArrayList;
import java.util.Date;

public class OrderDto {

	private int ordIndexint = 0;
	private String staStatStr = "";
	private Date ordTime = null;
	private String memAdd1Str = "";
	private String memAdd2Str = "";
	private String memTelStr = "";
	private int memIndexInt = 0;
	private String memNameStr = "";
	private int totalPriceInt = 0;
	
	public OrderDto() {
		super();
	}

	
	
	
	public OrderDto(int ordIndexint, String staStatStr, String memAdd1Str, String memAdd2Str,
			String memTelStr) {
		super();
		this.ordIndexint = ordIndexint;
		this.staStatStr = staStatStr;
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




	public int getMemIndexInt() {
		return memIndexInt;
	}




	public void setMemIndexInt(int memIndexInt) {
		this.memIndexInt = memIndexInt;
	}




	public String getMemNameStr() {
		return memNameStr;
	}




	public void setMemNameStr(String memNameStr) {
		this.memNameStr = memNameStr;
	}




	public int getTotalPriceInt() {
		return totalPriceInt;
	}




	public void setTotalPriceInt(int totalPriceInt) {
		this.totalPriceInt = totalPriceInt;
	}




	

	
	
	
}
