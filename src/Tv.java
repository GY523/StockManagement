
public class Tv extends Product{
	private String screenType;
	private String resolution;
	private int displaySize;
	
	public Tv(int num, String name, double price, int quantity, String screenType, String resolution, int displaySize) {
		super(num,name,price,quantity);
		this.screenType=screenType;
		this.resolution=resolution;
		this.displaySize=displaySize;
	}

	public String getScreenType() {
		return screenType;
	}

	public String getResolution() {
		return resolution;
	}

	public int getDisplaySize() {
		return displaySize;
	}
	//setter
	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}
	//inventory value
	public double getInventoryValue() {
		return quantity*price;
	}
	
	public String toString() {
		return "Item number\t:" + number + "\nProduct name\t:" + name + 
				"\nScreen type\t:" + screenType +
				"\nResolution\t:" + resolution +
				"\nDisplay size\t:" + displaySize +
				"\nQuantity available:" + quantity + 
				"\nPrice (RM)\t:" + price + 
				"\nInventory value (RM) : " + getInventoryValue() + 
				"\nProduct Status\t:" + status;
	}
}
