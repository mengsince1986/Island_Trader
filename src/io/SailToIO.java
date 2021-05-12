package io;

import java.util.*;
import map.*;

import trader.Trader;

public class SailToIO extends IO {
	
	private ArrayList<Route> routes;

	public SailToIO(Trader player) {
		super(player);
		super.setPromp("Where do you wish to sail?");
		this.routes = getTrader().getCurrentIsland().getRoutes();
		for (Route route : routes) {
			addCommand(route.getDest().getName());;
		}
		// Cancel sailing is not a valid arg for commandHandler
		// addCommand("Cancel sailing");
		addCommand("cancel");
	}
	
	public void processPlayerInput(int playerChoice) {
		//MZ: As we always enter sub IOs from main IOs like PortIO and StoreIO,
		//and CommandArguments is static. It is ok just reset in PortIO & ScoreIO.
		//resetCommandArguments();
		//String keyWord = null;
		String argument = null;
		//if (playerChoice < (getCommandsList().size() - 1)) {
			//keyWord = "sail";
	    argument = getCommandsList().get(playerChoice);
		//} else {
		//	argument = "cancel";
		//}
		//super.addCommandArgument(keyWord);
		super.addCommandArgument(argument);
	}

}
