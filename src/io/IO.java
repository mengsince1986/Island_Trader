package io;
import java.util.*;
import trader.*;

public abstract class IO {
	
	/**
	 * read type: String
	 * return type: [String command, String argument]
	 */
	private static Trader player;
	private ArrayList<String> commandsList;
	private static ArrayList<String> commandArguments = new ArrayList<String>();
	//private static final Scanner commandReader = new Scanner(System.in);
	private static boolean gettingTransactionQuantity = false;
	
	public IO(Trader trader) {
		
		player = trader;
		commandsList = new ArrayList<String>();
		
	}
	
	// MZ use this constructor for IOs without Trader
	public IO() {

		commandsList = new ArrayList<String>();
	
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
			
			String errorMessage = "=====================!!!========================\n" +
								  "You need to choose a valid number. Choose again.\n" +
					              "================================================\n";
			Scanner commandReader = new Scanner(System.in);

			try {
				playerChoice = commandReader.nextInt();
				
				if (playerChoice >= 0) {
					if (gettingTransactionQuantity) {
						processPlayerInput(playerChoice);
						isValid = true;
					} else if (playerChoice <= commandsList.size() - 1) {
						processPlayerInput(playerChoice);
						isValid = true;
					} else {
						System.out.println(errorMessage);
					}
				} else {
					System.out.println(errorMessage);
				}
			} catch (InputMismatchException e) {

					System.out.println(errorMessage);
			}
				
		} while(!isValid);
		
		return commandArguments;		
	}
	
	public abstract void processPlayerInput(int choice);
	
	public static Trader getTrader() {
		return player;
	}

	
	public ArrayList<String> getCommandsList() {
		return commandsList;
	}
	
	public void setGettingTransactionQuantity(boolean value) {
		gettingTransactionQuantity = value;
	}
	
	public void addCommand(String command) {
		commandsList.add(command);
	}
	
	public String getCommandsListString() {
		String commandList = "============== Commands List =================" + "\n\n";
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
	
	public static void addCommandArgument(String arg) {
		commandArguments.add(arg);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
