package main;

import java.util.*;

import gui.PortWindow;
import gui.SetupWindow;
import gui.StoreWindow;
import items.Item;
import trader.Trader;
import map.Island;
import map.Route;
import map.World;
import map.WorldConstructor;
import ships.Ship;
import terminalPrinter.StatusLine;

/**
 * The GUIGameEnvironment class is the starter of the GUI interface. It is used
 * to initialize an object working as a manager for {@link SetupWindow},
 * {@link PortWindow}, and {@link StoreWindow}. It launches, switches and
 * finishes the GUI window frames. A GUIGameEnvironment object stores the state
 * of the game and is a media for calling the methods from the back end to 
 * implement different commands from GUI interface.  
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class GUIGameEnvironment {

	World world = null;
	Trader player = null;
	Ship ship = null;

	public GUIGameEnvironment() {

		// Constructing a new world
		WorldConstructor newWorld = new WorldConstructor();
		world = newWorld.getMap();
	}

	// playing methods
	public World getWorld() {
		return this.world;
	}

	public Trader getTrader() {
		return this.player;
	}

	public Ship getShip() {
		return this.ship;
	}
	
	// setter methods still return report for GUI report display
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
	
	public String getTraderName() {
		
		String name = player.getName();
		return name;
		
	}

	public int getRemainingDays() {
		
		int days = player.getRemainingDays();
		return days;
		
	}
	
	public int getOwnedMoney() {
		
		int money = player.getOwnedMoney();
		return money;
		
	}
	
	public String getCurrentIslandName() {
		
		String name = player.getCurrentIsland().getName();
		return name;
		
	}
	
	public ArrayList<Route> getCurrentRoutes() {
		
		ArrayList<Route> routes = player.getCurrentIsland().getRoutes();
		return routes;
		
	}
	
	public String getRoutesDescription() {
		
		String routesDescription = player.getCurrentIsland().getRoutesString(ship);
		return routesDescription;
		
	}
	
	public String getTraderDescription() {
		
		String traderDescription = player.toString();
		return traderDescription;
		
	}
	
	public String getTradingLogsDescription() {
		
		String tradingLogsDescription = player.getTradingLogsString();
		return tradingLogsDescription;
		
	}
	
	public String getShipDescription() {
		
		String shipDescription = ship.toString();
		return shipDescription;
		
	}
	
	public String getCargosDescription() {
		
		String cargosDescription = ship.getCargosString();
		return cargosDescription;
		
	}
	
	public String getUpgradeLogsDescription() {
		
		String upgradeLogsDescripion = ship.getUpgradeLogString();
		return upgradeLogsDescripion;
		
	}
	
	public int getCostPerCannon() {
		
		int cost = player.getCurrentIsland().getPort().getcannonCost();
		return cost;
		
	}
	
	public String sail(String destination) {

		String report = "";

		Island destIsland = world.getIsland(destination);
		ArrayList<String> reportList = ship.sailTo(destIsland);
		report = "~~~ ~~~ ~~~ Sailing ~~~ ~~~ ~~~\n\n";

		for (String event : reportList) {
			report += event;
		}

		return report;

	}
	
	public String repair() {
		
		String report = player.repairShip();
		return report;
		
	}
	
	public String upgradeCannons(int cannonNum) {
		
		String report = player.upgradeCannons(cannonNum);
		return report;
		
	}
	
	public ArrayList<Item> getItemsToSell() {
		
		ArrayList<Item> itemsToSell = player.getCurrentIsland().getStore().getToSell();
		return itemsToSell;
		
	}
	
	public ArrayList<Item> getSellableTraderItems() {
		
		ArrayList<Item> sellableTraderItems = player.getCurrentIsland().getStore().getSellablePlayerItems(ship);
		return sellableTraderItems;
		
	}
	
	public String buy(String itemName, int quantity) {
		
		String report = player.buy(player.getCurrentIsland(), 
				                   itemName, quantity);
		return report;
		
	}
	
	public String sell(String itemName, int quantity) {
		
		String report = player.sell(player.getCurrentIsland(), 
				                    itemName, quantity);
		return report;
		
	}
	
	public String getListForSale() {
		
		String listForSale = player.getCurrentIsland().getStore().forSale();
		return listForSale;
		
	}
	
	public String getListToPurchase() {
		
		String listToPurchase = player.getCurrentIsland().getStore().forPurchase();
		return listToPurchase;
		
	}

	public String gameOver() {

		StatusLine summary = new StatusLine(player);
		String gameOverReport = summary.getGameOverReport("");
		
		return gameOverReport;
	}

	
	// window methods
	// SetupWindow
	public void launchSetupWindow() {
		@SuppressWarnings("unused")
		SetupWindow setupWindow = new SetupWindow(this);
	}

	public void closeSetupWindow(SetupWindow setupWindow) {
		setupWindow.closeSetupWindow();
		launchPortWindow();
	}

	// PortWindow
	public void launchPortWindow() {
		@SuppressWarnings("unused")
		PortWindow portWindow = new PortWindow(this);
	}

	public void closePortWindow(PortWindow portWindow) {
		portWindow.closePortWindow();
		// launch store window next
		launchStoreWindow();
	}

	public void restartSetupWindow(PortWindow portWindow) {
		portWindow.closePortWindow();
	
		launchSetupWindow();
	}

	// StoreWindow
	public void launchStoreWindow() {
		@SuppressWarnings("unused")
		StoreWindow storeWindow = new StoreWindow(this);
	}

	public void closeStoreWindow(StoreWindow storeWindow) {
		storeWindow.closeStoreWindow();
		// launch port window next
		launchPortWindow();
	}

	public static void main(String[] args) {

		GUIGameEnvironment manager = new GUIGameEnvironment();

		manager.launchSetupWindow();

	}

}
