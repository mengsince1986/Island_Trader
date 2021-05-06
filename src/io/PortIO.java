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
			System.out.println("Captain, what's your next move?");
			playerChoice =  playerCommands.nextInt();
			
			if (playerChoice >= 0 && 
				playerChoice <= super.getCommands().size()-1) {
				processPlayerInput(playerChoice);
				//playerCommands.close(); cause problem
				isValid = true;
			} else {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("You need to choose a valid number. Choose again.");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}		
		} while(!isValid);
			
		return super.getUpdates();		
	}
	
	public void processPlayerInput(int choice) {
		String key = null;
		String argument = null;
		switch(choice) {
		case 0: 
			key = "sail";
			sailToIO newSail = new sailToIO(super.getTrader());
			argument = newSail.read().get(0);
			super.addUpdate(key);
			super.addUpdate(argument);
			break;
		case 1:
			key = "store";
			break;
		case 2:
			key = "repair";
			break;
		case 3:
			key = "upgradeCannons";
			break;
		case 4:
			key = "view";
			break;
		default:
			super.addUpdate(key);
			super.addUpdate(argument);
			break;
		}

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
