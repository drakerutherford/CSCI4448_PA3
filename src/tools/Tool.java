package tools;
//the abstract Tool class handles the logic of cost and formatting rental output
public abstract class Tool
{
	protected String name;
	protected String category;
	protected double price;
	private boolean rented = false;
	
	protected Tool(String name, String category, double price) {
		this.name = name;
		this.category = category;
		this.price = price;
	}
	
	public double getCost(int days) {
		return price * days;
	}
	
	public String getRentalName() {
		return name + " [" + category + "]";
	}
	
	public void setRented(boolean set) {
		rented = set;
	}
	
	public boolean getRented() {
		return rented;
	}
}
