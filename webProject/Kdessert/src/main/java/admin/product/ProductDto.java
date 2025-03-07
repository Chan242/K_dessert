package admin.product;

import java.util.Date;

public class ProductDto {
	
	private int proIndexInt;
	private String proNameStr;
	private int proPriceInt;
	private int proStockInt;
	private String proIntroStr;
	private int proOpenInt;
	private String proImageStr;
	private Date proCreDateDate;
	private Date proChanDateDate;
	
	public ProductDto() {
		super();
	}
	
	public ProductDto(int proIndexInt, String proNameStr, int proPriceInt, int proStockInt, int proOpenInt, Date proCreDateDate, Date proChanDateDate) {
		super();
		this.proIndexInt = proIndexInt;
		this.proNameStr = proNameStr;
		this.proPriceInt = proPriceInt;
		this.proStockInt = proStockInt;
		this.proOpenInt = proOpenInt;
		this.proCreDateDate = proCreDateDate;
		this.proChanDateDate = proChanDateDate;
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

	public int getProOpenInt() {
		return proOpenInt;
	}

	public void setProOpenInt(int proOpenInt) {
		this.proOpenInt = proOpenInt;
	}

	public String getProImageStr() {
		return proImageStr;
	}

	public void setProImageStr(String proImageStr) {
		this.proImageStr = proImageStr;
	}
	
	
	

	public Date getProCreDateDate() {
		return proCreDateDate;
	}

	public void setProCreDateDate(Date proCreDateDate) {
		this.proCreDateDate = proCreDateDate;
	}

	public Date getProChanDateDate() {
		return proChanDateDate;
	}

	public void setProChanDateDate(Date proChanDateDate) {
		this.proChanDateDate = proChanDateDate;
	}

	@Override
	public String toString() {
		return "ProductDto [proIndexInt=" + proIndexInt + ", proNameStr=" + proNameStr + ", proPriceInt=" + proPriceInt
				+ ", proStockInt=" + proStockInt + ", proIntroStr=" + proIntroStr + ", proOpenInt=" + proOpenInt
				+ ", proImageStr=" + proImageStr + ", proCreDateDate=" + proCreDateDate + ", proChanDateDate="
				+ proChanDateDate + "]";
	}


	
	
	
}
