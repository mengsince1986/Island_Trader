package io;
import java.util.*;
import trader.*;

public abstract class IO {
	
	/**
	 * read type: String
	 * return type: [String command, String argument]
	 */
	private Trader player;
	private ArrayList<String> commandsList;
	private ArrayList<String> commandArguments;
	private static final Scanner commandReader = new Scanner(System.in);
	
	public IO(Trader player) {
		this.player = player;
		this.commandsList = new ArrayList<String>();
		this.commandArguments = new ArrayList<String>();
	}
	
	public ArrayList<String> readCommandArguments(String prompt) {
		
		int playerChoice;
		// Validation while loop
		boolean isValid = false;
		do {
			//Print available commandsList
			System.out.println(getCommandsListString());
			//Prompt for player
			System.out.println(prompt);
			
			try {
				playerChoice = commandReader.nextInt();
				if (playerChoice >= 0 && playerChoice <= getCommandsList().size()-1) {
					processPlayerInput(playerChoice);
					isValid = true;
				}
			} catch (InputMismatchException e) {
				isValid = false;
			} finally {
				if (!isValid) {
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("You need to choose a valid number. Choose again.");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				}
			}
				
		} while(!isValid);
		
		return commandArguments;		
	}
	
	public abstract void processPlayerInput(int choice);
	
	public Trader getTrader() {
		return this.player;
	}
	
	public ArrayList<String> getCommandsList() {
		return this.commandsList;
	}
	
	public void addCommand(String command) {
		this.commandsList.add(command);
	}
	
	public String getCommandsListString() {
		String commandList = "=== commandsList ===" + "\n";
		for (int i=0; i < this.commandsList.size(); i++) {
			commandList += i + ". " + this.commandsList.get(i) + "\n"; 
		}
		return commandList;
	}

	
	public ArrayList<String> getCommandArguments() {
		return this.commandArguments;
	}
	
	public void resetCommandArguments() {
		this.commandArguments.clear();
	}
	
	public void addCommandArgument(String update) {
		this.commandArguments.add(update);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
