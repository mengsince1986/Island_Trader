package io;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import map.World;

public abstract class NewPlayerIO {

	private String promp;
	private ArrayList<String> commandsList;
	private static World world;
	private static ArrayList<String> commandArguments = new ArrayList<String>();
	private static boolean gettingName = false;
	private static boolean gettingPlayingTime = false;
	//private static final Scanner commandReader = new Scanner(System.in);

	public NewPlayerIO(World newWorld) {
		commandsList = new ArrayList<String>();
		world = newWorld;
	}

	public ArrayList<String> readCommandArguments() {

		String playerChoice;
		boolean isValid = false;

		do {
			//Print available commandList
			System.out.println(getCommandsListString());
			//Prompt for player
			System.out.println(this.promp);
			
			String errorMessage = "=====================!!!========================\n" +
					  			  "The input is invalid. Check the promp and do it again.\n" +
					  			  "================================================\n";

			Scanner commandReader = new Scanner(System.in);

			try {
				playerChoice = commandReader.nextLine();

				if (gettingName) {
					if (playerChoice == "0") {
						processPlayerInput(playerChoice);
						isValid = true;
					}
					else if (!playerChoice.matches("[a-zA-Z]+")) {
						System.out.println("\nSorry, the name can only include letters.");
					} else if (playerChoice.length() < 3 || playerChoice.length() > 15 ) {
						System.out.println("\nThe name should be between 3 and 15 characters.");
					} else {
						processPlayerInput(playerChoice);
						isValid = true;
					}
				} else if (gettingPlayingTime) {
	                if (Integer.parseInt(playerChoice) < 10 || 
	                	Integer.parseInt(playerChoice) > 100) {
	                	System.out.println("\nThe number of the days should be between 10 and 100.");
	                } else {
	                	processPlayerInput(playerChoice);
						isValid = true;
	                }
					
				} else if (Integer.parseInt(playerChoice) >= 0
						&& Integer.parseInt(playerChoice) <= commandsList.size() - 1) {
					processPlayerInput(playerChoice);
					isValid = true;
				} else {
					System.out.println(errorMessage);
				}
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
			}
			
		} while (!isValid);
		
		// System.out.println(commandArguments);
		return commandArguments;
	}

	public abstract void processPlayerInput(String choice);
	
	public static World getWorld() {
		return world;
	}

	public ArrayList<String> getCommandsList() {
		return commandsList;
	}

	public void addCommand(String command) {
		commandsList.add(command);
	}

	public String getCommandsListString() {
		String commandList = "============== Commands List =================" + "\n\n";
		for (int i = 0; i < commandsList.size(); i++) {
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
	
	public void setPromp(String newPromp) {
		this.promp = newPromp;
	}
	
	public String getPromp() {
		return this.promp;
	}

	public static void setGettingName(boolean value) {
		gettingName = value;
	}

	public static void setGettingPlayingTime(boolean value) {
		gettingPlayingTime = value;
	}
}
