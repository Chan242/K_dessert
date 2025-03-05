package admin.product;

public class ProductDto {
	
	private int proIndexInt;
	private String proNameStr;
	private int proPriceInt;
	private int proStockInt;
	private String proIntroStr;
	private int proOpenInt;
	private String proImageStr;
	
	public ProductDto() {
		super();
	}
	
	public ProductDto(int proIndexInt, String proNameStr, int proPriceInt, int proStockInt, int proOpenInt) {
		super();
		this.proIndexInt = proIndexInt;
		this.proNameStr = proNameStr;
		this.proPriceInt = proPriceInt;
		this.proStockInt = proStockInt;
		this.proOpenInt = proOpenInt;
	}

	public int getproIndexInt() {
		return proIndexInt;
	}

	public void setproIndexInt(int proIndexInt) {
		this.proIndexInt = proIndexInt;
	}

	public String getproNameStr() {
		return proNameStr;
	}

	public void setproNameStr(String proNameStr) {
		this.proNameStr = proNameStr;
	}

	public int getproPriceInt() {
		return proPriceInt;
	}

	public void setproPriceInt(int proPriceInt) {
		this.proPriceInt = proPriceInt;
	}

	public int getproStockInt() {
		return proStockInt;
	}

	public void setproStockInt(int proStockInt) {
		this.proStockInt = proStockInt;
	}

	public String getproIntroStr() {
		return proIntroStr;
	}

	public void setproIntroStr(String proIntroStr) {
		this.proIntroStr = proIntroStr;
	}

	public int getproOpenInt() {
		return proOpenInt;
	}

	public void setproOpenInt(int proOpenInt) {
		this.proOpenInt = proOpenInt;
	}

	public String getproImageStr() {
		return proImageStr;
	}

	public void setproImageStr(String proImageStr) {
		this.proImageStr = proImageStr;
	}

	@Override
	public String toString() {
		return "ProductDto [proIndexInt=" + proIndexInt + ", proNameStr=" + proNameStr + ", proPriceInt=" + proPriceInt
				+ ", proStockInt=" + proStockInt + ", proIntroStr=" + proIntroStr + ", proOpenInt=" + proOpenInt + ", proImageStr="
				+ proImageStr + "]";
	}
	
	
	
}
