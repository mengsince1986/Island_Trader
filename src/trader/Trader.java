package trader;
import java.util.*;
import ships.*;
import map.*;
import items.*;

public class Trader {
	
	private int remainingDays;
	private String name;
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
		
		this.remainingDays = days;
		this.name = name;
		this.ownedMoney = money;
		this.homeIsland = home;
		//this.ownedShip = ship;
		this.currentIsland = home;
		this.curentLocation = currentLocation;
		this.tradingLogs = new ArrayList<TradingLog>();	
	}
	
	public Trader(int days, String name, Island home) {
	
	this.remainingDays = days;
	this.name = name;
	this.ownedMoney = 1000;
	this.homeIsland = home;
	//this.ownedShip = ship;
	this.currentIsland = home;
	this.curentLocation = "port";
	this.tradingLogs = new ArrayList<TradingLog>();	
}
	
	// getters
	public int getRemainingDays() {
		return this.remainingDays;
	}
	
	public String getName() {
		return this.name;
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
	public String TradingLogsToString() {
		String logs = "";
		for (TradingLog log : this.tradingLogs) {
			logs += log.toString() + "\n";
		}
		return logs;
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
	
	public void sell(Island currentIsland, String itemName, int itemSize) { 
		if (getCurrentLocation() == "store") {
			Store currentStore = currentIsland.getStore();
			int itemPrice = currentStore.checkItemPrice(itemName, "toBuy");
			if (itemPrice != -1) {
				//update cargo
				Item itemSold = currentStore.itemToBuy(itemName, itemSize); // store.buy() return the item it buys
				getOwndedShip().subtractFromCargos(itemSold);
				
				//update money
				int priceToReceive = itemSold.getCargoSize() * itemPrice;
				addMoney(priceToReceive);
				
				//update trading log
				this.addTradingLog(currentIsland, itemSold, "sell");
			} else {
				System.out.println("The store is not looking for the item.");
			}
		}	
	}
	
	public void buy(Island currentIsland, String itemName, int quantity) {
		if (getCurrentLocation() == "store") {
			Store currentStore = currentIsland.getStore();
			int itemPrice = currentStore.checkItemPrice(itemName, "toSell");
			boolean enoughCapacity = getOwndedShip().checkCapacity(quantity);
			//ArrayList<Item> saleList = currentStore.getToSell();
			
			if (itemPrice != -1 && enoughCapacity) {
				//update cargo
				Item itemBought = currentStore.itemToSell(itemName, quantity); // store.sell() return the item it sells
				getOwndedShip().addToCargos(itemBought);
				
				//update money
				int priceToPay = itemBought.getCargoSize() * itemPrice;
				subtractMoney(priceToPay);
				
				//update trading log
				this.addTradingLog(currentIsland, itemBought, "buy");
			} else {
				System.out.println("The item is not available in store or your ship doesn't have enough capacity.");
			}
		}	
	}
	
	public void repairShip() {
		if (getCurrentLocation() == "port") {
			int repairCost = getCurrentIsland().getPort().getRepairCost();
			if (repairCost <= getOwnedMoney()) {
				getOwndedShip().setDurability(getOwndedShip().getDefaultDurability());
				subtractMoney(repairCost);
			} else {
				System.out.println("You money is not enough to repair the ship.");
			}
		}
		
	}
	
	public void upgradeCannons(int cannonNum) {
		if (getCurrentLocation() == "port") {
			int costPerCannon = getCurrentIsland().getPort().getcannonCost();
			int totalCost = costPerCannon * cannonNum;
			if (totalCost <= getOwnedMoney() && 
				getOwndedShip().getCannons() + cannonNum <= getOwndedShip().getMaxCannons()) {
				getOwndedShip().addCannons(cannonNum);
				subtractMoney(totalCost);
			}
		}	
	}
	
	public String toString() {
		String status = "Remaining Days: " + this.remainingDays + "\n" +
						"Name: " + this.name + "\n" +
						"Money: " + this.ownedMoney + "\n" +
						"Hometown: " + this.homeIsland.getName() + "\n" +
						"Ship: " + this.ownedShip.getName() + "\n" +
						"Current Island: " + this.currentIsland.getName() + "\n" +
						"Current Location: " + this.curentLocation;
		return status;
	}
	
	public static void main(String[] args) {
		
	}
}
