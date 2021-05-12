package io;
import java.util.*;
import trader.*;

public abstract class IO {
	
	/**
	 * read type: String
	 * return type: [String command, String argument]
	 */
	private static Trader player;
	private static ArrayList<String> commandsList;
	private static ArrayList<String> commandArguments;
	private static final Scanner commandReader = new Scanner(System.in);
	
	public IO(Trader trader) {
		player = trader;
		commandsList = new ArrayList<String>();
		commandArguments = new ArrayList<String>();
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
	
	public static Trader getTrader() {
		return player;
	}
	
	public static ArrayList<String> getCommandsList() {
		return commandsList;
	}
	
	public static void addCommand(String command) {
		commandsList.add(command);
	}
	
	public static String getCommandsListString() {
		String commandList = "=== commandsList ===" + "\n";
		for (int i=0; i < commandsList.size(); i++) {
			commandList += i + ". " + commandsList.get(i) + "\n"; 
		}
		return commandList;
	}

	
	public static ArrayList<String> getCommandArguments() {
		return commandArguments;
	}
	
	public static void resetCommandArguments() {
		commandArguments.clear();
	}
	
	public static void addCommandArgument(String update) {
		commandArguments.add(update);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
