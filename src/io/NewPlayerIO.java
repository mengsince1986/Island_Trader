package io;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import commands.TraderCreatorHandler;
import main.GameEnvironment;
import map.Island;
import map.World;
import ships.Ship;
import trader.Trader;

/**
 * The NewPlayerIO is an abstract class for creating command line interface. It
 * is the base for {@link NewPlayerConstructorIO} {@link NewPlayerNameIO},
 * {@link NewPlayerTimeIO}, {@link NewPlayerHomeIO}, and
 * {@link NewPlayerShipIO}.
 * <p>
 * The sub-classes created based on NewPlayerIO print prompts for creating a new
 * {@link Trader}, take string inputs from users, validate the inputs, and pass
 * the inputs as an ArrayList to {@link TraderCreatorHandler} through
 * {@link GameEnvironment}.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public abstract class NewPlayerIO {

	/**
	 * Attribute world stores the current World object which stores all the
	 * {@link Island} and {@link Ship} objects.
	 * <p>
	 * Attribute prompt stores a string prompt for this command line interface
	 * object.
	 * <p>
	 * Attribute commandsList stores an ArrayList of available commands in this
	 * command line interface object.
	 * <p>
	 * Attribute commandArguments stores an ArrayList of commands users choose from
	 * the command line interface. This attribute is set static so that it can store
	 * command inputs from different command line interface objects.
	 * <p>
	 * Attribute gettingName is a boolean value which is set true when the command
	 * line interface object is expecting a new player's name as the input.
	 * Otherwise, it is set false.
	 * <p>
	 * Attribute gettingPlayingTimes is a boolean value which is set true when the
	 * command line interface object is expecting the number of the playing days as
	 * the input. Otherwise, it is set false.
	 */
	private static World world;
	private String prompt;
	private ArrayList<String> commandsList;
	private static ArrayList<String> commandArguments = new ArrayList<String>();
	private static boolean gettingName = false;
	private static boolean gettingPlayingTime = false;

	/**
	 * This constructor sets the world attribute with the current World object and
	 * initializes a new ArrayList for the commandsList attribute.
	 * 
	 * @param newWorld a World object
	 */
	public NewPlayerIO(World newWorld) {

		world = newWorld;
		commandsList = new ArrayList<String>();

	}

	/**
	 * This method prints the prompt and available command options returned by
	 * getCommandsListString(), gets a an input of string from users, and validates
	 * the input. If the input is valid, this method will add the command matching
	 * the input to attribute commandArguments. If the input is invalid, this method
	 * will print an error message.
	 * <p>
	 * 
	 * @return the ArrayList of commandArguments
	 */
	public ArrayList<String> readCommandArguments() {

		String playerChoice;
		boolean isValid = false;

		do {
			// Print available commandList
			System.out.println(getCommandsListString());
			// Prompt for player
			System.out.println(this.prompt);

			String errorMessage = "=====================!!!========================\n"
					+ "The input is invalid. Check the prompt and do it again.\n"
					+ "================================================\n";

			Scanner commandReader = new Scanner(System.in);

			try {

				playerChoice = commandReader.nextLine();

				if (gettingName) {

					if (playerChoice.matches("0") && Integer.parseInt(playerChoice) == 0) {
						processPlayerInput(playerChoice);
						isValid = true;
					} else if (!playerChoice.matches("[a-zA-Z]+")) {
						System.out.println("\nSorry, the name can only include letters.");
					} else if (playerChoice.length() < 3 || playerChoice.length() > 15) {
						System.out.println("\nThe name should be between 3 and 15 characters.");
					} else {
						processPlayerInput(playerChoice);
						isValid = true;
					}

				} else if (gettingPlayingTime) {
					if (playerChoice.matches("0") && Integer.parseInt(playerChoice) == 0) {

						processPlayerInput(playerChoice);
						isValid = true;

					} else if (Integer.parseInt(playerChoice) < 10 || Integer.parseInt(playerChoice) > 100) {

						System.out.println("\nThe number of the days should be between 10 and 100.");

					} else {

						processPlayerInput(playerChoice);
						isValid = true;
					}

				} else if (Integer.parseInt(playerChoice) >= 0
						&& !playerChoice.matches("00+") 
						&& Integer.parseInt(playerChoice) <= commandsList.size() - 1) {

					processPlayerInput(playerChoice);
					isValid = true;

				} else {

					System.out.println(errorMessage);

				}

			} catch (InputMismatchException e) {

				System.out.println(errorMessage);

			} catch (NumberFormatException e) {

				System.out.println(errorMessage);

			}

		} while (!isValid);

		return commandArguments;
	}

	/**
	 * This is an abstract method which processes a command input from users.
	 * 
	 * @param choice a string which matches a command in this interface
	 */
	public abstract void processPlayerInput(String choice);

	/**
	 * This method is the getter for attribute world.
	 * 
	 * @return the World object stored in the world attribute
	 */
	public static World getWorld() {
		return world;
	}

	/**
	 * This method is the getter for attribute commandsList.
	 * 
	 * @return the ArrayList stored in the commandsList attribute
	 */
	public ArrayList<String> getCommandsList() {
		return commandsList;
	}

	/**
	 * This method adds a single command string to the ArrayList stored in attribute
	 * commandsList.
	 * 
	 * @param command a string command
	 */
	public void addCommand(String command) {
		commandsList.add(command);
	}

	/**
	 * This method iterates attribute commandsList and concatenates all the commands
	 * into a string.
	 * 
	 * @return a formated string which includes all the command options
	 */
	public String getCommandsListString() {
		String commandList = "============== Commands List =================" + "\n\n";
		for (int i = 0; i < commandsList.size(); i++) {
			commandList += i + ". " + commandsList.get(i) + "\n";
		}
		return commandList;
	}

	/**
	 * This method is the getter for attribute commandArguments.
	 * 
	 * @return the ArrayList stored in attribute commandArguments
	 */
	public static ArrayList<String> getCommandArguments() {
		return commandArguments;
	}

	/**
	 * This method resets the commandArguments attribute to be an empty ArrayList
	 */
	public static void resetCommandArguments() {
		commandArguments.clear();
	}

	/**
	 * This method adds a single command/argument string to attribute
	 * commandArguments.
	 * 
	 * @param arg a command/argument string
	 */
	public static void addCommandArgument(String arg) {
		commandArguments.add(arg);
	}

	/**
	 * This method is the setter for attribute prompt.
	 * 
	 * @param newPrompt a string prompt for this interface object
	 */
	public void setPrompt(String newPrompt) {
		this.prompt = newPrompt;
	}

	/**
	 * This method is the getter for attribute prompt.
	 * 
	 * @return the string stored in attribute prompt
	 */
	public String getPrompt() {
		return this.prompt;
	}

	/**
	 * This method is the setter for attribute gettingName.
	 * 
	 * @param value true if this interface object is expecting a new player's name
	 *              as the input. Otherwise, it's false.
	 */
	public static void setGettingName(boolean value) {
		gettingName = value;
	}

	/**
	 * This method is the setter for attribute gettingPlayingTime.
	 * 
	 * @param value true if this interface object is expecting the number of the
	 *              playing days as the input. Otherwise, it is set false.
	 */
	public static void setGettingPlayingTime(boolean value) {
		gettingPlayingTime = value;
	}
}
