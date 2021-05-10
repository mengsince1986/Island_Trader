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
	
	public PortIO(Trader player) {
		
		super(player);
		super.addCommand("Sail to another Island"); //0
		super.addCommand("Go to store");            //1
		super.addCommand("Repair ship"); 		    //2
		super.addCommand("Upgrade cannons");        //3
		super.addCommand("View properties");        //4
		
	}
	
	public void processPlayerInput(int playerChoice) {
		this.resetCommandArguments();
		String keyWord = null;
		String argument = null;
		switch(playerChoice) {
		case 0: //sail
			SailToIO newSail = new SailToIO(super.getTrader());
			newSail.readCommandArguments("Where do you wish to sail?");
			keyWord = newSail.getCommandArguments().get(0);
			argument = newSail.getCommandArguments().get(1);
			System.out.println("Port keyword " + keyWord);
			System.out.println("Port argument " + argument);
			break;
		case 1: //visit store
			
			break;
		case 2: //repair ship
			
			break;
		case 3: //upgrade cannons
			
			break;
		case 4: //view properties
			
			break;
		}
		super.addCommandArgument(keyWord);
		super.addCommandArgument(argument);

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
		PortIO port = new PortIO(player);
		port.processPlayerInput(0);
		

	}

}
