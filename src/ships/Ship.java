package ships;
import java.util.*;
import trader.*;
import map.*;
import items.*;

public abstract class Ship {

	private String name;
	private Trader captain;
	private int crewNum;
	private int minimumCrewNum;
	private int costPerSailor; // how much to pay a sailor per day
	private int costPerDay;
	private ArrayList<Item> cargos;
	private int defaultCapacity;
	private int capacity;
	private int cannons;
	private int maxCannons;
	private int defaultDurability;
	private int durability;
	private String speed;
	private int shipSailingModifier;
	private ArrayList<UpgradeLog> upgradeLogs;
	
	public Ship(String name, int minCrewNum, int sailorCost,
			    int defaultCapacity, int cannons, int defautDurability, String speed) {
		this.name = name;
		this.minimumCrewNum = minCrewNum;
		this.crewNum = minCrewNum;
		this.costPerSailor = sailorCost;
		this.costPerDay = crewNum * sailorCost; 
		this.cargos = new ArrayList<Item>();
		this.defaultCapacity = defaultCapacity;
		this.capacity = defaultCapacity;
		this.cannons = cannons;
		this.defaultDurability = defautDurability;
		this.durability = defautDurability;
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
	
	public int getCapacity() {
		return this.capacity;
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
	
	public void addToCargos(Item item) {
		this.cargos.add(item);
		subtractCapacity(item.getCargoSize());
	}
	
	public void subtractFromCargos(Item soldItem) {
		for (Item item : this.cargos) {
			if (item.getName() == soldItem.getName()) {
				this.cargos.remove(item);
				addCapacity(soldItem.getCargoSize());
			}
		}
	}
	
	public void addCapacity(int amount) {
		this.capacity = Integer.min(this.defaultCapacity, this.capacity+amount);
	}
	
	public void subtractCapacity(int amount) {
		this.capacity = Integer.max(0, this.capacity-amount);
	}
	
	public boolean checkCapacity(int toReduce) {
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
		
		
		if ((remainingDays - daysToDestination) <= 0) {
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
	
	public void sailTo(Island destination) {
		if (readyToSail(destination)) {

			// update remaining days
			
			int daysToDestination = getCaptain().getCurrentIsland().daysToIsland(destination, this);
			
			getCaptain().subtractRemainingDays(daysToDestination);
			
			// pay crew and update captain ownedMoney
			int costToDestination = getCostPerDay() * daysToDestination;
			getCaptain().subtractMoney(costToDestination);

			// call random events on the route:
			/// get events from array
			//// String PirateReport =
			//// String WeatherReport =
			//// String RescueReport =
			
			//return array of report strings for further processing in GameEnvironment?
			
			
			// update captain currentIsland and currentLocation
			getCaptain().setCurrentIsland(destination);
			getCaptain().setCurrentLocation("port");
		}
	}
	
	
	// print
	public String toString() {
		String properties = "Name: " + getName() + "\n" +
							"Speed: " + getSpeed() + "\n" +
							"Default durability: " + getDurability() + "\n" +
							"Minimum crew number: " + getMinimumCrewNum() + "\n" +
							"Current crew number: " + getCrewNumber() + "\n" +
							"Cannons: " + getCannons() + "\n" +
							"Cost per day: " + getCostPerDay() + "\n" +
							"Remaining capacity: " + getCapacity() + "\n" +
	                        "Current damage: " + getDamage() + "\n";
		return properties;
	}
	
	// Abstract methods
}
