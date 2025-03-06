package user.basket;

public class BasketDto {
	
	int memIndexInt = 0;
	int proIndexInt = 0;
	int basStockNum = 0;
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
	public int getBasStockNum() {
		return basStockNum;
	}
	public void setBasStockNum(int basStockNum) {
		this.basStockNum = basStockNum;
	}
	
	public BasketDto() {
		super();
	}
	
	public BasketDto(int memIndexInt, int proIndexInt, int basStockNum) {
		super();
		this.memIndexInt = memIndexInt;
		this.proIndexInt = proIndexInt;
		this.basStockNum = basStockNum;
	}

	

}
