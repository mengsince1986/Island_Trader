package events;

import trader.Trader;
import ships.Ship;

/**
 * A class to implement the "unfortunate weather" random event, which causes
 * a storm to deal a random amount of damage to the player's {@link Ship}.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 *
 */
public class WeatherEvent extends RandomEvent {
	
	/**
	 * a constant maximum amount of damage to be dealt by the event, set to be 1 less
	 * than the defaultDurability attribute of the {@link Ship}s on offer to the 
	 * player.
	 */
	private final int MAX_DAMAGE = 69;
	
	/**
	 * initialises a new WeatherEvent and with the value given as the {@link chanceLevel}.
	 * @param chanceLevel the number representing the likelihood of the event triggering
	 * on any one occasion
	 */
	public WeatherEvent(int chanceLevel) {
		setChanceLevel(chanceLevel);
	}
	
	/**
	 * generates and returns the pseudorandom amount of damage to be dealt to a Ship 
	 * object as a result of the event's being triggered.
	 * @return the damage to be dealt to a Ship object as a result of the event
	 */
	public int getDamage() {
		int damageDealt = getRandomInRange(1, MAX_DAMAGE);
		return damageDealt;
	}

	/**
	 * takes the amount of damage dealt to a Ship and returns a readable String
	 * detailing the damage dealt.
	 * @param shipDamage the amount of damage dealt
	 * @return a report String detailing the damage dealt
	 */
	public String getReport(int shipDamage) {
		return String.format(("Your ship struck a storm and took %d damage! " + 
				"You'll have to repair it at the next port.\n"), shipDamage);
	}
	
	/**
	 * processes the impact of the triggered WeatherEvent on the given {@link Trader}
	 * and returns a report detailing this impact to be displayed to the player.
	 * @return a report String detailing the impact of the event and to be displayed
	 * to the player 
	 */
	public String processImpact(Trader player) {
		int shipDamage = getDamage();
		Ship ship = player.getOwndedShip();
		ship.sufferDamage(shipDamage);
		String reportString = getReport(shipDamage);
		return reportString;
	}
}