package commands;

import java.util.ArrayList;
import trader.*;
import ships.*;
import map.*;

/**
 * The CommandHandler is created for the playing part of command line interface.
 * It gets an ArrayList of commands in string form from the IO object, invokes
 * matching methods from Trader and Ship objects, and returns a report in string
 * for the ReportPrinter object.
 * <p>
 * The CommandHandler is a command processor getting user's inputs from IO
 * objects of io package and processes the inputs by calling matching methods
 * from other objects.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class CommandHandler {
	
	/**
	 * Attribute map is a World object which stores all the environment objects 
	 * like Islands, Stores, Ports, Routes and Items. It is used to get 
	 * arguments for calling sailing method.
	 */ 
	private static World map;
	
	/**
	 * Attribute player is the current player object. It is used to call 
	 * trading methods, repair method, cannon upgrade method and viewing status 
	 * methods.
	 */ 
	private static Trader player;
	
	/**
	 * Attribute ship is the current player's ship object. It is used to call
	 * sailing method, trading methods and Ship status viewing methods.
	 */ 
	private static Ship ship;

	/**
	 * This constructor sets the values for all the attributes map, player and
	 * ship.
	 * @param map the current World object
	 * @param player the current Trader object
	 * @param ship the current Ship object
	 */
	public CommandHandler(World map, Trader player, Ship ship) {
		CommandHandler.map = map;
		CommandHandler.player = player;
		CommandHandler.ship = ship;
	}

	/**
	 * This method takes an ArrayList of string commands, separates the command 
	 * name from its arguments and calls their matching methods.
	 * @param commandArguments An ArrayList including all the commands and 
	 * arguments in string form
	 * @return the string report of implementing the command in the argument
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
				report = processVisitStoreCommand();
				break;
			case "repair":
				report = processRepairCommand();
				break;
			case "upgrade":
				report = processUpgradeCommand(Integer.parseInt(argument));
				break;
			case "quit":
				report = processQuitCommand();
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
	 * This method calls the sailing method of the ship object, iterate the
	 * string reports in the ArrayList returned by the sailing method and
	 * combine them into a single string. 
	 * @param destination a string name of the destination island name
	 * @return a string report of the random events which happen during this 
	 * sailing
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
	 * @param itemName the string name of the item on sale
	 * @param quantity the integer number of the item quantity
	 * @return the string report of the result of this purchase
	 */
	public static String processBuyCommand(String itemName, int quantity) {
		Island currentIsland = player.getCurrentIsland();
		String report = player.buy(currentIsland, itemName, quantity);
		return report;
	}

	/**
	 * This method calls the selling method of the Trader object.
	 * @param itemName the string name of the item to sell
 	 * @param quantity the integer number of the item quantity
	 * @return the string report of the result of this selling 
	 */
	public static String processSellCommand(String itemName, int quantity) {
		Island currentIsland = player.getCurrentIsland();
		String report = player.sell(currentIsland, itemName, quantity);
		return report;
	}

	/**
	 * This method calls the Trading log getter of the Trader object.
	 * @return the string report including all the trading logs
	 */
	public static String processViewLogsCommand() {
		return player.getTradingLogsString();
	}

	/**
	 * This method calls the status getter, cargos getter and upgrade log getter 
	 * of the Ship object, and combine them together to form a single report.
	 * @return the string report including the ship's basic properties, cargo 
	 * status and upgrade logs
	 */
	public static String processViewShipCommand() {
		Ship playerShip = player.getOwndedShip();
		String report = playerShip.toString() + "\n\n" + 
		                playerShip.getCargosString() + "\n" + 
				        playerShip.getUpgradeLogString();
		return report;
	}

	/**
	 * This method calls the for-sale getter and for-purchase getter from the 
	 * store object on the trader's located island and combine them as a single
	 * report.
	 * @return the string report including this store's items for sale list and 
	 * items to buy list.
	 */
	public static String processVisitStoreCommand() {
		player.setCurrentLocation("store");
		String report = "Welcome to the store!\n\n";
		report += player.getCurrentIsland().getStore().forSale() + "\n";
		report += player.getCurrentIsland().getStore().forPurchase();
		return report;
	}

	/**
	 * This method calls the repairShip method of the Trader object.
	 * @return the string report of the repair command
	 */
	public static String processRepairCommand() {
		String report = player.repairShip();
		return report;
	}

	/**
	 * This method takes an integer of the cannon number and calls the 
	 * upgradeCannons method of the Trader object.
	 * @param cannonNum the integer number of the cannons to be upgraded
	 * @return the string report of this cannon upgrade
	 */
	public static String processUpgradeCommand(int cannonNum) {
		String report = player.upgradeCannons(cannonNum);
		return report;
	}

	/**
	 * This method calls the link hasQuit setter of the Trader object and 
	 * sets hasQuit to true.
	 * @return a string report saying "Goodbye!"
	 */
	public static String processQuitCommand() {
		String report = "Goodbye!";
		player.setHasQuit(true);
		return report;
	}

}
