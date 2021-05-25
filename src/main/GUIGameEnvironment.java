package main;

import java.util.*;

import gui.PortWindow;
import gui.SetupWindow;
import gui.StoreWindow;
import trader.Trader;
import map.Island;
import map.World;
import map.WorldConstructor;
import ships.Ship;
import terminalPrinter.StatusLine;

/**
 * <h1>SENG201 Project: Island Trader</h1> <br>
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
		                + player.getCurrentIsland().getName() + ".";
		return report;

	}

	public String sail(String destination) {

		String report = "";

		Island destIsland = world.getIsland(destination);
		ArrayList<String> reportList = ship.sailTo(destIsland);
		report = "Sailing ~~~ ~~~ ~~~\n\n";

		for (String event : reportList) {
			report += event;
		}

		return report;

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
