package commands;

import java.util.ArrayList;

import map.Island;
import map.World;
import ships.Ship;
import trader.Trader;

/**
 * The TraderCreatorHandler is created for setting up a new player in the
 * command line interface. It gets an ArrayList of commands and profile
 * information of a new player in string form from the NewPlayerIO object,
 * invokes matching methods and creates a new Trader object and a new Ship project.
 * It also returns a report of implementing the command in string for the 
 * ReportPrinter object.
 * <p>
 * The TraderCreatorHandler saves the new created Trader object and Ship object
 * to its attributes for the use of GameEnvironment.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class TraderCreatorHandler {
	
	/**
	 * Attribute world is a World object which stores all the environment 
	 * objects like Islands, Stores, Ports, Routes and Items. It is used to get 
	 * the Island object with the name storing in the ArrayList of 
	 * commandArguments. 
	 * <p>
	 * Attribute newPlayer stores the new created Trader object. It will be passed
	 * to the GameEnvironment as the player object.
	 * <p>
	 * Attribute newShip stores the new created ship object. It will be passed
	 * to the GameEnvironment as the ship object.
	 */ 
	private static World world;
	private static Trader newPlayer;
	private static Ship newShip; 
	
	/**
	 * This constructor sets the value of attribute world.
	 * @param newWorld the current World object 
	 */
	public TraderCreatorHandler(World newWorld) {
		
		world = newWorld;
		
	}
	
	/**
	 * This method takes an ArrayList of string commands, separates the command 
	 * name from its arguments and call their matching methods. If the argument
	 * doesn't include commands "quit" or "cancel", this method will create a new
	 * Trader object and a new Ship object and store them in the class 
	 * attributes.
	 * @param commandArguments An ArrayList including all the commands and 
	 * arguments in string form
	 * @return a string report of implementing the command in the argument
	 */
	public static String createPlayer(ArrayList<String> commandArguments) {
		
		String report = "No trader created. Please restart the game.";
		
		if (commandArguments.contains("quit")) {
			report = quitCommand();
		} else if (commandArguments.contains("cancel")) {
			report = "You've cancelled it. You can create a new Trader again.";
		} else {
			String name = commandArguments.get(0);
			String daysStr = commandArguments.get(1);
			String homeIslandName = commandArguments.get(2);
			String shipName = commandArguments.get(3);
			
			int days = Integer.parseInt(daysStr);
			Island homeIsland = world.getIsland(homeIslandName);
			
			newPlayer = new Trader(days, name, homeIsland);
			newShip = world.getShip(shipName);
			newShip.setCaptain(newPlayer);
			newPlayer.setOwnedShip(newShip);
			
			report = "Congratulations! A new trader has been created.\n\n" 
			        + newPlayer.toString() + "\n\n" 
					+ "Let's get started ... ";
		}
		
		return report;
	}
	
	/**
	 * This method is the getter of the newPlayer attribute.
	 * @return the newly created Trader object
	 */
	public static Trader getNewPlayer() {
		return newPlayer;
	}
	
	/**
	 * This method is the getter of the newShip attribute.
	 * @return the newly created Ship object
	 */
	public static Ship getNewShip() {
		return newShip;
	}
	
	/**
	 * This method terminates the whole program.
	 * @return a string report showing the user that they have quit the game
	 */
	public static String quitCommand() {
		String report = "You quit the game.";
		System.exit(0);
		return report;
	}
}
