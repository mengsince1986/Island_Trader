package trader;
import java.util.*;
import ships.*;
import map.*;
import items.*;
import events.*;

/**
 * A class to represent the current player of the game. Key behaviours include
 * buying and selling {@link Item}s, upgrading the cannons on the player's {@link Ship},
 * repairing the Ship.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */
public class Trader {
	
	/**
	 * the number of days the player chose to play for.
	 */
	private int selectedDays;
	
	/**
	 * the number of days the player has still to play.
	 */
	private int remainingDays;
	
	/**
	 * the name String of the player, chosen at the beginning of the game.
	 */
	private String name;
	
	/**
	 * the default amount of money a player starts the game with.
	 */
	private int startingMoney;
	
	/**
	 * the amount of money a player currently has.
	 */
	private int ownedMoney;
	
	/**
	 * the {@link Island} which the player starts the game at.
	 */
	private Island homeIsland;
	
	/**
	 * the player's {@link Ship}, used to store cargo for trading and for 
	 * sailing between Islands.
	 */
	private Ship ownedShip;
	
	/**
	 * the {@link Island} where the player is currently located.
	 */
	private Island currentIsland;
	
	/**
	 * a String representation of which object, an Island's {@link Port} or {@link Store},
	 * the player can currently interact with.
	 * Can take the values "port" or "store".
	 */
	private String currentLocation;
	
	/**
	 * stores a {@link TradingLog} for each transaction that the player has made.
	 */
	private ArrayList<TradingLog> tradingLogs;
	
	/**
	 * stores a boolean value signifying whether the player has been forced to
	 * walk the plank by pirates (initialised to false). 
	 * If true, the game will be over.
	 */
	private boolean killedByPirates = false;
	
	/**
	 * stores a boolean value signifying whether the player has chosen to quit
	 * the game (initialised to false). 
	 * If true, the game will be over.
	 */
	private boolean hasQuit = false;
	
	/**
	 * provides customisation of the initial values of all starting attributes.
	 * Convenient for testing.
	 * @param days the selected number of days to play
	 * @param name the selected name of the player
	 * @param money the player's starting money
	 * @param home the player's starting Island
	 * @param currentLocation "port" or "store"
	 */
	public Trader(int days, String name, int money,
			      Island home, String currentLocation) {
		this.selectedDays = days;
		this.remainingDays = days;
		this.name = name;
		this.startingMoney = money;
		this.ownedMoney = money;
		this.homeIsland = home;
		this.currentIsland = home;
		this.currentLocation = currentLocation;
		this.tradingLogs = new ArrayList<TradingLog>();	
	}
	
	/**
	 * allows customisation of only those attributes that the player can choose 
	 * themselves.
	 * @param days the selected number of days to play
	 * @param name the selected name String of the player
	 * @param home the player's starting Island
	 */
	public Trader(int days, String name, Island home) {
		this.selectedDays = days;
		this.remainingDays = days;
		this.selectedDays = days;
		this.name = name;
		this.ownedMoney = 10000;
		this.startingMoney = 15000;
		this.ownedMoney = 15000;
		this.homeIsland = home;
		this.currentIsland = home;
		this.currentLocation = "port";
		this.tradingLogs = new ArrayList<TradingLog>();	
}
	
	// getters
	/**
	 * @return the player's {@link selectedDays}.
	 */
	public int getSelectedDays() {
		return this.selectedDays;
	}
	
	/**
	 * @return the player's {@link remainingDays}.
	 */
	public int getRemainingDays() {
		return this.remainingDays;
	}
	
	/**
	 * @return the player's {@link name}.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the player's {@link startingMoney}.
	 */
	public int getStartingMoney() {
		return this.startingMoney;
	}
	
	/**
	 * @return the player's {@link ownedMoney}.
	 */
	public int getOwnedMoney() {
		return this.ownedMoney;
	}
	
	/**
	 * @return the player's {@link homeIsland}.
	 */
	public Island getHomeIsland() {
		return this.homeIsland;
	}
	
	/**
	 * @return the player's {@link ownedShip}.
	 */
	public Ship getOwndedShip() {
		return this.ownedShip;
	}
	
	/**
	 * @return the player's {@link currentIsland}.
	 */
	public Island getCurrentIsland() {
		return this.currentIsland;
	}
	
	/**
	 * @return the player's {@link currentLocation}.
	 */
	public String getCurrentLocation() {
		return this.currentLocation;
	}
	
