	package user.basket;

public class BasketDto {
	
	int memIndexInt = 0;
	int proIndexInt = 0;
	int basStockInt = 0;
	int proPriceInt = 0;
	String proNameStr = "";
//	String proImgStr = "";
	
	public int getMemIndexInt() {
		return memIndexInt;
	}
	public void setMemIndexInt(int memIndexInt) {
		this.memIndexInt = memIndexInt;
	}
	public int getProIndexInt() {
		return proIndexInt;
	}
	public void setProIndexInt(int proIndexInt) {
		this.proIndexInt = proIndexInt;
	}
	public int getBasStockInt() {
		return basStockInt;
	}
	public void setBasStockInt(int basStockInt) {
		this.basStockInt = basStockInt;
	}
	
	public int getProPriceInt() {
		return proPriceInt;
	}
	public void setProPriceInt(int proPriceInt) {
		this.proPriceInt = proPriceInt;
	}
	public String getProNameStr() {
		return proNameStr;
	}
	public void setProNameStr(String proNameStr) {
		this.proNameStr = proNameStr;
	}
	public BasketDto() {
		super();
	}
	
	public BasketDto(int memIndexInt, int proIndexInt, int basStockInt) {
		super();
		this.memIndexInt = memIndexInt;
		this.proIndexInt = proIndexInt;
		this.basStockInt = basStockInt;
	}

	

}
