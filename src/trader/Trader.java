package trader;
import java.util.*;
import ships.*;
import map.*;
import items.*;

public class Trader {
	
	private int selectedDays;
	private int remainingDays;
	private String name;
	private int startingMoney;
	private int ownedMoney;
	private Island homeIsland;
	private Ship ownedShip;
	private Island currentIsland;
	private String curentLocation;
	private ArrayList<TradingLog> tradingLogs;
	
	// constructor
	public Trader(int days, String name, int money,
			      //Island home, Ship ship, 
			      Island home, String currentLocation) {
		this.selectedDays = days;
		this.remainingDays = days;
		this.name = name;
		this.startingMoney = money;
		this.ownedMoney = money;
		this.homeIsland = home;
		//this.ownedShip = ship;
		this.currentIsland = home;
		this.curentLocation = currentLocation;
		this.tradingLogs = new ArrayList<TradingLog>();	
	}
	
	public Trader(int days, String name, Island home) {
	
	this.remainingDays = days;
	this.selectedDays = days;
	this.name = name;
	this.ownedMoney = 10000;
	this.homeIsland = home;
	//this.ownedShip = ship;
	this.currentIsland = home;
	this.curentLocation = "port";
	this.tradingLogs = new ArrayList<TradingLog>();	
}
	
	// getters
	
	public int getSelectedDays() {
		return this.selectedDays;
	}
	
	public int getRemainingDays() {
		return this.remainingDays;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getStartingMoney() {
		return this.startingMoney;
	}
	
	public int getOwnedMoney() {
		return this.ownedMoney;
	}
	
	public Island getHomeIsland() {
		return this.homeIsland;
	}
	
	public Ship getOwndedShip() {
		return this.ownedShip;
	}
	
	public Island getCurrentIsland() {
		return this.currentIsland;
	}
	
	public String getCurrentLocation() {
		return this.curentLocation;
	}
	
	public ArrayList<TradingLog> getTradingLogs() {
		return this.tradingLogs;
	}
	
	// print trading logs
	public String getTradingLogsString() {
		String logsString = "Your trading log:\n";
		if (this.tradingLogs.size() > 0) {
			for (TradingLog log : this.tradingLogs) {
				logsString += log.toString() + "\n";
			}
		} else {
			logsString += "You haven't bought or sold anything yet!";
		}
		return logsString;
	}
	
	// setters
	public void setRemainingDays(int days) {
		this.remainingDays = days;
	}
	
	public void subtractRemainingDays(int days) {
		this.remainingDays -= days;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public void setOwnedMoney(int amount) {
		this.ownedMoney = amount;
	}
	
	
	// addMoeny
	public void addMoney(int amount) {
		this.ownedMoney += amount;
	}
	
	// subtractMoney
	public void subtractMoney(int amount) {
		this.ownedMoney -= amount;
	}
	
	public void setHomeIsland(Island island) {
		this.homeIsland = island;
	}
	
	public void setOwnedShip(Ship ship) {
		this.ownedShip = ship;
	}
	
	public void setCurrentIsland(Island island) {
		this.currentIsland = island;
	}
	
	public void setCurrentLocation(String location) {
		this.curentLocation = location;
	}
	
	// log trading history
	public void addTradingLog(Island tradingIsland, 
			                  Item item, String sellOrBuy) {
		TradingLog log = new TradingLog(tradingIsland, item, sellOrBuy);
		this.tradingLogs.add(log);
	}
	
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
				report = "Oh no! You don't have enough money for the repair!";
			}
		}
		
		return report;
	}
	
	public String upgradeCannons(int cannonNum) {
		
		String report = "You have to upgrade cannons at port";
		int currentCannons = this.getOwndedShip().getCannons();
		int maxCannons = this.getOwndedShip().getMaxCannons();
		
		if (getCurrentLocation() == "port") {
			int costPerCannon = getCurrentIsland().getPort().getcannonCost();
			int totalCost = costPerCannon * cannonNum;
			if (currentCannons >= maxCannons) {
				report = "You've got enough cannons on your ship. No space for another one.";
			} else if (currentCannons + cannonNum > maxCannons) {
				report = "Captain, you can't equip so many cannons on your ship.\n" +
						 "You can have at most " + (maxCannons - currentCannons) +
						 " more cannons to upgrade.";
			} else if (totalCost <= getOwnedMoney()) {
				// subtract cannon upgrade cost from trader's owned money
				getOwndedShip().addCannons(cannonNum);
				subtractMoney(totalCost);
				// log the upgrade service into upgradeLog
				getOwndedShip().addToUpgradeLogs(currentIsland, "Cannon Upgrade", totalCost);
				report = "You now have " + cannonNum + " more cannons equiped on your ship.";
			} else {
				report = "Sorry, you don't have enough money to upgrade cannons.";
			}
		}	
		
		return report;
	}
	
	public boolean noTimeToSail() {
		boolean gameOver = true;
		for (Route route: this.currentIsland.getRoutes()) {
			if (this.remainingDays > route.getSailingTime(this.ownedShip)) {
				gameOver = false;
			}
		}
		return gameOver;
	}
	
	public boolean noMoneyToSail() {
		boolean gameOver = true;
		for (Route route: this.currentIsland.getRoutes()) {
			int sailingCost = route.getSailingTime(this.ownedShip) * this.ownedShip.getCostPerDay();
			if (sailingCost < this.ownedMoney) {
				gameOver = false;
			}
		}
		return gameOver;
	}
	
	public String toString() {
		String status = "Name: " + this.name + "\n\n" +
						"Money Owend: " + this.ownedMoney + "\n\n" +
						"Home Island: " + this.homeIsland.getName() + "\n" +
						"Ship Owned: " + this.ownedShip.getName() + "\n\n" +
						"Current Island: " + this.currentIsland.getName() + "\n" +
						"Current Location: " + this.curentLocation;
		return status;
	}
	
	public static void main(String[] args) {
		
	}
}
