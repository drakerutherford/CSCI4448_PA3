package store;

import java.util.concurrent.ThreadLocalRandom;

import tools.AKDecorator;
import tools.ECDecorator;
import tools.PGPDecorator;
import tools.Tool;

public class BusinessStrategy extends RentalStrategy
{
	
	public BusinessStrategy() {
		this.type = "Business";
	}
	
	@Override
	public Rental rentTools(int rentday, Customer customer, Inventory inventory)
	{
		Rental rental = new Rental(customer, 7, rentday);
		for (int i = 0; i < 3; i++) {
			Tool rented = inventory.getRandomTool();
			rented.setRented(true);
			int numAccessories = ThreadLocalRandom.current().nextInt(0, 7);
			for (int j = 0; j < numAccessories; j++) {
				int accessory = ThreadLocalRandom.current().nextInt(0, 3);
				switch (accessory) {
				case 0: rented = new AKDecorator(rented); break;
				case 1: rented = new ECDecorator(rented); break;
				case 2: rented = new PGPDecorator(rented); break;
				}
			}
			rental.addTool(rented);
		}
		return rental;
	}

}