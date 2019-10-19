package main;

import store.Store;

public class Main
{

	public static void main(String[] args)
	{
		Store s = new Store();
		for (int i = 0; i < 35; i++) {
			s.simulateDay();
		}
		s.printEndStats();
	}

}
