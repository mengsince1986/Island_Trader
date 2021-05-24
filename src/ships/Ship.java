package ships;

import java.util.*;

import trader.*;
import map.*;
import items.*;
import events.*;

/**
 * The Ship class is for creating Ship objects with different properties. In
 * this game, there are four kinds of Ship objects which are different from each
 * other by name, speed, maximum cannon number, minimum crew number, default
 * capacity and default durability. All ship objects are initialized in
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

	/**
	 * Attribute minimumCrewNum is an integer which stores a Ship object's minimum
	 * crew number.
	 */
	private int minimumCrewNum;

	/**
	 * Attribute costPerSailor is an integer which stores how much to pay one sailor
	 * per day.
	 */
	private int costPerSailor;

	/**
	 * Attribute costPerDay is an integer which stores the total sailor cost per
	 * day.
	 */
	private int costPerDay;

	/**
	 * Attribute cargos is an ArrayList which holds the {@link Item} objects which
	 * {@link Trader} buy from {@link Store}.
	 */
	private ArrayList<Item> cargos;

	/**
	 * Attribute defaultCapacity is an integer which stores the default capacity of
	 * this Ship object.
	 */
	private int defaultCapacity;

	/**
	 * Attribute remainingCapacity is an integer which stores the current capacity
	 * (defaultCapacity minus total weight of bought items) of this Ship object.
	 */
	private int remainingCapacity;

	/**
	 * Attribute cannons is an integer which stores the number of the cannons of
	 * this Ship object.
	 */
	private int cannons;

	/**
	 * Attribute maxCannons is an integer which stores the maximum number of the
	 * cannons of this Ship object.
	 */
	private int maxCannons;

	/**
	 * Attribute defaultDurability is an integer which stores the value of the
	 * default durability of this Ship object.
	 */
	private int defaultDurability;

	/**
	 * Attribute durability is an integer which stores the value of the current
	 * durability (defaultDurability minus damage) of this Ship object.
	 */
	private int durability;

	/**
	 * Attribute speed is a string which stores a description of the speed of this
	 * Ship object.
	 */
	private String speed;

	/**
	 * Attribute shipSailingModifier is an integer which stores a value
	 * corresponding to this Ship object's speed description. This attribute is used
	 * to modify the sailing days of this Ship object between {@link Island}s.
	 */
	private int shipSailingModifier;

	/**
	 * Attribute upgradeLogs is an ArrayList which stores all the
	 * {@link UpgradeLog}s (including repair services and cannon upgrade service) of
	 * this Ship object.
	 */
	private ArrayList<UpgradeLog> upgradeLogs;

	/**
	 * This constructor sets the values for all the attributes except for the
	 * captain attribute, calculates the maxCannons by adding 10 to cannons, set
	 * shipSailingModifier by the string value of speed, and initializes new empty
	 * ArrayLists for attribute cargos and upgradLogs.
	 * 
	 * @param name             a sting describing this Ship object's name
	 * @param minCrewNum       an integer of this Ship object's minimum crew number
	 * @param sailorCost       the cost of each sailor per day on this Ship object
	 * @param defaultCapacity  the number of this Ship object's default capacity
	 * @param cannons          the number of this Ship object's cannons
	 * @param defautDurability the number of this Ship object's default durability
	 * @param speed            a string describing this Ship object's speed which
	 *                         can be set as "fast", "normal", or "slow".
	 */
	public Ship(String name, int minCrewNum, int sailorCost, int defaultCapacity, int cannons, int defautDurability,
			String speed) {
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

	/**
	 * This method is the getter of name attribute.
	 * 
	 * @return the string stored in name attribute
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method is the getter of captain attribute.
	 * 
	 * @return the {@link Trader} object stored in captain attribute
	 */
	public Trader getCaptain() {
		return this.captain;
	}

	/**
	 * This method is the getter of creNum attribute.
	 * 
	 * @return the integer value stored in crewNum attribute
	 */
	public int getCrewNumber() {
		return this.crewNum;
	}

	/**
	 * This method is the getter of minimumCrewNum attribute.
	 * 
	 * @return the integer value stored in minimumCrewNum attribute
	 */
	public int getMinimumCrewNum() {
		return this.minimumCrewNum;
	}

	/**
	 * This method is the getter of costPerDay attribute.
	 * 
	 * @return the integer value stored in costPerDay attribute
	 */
	public int getCostPerDay() {
		return this.costPerDay;
	}

	/**
	 * This method is the getter of cargos attribute.
	 * 
	 * @return the ArrayList {@link Item}s stored in cargos attribute.
	 */
	public ArrayList<Item> getCargos() {
		return this.cargos;
	}

	/**
	 * This method formats a string report of the cargos on this {@link Ship} object
	 * by iterating all the {@link Item}s stored in cargos attribute.
	 * 
	 * @return a string describing the contents of the cargos on this {@link Ship}
	 *         object.
	 */
	public String getCargosString() {
		String cargosString = "Remaining Capacity: " + this.getCapacity() + "\n\n";
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

	/**
	 * This method is the getter for remainingCapacity attribute
	 * 
	 * @return the integer value stored in remainingCapacity attribute
	 */
	public int getCapacity() {
		return this.remainingCapacity;
	}

	/**
	 * This method is the getter for defaultCapacity attribute
	 * 
	 * @return the integer value stored in defaultCapacity attribute
	 */
	public int getDefaultCapacity() {
		return this.defaultCapacity;
	}

	/**
	 * This method is the getter for cannons attribute
	 * 
	 * @return the integer value stored in cannons attribute
	 */
	public int getCannons() {
		return this.cannons;
	}

	/**
	 * This method is the getter for maxCannons attribute
	 * 
	 * @return the integer value stored in maxCannons attribute
	 */
	public int getMaxCannons() {
		return this.maxCannons;
	}

	/**
	 * This method is the getter for durability attribute.
	 * 
	 * @return the integer value stored in durability attribute
	 */
	public int getDurability() {
		return this.durability;
	}

	/**
	 * This method is the getter for defaultDurability attribute.
	 * 
	 * @return the integer value stored in defaultDurability attribute
	 */
	public int getDefaultDurability() {
		return this.defaultDurability;
	}

	/**
	 * This method calculates the damage value this {@link Ship} object has
	 * suffered.
	 * 
	 * @return the integer value of the damage
	 */
	public int getDamage() {
		return this.defaultDurability - this.durability;
	}

	/**
	 * This method is the getter for speed attribute
	 * 
	 * @return a string describing this {@link Ship} object's speed
	 */
	public String getSpeed() {
		return this.speed;
	}

	/**
	 * This method is the getter for shipSailingModifier attribute.
	 * 
	 * @return the integer value stored in shipSaiolingModifier attribute
	 */
	public int getShipSailingModifier() {
		return shipSailingModifier;
	}

	/**
	 * This method is the getter for upgradeLogs attribute.
	 * 
	 * @return the ArrayList stored in upgradeLogs attribute
	 */
	public ArrayList<UpgradeLog> getUpgradeLogs() {
		return this.upgradeLogs;
	}

	/**
	 * This method formats a string report of the upgrade logs of this {@link Ship}
	 * object by iterating all the {@link UpgradeLog}s stored in upgradeLogs
	 * attribute.
	 * 
	 * @return a string describing the contents of the upgrade logs of this
	 *         {@link Ship} object.
	 */
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

	/**
	 * This method iterates the ArrayList stored in cargos attribute to check if
	 * there is an {@link Item} object with the name itemName.
	 * 
	 * @param itemName a string name of an item
	 * @return If the item with the name itemName exists, this method returns the
	 *         {@link Item} object. If not, this method returns null.
	 */
	public Item getCargoItem(String itemName) {
		for (Item cargoItem : cargos) {
			if (cargoItem.getName() == itemName) {
				return cargoItem;
			}
		}
		return null;
	}

	/**
	 * This method is the setter of name attribute.
	 * 
	 * @param name a string describing the name of this {@link Ship} object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method is the setter of captain attribute.
	 * 
	 * @param captain a {@link Trader} object
	 */
	public void setCaptain(Trader captain) {
		this.captain = captain;
	}

	/**
	 * This method is the setter of creNum attribute.
	 * 
	 * @param num the value of the crew number for this {@link Ship} object
	 */
	public void setCrewNumber(int num) {
		this.crewNum = num;
	}

	/**
	 * This method adds an integer num to the crewNum attribute and updates the
	 * costPerDay attribute according to the value of the new crewNum.
	 * 
	 * @param num the value to add to the current crew number of this {@link Ship}
	 *            object.
	 */
	public void addCrewNumber(int num) {
		this.crewNum += num;
		this.costPerDay = this.costPerSailor * this.crewNum;
	}

	/**
	 * This method is the setter of minimumCrewNum attribute.
	 * 
	 * @param num an integer to set the minimum crew number of this {@link Ship}
	 *            object
	 */
	public void setMinimumCrewNumber(int num) {
		this.minimumCrewNum = num;
	}

	/**
	 * This method is the setter of costPerDay attribute.
	 * 
	 * @param amount an integer value for the total cost per day of this
	 *               {@link Ship} object.
	 */
	public void setCostPerDay(int amount) {
		this.costPerDay = amount;
	}

	/**
	 * This method adds a new {@link Item} to the cargos attribute and reduce the
	 * current capacity of the this Ship object. If the same kind of {@link Item} is
	 * already in cargos, only its quantity will be updated. Otherwise, the new
	 * {@link Item} will be added to the cargos.
	 * 
	 * @param item     an {@link Item} object
	 * @param quantity the quantity number of the new {@link Item} to add to the
	 *                 cargoes
	 */
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

	/**
	 * This method removes a specific quantity of {@link Item} out of cargos and
	 * 
	 * @param soldItem
	 * @param quantity
	 */
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
		}
		if (toDelete) {
			this.cargos.remove(indexToDelete);
		}

		addCapacity(soldItem.getQuantity());
	}

	public void addCapacity(int amount) {
		this.remainingCapacity = Integer.min(this.defaultCapacity, this.remainingCapacity + amount);
	}

	public void subtractCapacity(int amount) {
		this.remainingCapacity = Integer.max(0, this.remainingCapacity - amount);
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
		if (this.cannons + num <= this.maxCannons) {
			this.cannons += num;
		} else {
			System.out.println("We can't install so many cannons on the ship");
		}
	}

	public void sufferDamage(int damage) {
		if (this.durability - damage > 0) {
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
			// return array of report strings for further processing in GameEnvironment?

			// update captain currentIsland and currentLocation
			getCaptain().setCurrentIsland(destination);
			getCaptain().setCurrentLocation("port");
		} else if (this.durability != this.defaultDurability) {
			String eventReport = "You need to fix your ship first!";
			eventReports.add(eventReport);
		} else if (this.getCaptain().getRemainingDays() < getCaptain().getCurrentIsland().daysToIsland(destination,
				this)) {
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
		String properties = "Name: " + getName() + "\n\n" + "Speed: " + getSpeed() + "\n" + "Default durability: "
				+ getDefaultDurability() + "\n" + "Damage suffered: " + getDamage() + "\n\n" + "Current crew number: "
				+ getCrewNumber() + "\n" + "Cannons: " + getCannons() + "\n\n" + "Cost per day: " + getCostPerDay();
		return properties;
	}

	// Abstract methods
}
