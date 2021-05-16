package io;

import java.util.*;

import trader.Trader;
import items.Item;
import ships.Ship;

public class SellIO extends IO {

	private ArrayList<Item> sellablePlayerItems;
	private TransactionQuantityIO quantityIO;
	
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
		//System.out.println("Sell keyword " + keyWord);
		//System.out.println("Sell argument " + argument);
		
	}
	
}
