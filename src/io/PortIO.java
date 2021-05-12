package io;

import java.util.*;

import map.Island;
import map.Map;
import map.WorldConstructor;
import ships.BalancedShip;
import ships.Ship;
import trader.*;

public class PortIO extends IO {
	
	/**
	 * read type: get input for playing commands. e.g. commands that match Ship.sailTo(), Trader.buy()... 
	 * sailDestination: String
	 */
	// !!!if initialize other IOs as properties the commandsList only get updated once.
	// e.g. the command list of sailToIO keeps returning the same list
	// solution: initialize *IOs in processPlayerInput.
	// this makes sense as sailToIO gets different commands on different islands
	// SailToIO sailTo = new SailToIO(getTrader());
	// add others
	
	public PortIO(Trader player) {
		
		super(player);
		super.setPromp("Captain, what next?");
		super.addCommand("Sail to another island"); //0
		super.addCommand("Go to store");            //1
		super.addCommand("Repair ship"); 		    //2
		super.addCommand("Upgrade cannons");        //3
		super.addCommand("View properties");        //4
		
	}
	
	public void processPlayerInput(int playerChoice) {
		//Reset all previous commandArguments!!!
		resetCommandArguments();
		//String keyWord = null; //MZ: update command directly in case
		String argument = null;
		
		switch(playerChoice) {
		case 0: //sail
			//MZ: it's workable to update "sail" command in sailIO, but is it
			// more natural update commandArgs in its own processPlyerInput?
			// and we can just update commandArgs directly with its static setter
			super.addCommandArgument("sail");
			SailToIO sailTo = new SailToIO(getTrader());
			sailTo.readCommandArguments();
			break;
			
		case 1: //visit store
			super.addCommandArgument("store");
			break;
			
		case 2: //repair ship
			super.addCommandArgument("repair");
			RepairIO repair = new RepairIO(getTrader());
			repair.readCommandArguments();
			break;
			
		case 3: //upgrade cannons
			super.addCommandArgument("upgrade");
			UpgradeIO upgrade = new UpgradeIO(getTrader());
			upgrade.readCommandArguments();
			break;
		case 4: //view properties
			
			break;
		}
		// MZ: in case there's only one element in commandArguments
		// as commandHandler always check the 2nd index of commandArguments
		if (super.getCommandArguments().size() ==1) {
			super.addCommandArgument(argument);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		Map map;
		Trader player;
		Ship ship;
		
		System.out.println("Welcome to the wolrd of Island Trader");
		
		// constructing a new map
		WorldConstructor newWorld = new WorldConstructor();
		map = newWorld.getMap();
		Island island1 = map.getIsland("Niawall Haven");
		System.out.println("Constructing game environment ...");
		System.out.println("A new world is created ... ");
		
		// create a new player
		String traderName = "Jon Snow"; // get user input + loop invoked by exception
		player = new Trader(30, traderName, 10000, 
							island1, "port");
		ship = new BalancedShip(); // get user input + loop invoked by exception
		ship.setCaptain(player);
		player.setOwnedShip(ship);
		PortIO portIO = new PortIO(player);
		portIO.processPlayerInput(0);
		*/

	}

}
