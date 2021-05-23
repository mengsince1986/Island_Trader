package io;

import trader.*;

/**
 * The UpgradeIO is an IO sub-class to create command line interface for the
 * cannon upgrade service.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class UpgradeIO extends IO {

	/**
	 * This constructor sets the player attribute with the current Trader object and
	 * adds all the cannon upgrade command options to attribute commandsList.
	 * 
	 * @param trader the current Trader object
	 */
	public UpgradeIO(Trader player) {

		super(player);
		super.addCommand("Not now");
	}

	@Override
	/**
	 * This method processes an input of cannon upgrade command from users. It adds
	 * the command matching users' input or any positive integer to attribute
	 * commandArguments.
	 * 
	 * @param playerChoice an integer which matches a command in this interface or
	 *        any positive integer
	 */
	public void processPlayerInput(int playerChoice) {

		if (playerChoice == 0) {
			super.addCommandArgument("cancel");
			;
		} else {
			super.addCommandArgument(String.valueOf(playerChoice));

		}

	}

}
