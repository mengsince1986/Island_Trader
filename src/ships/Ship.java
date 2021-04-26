package ships;
import java.util.*;
import trader.*;
import map.*;
import items.*;
import events.*;

public abstract class Ship {

	private String name;
	private Trader captain;
	private int crewNum;
	private int minimumCrewNum;
	private int costPerDay;
	private ArrayList<Item> cargos;
	private int capacity;
	private int cannons;
	private int maxCannons;
	private int durability;
	private int defaultDurability;
	private String speed;
	private int sailingDaysModifier; //negative for faster ship; positive for slower
	private ArrayList<UpgradeLog> upgradeLogs;
	
	public Ship() {
		this.upgradeLogs = new ArrayList<UpgradeLog>();
	}
	
	//Concrete methods
	
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
	
	public int getCannons() {
		return this.cannons;
	}
	
	public int getDurability() {
		return this.durability;
	}
	
	public int getDefaultDurability() {
		return this.defaultDurability;
	}
	
	public String getSpeed() {
		return this.speed;
	}
	
	public int getSailingDaysModifier() {
		return this.sailingDaysModifier;
	}
	
	public ArrayList<UpgradeLog> getUpgradeLogs() {
		return this.upgradeLogs;
	}
	
	// getters =============================
	
	// setters =============================
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCaptain(Trader captain) {
		this.captain = captain;
	}
	
	public void setCrewNumber(int num) {
		this.crewNum = num;
		this.costPerDay = 1 * this.crewNum; // 1 gold per sailor
	}
	
	public void setMinimumCrewNumber(int num) {
		this.minimumCrewNum = num;
	}
	
	public void setCostPerDay(int num) {
		this.costPerDay = num;
	}
	
	public void addToCargos(Item item) {
		this.cargos.add(item);
		this.capacity -= item.getCargoSize();
	}
	
	public void setCapacity(int num) {
		this.capacity = num;
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
	
	public void setSailingDaysModifier(int days) {
		this.sailingDaysModifier = days;
	}
	
	public void emptyCargos() {
		// set cargos
		this.cargos = new ArrayList<Item>();
	}

	public void addToUpgradeLogs(Island location, String service, int cost) {
		UpgradeLog log = new UpgradeLog(location, service, cost);
		this.upgradeLogs.add(log);
	}
	
	// setters =============================
	
	public boolean readyToSail(Island destination) {
		boolean isReady;

		// check if Remaining days enough
		int remainingDays = this.getCaptain().getRemainingDays();
		int daysToDestination = this.captain.getCurrentIsland().daysToIsland(destination, this.getSailingDaysModifier());
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
	
	public String toString() {
		int currentDamage = this.defaultDurability - this.durability;
		int repairFeePerDamage = this.getCaptain().getCurrentIsland().getPort().getRepairCost();
		int costToRepair = repairFeePerDamage * currentDamage;
		String properties = "Name: " + this.name + "\n" +
							"Crew number: " + this.crewNum + "\n" +
							"Cost per day: " + this.costPerDay + "\n" +
							"Remaining capacity: " + this.capacity + "\n" +
	                        "Damage: " + currentDamage + "\n" +
							"Cost to repair: " + costToRepair;
		return properties;
	}
	
	// Abstract methods
	
	public abstract void sailTo(Island destination);
	//public abstract void battle(String strategy, Pirate enemy); 
	
}
