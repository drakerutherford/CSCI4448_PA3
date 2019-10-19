package store;
import java.util.concurrent.ThreadLocalRandom;

import tools.AKDecorator;
import tools.ECDecorator;
import tools.PGPDecorator;
import tools.Tool;

public class CasualStrategy extends RentalStrategy
{
	public CasualStrategy() {
		this.type = "Casual";
	}
	
	@Override
	public Rental rentTools(int rentday, Customer customer, Inventory inventory)
	{
		int numDays = ThreadLocalRandom.current().nextInt(1, 3);
		int numTools;
		if (customer.numTools() > 1) numTools = 1;
		else numTools = ThreadLocalRandom.current().nextInt(1, 3);
		Rental rental = new Rental(customer, numDays, rentday);
		for (int i = 0; i < numTools; i++) {
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
