package main;
import java.util.*;

import commands.TraderCreatorHandler;
import commands.CommandHandler;
import io.*;
import trader.Trader;
import map.World;
import map.WorldConstructor;
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
		
		World world = null;
		Trader player = null;
		Ship ship = null;
		boolean constructed = false;
		
		//Stage 1: Constructing a new world
		WorldConstructor newWorld = new WorldConstructor();
		world = newWorld.getMap();
		
		
		//Stage 2: Creating new player and new ship
		/*
		NewPlayerConstructorIO newPlayerConstructorIO = new NewPlayerConstructorIO(world);
		
		new TraderCreatorHandler(world);
		
		while (!constructed) {
			
			ArrayList<String> NewPlayerArguments =  newPlayerConstructorIO.readCommandArguments();
			String newPlayerReport = TraderCreatorHandler.createPlayer(NewPlayerArguments);
			
			player = TraderCreatorHandler.getNewPlayer();
			ship = TraderCreatorHandler.getNewShip();
			
			ReportPrinter.printReport(newPlayerReport);
			
			if (player != null && ship != null) {
				constructed = true;
			}
		}
		

		/*
	    // Manually create a new world, a trader and a ship, only for quick test
		
		System.out.println("Welcome to the wolrd of Island Trader");
		System.out.println();
		// constructing a new map
		// MZ: can put new world and new player into a big constructor which
		// returns map, player and ship
		// print with constructor printer
		//WorldConstructor newWorld = new WorldConstructor();
		//world = newWorld.getMap();
		System.out.println("Constructing game environment ...");
		System.out.println("A new world is created ... ");
		// create a new player
		String traderName = "Alice"; // name and time can be read from constructorIO
		player = new Trader(50, traderName, 20000, world.getIsland("Ceylon"), "port");
		ship = new Ship("Black Pearl", 10, 2, 1500, 6, 70, "fast"); 
		ship.setCaptain(player);
		player.setOwnedShip(ship);
		System.out.println("A new Trader named " + player.getName() + " is created ... ");
		System.out.println("A new Ship named " + player.getOwndedShip().getName() + " is created ...");
		System.out.println();
		System.out.println("========= All Set. Let's get started!=========");
		System.out.println();
		constructed = true;
		*/
		
		// Stage3: Playing
		
		new CommandHandler(world, player, ship);
		StatusLine statusLine = new StatusLine(player, ship);
		PortIO portIO = new PortIO(player);
		
		boolean gameOver = false;
		
		while (constructed && !gameOver) {
			
			statusLine.printStatusLine();
			StoreIO storeIO = new StoreIO(player);
			
			if (player.getCurrentLocation() == "port") {
				
				ArrayList<String> commandArguments = portIO.readCommandArguments("Captain, what next?");
				String report = CommandHandler.processCommand(commandArguments);
				ReportPrinter.printReport(report);
				
			} else if (player.getCurrentLocation() == "store") {

				ArrayList<String> commandArguments = storeIO.readCommandArguments("What would you like to do?");
				String report = CommandHandler.processCommand(commandArguments);
				ReportPrinter.printReport(report);
				
			}
			if (player.noTimeToSail()) {
				statusLine.printGameOverReport("You didn't have enough days left to sail anywhere!");
				gameOver = true;
			} else if (player.noMoneyToSail()) {
				if (player.getKilledByPirates()) {
					statusLine.printGameOverReport("You're dead!");
					gameOver = true;
				} else {
				statusLine.printGameOverReport("You ran out of money to sail anywhere!");
				gameOver = true;
				}
			}
			
		}

	}

}
