package commands;
import java.util.ArrayList;
import trader.*;
import ships.*;
import map.*;

public class CommandHandler {
	
	//MZ Is it necessary to make things static here?
	// we don't have any subclasses to share information 
	private static World map;
	private static Trader player;
	private static Ship ship;
	
	public CommandHandler(World map, Trader player, Ship ship) {
		CommandHandler.map = map;
		CommandHandler.player = player;
		CommandHandler.ship = ship;
	}

	public static String processCommand(ArrayList<String> commandArguments) {
		
		String report = "";
		System.out.println(commandArguments);
		String keyWord = commandArguments.get(0);
		String argument = commandArguments.get(1);
		
		if (!commandArguments.contains("cancel")) {
			int quantity = 0;
			if (commandArguments.size() == 3) {
				quantity = Integer.parseInt(commandArguments.get(2));
			}

			switch(keyWord) {
			
			case "sail": 
				report = processSailCommand(argument);
				break;
			case "buy":
				report = processBuyCommand(argument, quantity);
				break;
			case "sell":
				report = processSellCommand(argument, quantity);
				break;
			case "logs":
				report = processViewLogsCommand();
				break;
			case "ship":
				report = processViewShipCommand();
				break;
			case "store":
				report = processVisitStore();
				break;
			case "repair":
				report = processRepairCommand();
				break;
			case "upgrade":
				report = upgradeCannonCommand(Integer.parseInt(argument));
				break;
			case "quit":
				report = quitCommand();
				break;
			} 
		} else if (player.getCurrentLocation() == "store") {
			report = "Cancelling...\nBack at storefront!";
		} else {
			report = String.format("Back at %s!", player.getCurrentLocation());
		}

		return report;	
	}
	
	public static String processSailCommand(String destination) {
		Island island = map.getIsland(destination);
		ArrayList<String> reportList = ship.sailTo(island);
		String report = "Sailing ~~~ ~~~ ~~~\n\n";
		
		for (String event : reportList) {
			report += event;
		}
		
		return report;
	}
	
	public static String processBuyCommand(String itemName, int quantity) {
		Island currentIsland = player.getCurrentIsland();
		String report = player.buy(currentIsland, itemName, quantity);
		return report;
	}
	
	public static String processSellCommand(String itemName, int quantity) {
		Island currentIsland = player.getCurrentIsland();
		String report = player.sell(currentIsland, itemName, quantity);
		return report;
	}
	

	public static String processViewLogsCommand() {
		return player.getTradingLogsString();
	}
	
	public static String processViewShipCommand() {
		Ship playerShip = player.getOwndedShip();
		String report = playerShip.toString() +
				playerShip.getCargosString() +
				playerShip.getUpgradeLogString() + "\n";
		return report;
	}

	public static String processVisitStore() {
		player.setCurrentLocation("store");
		String report = "You go into the store on the island.";
		return report;
	}
	
	public static String processRepairCommand() {
		String report = player.repairShip();
		return report;
	}
	
	public static String upgradeCannonCommand(int cannonNum) {
		String report = player.upgradeCannons(cannonNum);
		return report;
	}
	
	public static String quitCommand() {
		String report = "Goodbye!";
		player.setRemainingDays(0);
		return report;
	}
	
	public static void main(String[] args) {
		/*
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
		*/
	}

}
