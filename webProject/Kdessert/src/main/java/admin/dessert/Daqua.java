package admin.dessert;

public class Daqua {
	private int id;
	private String name;
	private int price;
	private String category;
	private int stock;

	public int getID() { return id; } // ✅ getID → getId (일반적인 네이밍)
	public void setId(int id) { this.id = id; }
		// TODO Auto-generated method stub

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }	// ✅ name 필드 저장	
		
	public int getPrice() { return price; } 
	public void setPrice(int price) { this.price = price; } // ✅ setPrice 추가

	public String setCategory() { return category;}
	public void setCategory(String category) {this.category = category; }
	
	public int getStock() { return stock; }
	public void setStock(int stock) { this.stock = stock; }


}
