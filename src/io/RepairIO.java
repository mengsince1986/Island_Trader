package io;
import trader.*;

/**
 * The RepairIO is an IO sub-class to create command line interface for the ship
 * repairing service.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class RepairIO extends IO {

	/**
	 * This constructor sets the player attribute with the current Trader object and
	 * adds all the ship repairing command options to attribute commandsList. 
	 * @param trader the current Trader object
	 */
	public RepairIO(Trader player) {
		
		super(player);
		super.addCommand("Yes");
		super.addCommand("Maybe later");
	}
	
	@Override
	/**
	 * This method processes an input of repairing command from users. It adds the
	 * command matching users' input to attribute commandArguments.
	 * 
	 * @param playerChoice an integer which matches a command in this interface
	 */
	public void processPlayerInput(int playerChoice) {
		
		String argument = getCommandsList().get(playerChoice);
		if (argument == "Maybe later") {
			argument = "cancel";
		}
		super.addCommandArgument(argument);
	}

}
