package store;

import java.util.ArrayList;

import tools.Tool;

public class Rental
{
	private Customer customer;
	private ArrayList<Tool> rentedTools;
	private int days;
	private int rentday;
	private boolean finished = false;
	
	public Rental(Customer customer, int days, int rentday) {
		this.customer = customer;
		this.days = days;
		this.rentday = rentday;
		rentedTools = new ArrayList<Tool>();
	}
	
	public void addTool(Tool tool) {
		rentedTools.add(tool);
	}
	
	public int getSize() {
		return rentedTools.size();
	}
	
	public double getTotalCost() {
		double ret = 0.00;
		for (Tool tool : rentedTools) {
			ret += tool.getCost(days);
		}
		return ret;
	}
	
	public String rentalFormat() {
		String rental = customer.getName() + " [" + customer.getType() + "]";
		double totalCost = 0;
		rental += "\nStarted on " + rentday + " for " + days + " days\n--------";
		for (Tool tool : rentedTools) {
			rental += "\n" + tool.getRentalName();
			totalCost += tool.getCost(days);
		}
		rental += "\nTotal cost: " + totalCost + "\n";
		return rental;
	}
	
	public void returnRental(int day) {
		if ((day - rentday) >= days) {
			this.finished = true;
			for (Tool tool : rentedTools) {
				tool.setRented(false);
			}
		}
	}
	
	public boolean getFinished() {
		return finished;
	}
	
	public String getType() {
		return customer.getType();
	}
}
