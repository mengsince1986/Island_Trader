package io;

import items.Item;
import trader.Trader;

/**
 * The TransactionQuantityIO is an IO sub-class to create command line interface
 * for users to input the quantity number of trading items.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class TransactionQuantityIO extends IO {

	/**
	 * This constructor sets the player attribute with the current Trader object,
	 * adds a cancel option to attribute commandsList.
	 * 
	 * @param trader the current Trader object
	 */
	public TransactionQuantityIO(Trader player) {
		super(player);
		this.addCommand("Cancel transaction");

	}

	/**
	 * This method processes an input of cancel command or an {@link Item} 
	 * quantity from users. It adds the command matching users' input to 
	 * attribute commandArguments.
	 * 
	 * @param playerChoice an integer which matches a command in this interface
	 * or any positive integers
	 */
	@Override
	public void processPlayerInput(int playerChoice) {

		if (playerChoice > 0) {
			String quantityArgument = String.valueOf(playerChoice);
			addCommandArgument(quantityArgument);
		} else {
			String keyWord = "cancel";
			addCommandArgument(keyWord);
		}

	}

}
