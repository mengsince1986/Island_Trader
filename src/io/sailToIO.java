package io;

import java.util.*;
import map.*;

import trader.Trader;

public class sailToIO extends IO {

	public sailToIO(Trader player) {
		super(player);
		ArrayList<Route> routes = super.getTrader().getCurrentIsland().getRoutes();
		for (Route route : routes) {
			super.addCommand(route.getDest().getName());;
		}
	}

	public ArrayList<String> read() {
		
		// Initialize scanner
		Scanner playerCommands = new Scanner(System.in);
		int playerChoice;
		// Validation while loop
		boolean isValid = false;
		do {
			//Print available commands
			System.out.println(super.getCommandString());
			//Prompt for player
			System.out.println("Captain, where are we going?");
			playerChoice =  playerCommands.nextInt();
			if (playerChoice >= 0 && 
				playerChoice <= super.getCommands().size()-1) {
				super.addUpdate(super.getCommands().get(playerChoice));
				// close scanner will cause problem
				//playerCommands.close();
				isValid = true;
			}	
		} while(!isValid);
		
		return super.getUpdates();		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
