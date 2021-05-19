package main;
import java.util.*;

import commands.TraderCreatorHandler;
import gui.PortWindow;
import gui.SetupWindow;
import gui.StoreWindow;
import commands.CommandHandler;
import io.*;
import trader.Trader;
import map.Island;
import map.World;
import map.WorldConstructor;
import ships.BalancedShip;
import ships.Ship;
import terminalPrinter.*;

/**
 * <h1> SENG201 Project: Island Trader </h1>
 * <br>
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
	
	// setter methods still return report for GUI report display
	public String setTrader(String name, int days, String homeName, String shipName) {
		
		Island home = this.world.getIsland(homeName);
		player = new Trader(days, name, home);
		
		ship = this.world.getShip(shipName);
		ship.setCaptain(player);
		player.setOwnedShip(ship);
		
		String report = "A new trader " + player.getName() +
		                " has been created!" +
		                "\nYour ship is " + ship.getName() + ".";
		return report;
		
	}
	
	public Ship getShip() {
		return this.ship;
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
	
	public String repair() {
		
		String report = player.repairShip();
		
		return report;
	}
	
	public String buy(String itemName, int quantity) {
		
		String report = player.buy(player.getCurrentIsland(), itemName, quantity);
		
		return report;
	}
	
	public String sell(String itemName, int quantity) {
		
		String report = player.sell(player.getCurrentIsland(), itemName, quantity);
		
		return report;
	}
	
	public String gameOver() {
		
		String upperDivider = "----------------Game Over!--------------------";
		String lowerDivider = "----------------------------------------------";
		int playerProfit = player.getOwnedMoney() - player.getStartingMoney();
		int daysPlayed = player.getSelectedDays() - player.getRemainingDays();
		// MZ: Fixed bug. Update selected days in 2nd constructor of Trader class
		// or trader.selectedDays is 0 all the time
		int profitPerDay = Math.floorDiv(playerProfit, daysPlayed);
		
		String congratulation = "Trader name: " + this.player.getName() +
				"\nSelected game duration: " + 
				this.player.getSelectedDays() + " days" +
				"\nActual duration: " + daysPlayed + " days" +
				"\nYou made a profit of " + playerProfit + " coins\n";
		if (profitPerDay <= 0) {
			congratulation += "Your score: 0/3 stars. Better luck next time!";
		} else if (profitPerDay <= 500) {
			congratulation += "Score: 1/3 stars. Good effort! At least you managed to make something!";
		} else if (profitPerDay <= 1000) {
			congratulation += "Score: 2/3 stars! Either you got lucky or are getting pretty good!";
		} else {
			congratulation += "Score: 3/3 stars! Outstanding! You must be a god gamer!\n" +
					"Have you considered a career in arbitrage?";
		}
		String gameOverReport = upperDivider + "\n" + "\n" +
				congratulation + "\n" + 
				"\n" +
				lowerDivider;
		
		return gameOverReport;
	}
	
	// window methods
	
	// SetupWindow
	public void launchSetupWindow() {
		SetupWindow setupWindow = new SetupWindow(this);
	}
	
	public void closeSetupWindow(SetupWindow setupWindow) {
		setupWindow.closeSetupWindow();
		launchPortWindow();
	}

	// PortWindow
	public void launchPortWindow() {
		PortWindow portWindow = new PortWindow(this);
	}
	
	public void closePortWindow(PortWindow portWindow) {
		portWindow.closePortWindow();
		// launch store window next
		launchStoreWindow();
	}
	
	// StoreWindow
	public void launchStoreWindow() {
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
		
		// Stage 1: Constructing a new world
		//WorldConstructor newWorld = new WorldConstructor();
		//world = newWorld.getMap();

		// Stage 2: Creating new player and new ship
		// NewPlayerConstructorIO newPlayerConstructorIO = new
		// NewPlayerConstructorIO(world);
		// new TraderCreatorHandler(world);

		/*
		 * // Manually create a new world, a trader and a ship
		 * 
		 * System.out.println("Welcome to the wolrd of Island Trader");
		 * System.out.println(); // constructing a new map // MZ: can put new world and
		 * new player into a big constructor which // returns map, player and ship //
		 * print with constructor printer //WorldConstructor newWorld = new
		 * WorldConstructor(); //world = newWorld.getMap();
		 * System.out.println("Constructing game environment ...");
		 * System.out.println("A new world is created ... "); // create a new player
		 * String traderName = "Jon Snow"; // name and time can be read from
		 * constructorIO player = new Trader(50, traderName, 20000,
		 * world.getIsland("Ceylon"), "port"); ship = new BalancedShip(); // get user
		 * input + loop invoked by exception ship.setCaptain(player);
		 * player.setOwnedShip(ship); System.out.println("A new Trader named " +
		 * player.getName() + " is created ... ");
		 * System.out.println("A new Ship named " + player.getOwndedShip().getName() +
		 * " is created ..."); System.out.println();
		 * System.out.println("========= All Set. Let's get started!=========");
		 * System.out.println(); constructed = true;
		 */

		// Stage3: Playing

	}

}
