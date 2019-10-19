package tools;

//rental addons such as the accessory kit are implemented as decorators
public abstract class ToolDecorator extends Tool
{
	private Tool tool;
	
	//"category" field left blank since addons are categoryless
	public ToolDecorator(String name, double price, Tool tool)
	{
		super(name,"",price);
		this.tool = tool;
	}
	
	//add constant (not dependent on length of rental) cost to over rental cost
	@Override
	public double getCost(int days) {
		return price + tool.getCost(days);
	}
	
	//rentals are additionally formatted with a list of addons below the main tool
	@Override
	public String getRentalName() {
		return tool.getRentalName() + "\n+" + name;
	}
	
	@Override
	public void setRented(boolean set) {
		tool.setRented(set);
	}
	
	@Override
	public boolean getRented() {
		return tool.getRented();
	}
}
