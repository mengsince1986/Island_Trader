package main;
import java.util.*;

import commands.Commands;
import io.PortIO;
import trader.Trader;
import map.Island;
import map.Map;
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

public class GameEnvironment {
	
   /**
   * This is the main method which creates a new player and starts the game.
   * @param args Unused.
   * @return Nothing.
   */
	
	public static void main(String[] args) {
		
		//Constructing
		Map map;
		Trader player;
		Ship ship;
		
		System.out.println("Welcome to the wolrd of Island Trader");
		System.out.println();
		// constructing a new map
		// MZ: can put new world and new player into a big constructor which
		// returns map, player and ship
		// print with constructor printer
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
		System.out.println();
		System.out.println("===== All Set. Let's get started!=====");
		
		
		// Playing
		
		
		boolean gameOver = false;
		
		while (!gameOver) {
			PortIO portIO = new PortIO(player);
			Commands commands = new Commands(map, player, ship);
			StatusLine statusLine = new StatusLine(player, ship);
			ReportPrinter reportPrinter = new ReportPrinter();
			statusLine.printStatusLine();
			
			if (player.getCurrentLocation() == "port") {
				ArrayList<String> command = portIO.read();// read player's input
				String report = commands.processCommands(command);
				reportPrinter.printReport(report);
			} else if (player.getCurrentLocation() == "store") {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("The store is not ready.");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
			}
			//gameOver = true;
			
		}
		

	}

}
