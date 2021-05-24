package io;

import java.util.*;

import commands.CommandHandler;
import main.GameEnvironment;
import trader.*;

/**
 * The IO (input and output) is an abstract class for creating command line
 * interface. It is the base for {@link PortIO}, {@link SailToIO},
 * {@link RepairIO}, {@link UpgradeIO}, {@link StoreIO}, {@link BuyIO}, and
 * {@link TransactionQuantityIO}.
 * <p>
 * The sub-classes created based on IO print prompts for different commands,
 * take integers inputs from users, validate the inputs, and pass the inputs as
 * an ArrayList to {@link CommandHandler} through {@link GameEnvironment}.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public abstract class IO {

	/**
	 * Attribute player stores the current Trader object which is used for printing
	 * related Trader properties for command line interface. This attribute is set
	 * static as there's only one Trader object in the game, so all command line
	 * interface objects share the same player attribute.
	 */
	private static Trader player;

	/**
	 * Attribute commandsList stores an ArrayList of available commands in this
	 * command line interface object.
	 */
	private ArrayList<String> commandsList;

	/**
	 * Attribute commandArguments stores an ArrayList of commands users choose from
	 * the command line interface. This attribute is set static so that it can store
	 * command inputs from different command line interface objects.
	 */
	private static ArrayList<String> commandArguments = new ArrayList<String>();

	/**
	 * Attribute gettingTransactionQuantity stores a boolean value. It is true when
	 * a command line interface object accepts all positive integer inputs from
	 * users, e.g. the quantity of items a trader buys or sells.
	 */
	private static boolean gettingTransactionQuantity = false;

	/**
	 * This constructor sets the player attribute with the current Trader object and
	 * initializes a new ArrayList for the commandsList attribute.
	 * 
	 * @param trader the current Trader object
	 */
	public IO(Trader trader) {

		player = trader;
		commandsList = new ArrayList<String>();

	}

	/**
	 * This method prints the prompt and available command options returned by
	 * getCommandsListString(), gets an input of integer from users, and validates
	 * the input. If the input is valid, this method will add the command matching
	 * the input to attribute commandArguments. If the input is invalid, this method
	 * will print an error message.
	 * <p>
	 * If attribute gettingTransactionQuantity is true, any positive integer input
	 * will be acceptable. Otherwise, only integer inputs matching available
	 * commands in attribute commandsList are accepted.
	 * 
	 * @param prompt the string prompt for this interface IO object
	 * @return the ArrayList of commandArguments
	 */
	public ArrayList<String> readCommandArguments(String prompt) {

		int playerChoice;
		boolean isValid = false;

		// A do while loop which keeps running until player enters a valid input
		do {

			System.out.println(getCommandsListString());
			System.out.println(prompt);

			String errorMessage = "=====================!!!========================\n"
					+ "You need to choose a valid number. Choose again.\n"
					+ "================================================\n";

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

		} while (!isValid);

		return commandArguments;
	}

	/**
	 * This is an abstract method which processes a command input from users.
	 * 
	 * @param choice an integer which matches a command in this interface
	 */
	public abstract void processPlayerInput(int choice);

	/**
	 * This method is the getter for attribute player.
	 * 
	 * @return the Trader object stored in attribute player
	 */
	public static Trader getTrader() {
		return player;
	}

	/**
	 * This method is the getter for attribute commandsList.
	 * 
	 * @return the ArrayList stored in attribute commandsList
	 */
	public ArrayList<String> getCommandsList() {
		return commandsList;
	}

	/**
	 * This method is the setter for attribute gettingTransactionQuantity.
	 * 
	 * @param value true if the interface object accepts all natural numbers as
	 *              input, otherwise false
	 */
	public void setGettingTransactionQuantity(boolean value) {
		gettingTransactionQuantity = value;
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

}
