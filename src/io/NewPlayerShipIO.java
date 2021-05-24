package io;

import java.util.ArrayList;
import java.util.HashMap;

import map.World;
import ships.Ship;
import trader.Trader;

/**
 * The {@link NewPlayerShipIO} is a {@link NewPlayerIO} sub-class to create a
 * command line interface for setting the ship of the new {@link Trader}.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class NewPlayerShipIO extends NewPlayerIO {

	/**
	 * Attribute islandsList is an ArrayList which stores the string names of the
	 * available {@link Ship} objects.
	 */
	private ArrayList<String> shipsList = new ArrayList<String>();

	/**
	 * This constructor sets the world attribute with a World object, creates and
	 * formats a string for the prompt attribute and adds all the available
	 * command options for this interface to attribute commandsList.
	 * 
	 * @param newWorld a World object
	 */
	public NewPlayerShipIO(World newWorld) {

		super(newWorld);
		super.setPrompt("Please choose your ship:\n");

		HashMap<String, Ship> ships = getWorld().getShips();
		for (String shipName : ships.keySet()) {
			super.addCommand(shipName);
			this.shipsList.add(shipName);
		}

		super.addCommand("Cancel"); // 4

	}

	/**
	 * This method processes a string name chosen by users or a cancel command
	 * input. It adds the string name users choose or the cancel command to
	 * attribute commandArguments.
	 * 
	 * @param playerChoice a string of "0" or a string integer
	 */
	@Override
	public void processPlayerInput(String playerChoice) {

		if (Integer.parseInt(playerChoice) == shipsList.size()) {

			super.addCommandArgument("cancel");

		} else {

			String chosenIslandName = this.shipsList.get(Integer.parseInt(playerChoice));
			super.addCommandArgument(chosenIslandName);

		}

	}

}
