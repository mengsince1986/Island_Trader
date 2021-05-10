package io;

import java.util.*;
import map.*;

import trader.Trader;

public class SailToIO extends IO {
	
	private ArrayList<Route> routes;

	public SailToIO(Trader player) {
		super(player);
		this.routes = super.getTrader().getCurrentIsland().getRoutes();
		for (Route route : routes) {
			this.addCommand(route.getDest().getName());;
		}
		this.addCommand("Cancel sailing");
	}
	
	public void processPlayerInput(int playerChoice) {
		this.resetCommandArguments();
		String keyWord = null;
		String argument = null;
		if (playerChoice < (this.getCommandsList().size() - 1)) {
			keyWord = "sail";
			argument = this.getCommandsList().get(playerChoice);
		} else {
			keyWord = "cancel";
		}
		System.out.println("Sail keyword " + keyWord);
		System.out.println("Sail argument " + argument);
		super.addCommandArgument(keyWord);
		super.addCommandArgument(argument);
	}

}
