package product;

public class ProductDto {
	
	private int pIndexInt;
	private String pNameStr;
	private int pPriceInt;
	private int pStockInt;
	private String pIntroStr;
	private int pOpenInt;
	private String pImageStr;
	
	public ProductDto() {
		super();
	}
	
	public ProductDto(int pIndexInt, String pNameStr, int pPriceInt, int pStockInt, int pOpenInt) {
		super();
		this.pIndexInt = pIndexInt;
		this.pNameStr = pNameStr;
		this.pPriceInt = pPriceInt;
		this.pStockInt = pStockInt;
		this.pOpenInt = pOpenInt;
	}

	public int getpIndexInt() {
		return pIndexInt;
	}

	public void setpIndexInt(int pIndexInt) {
		this.pIndexInt = pIndexInt;
	}

	public String getpNameStr() {
		return pNameStr;
	}

	public void setpNameStr(String pNameStr) {
		this.pNameStr = pNameStr;
	}

	public int getpPriceInt() {
		return pPriceInt;
	}

	public void setpPriceInt(int pPriceInt) {
		this.pPriceInt = pPriceInt;
	}

	public int getpStockInt() {
		return pStockInt;
	}

	public void setpStockInt(int pStockInt) {
		this.pStockInt = pStockInt;
	}

	public String getpIntroStr() {
		return pIntroStr;
	}

	public void setpIntroStr(String pIntroStr) {
		this.pIntroStr = pIntroStr;
	}

	public int getpOpenInt() {
		return pOpenInt;
	}

	public void setpOpenInt(int pOpenInt) {
		this.pOpenInt = pOpenInt;
	}

	public String getpImageStr() {
		return pImageStr;
	}

	public void setpImageStr(String pImageStr) {
		this.pImageStr = pImageStr;
	}

	@Override
	public String toString() {
		return "ProductDto [pIndexInt=" + pIndexInt + ", pNameStr=" + pNameStr + ", pPriceInt=" + pPriceInt
				+ ", pStockInt=" + pStockInt + ", pIntroStr=" + pIntroStr + ", pOpenInt=" + pOpenInt + ", pImageStr="
				+ pImageStr + "]";
	}
	
	
	
}
