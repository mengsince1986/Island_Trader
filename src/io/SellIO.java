package io;

import java.util.*;

import trader.Trader;
import items.Item;
import map.Island;
import ships.Ship;

/**
 * The SellIO is an IO sub-class to create command line interface for users to 
 * sell items in the store.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class SellIO extends IO {

	/**
	 * Attribute sellablePlayerItems is an ArrayList storing all the {@link Item} 
	 * objects which are available for the {@link Trader} to sell in the store 
	 * of the {@link Island} where the Trader is located. 
	 * <p>
	 * Attribute quantityIO stores a {@link TransactionQuantityIO} object which 
	 * is a command line interface for the Trader to input the quantity for the
	 * trading items.
	 */
	private ArrayList<Item> sellablePlayerItems;
	private TransactionQuantityIO quantityIO;
	
	/**
	 * This constructor sets the player attribute with the current Trader object,
	 * adds all the selling command options to attribute commandsList,
	 * initializes a {@link TransactionQuantityIO} object and stores it in the 
	 * quantityIO attribute. 
	 * @param trader the current Trader object
	 */
	public SellIO(Trader player) {
		super(player);
		Ship playerShip = player.getOwndedShip();
		sellablePlayerItems = player.getCurrentIsland().getStore().getSellablePlayerItems(playerShip);
		for (Item item : sellablePlayerItems) {
			this.addCommand(item.getName());
		} 
		this.addCommand("Cancel transaction");
		quantityIO = new TransactionQuantityIO(player);
	}
	

	/**
	 * This method processes an input of selling command from users. It adds the 
	 * command matching users' input to attribute commandArguments, and invokes
	 * the readCommandArguments method from {@link TransactionQuantityIO} 
	 * object when the {@link Trader} choose to sell items.
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
			keyWord = "sell";
			argument = getCommandsList().get(playerChoice);
			addCommandArgument(keyWord);
			addCommandArgument(argument);
			setGettingTransactionQuantity(true);
			quantityIO.readCommandArguments(
					String.format("Enter the quantity of %s you wish to sell: \n",
							this.getCommandsList().get(playerChoice)));
			setGettingTransactionQuantity(false);
		} else {
			keyWord = "cancel";
			addCommandArgument(keyWord);
			addCommandArgument(argument);
		}
		
	}
	
}
