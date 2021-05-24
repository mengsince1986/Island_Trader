package io;

import java.util.ArrayList;
import java.util.HashMap;

import map.Island;
import map.World;
import trader.Trader;

/**
 * The NewPlayerHomeIO is a {@link NewPlayerIO} sub-class to create a command
 * line interface for setting the home island of the new {@link Trader}.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class NewPlayerHomeIO extends NewPlayerIO {

	/**
	 * Attribute islandsList is an ArrayList which stores the string names of the
	 * available {@link Island} objects.
	 */
	private ArrayList<String> islandsList = new ArrayList<String>();

	/**
	 * This constructor sets the world attribute with a World object, creates and
	 * formats a string for the prompt attribute and adds all the available
	 * command options for this interface to attribute commandsList.
	 * 
	 * @param newWorld a World object
	 */
	public NewPlayerHomeIO(World newWorld) {

		super(newWorld);
		super.setPrompt("Please choose your home island:\n");

		HashMap<String, Island> islands = getWorld().getIslands();
		for (String islandName : islands.keySet()) {
			super.addCommand(islandName);
			islandsList.add(islandName);
		}
		super.addCommand("Cancel"); // 2

	}

	/**
	 * This method processes a string name chosen by users or a cancel command
	 * input. It adds the string name users choose or the cancel command to
	 * attribute commandArguments. This method also initializes a
	 * {@link NewPlayerShipIO} object after users choose a valid island name.
	 * 
	 * @param playerChoice a string of "0" or a string integer
	 */
	@Override
	public void processPlayerInput(String playerChoice) {

		if (Integer.parseInt(playerChoice) == islandsList.size()) {

			super.addCommandArgument("cancel");

		} else {

			String chosenIslandName = this.islandsList.get(Integer.parseInt(playerChoice));
			super.addCommandArgument(chosenIslandName);
			NewPlayerShipIO newShip = new NewPlayerShipIO(getWorld());
			newShip.readCommandArguments();

		}

	}

}
