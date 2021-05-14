package io;

import java.util.*;

import items.Item;
import trader.Trader;
import ships.Ship;

public class BuyIO extends IO {

	private ArrayList<Item> forSale;
	private TransactionQuantityIO quantityIO;
	
	public BuyIO(Trader player) {
		super(player);
		this.forSale = player.getCurrentIsland().getStore().getToSell();
		for (Item item : forSale) {
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
			keyWord = "buy";
			argument = getCommandsList().get(playerChoice);
			addCommandArgument(keyWord);
			addCommandArgument(argument);
			setGettingTransactionQuantity(true);
			quantityIO.readCommandArguments(
					String.format("You have $%d\n"
							+ "Enter the quantity of %s you wish to buy: \n",
							getTrader().getOwnedMoney(), 
							this.getCommandsList().get(playerChoice)));
			setGettingTransactionQuantity(false);
		} else {
			keyWord = "cancel";
			addCommandArgument(keyWord);
			addCommandArgument(argument);
		}
		System.out.println("Buy keyword " + keyWord);
		System.out.println("Buy argument " + argument);
	}
	
}