package main;
import java.util.*;

import commands.TraderCreatorHandler;
import gui.SetupWindow;
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
	
	public World getWorld() {
		return this.world;
	}
	
	public Trader getTrader() {
		return this.player;
	}
	
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
	
	public String setShip(String shipName) {
		
		String report = "A new ship has been created!";

		Ship ship = this.world.getShip(shipName);
		
		if (player != null) {
			ship.setCaptain(player);
			player.setOwnedShip(ship);
		}
		
		return report;
		
	}
	
	
	
	
	

	
	// window methods
	
	public void launchSetupWindow() {
		SetupWindow setupWindow = new SetupWindow(this);
	}
	
	public void closeSetupWindow(SetupWindow setupWindow) {
		setupWindow.closeWindow();
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
