package tools;

public class AKDecorator extends ToolDecorator
{
	public AKDecorator(Tool tool)
	{
		super("Accessory kit", 0.50, tool);
	}
}
