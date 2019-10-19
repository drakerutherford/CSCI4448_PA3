package store;

import tools.Tool;
import java.util.ArrayList;
import java.util.Collections;

public class Inventory
{
	private ArrayList<Tool> inventory;
	
	public Inventory() {
		inventory = new ArrayList<Tool>();
	}
	
	public void addTool(Tool t) {
		inventory.add(t);
	}
	
	public Tool getRandomTool() {
		Collections.shuffle(inventory);
		int index = 0;
		while (inventory.get(index).getRented()) {
			index++;
		}
		return inventory.get(index);
	}
	
	public int getNumInStock() {
		int num = 0;
		for (Tool t : inventory) {
			if (!t.getRented()) num++;
		}
		return num;
	}
	
	public String availableInventory() {
		String ret = "";
		for (Tool t : inventory) {
			if (!t.getRented()) ret += t.getRentalName() + "\n";
		}
		return ret;
	}
}
