package io;

import java.util.*;
import map.*;

import trader.Trader;

public class SailToIO extends IO {
	
	private ArrayList<Route> routes;

	public SailToIO(Trader player) {
		super(player);
		this.routes = getTrader().getCurrentIsland().getRoutes();
		for (Route route : routes) {
			addCommand(route.getDest().getName());;
		}
		addCommand("Cancel sailing");
	}
	
	public void processPlayerInput(int playerChoice) {
		resetCommandArguments();
		String keyWord = null;
		String argument = null;
		if (playerChoice < (getCommandsList().size() - 1)) {
			keyWord = "sail";
			argument = getCommandsList().get(playerChoice);
		} else {
			keyWord = "cancel";
		}
		System.out.println("Sail keyword " + keyWord);
		System.out.println("Sail argument " + argument);
		addCommandArgument(keyWord);
		addCommandArgument(argument);
	}

}
