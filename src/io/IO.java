package io;
import java.util.*;

import commands.CommandHandler;
import main.GUIGameEnvironment;
import main.GameEnvironment;
import trader.*;

/**
 * The IO (input and output) is an abstract class for creating command line interfaces.
 * It is the base for {@link PortIO}, {@link SailToIO}, {@link RepairIO}, {@link UpgradeIO},
 * {@link StoreIO}, {@link BuyIO}, and {@link TransactionQuantityIO}.
 * <p>
 * The sub classes created based on IO print prompts for different commands, 
 * take integer inputs from users, validate the inputs, and pass the inputs as 
 * an ArrayList to {@link CommandHandler} through {@link GameEnvironment}.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public abstract class IO {
	
	/**
	 * Attribute setupFrame stores the Frame object of this window.
	 * <p>
	 * Attribute nameTextField stores the string of the new name entered by 
	 * users.
	 * <p>
	 * Attribute shipButtonGroup stores a ButtonGroup object including all the
	 * RadioButtons with the name of the available Ship objects.
	 * <p>
	 * Attribute homeButtonGroup stores a ButtonGroup object including all the
	 * RadioButtons with the name of the available Island objects.
	 * <p>
	 * Attribute manager is the {@link GUIGameEnvironment} object which maintains the 
	 * state of the program and makes instances of window classes.
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
	

}
