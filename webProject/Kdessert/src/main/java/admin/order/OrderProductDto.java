package admin.order;

public class OrderProductDto {
	private int productIndexInt = 0;
	private String productNameStr = "";
	private int productStockInt = 0;
	private int productPriceInt = 0;
	
	
	
	public OrderProductDto() {
		super();
	}






	public OrderProductDto(int productIndexInt, String productNameStr, int productStockInt, int productPriceInt) {
		super();
		this.productIndexInt = productIndexInt;
		this.productNameStr = productNameStr;
		this.productStockInt = productStockInt;
		this.productPriceInt = productPriceInt;
	}






	public int getProductIndexInt() {
		return productIndexInt;
	}



	public void setProductIndexInt(int productIndexInt) {
		this.productIndexInt = productIndexInt;
	}



	public String getProductNameStr() {
		return productNameStr;
	}



	public void setProductNameStr(String productNameStr) {
		this.productNameStr = productNameStr;
	}



	public int getProductStockInt() {
		return productStockInt;
	}



	public void setProductStockInt(int productStockInt) {
		this.productStockInt = productStockInt;
	}



	public int getProductPriceInt() {
		return productPriceInt;
	}



	public void setProductPriceInt(int productPriceInt) {
		this.productPriceInt = productPriceInt;
	}




	
	

}
