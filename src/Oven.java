
public class Oven extends Product{
	private String type;
	private String color;
	private int power;
	
	public Oven(int num, String name, double price, int quantity, String type, String color, int pwr) {
		super(num,name,price,quantity);
		this.type= type;
		this.color= color;
		power= pwr;
	}

	public String getType() {
		return type;
	}

	public String getColor() {
		return color;
	}

	public int getPower() {
		return power;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	//calculate inventory value
	public double getInventoryValue() {
		return quantity*price;
	}
}
