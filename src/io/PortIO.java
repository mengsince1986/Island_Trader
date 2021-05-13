package io;

import java.util.*;

import map.*;
import map.Map;
import ships.*;
import trader.*;

public class PortIO extends IO {
	
	/**
	 * read type: get input for playing commands. e.g. commands that match Ship.sailTo(), Trader.buy()... 
	 * sailDestination: String
	 */
	
	public PortIO(Trader player) {
		
		super(player);

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
			sailTo.readCommandArguments("Where do you wish to sail?");
			break;
			
		case 1: //visit store
			super.addCommandArgument("store");
			break;
			
		case 2: //repair ship
			super.addCommandArgument("repair");
			RepairIO repair = new RepairIO(getTrader());
			int repairFee = super.getTrader().getCurrentIsland().getPort().getRepairCost();
			repair.readCommandArguments("Hi, Captain! Wanna repair your ship?\n"
				       				  + "Only " + repairFee + " dollars.");
			break;
			
		case 3: //upgrade cannons
			super.addCommandArgument("upgrade");
			UpgradeIO upgrade = new UpgradeIO(getTrader());
			int upgradeCannonFee = super.getTrader().getCurrentIsland().getPort().getcannonCost();
			upgrade.readCommandArguments("Yo, Captain! How many cannons do you want?\n" 
									   + upgradeCannonFee + " dollars for one.\n"
				                       + "We can install at most two.");
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
>>>>>>> constructorIO
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
