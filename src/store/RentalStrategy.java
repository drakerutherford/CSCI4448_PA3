package store;

public abstract class RentalStrategy
{
	protected String type;
	
	public String getType() {
		return type;
	}
	
	public abstract Rental rentTools(int rentday, Customer customer, Inventory inventory);
}
