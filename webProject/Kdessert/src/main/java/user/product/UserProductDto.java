package user.product;

public class UserProductDto {
	
	
	private int proIndexInt;
	private String proNameStr;
	private int proPriceInt;
	private int proStockInt;
	private String proIntroStr;
	private int proOpenInt;
	private String proImageStr;
	
	public UserProductDto() {
		super();
	}
	
	public UserProductDto(int proIndexInt, String proNameStr, int proPriceInt, int proStockInt) {
		super();
		this.proIndexInt = proIndexInt;
		this.proNameStr = proNameStr;
		this.proPriceInt = proPriceInt;
		this.proStockInt = proStockInt;
	}

	public int getProIndexInt() {
		return proIndexInt;
	}

	public void setProIndexInt(int proIndexInt) {
		this.proIndexInt = proIndexInt;
	}

	public String getProNameStr() {
		return proNameStr;
	}

	public void setProNameStr(String proNameStr) {
		this.proNameStr = proNameStr;
	}

	public int getProPriceInt() {
		return proPriceInt;
	}

	public void setProPriceInt(int proPriceInt) {
		this.proPriceInt = proPriceInt;
	}

	public int getProStockInt() {
		return proStockInt;
	}

	public void setProStockInt(int proStockInt) {
		this.proStockInt = proStockInt;
	}

	public String getProIntroStr() {
		return proIntroStr;
	}

	public void setProIntroStr(String proIntroStr) {
		this.proIntroStr = proIntroStr;
	}


	public String getProImageStr() {
		return proImageStr;
	}

	public void setProImageStr(String proImageStr) {
		this.proImageStr = proImageStr;
	}

	@Override
	public String toString() {
		return "ProductDto [proIndexInt=" + proIndexInt + ", proNameStr=" + proNameStr + ", proPriceInt=" + proPriceInt
				+ ", proStockInt=" + proStockInt + ", proIntroStr=" + proIntroStr + ", proOpenInt=" + proOpenInt + ", proImageStr="
				+ proImageStr + "]";
	}
	

}
