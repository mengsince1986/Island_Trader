package io;

import java.util.*;
import map.*;
import trader.*;

public class SailToIO extends IO {
	
	private ArrayList<Route> routes;

	public SailToIO(Trader player) {
		super(player);
		this.routes = getTrader().getCurrentIsland().getRoutes();
		for (Route route : routes) {
			this.addCommand(route.getDest().getName());;
		}
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
