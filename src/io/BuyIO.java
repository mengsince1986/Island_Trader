package io;

import java.util.*;

import items.Item;
import trader.Trader;

/**
 * The BuyIO is an {@link IO} sub-class to create command line interface for users to 
 * buy items in the store
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class BuyIO extends IO {

	/**
	 * Attribute forSale is an ArrayList storing all the {@link Item} objects which are
	 * available for sale in the store of the island where the Trader is located. 
	 */
	private ArrayList<Item> forSale;
	
	/**
	 * Attribute quantityIO stores a {@link TransactionQuantityIO} object which 
	 * is a command line interface for the Trader to input the quantity for the
	 * trading items.
	 */
	private TransactionQuantityIO quantityIO;
	
	/**
	 * This constructor sets the player attribute with the current Trader object,
	 * adds all the buying command options to attribute commandsList,
	 * initializes a {@link TransactionQuantityIO} object and stores it in the 
	 * quantityIO attribute. 
	 * @param player the current Trader object
	 */
	public BuyIO(Trader player) {
		super(player);
		this.forSale = player.getCurrentIsland().getStore().getToSell();
		for (Item item : forSale) {
			this.addCommand(item.getName());
		} 
		this.addCommand("Cancel transaction");
		quantityIO = new TransactionQuantityIO(player);
	}
	
	
	/**
	 * This method processes an input of buying command from users. It adds the 
	 * command matching users' input to attribute commandArguments, and invokes
	 * the readCommandArguments method from {@link TransactionQuantityIO} 
	 * object when the {@link Trader} choose to buy items.
	 * 
	 * @param playerChoice an integer which matches a command in this interface
	 * or any positive integers
	 */
	@Override
	public void processPlayerInput(int playerChoice) {
		resetCommandArguments();
		String keyWord = null;
		String argument = null;
		if (playerChoice < (getCommandsList().size() - 1)) {
			keyWord = "buy";
			argument = getCommandsList().get(playerChoice);
			addCommandArgument(keyWord);
			addCommandArgument(argument);
			setGettingTransactionQuantity(true);
			quantityIO.readCommandArguments(
					String.format("You have %d coins.\n" + "Enter the quantity of %s you wish to buy: \n",
							getTrader().getOwnedMoney(), this.getCommandsList().get(playerChoice)));
			setGettingTransactionQuantity(false);
		} else {
			keyWord = "cancel";
			addCommandArgument(keyWord);
			addCommandArgument(argument);
		}
	}

}