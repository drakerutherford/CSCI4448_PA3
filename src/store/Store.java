package store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import tools.ConcTool;
import tools.PaintTool;
import tools.PlumbTool;
import tools.WoodTool;
import tools.YardTool;

public class Store
{
	private Inventory inventory;
	private ArrayList<Customer> customers;
	private ArrayList<Rental> rentals;
	int currentDay = 0;
	double moneyMade = 0.00;
	
	public Store() {
		inventory = new Inventory();
		inventory.addTool(new ConcTool("Concrete 1"));
		inventory.addTool(new ConcTool("Concrete 2"));
		inventory.addTool(new ConcTool("Concrete 3"));
		inventory.addTool(new ConcTool("Concrete 4"));
		inventory.addTool(new ConcTool("Concrete 5"));
		inventory.addTool(new PaintTool("Paint 1"));
		inventory.addTool(new PaintTool("Paint 2"));
		inventory.addTool(new PaintTool("Paint 3"));
		inventory.addTool(new PaintTool("Paint 4"));
		inventory.addTool(new PaintTool("Paint 5"));
		inventory.addTool(new PlumbTool("Plumbing 1"));
		inventory.addTool(new PlumbTool("Plumbing 2"));
		inventory.addTool(new PlumbTool("Plumbing 3"));
		inventory.addTool(new PlumbTool("Plumbing 4"));
		inventory.addTool(new PlumbTool("Plumbing 5"));
		inventory.addTool(new WoodTool("Woodworking 1"));
		inventory.addTool(new WoodTool("Woodworking 2"));
		inventory.addTool(new WoodTool("Woodworking 3"));
		inventory.addTool(new WoodTool("Woodworking 4"));
		inventory.addTool(new WoodTool("Woodworking 5"));
		inventory.addTool(new YardTool("Yardworking 1"));
		inventory.addTool(new YardTool("Yardworking 2"));
		inventory.addTool(new YardTool("Yardworking 3"));
		inventory.addTool(new YardTool("Yardworking 4"));
		
		customers = new ArrayList<Customer>();
		customers.add(new Customer("Michael", "Regular"));
		customers.add(new Customer("David", "Regular"));
		customers.add(new Customer("John", "Business"));
		customers.add(new Customer("James", "Business"));
		customers.add(new Customer("Robert", "Casual"));
		customers.add(new Customer("Christopher", "Casual"));
		customers.add(new Customer("Jennifer", "Regular"));
		customers.add(new Customer("Lisa", "Regular"));
		customers.add(new Customer("Kimberly", "Business"));
		customers.add(new Customer("Michelle", "Business"));
		customers.add(new Customer("Angela", "Casual"));
		customers.add(new Customer("Maria", "Casual"));
		
		rentals = new ArrayList<Rental>();
	}
	
	public void simulateDay() {
		currentDay++;
		double moneymade = 0.00;
		
		for (Customer c : customers) c.returnRentals(currentDay);
		
		int numCustomers = ThreadLocalRandom.current().nextInt(1, customers.size()+1);
		int index = 0;
		int served = 0;
		Collections.shuffle(customers);
		while (served < numCustomers && index < customers.size() && inventory.getNumInStock() > 0) {
			while (!customers.get(index).validCust(inventory) && index < customers.size()) index++;
			if (index >= customers.size()) break;
			
			Customer c = customers.get(index);
			Rental r = c.rentTools(inventory, currentDay);
			rentals.add(r);
			moneyMade += r.getTotalCost();
			served++;
			index++;
		}
		printDay(moneymade);
		this.moneyMade += moneyMade;
	}
	
	private void printDay(double moneymade) {
		System.out.println("End of Day " + currentDay + " Stats\n");
		System.out.println("COMPLETED RENTALS\n========");
		for (Rental r : rentals) {
			if (r.getFinished()) System.out.println(r.rentalFormat());
		}
		System.out.println("\nACTIVE RENTALS\n========");
		for (Rental r : rentals) {
			if (!r.getFinished()) System.out.println(r.rentalFormat());
		}
		System.out.println("\nAVAILABLE INVENTORY\n========");
		System.out.println(inventory.availableInventory());
		System.out.println("\nMONEY MADE: " + moneymade + "\n");
	}
	
	public void printEndStats() {
		int casual = 0;
		int business = 0;
		int regular = 0;
		for (Rental r : rentals) {
			if (r.getFinished()) {
				switch (r.getType()) {
				case "Casual": casual++; break;
				case "Business": business++; break;
				case "Regular": regular++; break;
				}
			}
		}
		System.out.println("\nEND OF SIMULATION STATS\n========");
		System.out.println("Completed regular rentals: " + regular);
		System.out.println("Completed business rentals: " + business);
		System.out.println("Completed casual rentals: " + casual);
		System.out.println("Total completed rentals: " + regular+business+casual);
		System.out.println("Gross profit: " + moneyMade);
	}
}
