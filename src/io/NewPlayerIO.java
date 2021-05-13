package io;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import map.Island;
import map.World;
import ships.Ship;

public abstract class NewPlayerIO {

	private String promp;
	private ArrayList<String> commandsList;
	private static World world;
	private static ArrayList<String> commandArguments = new ArrayList<String>();
	//private static final Scanner commandReader = new Scanner(System.in);

	public NewPlayerIO(World newWorld) {
		commandsList = new ArrayList<String>();
		world = newWorld;
	}

	public ArrayList<String> readCommandArguments() {

		String playerChoice;
		boolean isValid = false;

		do {
			
			System.out.println(getCommandsListString());

			System.out.println(this.promp);
			
			Scanner commandReader = new Scanner(System.in);

			try {
				playerChoice = commandReader.nextLine();
				processPlayerInput(playerChoice);
				
				//debug test
				

				isValid = true;
				/*
				if (playerChoice >= 0 && playerChoice <= getCommandsList().size() - 1) {
					processPlayerInput(playerChoice);
					isValid = true;
				*/
				
			} catch (InputMismatchException e) {
				isValid = false;
			} finally {
				if (!isValid) {
					System.out.println("=====================!!!========================");
					System.out.println("You need to enter a valid value.");
					System.out.println("================================================");
				}
			}

		} while (!isValid);
		//System.out.println(commandArguments);
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


}
