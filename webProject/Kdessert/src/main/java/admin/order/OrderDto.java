package admin.order;

import java.util.Date;

public class OrderDto {

	int ordIndexint = 0;
	String staStatStr = "";
	Date ordTime = null;
	String memAdd1Str = "";
	String memAdd2Str = "";
	String memTelStr = "";
	int memIndexInt = 0;
	String memNameStr = "";
	
	
	
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

	
	
	
}
