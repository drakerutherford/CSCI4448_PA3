package tools;

public class ECDecorator extends ToolDecorator
{

	public ECDecorator(Tool tool)
	{
		super("Extension cord", 0.40, tool);
	}
}
