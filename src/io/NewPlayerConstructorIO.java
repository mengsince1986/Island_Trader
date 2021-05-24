package io;

import map.World;
import trader.Trader;

/**
 * The NewPlayerConstructorIO is a {@link NewPlayerIO} sub-class to create 
 * a command line interface for setting up a new {@link Trader}.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */


public class NewPlayerConstructorIO extends NewPlayerIO {
	
	/**
	 * This constructor sets the world attribute with a World object,
	 * creates and formats a string for the prompt attribute and adds all the 
	 * available command options for this interface to attribute commandsList.
	 * 
	 * @param newWorld a World object
	 */
	public NewPlayerConstructorIO(World newWorld) {

		super(newWorld);
		super.setPrompt("Welcome to the wolrd of Island Trader!\n" + "Before we start,\n"
				+ "Let's first create a new player.\n\n" + "Choose a command in the \"Commands List\",\n"
				+ "Input the number before the command and press \"Enter\" key\n");
		super.addCommand("Create a new player"); // 0
		super.addCommand("Quit"); // 1

	}

	/**
	 * This method processes an input of a new {@link Trader} setup command 
	 * from users. It categorizes the commands in attribute commandList with a 
	 * switch statement and adds the command matching users' input to attribute 
	 * commandArguments. This method also initializes a {@link NewPlayerNameIO} 
	 * object when users choose to create a new {@link Trader}.
	 * 
	 * @param playerChoice a string number which matches a command in this 
	 * interface
	 */
	@Override
	public void processPlayerInput(String playerChoice) {

		resetCommandArguments();

		switch (playerChoice) {
		case "0": // new player
			NewPlayerNameIO newName = new NewPlayerNameIO(getWorld());
			setGettingName(true);
			newName.readCommandArguments();
			break;

		case "1": // quit
			super.addCommandArgument("quit");
			break;

		}

	}

}
