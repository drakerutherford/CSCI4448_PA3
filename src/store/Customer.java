package store;

import java.util.ArrayList;

//different types of customers are implemented with the strategy pattern
public class Customer
{
	private ArrayList<Rental> currentRentals;
	private RentalStrategy strategy;
	private String name;
	
	public int numTools() {
		int numTools = 0;
		for (Rental r : currentRentals) {
			numTools += r.getSize();
		}
		return numTools;
	}
	
	public Customer(String name, String type) {
		this.name = name;
		currentRentals = new ArrayList<Rental>();
		if (type.equals("Casual")) strategy = new CasualStrategy();
		else if (type.equals("Business")) strategy = new BusinessStrategy();
		else if (type.equals("Regular")) strategy = new RegularStrategy();
		else throw new IllegalArgumentException("Non-valid customer type " + type);
	}
	
	public String getType() {
		return strategy.getType();
	}
	
	public String getName() {
		return name;
	}
	
	public Rental rentTools(Inventory inventory, int rentday) {
		Rental rental = strategy.rentTools(rentday, this, inventory);
		currentRentals.add(rental);
		return rental;
	}
	
	public boolean validCust(Inventory inventory) {
		if (this.getType() == "Business") {
			return (this.numTools() < 3 && inventory.getNumInStock() >= 3);
		}
		else return this.numTools() < 3;
	}
	
	public void returnRentals(int day) {
		ArrayList<Rental> remove = new ArrayList<Rental>();
		for (Rental r : currentRentals) {
			r.returnRental(day);
			if (r.getFinished()) remove.add(r);
		}
		for (Rental r : remove) {
			currentRentals.remove(r);
		}
	}
}
