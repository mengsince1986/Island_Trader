package ships;

import map.*;
import trader.Trader;

/**
 * The UpgradeLog class is for initializing an object to record the repair or
 * cannon upgrade service a {@link Trader} purchased for a {@link Ship}.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class UpgradeLog {

	/**
	 * Attribute upgradeLocation stores the string name of the {@link Island} where
	 * the upgrade service is purchased
	 */
	private String upgradeLocation;

	/**
	 * Attribute upgradeService stores the string name of the purchased upgrade
	 * service.
	 */
	private String upgradeService;

	/**
	 * Attribute upgradeCost stores the value of the upgrade service cost.
	 */
	private int upgradeCost;

	/**
	 * This constructor sets the values for all three class attributes.
	 * 
	 * @param location an {@link Island} object
	 * @param service  the string name of the upgrade service ("Damage Repair" or
	 *                 "Cannon Upgrade")
	 * @param cost     the integer value of the upgrade/repair cost
	 */
	public UpgradeLog(Island location, String service, int cost) {
		this.upgradeLocation = location.getName();
		this.upgradeService = service;
		this.upgradeCost = cost;
	}

	/**
	 * This methods modifies the string representation of this UpgradeLog object.
	 */
	@Override
	public String toString() {

		String log = this.upgradeService + " at " + this.upgradeLocation + " for " + this.upgradeCost + " coins";
		return log;
	}

}
