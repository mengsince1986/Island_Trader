package io;

import java.util.*;

public class PortIO extends IO {
	
	/**
	 * read type: get input for playing commands. e.g. commands that match Ship.sailTo(), Trader.buy()... 
	 * sailDestination: String
	 */
	private ArrayList<String> portCommands;
	
	public PortIO() {
		this.portCommands.add("Sail to another Island"); //0
		this.portCommands.add("Go to store");            //1
		this.portCommands.add("Repair ship"); 		     //2
		this.portCommands.add("Upgrade cannons");        //3
		this.portCommands.add("View properties");        //4
		
	}
	
	@Override
	public ArrayList<String> read() {
		
		int playerChoice;
		
		// Validation while loop
		boolean isValid = false;
		do {
			// Initialize scanner
			Scanner playerCommands = new Scanner(System.in);
			
			//Print available commands
			System.out.println(getPortCommands());
			//Prompt for player
			System.out.println("Captain, what's your next move?");
			playerChoice =  playerCommands.nextInt();
			
			if (playerChoice >= 0 && playerChoice <= this.portCommands.size()) {
				isValid = true;
			}
			
			// close scanner
			playerCommands.close();
		} while(!isValid);
		
		processPlayerInput(playerChoice);
		
		// return update
		return super.getUpdates();		
	}
	
	public void processPlayerInput(int choice) {
		String key = null;
		String argument = null;
		switch(choice) {
		case 0: 
			key = "sail";
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
		}
		
		if (key == "sail") {
			sailToIO newSail = new sailToIO();
			argument = newSail.read().get(0);
		}
		
		super.addUpdate(key);
		super.addUpdate(argument);
	}
	
	public String getPortCommands() {
		String commandList = "=== Commands ===" + "\n";
		for (int i=0; i < this.portCommands.size(); i++) {
			commandList += this.portCommands.get(i) + "\n"; 
		}
		return commandList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
