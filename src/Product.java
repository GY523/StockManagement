
public abstract class Product {
	protected int number;
	protected String name;
	protected double price;
	protected int quantity;
	protected boolean status;
	
	//default constructor
	public Product() {
		this(0,"",0.0,0);
	}
	//constructors with four parameters
	public Product(int num, String name, double price, int quantity) {
		number=num;
		this.name=name;
		this.price=price;
		this.quantity=quantity;
		status=true;
	}
	
	//getter
	public int getNumber() {
		return number;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public boolean isStatus() {
		return status;
	}
	//setter
	public void setNumber(int number) {
		this.number = number;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	//instance method: a) get inventory value
	public double getInventoryValue() {
		return quantity*price;
	}
	//b) method to add and method to deduct both no return value
	public void addQuantity(int value) {
		if(status==false) {
			System.out.println("Not allow to add stock to a discontinued product");
			return;
		}
		quantity+=value;
	}
	public void deductQuantity(int value) {
		if(status==false) {
			System.out.println("Not allow to deduct stock to a discontinued product");
			return;
		}
		quantity-=value;
	}
	
	//override toString
	public String toString() {
		return "Item number\t:" + number + "\nProduct name\t:" + name + "\nQuantity available:" + quantity 
				+ "\nPrice (RM)\t:" + price + "\nInventory value (RM) : " + getInventoryValue() 
				+ "\nProduct Status\t:" + status;
	}
	
}