	/**
	 * @return the player's {@link tradingLogs}.
	 */
	public ArrayList<TradingLog> getTradingLogs() {
		return this.tradingLogs;
	}
	
	/**
	 * @return a pretty String representation of the player's {@link tradingLogs}.
	 */
	public String getTradingLogsString() {
		String logsString = "Your trading log:\n\n";
		if (this.tradingLogs.size() > 0) {
			for (TradingLog log : this.tradingLogs) {
				logsString += log.toString() + "\n";
			}
		} else {
			logsString += "You haven't bought or sold anything yet!";
		}
		return logsString;
	}
	
	/**
	 * @return the boolean {@link killedByPirates} attribute value
	 */
	public boolean getKilledByPirates() {
		return this.killedByPirates;
	}
	
	/**
	 * @return the boolean {@link hasQuit} attribute value
	 */
	public boolean getHasQuit() {
		return this.hasQuit;
	}
	
	// setters
	/**
	 * sets a new value for the player's {@link remainingDays}.
	 * @param days the new number of remaining days
	 */
	public void setRemainingDays(int days) {
		this.remainingDays = days;
	}
	
	/**
	 * updates the player's {@link remainingDays}.
	 * @param days the number of days to subtract
	 */
	public void subtractRemainingDays(int days) {
		this.remainingDays -= days;
	}
	
	/**
	 * sets the player's {@link name}.
	 * @param name the new name String
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * sets a new value for the player's {@link ownedMoney}.
	 * @param amount the new amount of money
	 */
	public void setOwnedMoney(int amount) {
		this.ownedMoney = amount;
	}
	
	/**
	 * updates the player's {@link ownedMoney}.
	 * @param amount the amount to add
	 */
	public void addMoney(int amount) {
		this.ownedMoney += amount;
	}
	
	/**
	 * updates the player's {@link ownedMoney}.
	 * @param amount the amount to deduct
	 */
	public void subtractMoney(int amount) {
		this.ownedMoney -= amount;
	}
	
	/**
	 * sets the player's {@link homeIsland}.
	 * @param island the new starting Island
	 */
	public void setHomeIsland(Island island) {
		this.homeIsland = island;
	}
	
	/**
	 * sets the player's {@link ownedShip}.
	 * @param ship the new Ship
	 */
	public void setOwnedShip(Ship ship) {
		this.ownedShip = ship;
	}
	
	/**
	 * sets the player's {@link currentIsland}.
	 * @param island the new current Island
	 */
	public void setCurrentIsland(Island island) {
		this.currentIsland = island;
	}
	
	/**
	 * sets the player's {@link currentLocation}.
	 * @param location "port" or "store", to designate the player's {@link currentLocation}
	 */
	public void setCurrentLocation(String location) {
		this.currentLocation = location;
	}
	
	/**
	 * sets the player's {@link killedByPirates} attribute to the given value.
	 * (Used to signify whether the player was forced to walk the plank by pirates.)
	 * @param value a boolean value
	 */
	public void setKilledByPirates(boolean value) {
		this.killedByPirates = value;
	}
	
	/**
	 * sets the player's {@link hasQuit} attribute to the given value.
	 * (Used to signify whether the player has chosen to quit the game.)
	 * @param value a boolean value
	 */
	public void setHasQuit(boolean value) {
		this.hasQuit = value;
	}
	
	/**
	 * adds a {@link TradingLog} to the player's {@link tradingLogs}
	 * @param tradingIsland the Island where the transaction occurred
	 * @param item the Item traded
	 * @param sellOrBuy "Bought" or "Sold"
	 */
	public void addTradingLog(Island tradingIsland, 
			                  Item item, String sellOrBuy) {
		TradingLog log = new TradingLog(tradingIsland, item, sellOrBuy);
		this.tradingLogs.add(log);
	}
	
