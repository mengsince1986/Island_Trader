package commands;

import java.util.ArrayList;

import map.Island;
import map.World;
import ships.Ship;
import trader.Trader;

public class TraderCreatorHandler {
	
	private static World world;
	private static Trader newPlayer;
	private static Ship newShip; 
	
	
	public TraderCreatorHandler(World newWorld) {
		
		world = newWorld;
		
	}
	
	public static String createPlayer(ArrayList<String> commandArguments) {
		
		String name = commandArguments.get(0);
		String daysStr = commandArguments.get(1);
		String homeIslandName = commandArguments.get(2);
		String shipName = commandArguments.get(3);
		
		int days = Integer.parseInt(daysStr);
		Island homeIsland = world.getIsland(homeIslandName);
		
		newPlayer = new Trader(days, name, homeIsland);
		newShip = world.getShip(shipName);
		newShip.setCaptain(newPlayer);
		newPlayer.setOwnedShip(newShip);
		
		String report = "Congratulations! A new trader has been created.\n\n" 
		                + newPlayer.toString() + "\n\n" 
				        + "Let's get started ... ";
		
		return report;
	}
	
	public static Trader getNewPlayer() {
		return newPlayer;
	}
	
	public static Ship getNewShip() {
		return newShip;
	}
}
