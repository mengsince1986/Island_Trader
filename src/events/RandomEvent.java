package events;

import java.util.Random;
import trader.Trader;

/**
 * An abstract class that defines the key characteristics and behaviours
 * of the game's random events.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 *
 */
public abstract class RandomEvent {
	
	/**
	 * a {@link Random} object that generates pseudorandom numbers to provide the 
	 * randomness of event outcomes.
	 */
	private Random randomGenerator = new Random();
	
	/**
	 * a number representing the likelihood that the event will be triggered.
	 * a value of 0 represents a 0% chance of the event being triggered, while
	 * a value greater or equal to 10 represents a 100% chance of the event being triggered.
	 */
	private int chanceLevel;
	
	/**
	 * @return the RandomEvent's {@link chanceLevel} value
	 */
	public int getchanceLevel() {
		return chanceLevel;
	}

	/**
	 * takes a number representing the {@link chanceLevel} that the event is 
	 * to have. If the given number is less than 0, the chanceLevel will be set to 0.
	 * @param chanceLevel the {@link chanceLevel} to be set
	 */
	public void setChanceLevel(int chanceLevel) {
		this.chanceLevel = Integer.max(0, chanceLevel);
	}

	/**
	 * generates and returns a pseudorandom, uniformly distributed integer between 
	 * a given lower bound, minRoll, and a given upper bound, maxRoll (inclusive). 
	 * @param minRoll the minimum integer value to be generated
	 * @param maxRoll the maximum integer value to be generated
	 * @return a uniform, pseudorandom integer in the interval [minRoll, maxRoll]
	 */
	public int getRandomInRange(int minRoll, int maxRoll) {
		return randomGenerator.nextInt((maxRoll - minRoll) + 1) + minRoll;
	}
	
	/**
	 * determines, based on the event's {@link chanceLevel} value, whether the event
	 * is triggered on this occasion and returns true if triggered, else false.
	 * @return true if the event is triggered, else false
	 */
	public boolean getTriggered() {
		int threshold = Integer.max(0, (10 - chanceLevel));
		int minRoll = 1;
		int maxRoll = 10;
		int randomRoll = getRandomInRange(minRoll, maxRoll);
		if (randomRoll > threshold) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * determines how the outcome of each particular event affects the player's 
	 * {@link Trader} object and returns a report String detailing the impact
	 * on the Trader to be displayed to the player.
	 * @param player the Trader to be impacted by an event
	 * @return a report String on the random event's impact
	 */
	public abstract String processImpact(Trader player);	
	
}
