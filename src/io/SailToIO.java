package io;

import java.util.*;
import map.*;
import trader.*;

/**
 * The SailToIO is an IO sub-class to create command line interface for the
 * sailing command.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class SailToIO extends IO {
	
	/**
	 * Attribute routes stores an ArrayList of {@link Route} objects which are
	 * available for sailing to.
	 */
	private ArrayList<Route> routes;

	/**
	 * This constructor sets the player attribute with the current Trader object and
	 * adds all the sailing command options to attribute commandsList. 
	 * @param trader the current Trader object
	 */
	public SailToIO(Trader player) {
		super(player);
		this.routes = getTrader().getCurrentIsland().getRoutes();
		for (Route route : routes) {
			this.addCommand(route.getDest().getName());;
		}
		addCommand("cancel");
	}
	
	@Override
	/**
	 * This method processes an input of sailing command from users and adds the
	 * command matching users' input to attribute commandArguments.
	 * 
	 * @param playerChoice an integer which matches a command in this interface
	 */
	public void processPlayerInput(int playerChoice) {
		
		String argument = null;
	    argument = getCommandsList().get(playerChoice);
		super.addCommandArgument(argument);
		
	}

}