	/**
	 * allows the player to try to sell an {@link Item} to the {@link Store} on the 
	 * current {@link Island}. Checks that the Store is willing to buy the given 
	 * quantity of the Item to be sold, and that the player owns that quantity, before 
	 * processing the transaction. Returns a report String detailing the outcome of the 
	 * attempted transaction to be displayed to the player.
	 * @param currentIsland the player's current Island
	 * @param itemName the name of the Item to be sold
	 * @param quantity the quantity of the Item to be sold
	 * @return a String detailing the outcome of the attempted sale
	 */
	public String sell(Island currentIsland, String itemName, int quantity) { 
		String report = "";
		Store currentStore = currentIsland.getStore();
		Ship playerShip = getOwndedShip();
		Item itemToSell = playerShip.getCargoItem(itemName);
		int pricePerUnit = currentStore.checkItemPrice(itemName, "toBuy");
		
		if (currentStore.getItem(itemName, "toBuy").getQuantity() < quantity) {
			report = "The store won't buy that many! Try again.\n" +
					"Redirecting you to storefront...";
		
		} else if (itemToSell.getQuantity() < quantity) {
			report = "You don't own that quantity of the item! Try again.\n" +
					"Redirecting you to storefront...";
			
		} else {
			
				//update store
				Item itemSold = currentStore.boughtItem(itemName, quantity);
				
				//update cargo
				getOwndedShip().subtractFromCargos(itemToSell, quantity);
				
				//update money
				int priceReceived = quantity * pricePerUnit;
				addMoney(priceReceived);
				
				//update trading log
				this.addTradingLog(currentIsland, itemSold, "Sold");
				
				report = "Success!\n" +
						"Return to port to view your trading logs\n" +
						"Most recent: " +
						this.getTradingLogs().get(this.getTradingLogs().size() - 1).toString() +
						"\nRedirecting you to storefront...";
		} 
		return report;
	}
	

	/**
	 * allows the player to try to buy an {@link Item} from the {@link Store} on the 
	 * current {@link Island}. Checks that the Store has the given 
	 * quantity of the Item in stock, that the player has enough money to purchase
	 * that many, and that the player's {@link Ship} has enough remaining capacity to 
	 * hold the Item before processing the transaction. 
	 * Returns a report String detailing the outcome of the attempted transaction to be 
	 * displayed to the player.
	 * @param currentIsland the player's current Island
	 * @param itemName the name of the Item to be bought
	 * @param quantity the quantity of the Item to be bought
	 * @return a String detailing the outcome of the attempted purchase
	 */
	public String buy(Island currentIsland, String itemName, int quantity) {
		String report = "";
		Store currentStore = currentIsland.getStore();
		Item itemToBuy = currentStore.getItem(itemName, "toSell");
		if (getOwnedMoney() < (itemToBuy.getPricePerUnit() * quantity)) {
			report = "You don't have enough funds to make that purchase!\n" +
					"Redirecting you to storefront...";
		} else if (quantity > itemToBuy.getQuantity()) {
			report = "The store can't sell you that many!\n" +
					"Redirecting you to storefront...";
		} else if (!getOwndedShip().checkCapacityOK(quantity)) {
			report = "Your ship doesn't have enough remaining cargo capacity!\n" +
					"Redirecting you to storefront...";
		} else {
			//update cargo
			Item itemBought = currentStore.soldItem(itemName, quantity);
			getOwndedShip().addToCargos(itemBought, quantity);
			
			//update money
			int priceToPay = itemBought.getPricePerUnit() * quantity;
			subtractMoney(priceToPay);
			
			//update trading log
			addTradingLog(currentIsland, itemBought, "Bought");
			
			report = "Success!\n" +
					"Return to port to view your trading logs\n" +
					"Most recent: " +
					this.getTradingLogs().get(this.getTradingLogs().size() - 1).toString() +
					"\nRedirecting you to storefront...";
		}
		return report;
	}
	
	/**
	 * allows the player to try to repair their {@link Ship} of the damage sustained as a result
	 * of a {@link WeatherEvent}. Checks that there is damage to be repaired, and that the player 
	 * has enough money to complete the repair, before processing the transaction.
	 * Returns a report String detailing the outcome of the attempted transaction to be displayed 
	 * to the player.
	 * @return a String detailing the outcome of the attempted repair
	 */
	public String repairShip() {
		
		String report = "You have to repair your ship at port";
		
		if (getCurrentLocation() == "port") {
			
			int repairCostPerDamage = getCurrentIsland().getPort().getRepairCost();
			int currentDamage = getOwndedShip().getDamage();
			int repairCost = currentDamage * repairCostPerDamage; 
			if (this.getOwndedShip().getDamage() == 0) {
				report = "Your ship is fine. There's nothing to repair.";
			} else if (repairCost <= getOwnedMoney()) {
				// subtract repair cost from trader's owned money
				getOwndedShip().setDurability(getOwndedShip().getDefaultDurability());
				subtractMoney(repairCost);
				// log the repair service into upgradelog
				getOwndedShip().addToUpgradeLogs(currentIsland, "Damage Repair", repairCost);
				report = "The damage has been fixed!\nYour ship is ready to sail again!";
			} else {
				report = "Oh no! You don't have enough money for the repair!\n" + 
						"You'll need to sell some items!";
			}
		}
		
		return report;
	}
	
