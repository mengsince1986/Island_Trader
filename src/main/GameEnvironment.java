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
				
		// Stage3: Playing
		new CommandHandler(world, player, ship);
		StatusLine statusLine = new StatusLine(player);
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
				
				System.out.print(statusLine.getGameOverReport("You didn't have enough days left to sail anywhere!"));
				gameOver = true;
				
			} else if (player.getKilledByPirates()) {
				
				System.out.print(statusLine.getGameOverReport("You're dead!"));
				gameOver = true;
				
			} else if (player.noMoneyToSail()) {
				
				System.out.print(statusLine.getGameOverReport("You ran out of money to sail anywhere!"));
				gameOver = true;
				
			} else if (player.getHasQuit()) {
				
				System.out.print(statusLine.getGameOverReport("You quit the game!"));
				gameOver = true;
				
			}
			
		}

	}

}
