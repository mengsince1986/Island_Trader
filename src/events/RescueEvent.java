package events;

import trader.Trader;

/**
 * A class to implement the "rescued sailors" random event, which causes
 * the player to rescue some sailors and receive a random monetary reward.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 *
 */
public class RescueEvent extends RandomEvent {
	
	/**
	 * determines the maximum amount of money this event can yield for the player.
	 */
	private int maxSailorGenerosity;

	/**
	 * initialises a new RescueEvent and sets the {@link chanceLevel} and
	 * {@link maxSailorGenerosity} to the values given.
	 * @param chanceLevel the number representing the likelihood of the event triggering
	 * on any one occasion
	 * @param maxSailorGenerosity the maximum amount a player can receive from
	 * this event
	 */
	public RescueEvent(int chanceLevel, int maxSailorGenerosity) {
		setChanceLevel(chanceLevel);
		this.maxSailorGenerosity =  Integer.max(1, maxSailorGenerosity);
	}
	
	/**
	 * generates and returns the pseudorandom amount of money to be rewarded to the 
	 * player's {@link Trader} object as a result of the event's being triggered.
	 * @return the amount the player is to receive as result of the event
	 */
	public int getReward() {
		int reward = getRandomInRange(1, maxSailorGenerosity);
		return reward;
	}

	/**
	 * takes the amount of money rewarded and returns a readable String
	 * detailing the reward.
	 * @param reward the amount of money rewarded
	 * @return a report String detailing the reward
	 */
	public String getReport(int reward) {
		return String.format(("Some good news: your crew rescued some sailors!\n" +
				"They rewarded you with %d coins!\n"), reward);
	}

	/**
	 * processes the impact of the triggered RescueEvent on the given {@link Trader}
	 * and returns a report detailing this impact to be displayed to the player.
	 * @return a report String detailing the impact of the event and to be displayed
	 * to the player 
	 */
	public String processImpact(Trader player) {
		int reward = getReward();
		player.addMoney(reward);
		String reportString = getReport(reward);
		return reportString;
	}
}