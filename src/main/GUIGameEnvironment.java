package main;

import java.util.*;

import javax.sound.sampled.Port;

import events.RandomEvent;
import gui.PortWindow;
import gui.SetupWindow;
import gui.StoreWindow;
import items.Item;
import trader.Trader;
import map.Island;
import map.Route;
import map.Store;
import map.World;
import map.WorldConstructor;
import ships.Ship;
import terminalPrinter.StatusLine;

/**
 * The GUIGameEnvironment class is the entry point for the GUI version of the game. 
 * It is used to initialize an object working as a manager for {@link SetupWindow},
 * {@link PortWindow}, and {@link StoreWindow}. It launches, switches and
 * finishes the GUI window frames. A GUIGameEnvironment object stores the state
 * of the game and is a media for calling the methods from the back end to 
 * implement different commands from GUI interface.  
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class GUIGameEnvironment {

	/**
	 * Attribute world is a {@link World} object which stores the state of the {@link Ship},
	 * {@link Island}s, {@link Store}s, {@link Port}s, {@link RandomEvent}s, and
	 * {@link Route}s. 
	 */
	World world = null;
	/**
	 * Attribute player is a {@link Trader} object which stores the state of the
	 * trader. 
	 */
	Trader player = null;
	/**
	 * Attribute ship is a {@link Ship} object which stores the state of the
	 * trader's ship.
	 */
	Ship ship = null;

	/**
	 * This constructor sets the value of world attribute. It constructs a new
	 * world by initializing a {@link WorldConstructor} object.
	 */
	public GUIGameEnvironment() {

		// Constructing a new world
		WorldConstructor newWorld = new WorldConstructor();
		world = newWorld.getMap();
	}

	// Playing methods
	/**
	 * This method is the getter for the world attribute.
	 * @return the {@link World} object stored in world attribute
	 */
	public World getWorld() {
		return this.world;
	}

	/**
	 * This method is the getter for the player attribute.
	 * @return the {@link Trader} object stored in player attribute
	 */
	public Trader getTrader() {
		return this.player;
	}

	/**
	 * This method is the getter for the ship attribute.
	 * @return the {@link Ship} object stored in ship attribute
	 */
	public Ship getShip() {
		return this.ship;
	}
	
	/**
	 * This method creates a new {@link Trader} object, sets the trader's {@link Ship},
	 * and formats a string report of the new trader's profile.  
	 * @param name a name for the {@link Trader}
	 * @param days the number of the remaining days of the game
	 * @param homeName the name of the home island for the {@link Trader}
	 * @param shipName the name of the selected {@link Ship}
	 * @return a report of the new {@link Trader}'s profile
	 */
	public String setTrader(String name, int days, String homeName, String shipName) {

		Island home = this.world.getIsland(homeName);
		player = new Trader(days, name, home);

		ship = this.world.getShip(shipName);
		ship.setCaptain(player);
		player.setOwnedShip(ship);

		String report = "A new trader named " + player.getName() + " has been created!" 
		                + "\nYour ship is " + ship.getName()
		                + " and you will start from your home island " 
		                + player.getCurrentIsland().getName() 
		                + "\nThe playing time is " + days + " days.";
		return report;

	}
	
	/**
	 * This method gets the name of the player.
	 * @return the name of the {@link Trader}
	 */
	public String getTraderName() {
		
		String name = player.getName();
		return name;
		
	}

	/**
	 * This method gets the remaining days of player.
	 * @return the remaining days of the {@link Trader}
	 */
	public int getRemainingDays() {
		
		int days = player.getRemainingDays();
		return days;
		
	}
	
	/**
	 * This method gets the number of player's owned money.
	 * @return the number of the player's owned money
	 */
	public int getOwnedMoney() {
		
		int money = player.getOwnedMoney();
		return money;
		
	}
	
	/**
	 * This method gets the name of the island the player is located.
	 * @return the name of the island the player is located
	 */
	public String getCurrentIslandName() {
		
		String name = player.getCurrentIsland().getName();
		return name;
		
	}
	
	/**
	 * This method gets an ArrayList of the {@link Route} objects from the {@link Island}
	 * the player is located.
	 * @return an ArrayList of {@link Route} objects
	 */
	public ArrayList<Route> getCurrentRoutes() {
		
		ArrayList<Route> routes = player.getCurrentIsland().getRoutes();
		return routes;
		
	}
	
	/**
	 * This method gets the string description of the routes from the {@link Island}
	 * the player is located
	 * @return a String description of the available routes
	 */
	public String getRoutesDescription() {
		
		String routesDescription = player.getCurrentIsland().getRoutesString(ship);
		return routesDescription;
		
	}
	
	/**
	 * This method gets the string description of the player {@link Trader}. 
	 * @return a String description of the player
	 */
	public String getTraderDescription() {
		
		String traderDescription = player.toString();
		return traderDescription;
		
	}
	
	/**
	 * This method gets the string description of the player's trading logs.
	 * @return a String description of the player's trading logs.
	 */
	public String getTradingLogsDescription() {
		
		String tradingLogsDescription = player.getTradingLogsString();
		return tradingLogsDescription;
		
	}
	
	/**
	 * This method gets the string description of the player's {@link Ship}.
	 * @return a String description of the player's {@link Ship}
	 */
	public String getShipDescription() {
		
		String shipDescription = ship.toString();
		return shipDescription;
		
	}
	
	/**
	 * This method gets the string description of the {@link Ship}'s cargos
	 * @return a String description of the {@link Ship}'s cargos
	 */
	public String getCargosDescription() {
		
		String cargosDescription = ship.getCargosString();
		return cargosDescription;
		
	}
	
	/**
	 * This method gets the string description of the {@link Ship}'s upgrade logs.
	 * @return a String description of the {@link Ship}'s upgrade logs
	 */
	public String getUpgradeLogsDescription() {
		
		String upgradeLogsDescripion = ship.getUpgradeLogString();
		return upgradeLogsDescripion;
		
	}
	
	/**
	 * This method gets the number of cost of one cannon from the {@link Port} the
	 * player is located.
	 * @return the cost number of one cannon
	 */
	public int getCostPerCannon() {
		
		int cost = player.getCurrentIsland().getPort().getcannonCost();
		return cost;
		
	}
	
	/**
	 * This method calls the sailTo() method from {@link Ship}
	 * @param destination a String name of an island
	 * @return a String report of the sailing
	 */
	public String sail(String destination) {

		String report = "";

		Island destIsland = world.getIsland(destination);
		ArrayList<String> reportList = ship.sailTo(destIsland);
		report = "\n\n~~~ ~~~ ~~~ Sailing ~~~ ~~~ ~~~\n\n";

		for (String event : reportList) {
			report += event;
		}

		return report;

	}
	
	/**
	 * This method calls the repairShip() method from the player.
	 * @return a String report of the repair result
	 */
	public String repair() {
		
		String report = player.repairShip();
		return report;
		
	}
	
	/**
	 * This method calls the upgradeCannons() method from the player.
	 * @param cannonNum the number of the cannons to be added
	 * @return a String report of the cannon upgrade service
	 */
	public String upgradeCannons(int cannonNum) {
		
		String report = player.upgradeCannons(cannonNum);
		return report;
		
	}
	
	/**
	 * This method gets an ArrayList of {@link Item}s for sale from the {@link Store}
	 * the player is located.
	 * @return an ArrayList of {@link Item}s
	 */
	public ArrayList<Item> getItemsToSell() {
		
		ArrayList<Item> itemsToSell = player.getCurrentIsland().getStore().getToSell();
		return itemsToSell;
		
	}
	
	/**
	 * This method gets an ArrayList of {@link Item}s for the player to sell from
	 * the {@link Store} the player is located.
	 * @return an ArrayList of {@link Item}s
	 */
	public ArrayList<Item> getSellableTraderItems() {
		
		ArrayList<Item> sellableTraderItems = player.getCurrentIsland().getStore().getSellablePlayerItems(ship);
		return sellableTraderItems;
		
	}
	
	/**
	 * This method calls the buy() method from the player.
	 * @param itemName the String name of the {@link Item} to buy
	 * @param quantity the number of the quantity of the {@link Item} to buy
	 * @return a String report of the purchase
	 */
	public String buy(String itemName, int quantity) {
		
		String report = player.buy(player.getCurrentIsland(), 
				                   itemName, quantity);
		return report;
		
	}
	
	/**
	 * This method calls the sell() method from the player.
	 * @param itemName the String name of the {@link Item} to sell
	 * @param quantity the number of the quantity of the {@link Item} to sell
	 * @return a String report of the selling
	 */
	public String sell(String itemName, int quantity) {
		
		String report = player.sell(player.getCurrentIsland(), 
				                    itemName, quantity);
		return report;
		
	}
	
	/**
	 * This method gets the sale list from the {@link Store} the player is located. 
	 * @return a String sale list
	 */
	public String getListForSale() {
		
		String listForSale = player.getCurrentIsland().getStore().forSale();
		return listForSale;
		
	}
	
	/**
	 * This method gets the the list of items the {@link Store} is looking to buy.
	 * @return a String list of items the {@link Store} to buy
	 */
	public String getListToPurchase() {
		
		String listToPurchase = player.getCurrentIsland().getStore().forPurchase();
		return listToPurchase;
		
	}

	/**
	 * This method gets the summary of the player's performance from {@link StatusLine}.
	 * @return a String report of the player's performance
	 */
	public String gameOver() {

		StatusLine summary = new StatusLine(player);
		String gameOverReport = summary.getGameOverReport("");
		
		return gameOverReport;
	}

	
	// Window managing methods
	
	// SetupWindow
	/**
	 * This method initializes a new {@link SetupWindow} object.
	 */
	public void launchSetupWindow() {
		@SuppressWarnings("unused")
		SetupWindow setupWindow = new SetupWindow(this);
	}

	/**
	 * This method closes the {@link SetupWindow} and launches the {@link PortWindow}.
	 * @param setupWindow a {@link SetupWindow} porject
	 */
	public void closeSetupWindow(SetupWindow setupWindow) {
		setupWindow.closeSetupWindow();
		launchPortWindow();
	}

	// PortWindow
	/**
	 * This method initializes a new {@link PortWindow} object.
	 */
	public void launchPortWindow() {
		@SuppressWarnings("unused")
		PortWindow portWindow = new PortWindow(this);
	}

	/**
	 * This method closes the {@link PortWindow} and launches the {@link StoreWindow}.
	 * @param portWindow a {@link PortWindow} object
	 */
	public void closePortWindow(PortWindow portWindow) {
		portWindow.closePortWindow();
		// launch store window next
		launchStoreWindow();
	}

	/**
	 * This method closes the {@link PortWindow} and launches the {@link SetupWindow}.
	 * @param portWindow a {@link PortWindow} object
	 */
	public void restartSetupWindow(PortWindow portWindow) {
		ship.setDurability(ship.getDefaultDurability());
		portWindow.closePortWindow();
		launchSetupWindow();
	}

	// StoreWindow
	/**
	 * This method initializes a new {@link StoreWindow} object.
	 */
	public void launchStoreWindow() {
		@SuppressWarnings("unused")
		StoreWindow storeWindow = new StoreWindow(this);
	}

	/**
	 * This method closes the {@link StoreWindow} and launches the {@link PortWindow}.
	 * @param storeWindow a {@link StoreWindow} object
	 */
	public void closeStoreWindow(StoreWindow storeWindow) {
		storeWindow.closeStoreWindow();
		// launch port window next
		launchPortWindow();
	}

	/**
	 * This main method initializes a new GUIGameEnvironment object and launches
	 * the {@link SetupWindow}.
	 * @param args default arguments for main method 
	 */
	public static void main(String[] args) {

		GUIGameEnvironment manager = new GUIGameEnvironment();

		manager.launchSetupWindow();

	}

}
