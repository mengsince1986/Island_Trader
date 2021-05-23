package io;

import map.*;
import ships.Ship;
import trader.*;

/**
 * The StoreIO is an IO sub-class to create command line interface for store.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class StoreIO extends IO {

	/**
	 * This constructor sets the player attribute with the current Trader object and
	 * adds all the store command options to attribute commandsList. 
	 * @param trader the current Trader object
	 */
	public StoreIO(Trader player) {

		super(player);
		this.addCommand("Buy items"); // 0
		this.addCommand("Sell items"); // 1
		this.addCommand("Go back to port"); // 2

	}

	/**
	 * This method processes an input of a store command from users. It categorizes the
	 * commands in attribute commandsList with a switch statement and adds the
	 * command matching users' input to attribute commandArguments. This method also
	 * initializes another new command line interface object if necessary.
	 * 
	 * @param playerChoice an integer which matches a command in this interface
	 */
	@Override
	public void processPlayerInput(int playerChoice) {
		
		resetCommandArguments();
		
		String argument = null;
		Store currentStore = getTrader().getCurrentIsland().getStore();
		
		switch (playerChoice) {
		case 0: // buy
			BuyIO buyIO = new BuyIO(getTrader());
			System.out.println(currentStore.forSale());
			if (currentStore.getToSell().size() > 0) {
				buyIO.readCommandArguments("Which item would you like to buy?");
			} else {
				System.out.println("Nothing for sale at this store!\n");
				addCommandArgument("cancel");
				addCommandArgument(argument);
			}
			break;
			
		case 1: // sell
			Ship playerShip = getTrader().getOwndedShip();
			System.out.println(currentStore.getSellablePlayerItemsString(playerShip));
			if (currentStore.getSellablePlayerItems(playerShip).size() > 0) {
				SellIO sellIO = new SellIO(getTrader());
				sellIO.readCommandArguments("Which item would you like to sell?");
			} else {
				addCommandArgument("cancel");
				addCommandArgument(argument);

			}
			break;
			
		case 2: // cancel
			addCommandArgument("cancel");
			addCommandArgument(argument);
			getTrader().setCurrentLocation("port");
			break;
			
		}

	}
}