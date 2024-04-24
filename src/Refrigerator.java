
public class Refrigerator extends Product{
	private String doorDesign;
	private String color;
	private double capacity;
	
	//constructor 
	public Refrigerator(int num, String name, double price, int quantity, String design,String color, double cap) {
		super(num,name,price,quantity);
		doorDesign=design;
		this.color=color;
		capacity=cap;
	}

	public String getDoorDesign() {
		return doorDesign;
	}

	public String getColor() {
		return color;
	}

	public double getCapacity() {
		return capacity;
	}
	//setter
	public void setDoorDesign(String doorDesign) {
		this.doorDesign = doorDesign;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setCapacity(double  capacity) {
		this.capacity = capacity;
	}
	//get inventory value
	public double getInventoryValue() {
		return quantity*price;
	}

	public String toString() {
		return "Item number\t:" + number + "\nProduct name\t:" + name + 
				"\nDoor Design\t:" + doorDesign +
				"\nColor\t\t:" + color +
				"\nCapacity (in Litres) :" + capacity +
				"\nQuantity available:" + quantity + 
				"\nPrice (RM)\t:" + price + 
				"\nInventory value (RM) : " + getInventoryValue() + 
				"\nProduct Status\t:" + status;
	}
}
