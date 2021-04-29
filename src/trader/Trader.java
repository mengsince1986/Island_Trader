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
			      Island home, Ship ship, 
			      Island currentIsland, String currentLocation) {
		
		this.remainingDays = days;
		this.name = name;
		this.ownedMoney = money;
		this.homeIsland = home;
		this.ownedShip = ship;
		this.currentIsland = currentIsland;
		this.curentLocation = currentLocation;
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
	public void addMoney(int num) {
		this.ownedMoney += num;
	}
	
	// subtractMoney
	public void subtractMoney(int num) {
		this.ownedMoney -= num;
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
	
	public void sell(Item item, int quantity) { 
		// 
		Store currentStore = this.currentIsland.getStore();
		
		
		// check cargo refer to Map.getIsland
		// update cargo
		
		
		//update money
		
		
		
		//update log
		this.addTradingLog(this.currentIsland, item, "sell");
	}
	
	public void buy(Island currentIsland, Item item) {
		//...
		//update cargo
		
		//update money
		
		this.addTradingLog(currentIsland, item, "buy");
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
		Island superIsland = new Island("super island");
		FastShip superShip = new FastShip();
		Trader superPlayer = new Trader(30, "Super Man", 10000, superIsland, superShip, superIsland, "port");
		System.out.println(superPlayer);
	}
}
