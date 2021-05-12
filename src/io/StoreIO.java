package io;

import java.util.*;

import items.Item;
import map.*;
import ships.Ship;
import trader.*;

public class StoreIO extends IO {
	

public StoreIO(Trader player) {
		
		super(player);
		this.addCommand("Buy items"); 		//0		
		this.addCommand("Sell items"); 		//1
		this.addCommand("Go back to port"); //2
		
	}
	
	public void processPlayerInput(int playerChoice) {
		resetCommandArguments();
		String keyWord = null;
		String argument = null;
		Store currentStore = getTrader().getCurrentIsland().getStore();
		switch(playerChoice) {
		case 0: //buy
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
		case 1: //sell
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
		case 2: //cancel
			addCommandArgument("cancel");
			addCommandArgument(argument);
			getTrader().setCurrentLocation("port");
			break;
		}

	}
}