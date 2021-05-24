package ships;
import java.util.*;

import trader.*;
import map.*;
import items.*;
import events.*;

/**
 * The Ship class is for creating Ship objects with different properties.
 * In this game, there are four kinds of Ship objects which are different from
 * each other by name, speed, maximum cannon number, minimum crew number,
 * default capacity and default durability. All ship objects are initialized in
 * {@link WorldConstructor} object.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class Ship {

	/**
	 * Attribute name is a string which stores a Ship object's name.
	 */
	private String name;
	/**
	 * Attribute captain is a Trader object who owns this Ship Object.
	 */
	private Trader captain;
	/**
	 * Attribute crewNum is an integer which stores a Ship object's current crew
	 * number.
	 */
	private int crewNum;
	private int minimumCrewNum;
	private int costPerSailor; // how much to pay a sailor per day
	private int costPerDay;
	private ArrayList<Item> cargos;
	private int defaultCapacity;
	private int remainingCapacity;
	private int cannons;
	private int maxCannons;
	private int defaultDurability;
	private int durability;
	private String speed;
	private int shipSailingModifier;
	private ArrayList<UpgradeLog> upgradeLogs;
	
	public Ship(String name, int minCrewNum, int sailorCost,
			    int defaultCapacity, int cannons, int defaultDurability, String speed) {
		this.name = name;
		this.minimumCrewNum = minCrewNum;
		this.crewNum = minCrewNum;
		this.costPerSailor = sailorCost;
		this.costPerDay = crewNum * sailorCost; 
		this.cargos = new ArrayList<Item>();
		this.defaultCapacity = defaultCapacity;
		this.remainingCapacity = defaultCapacity;
		this.cannons = cannons;
		this.maxCannons = cannons + 10;
		this.defaultDurability = defaultDurability;
		this.durability = defaultDurability;
		this.speed = speed;
		if (speed == "fast") {
			this.shipSailingModifier = 1; // save 1 day for fast speed
		} else if (speed == "slow") {
			this.shipSailingModifier = -1; // add 1 day for slow speed
		}
		this.upgradeLogs = new ArrayList<UpgradeLog>();
	}
	
	// getters =============================
	public String getName() {
		return this.name;
	}
	
	public Trader getCaptain() {
		return this.captain;
	}
	
	public int getCrewNumber() {
		return this.crewNum;
	}
	
	public int getMinimumCrewNum() {
		return this.minimumCrewNum;
	}
	
	public int getCostPerDay() {
		return this.costPerDay;
	}
	
	public ArrayList<Item> getCargos() {
		return this.cargos;
	}
	
	public String getCargosString() {
		String  cargosString = "Remaining Capacity: " + this.getCapacity() + "\n\n";
		cargosString += "Cargo contents:\n\n";
		if (this.cargos.size() > 0) {
			for (Item cargoItem : this.cargos) {
				cargosString += cargoItem.getCargoItemString() + "\n";
			}
		} else {
			cargosString += "Your ship isn't carrying any cargo.\n";
		}
		return cargosString;
	}
	
	
	public int getCapacity() {
		return this.remainingCapacity;
	}
	
	public int getDefaultCapacity() {
		return this.defaultCapacity;
	}
	
	public int getCannons() {
		return this.cannons;
	}
	
	public int getMaxCannons() {
		return this.maxCannons;
	}
	
	public int getDurability() {
		return this.durability;
	}
	
	public int getDefaultDurability() {
		return this.defaultDurability;
	}
	
	public int getDamage() {
		return this.defaultDurability - this.durability;
	}
	
	public String getSpeed() {
		return this.speed;
	}
	
	public int getShipSailingModifier() {
		return shipSailingModifier;
	}

	public ArrayList<UpgradeLog> getUpgradeLogs() {
		return this.upgradeLogs;
	}
	
	public String getUpgradeLogString() {
		String logString = "Upgrade log:\n\n";
		if (this.upgradeLogs.size() > 0) {
			for (UpgradeLog upgrade : this.upgradeLogs) {
				logString += upgrade.toString() + "\n";
			}
		} else {
			logString += "Your ship hasn't been upgraded yet!\n";
		}
		return logString;
	}
	
	public Item getCargoItem(String itemName) {
		for (Item cargoItem : cargos) {
			if (cargoItem.getName() == itemName) {
				return cargoItem;
			}
		} return null;
	}
	
	// setters =============================
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCaptain(Trader captain) {
		this.captain = captain;
	}
	
	public void setCrewNumber(int num) {
		this.crewNum = num;
	}
	
	public void addCrewNumber(int num) {
		this.crewNum += num;
		this.costPerDay = this.costPerSailor * this.crewNum;
	}
	
	public void setMinimumCrewNumber(int num) {
		this.minimumCrewNum = num;
	}
	
	public void setCostPerDay(int amount) {
		this.costPerDay = amount;
	}
	
	public void addToCargos(Item item, int quantity) {
		boolean found = false;
		for (Item cargoItem : cargos) {
			if (cargoItem.getName() == item.getName()) {
				cargoItem.addQuantity(item.getQuantity());
				found = true;
			}
		}
		if (!found) {
			this.cargos.add(item);
		}
		subtractCapacity(item.getQuantity());
	}
	
	public void subtractFromCargos(Item soldItem, int quantity) {
		boolean toDelete = false;
		int indexToDelete = 0;
		for (Item item : this.cargos) {
			if (item.getName() == soldItem.getName()) {
				if (item.getQuantity() > quantity) {
					item.subtractQuantity(quantity);
				} else {
					toDelete = true;
					indexToDelete = this.cargos.lastIndexOf(item);
				}
			}
		} if (toDelete) {
			this.cargos.remove(indexToDelete);
		}
		
		addCapacity(soldItem.getQuantity());
	}
	
	public void addCapacity(int amount) {
		this.remainingCapacity = Integer.min(this.defaultCapacity, this.remainingCapacity+amount);
	}
	
	public void subtractCapacity(int amount) {
		this.remainingCapacity = Integer.max(0, this.remainingCapacity-amount);
	}
	
	public boolean checkCapacityOK(int toReduce) {
		if (getCapacity() - toReduce < 0) {
			return false;
		}
		return true;
	}
	
	public void setDurability(int num) {
		this.durability = num;
	}
	
	public void setDefaultDurability(int num) {
		this.defaultDurability = num;
	}
	
	public void setCannons(int num) {
		this.cannons = num;
	}
	
	public void addCannons(int num) {
		if (this.cannons+num <= this.maxCannons) {
			this.cannons += num;
		} else {
			System.out.println("We can't install so many cannons on the ship");
		}	
	}
	
	public void sufferDamage(int damage) {
		if (this.durability-damage > 0) {
			this.durability -= damage;
		} else {
			this.durability = 0;
		}
	}
	
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	

	public void setShipSailingModifier(int shipSailingModifier) {
		this.shipSailingModifier = shipSailingModifier;
	}
	
	public void emptyCargos() {
		this.cargos = new ArrayList<Item>();
	}

	public void addToUpgradeLogs(Island location, String service, int cost) {
		UpgradeLog log = new UpgradeLog(location, service, cost);
		this.upgradeLogs.add(log);
	}
	

	// Sailing
	public boolean readyToSail(Island destination) {
		
		boolean isReady;

		// check if Remaining days enough
		int remainingDays = this.getCaptain().getRemainingDays();
		// update remaining days
		int daysToDestination = getCaptain().getCurrentIsland().daysToIsland(destination, this);
		
		
		if ((remainingDays - daysToDestination) < 0) {
			isReady = false;
			// add exceptions
			return isReady;
		}
		
		// check if damage
		if (this.durability != this.defaultDurability) {
			isReady = false;
			// add exceptions
			return isReady;
		}
		
		// check if money enough to pay crew
		int costToDestination = this.costPerDay * daysToDestination;
		if (costToDestination > this.getCaptain().getOwnedMoney()) {
			isReady = false;
			// add exceptions and add exception package
			return isReady;
		}
		
		return true;
	}
	
	public ArrayList<String> sailTo(Island destination) {
		
		ArrayList<String> eventReports = new ArrayList<String>();
		
		if (readyToSail(destination)) {
			Trader captain = getCaptain();
			Island currentIsland = captain.getCurrentIsland();

			// update remaining days
			int daysToDestination = currentIsland.daysToIsland(destination, this);
			getCaptain().subtractRemainingDays(daysToDestination);
			
			// pay crew and update captain ownedMoney
			int costToDestination = getCostPerDay() * daysToDestination;
			getCaptain().subtractMoney(costToDestination);

			// call random events on the route:
			
			Route route = currentIsland.getRoute(destination);
			boolean reportsAllEmpty = true;
			for (RandomEvent randomEvent : route.getEvents()) {

				if (randomEvent.getTriggered()) {
					String eventReport = randomEvent.processImpact(captain);
					if (!eventReport.isBlank()) {
						eventReports.add(eventReport + "\n");
						reportsAllEmpty = false;
					}
				}
			}
			
			if (route.getEvents().size() == 0 | reportsAllEmpty) {
				eventReports.add("You had a safe and uneventful journey!\n");

			}
			//// String PirateReport =
			//// String WeatherReport =
			//// String RescueReport =
			//return array of report strings for further processing in GameEnvironment?
			
			// update captain currentIsland and currentLocation
			getCaptain().setCurrentIsland(destination);
			getCaptain().setCurrentLocation("port");
		} else if (this.durability != this.defaultDurability) {
			String eventReport = "You need to fix your ship first!";
			eventReports.add(eventReport);
		} else if (this.getCaptain().getRemainingDays() < 
				   getCaptain().getCurrentIsland().daysToIsland(destination, this)) {
			String eventReport = "Oh no! You don't have enough time to sail to that island!";
			eventReports.add(eventReport);
		} else {
			String eventReport = "Oh no! You don't have enough money to pay your crew.";
			eventReports.add(eventReport);
		}
		
		return eventReports;
	}
	
	
	// print
	public String toString() {
		String properties = "Name: " + getName() + "\n\n" +
							"Speed: " + getSpeed() + "\n" +
							"Default durability: " + getDefaultDurability() + "\n" +
							"Damage suffered: " + getDamage() + "\n\n" +
							"Current crew number: " + getCrewNumber() + "\n" +
							"Cannons: " + getCannons() + "\n\n" +
							"Cost per day: " + getCostPerDay();
		return properties;
	}
	
	// Abstract methods
}
