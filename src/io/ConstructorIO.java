package io;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class ConstructorIO {

	private String prompt;
	private ArrayList<String> commandsList;
	private static ArrayList<String> commandArguments = new ArrayList<String>();
	private static final Scanner commandReader = new Scanner(System.in);

	public ConstructorIO() {
		commandsList = new ArrayList<String>();
	}

	public ArrayList<String> readCommandArguments() {

		String playerChoice;
		boolean isValid = false;

		do {
			
			System.out.println(getCommandsListString());

			System.out.println(this.prompt);

			try {
				playerChoice = commandReader.nextLine();
				processPlayerInput(playerChoice);
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

	public void setPromp(String message) {
		this.prompt = message;
	}

	public String getPrompt() {
		return this.prompt;
	}
}
