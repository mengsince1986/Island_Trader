package commands;
import java.util.ArrayList;
import trader.*;
import ships.*;
import map.*;

public class CommandHandler {
	
	private static Map map;
	private static Trader player;
	private static Ship ship;
	
	public CommandHandler(Map map, Trader player, Ship ship) {
		CommandHandler.map = map;
		CommandHandler.player = player;
		CommandHandler.ship = ship;
	}

	public static String processCommand(ArrayList<String> commandArguments) {
		
		String report = new String();
		String keyWord = commandArguments.get(0);
		String argument = commandArguments.get(1);
		System.out.println(commandArguments);
		
		if (keyWord != "cancel" && argument != "cancel") { //move into switch block
			switch(keyWord) {
			case "sail": 
				report = processSailCommand(argument);
				break;
			
			} 
		} else {
				report = "Cancelled!";
			}

		return report;	
	}
	
	public static String processSailCommand(String destination) {
		Island island = map.getIsland(destination);
		ArrayList<String> reportList = ship.sailTo(island);
		String report = "Sailing ... ...\n";
		
		for (String event : reportList) {
			report += event;
		}
		
		return report;
	}
	
	public static void main(String[] args) {
		WorldConstructor newWorld = new WorldConstructor();
		map = newWorld.getMap();
		System.out.println("Constructing game environment ...");
		System.out.println("A new world is created ... ");
		// create a new player
		String traderName = "Jon Snow"; // name and time can be read from constructorIO
		player = new Trader(30, traderName, 10000, map.getIsland("Niawall Haven"), "port");
		ship = new BalancedShip(); // get user input + loop invoked by exception
		ship.setCaptain(player);
		player.setOwnedShip(ship);
		System.out.println("A new Trader named " + player.getName() + " is created ... ");
		System.out.println("A new Ship named " + player.getOwndedShip().getName() + " is created ...");
		System.out.println(player.getCurrentIsland().getRoutesString(ship));
		System.out.println(processSailCommand(player.getCurrentIsland().getRoutes().get(0).getDest().getName()));
		
	}

}