	/**
	 * allows the player to try to increase the number of cannons on their {@link Ship}, for better
	 * chances of survival in a {@link PirateEvent}. Checks that the Ship has enough space
	 * to equip the given number of cannons, and that the player has enough money to complete the 
	 * upgrade, before processing the transaction.
	 * Returns a report String detailing the outcome of the attempted transaction to be displayed 
	 * to the player.
	 * @param cannonNum the number of cannons to install
	 * @return a String detailing the outcome of the attempted upgrade
	 */
	public String upgradeCannons(int cannonNum) {
		
		String report = "You have to upgrade cannons at port";
		int currentCannons = this.getOwndedShip().getCannons();
		int maxCannons = this.getOwndedShip().getMaxCannons();
		
		if (getCurrentLocation() == "port") {
			int costPerCannon = getCurrentIsland().getPort().getcannonCost();
			int totalCost = costPerCannon * cannonNum;
			if (currentCannons >= maxCannons) {
				report = "You've got enough cannons on your ship. No space for any more!";
			} else if (currentCannons + cannonNum > maxCannons) {
				report = "Captain, you can't equip so many cannons on your ship!\n" +
						 "You can add at most " + (maxCannons - currentCannons) +
						 " more.";
			} else if (totalCost <= getOwnedMoney()) {
				// subtract cannon upgrade cost from trader's owned money
				getOwndedShip().addCannons(cannonNum);
				subtractMoney(totalCost);
				// log the upgrade service into upgradeLog
				getOwndedShip().addToUpgradeLogs(currentIsland, "Cannon Upgrade", totalCost);
				report = "You now have " + cannonNum + " more cannons equipped on your ship.";
			} else {
				report = "Sorry, you don't have enough money to complete this upgrade.";
			}
		}	
		
		return report;
	}
	
	/**
	 * checks whether the player has enough {@link remainingDays} left to sail to another 
	 * {@link Island}. If not, the game will be over.
	 * @return a boolean signifying whether or not the game should be over.
	 */
	public boolean noTimeToSail() {
		boolean gameOver = true;
		for (Route route: this.currentIsland.getRoutes()) {
			if (this.remainingDays > route.getSailingTime(this.ownedShip)) {
				gameOver = false;
			}
		}
		return gameOver;
	}
	
	/**
	 * checks whether the player has enough {@link ownedMoney}, including the total value
	 * of the player's saleable Item objects, to be able to sail to another {@link Island}. 
	 * If not, the game will be over.
	 * @return a boolean signifying whether or not the game should be over.
	 */
	public boolean noMoneyToSail() {
		boolean gameOver = true;
		int sellableCargoValue = getSellableCargoValue();
		for (Route route: this.currentIsland.getRoutes()) {
			int sailingCost = route.getSailingTime(this.ownedShip) * this.ownedShip.getCostPerDay();
			if (sailingCost <= (this.ownedMoney + sellableCargoValue)) {
				gameOver = false;
			}
		}
		Ship playerShip = this.ownedShip;
		Port currentPort = this.getCurrentIsland().getPort();
		int repairCost = playerShip.getDamage() * currentPort.getRepairCost();
		if (repairCost > (this.ownedMoney + sellableCargoValue)) {
			gameOver = true;
		}
		return gameOver;
	}
	
	/**
	 * @return the total sale value of the player's owned Item objects at the player's
	 * current Store.
	 */
	public int getSellableCargoValue() {
		Ship playerShip = this.getOwndedShip();
		Island currentIsland = this.getCurrentIsland();
		Store currentStore = currentIsland.getStore();
		int sellableCargoValue = 0;
		for (Item sellableItem: currentStore.getSellablePlayerItems(playerShip)) {
			int salePrice = sellableItem.getPricePerUnit();
			int quantity = sellableItem.getQuantity();
			sellableCargoValue += (salePrice * quantity);
		}
		return sellableCargoValue;
	}
			
	/**
	* @return a readable String representation of the player.
	*/
	public String toString() {
		String status = "Name: " + this.name + "\n\n" +
						"Money: " + this.ownedMoney + "\n\n" +
						"Ship: " + this.ownedShip.getName() + "\n\n" +
						"Current island: " + this.currentIsland.getName() + "\n" +
						"Current location on the island: " + this.currentLocation;
		return status;
	}
}
