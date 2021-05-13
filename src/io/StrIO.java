package io;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import map.World;

public abstract class StrIO {

	private ArrayList<String> commandsList;
	private static World world;
	private static ArrayList<String> commandArguments = new ArrayList<String>();
	//private static final Scanner commandReader = new Scanner(System.in);

	public StrIO(World newWorld) {
		commandsList = new ArrayList<String>();
		world = newWorld;
	}

	public ArrayList<String> readCommandArguments(String prompt) {

		String playerChoice;
		boolean isValid = false;

		do {
			
			System.out.println(getCommandsListString());

			System.out.println(prompt);
			
			Scanner commandReader = new Scanner(System.in);

			try {
				playerChoice = commandReader.nextLine();
				processPlayerInput(playerChoice);
				
				//debug test
				System.out.println("constructor playerChoice: " + playerChoice);

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

}
