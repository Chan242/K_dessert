package admin.order;

public class OrderDto {
	int orderIndexInt = 0;
	int productIndexInt = 0;
	int orderStock = 0;
	int orderProductPrice = 0;
	
	
	
	public OrderDto() {
		super();
	}



	public OrderDto(int orderIndexInt, int productIndexInt, int orderStock, int orderProductPrice) {
		super();
		this.orderIndexInt = orderIndexInt;
		this.productIndexInt = productIndexInt;
		this.orderStock = orderStock;
		this.orderProductPrice = orderProductPrice;
	}



	public int getOrderIndexInt() {
		return orderIndexInt;
	}



	public void setOrderIndexInt(int orderIndexInt) {
		this.orderIndexInt = orderIndexInt;
	}



	public int getProductIndexInt() {
		return productIndexInt;
	}



	public void setProductIndexInt(int productIndexInt) {
		this.productIndexInt = productIndexInt;
	}



	public int getOrderStock() {
		return orderStock;
	}



	public void setOrderStock(int orderStock) {
		this.orderStock = orderStock;
	}



	public int getOrderProductPrice() {
		return orderProductPrice;
	}



	public void setOrderProductPrice(int orderProductPrice) {
		this.orderProductPrice = orderProductPrice;
	}
	
	
	
	
	

}
