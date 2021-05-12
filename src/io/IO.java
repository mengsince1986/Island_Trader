package io;
import java.util.*;
import trader.*;

public abstract class IO {
	
	/**
	 * read type: String
	 * return type: [String command, String argument]
	 */
	private static Trader player;
	//MZ store prompt as a property
	private String prompt;
	
	//MZ remove static from commandList because
	// commandList is updated when each IO object is initialized based on
	// different states. e.g. SailToIO update commandList by different routes
	// main IOs like PortIO and StoreIO are initialized once only
	// so the commandList is not updated when IOs are not initialized
	private ArrayList<String> commandsList; 
	//MZ initialize static commandArguments directly
	private static ArrayList<String> commandArguments = new ArrayList<String>();
	private static final Scanner commandReader = new Scanner(System.in);
	
	public IO(Trader trader) {
		player = trader;
		commandsList = new ArrayList<String>();
		//MZ don't initialize static commandArguments here or it will reset 
		// commandArguments whenever a new IO is initialized
		//commandArguments = new ArrayList<String>();
	}
	
	public ArrayList<String> readCommandArguments() {
		
		int playerChoice;
		// Validation while loop
		boolean isValid = false;
		do {
			//Print available commandsList
			System.out.println(getCommandsListString());
			//Prompt for player
			System.out.println(this.prompt);
			
			//MZ good job!!!
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
					System.out.println("=====================!!!========================");
					System.out.println("You need to choose a valid number. Choose again.");
					System.out.println("================================================");
				}
			}
				
		} while(!isValid);
		
		return commandArguments;		
	}
	
	public abstract void processPlayerInput(int choice);
	
	public static Trader getTrader() {
		return player;
	}
	
	//MZ remove static
	public ArrayList<String> getCommandsList() {
		return commandsList;
	}
	
	//MZ remove static
	public void addCommand(String command) {
		commandsList.add(command);
	}
	
	//MZ remove static
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
	
	//MZ
	public void setPromp(String message) {
		this.prompt = message;
	}
	
	//MZ 
	public String getPrompt() {
		return this.prompt;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
