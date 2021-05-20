package commands;

import java.util.ArrayList;
import trader.*;
import ships.*;
import map.*;

/**
* The CommandHandler is created for the command line interface. 
* It gets an ArrayList of commands in string form from IO objects,  
* invoke matching methods from Trader and Ship objects,
* and return a report in string to ReportPrinter object. 
* <p>
* The CommandHandler can be seen as a command processor getting user's inputs 
* from IO objects of io package and processes the inputs by calling matching 
* methods from other objects. 
* <p>
* @author  Finn van Dorsser
* @author  Meng Zhang
*/

public class CommandHandler {
	
	/**
	 * Attribute map is a World object which stores all the environment objects 
	 * like Islands, Stores, Ports, Routes and Items. It is used to get 
	 * arguments for calling sailing method.
	 * <p>
	 * Attribute player is the current player object. It is used to call 
	 * trading methods, repair method, cannon upgrade method and viewing status 
	 * methods.
	 * <p>
	 * Attribute ship is the current player's ship object. It is used to call
	 * sailing method, trading methods and Ship status viewing methods.
	 */ 
	private static World map;
	private static Trader player;
	private static Ship ship;

	/**
	 * Class constructor
	 * @param map
	 * @param player
	 * @param ship
	 */
	public CommandHandler(World map, Trader player, Ship ship) {
		CommandHandler.map = map;
		CommandHandler.player = player;
		CommandHandler.ship = ship;
	}

	/**
	 * This method takes an ArrayList of string commands, separates the command 
	 * name from its arguments and call their matching methods.
	 * @param commandArguments
	 * @return the string report for calling each command
	 */
	public static String processCommand(ArrayList<String> commandArguments) {

		String report = "";
		// System.out.println(commandArguments);
		String keyWord = commandArguments.get(0);
		String argument = commandArguments.get(1);

		if (!commandArguments.contains("cancel")) {
			int quantity = 0;
			if (commandArguments.size() == 3) {
				quantity = Integer.parseInt(commandArguments.get(2));
			}

			switch (keyWord) {

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
	
	/**
	 * This method calls the sailing method of the ship object.
	 * @param destination
	 * @return a string report of the random events which happened during sailing
	 */
	public static String processSailCommand(String destination) {
		Island island = map.getIsland(destination);
		ArrayList<String> reportList = ship.sailTo(island);
		String report = "Sailing ~~~ ~~~ ~~~\n\n";

		for (String event : reportList) {
			report += event;
		}

		return report;
	}

	/**
	 * This method calls the buying method of the Trader object.
	 * @param itemName
	 * @param quantity
	 * @return a string report about the result of the purchase
	 */
	public static String processBuyCommand(String itemName, int quantity) {
		Island currentIsland = player.getCurrentIsland();
		String report = player.buy(currentIsland, itemName, quantity);
		return report;
	}

	/**
	 * This method calls the selling method of the Trader object.
	 * @param itemName
	 * @param quantity
	 * @return a string report about the result of the selling 
	 */
	public static String processSellCommand(String itemName, int quantity) {
		Island currentIsland = player.getCurrentIsland();
		String report = player.sell(currentIsland, itemName, quantity);
		return report;
	}

	/**
	 * This method calls the Trading log getter of the Trader object.
	 * @param itemName
	 * @param quantity
	 * @return a string report including all the trading logs
	 */
	public static String processViewLogsCommand() {
		return player.getTradingLogsString();
	}

	/**
	 * This method calls the status getter, cargos getter and upgrade log getter 
	 * of the Ship object.
	 * @param itemName
	 * @param quantity
	 * @return a string report including the ship, cargo status and upgrade logs.
	 */
	public static String processViewShipCommand() {
		Ship playerShip = player.getOwndedShip();
		String report = playerShip.toString() + "\n\n" + 
		                playerShip.getCargosString() + "\n" + 
				        playerShip.getUpgradeLogString();
		return report;
	}

	public static String processVisitStore() {
		player.setCurrentLocation("store");
		String report = "Welcome to the store!\n\n";
		report += player.getCurrentIsland().getStore().forSale() + "\n";
		report += player.getCurrentIsland().getStore().forPurchase();
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
		 * WorldConstructor newWorld = new WorldConstructor(); map = newWorld.getMap();
		 * System.out.println("Constructing game environment ...");
		 * System.out.println("A new world is created ... "); // create a new player
		 * String traderName = "Jon Snow"; // name and time can be read from
		 * constructorIO player = new Trader(30, traderName, 10000,
		 * map.getIsland("Niawall Haven"), "port"); ship = new BalancedShip(); // get
		 * user input + loop invoked by exception ship.setCaptain(player);
		 * player.setOwnedShip(ship); System.out.println("A new Trader named " +
		 * player.getName() + " is created ... ");
		 * System.out.println("A new Ship named " + player.getOwndedShip().getName() +
		 * " is created ...");
		 * System.out.println(player.getCurrentIsland().getRoutesString(ship));
		 * System.out.println(processSailCommand(player.getCurrentIsland().getRoutes().
		 * get(0).getDest().getName()));
		 */
	}

}
