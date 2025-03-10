package admin.order;

import java.util.Date;
import java.util.List;

public class OrderListDto {

	int ordIndexint = 0;
	String staStatStr = "";
	Date ordTime = null;
	String memAdd1Str = "";
	String memAdd2Str = "";
	String memTelStr = "";
	int memIndexInt = 0;
	String memNameStr = "";
	int totalPriceInt = 0;
	OrderDto order = new OrderDto();
	
	
	public OrderListDto() {
		super();
	}

	
	
	
	public OrderListDto(int ordIndexint, String staStatStr, String memAdd1Str, String memAdd2Str,
			String memTelStr) {
		super();
		this.ordIndexint = ordIndexint;
		this.staStatStr = staStatStr;
		this.memAdd1Str = memAdd1Str;
		this.memAdd2Str = memAdd2Str;
		this.memTelStr = memTelStr;
	}

	
	
	
}
