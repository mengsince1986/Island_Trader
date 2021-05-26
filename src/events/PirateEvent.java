package events;

import ships.Ship;
import trader.Trader;

/**
 * A class to implement the "pirates" random event, which causes
 * the player to fall victim to an attempted raid by pirates, losing their
 * items and possibly all their money if their {@link Trader}'s {@link Ship} is
 * defeated in battle with the pirates. If the pirates aren't satisfied by the
 * player's money, the player is forced to walk the plank and the game is over.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 *
 */
public class PirateEvent extends RandomEvent {
	
	/**
	 * the number of cannons equipped on the pirate's ship (for determining the
	 * outcome of a battle with the player's Ship).
	 */
	private int pirateCannons;
	
	/**
	 * the amount of money the pirates will attempt to steal from the player
	 * in addition to the value of all their items.
	 */
	private int pirateGreed;
	
	/**
	 * initialises a new PirateEvent and sets the {@link chanceLevel},
	 * {@link pirateCannons}, and {@link pirateGreed} to the given values.
	 * @param chanceLevel the number representing the likelihood of the event triggering
	 * on any one occasion
	 * @param pirateCannons the number of cannons for the pirates of this event
	 * @param pirateGreed the amount of money the pirates will attempt to steal from the
	 * player
	 */
	public PirateEvent(int chanceLevel, int pirateCannons, int pirateGreed) {
		this.setChanceLevel(chanceLevel);
		this.pirateCannons = pirateCannons;
		this.pirateGreed = pirateGreed;
	}
	
	/**
	 * takes a player's {@link Ship} object and determines the outcome of the chase
	 * between the event's pirates and the player's Ship. The faster the Ship, the more
	 * likely it is to escape the pirates. If the Ship fails to outrun the pirates, 
	 * then the outcome will be determined by combat. Returns the outcome as a constant
	 * of type {@link PirateScenarios}.
	 * @param ship the player's Ship
	 * @return the outcome of the event as a {@link PirateScenarios} constant
	 */
	public PirateScenarios getChaseOutcome(Ship ship) {
		String shipSpeed = ship.getSpeed();
		switch (shipSpeed) {
		case "fast": 
			int fastRoll = getRandomInRange(0, 2);
			if (fastRoll > 1) {
				return PirateScenarios.FLED_AND_ESCAPED;
			} else {
				return getFightOutcome(ship, false);
			}
		case "normal": 
			int normalRoll = getRandomInRange(0, 3);
			if (normalRoll > 2) {
				return PirateScenarios.FLED_AND_ESCAPED;
			} else {
				return getFightOutcome(ship, false);
			}
		case "slow":
			int slowRoll = getRandomInRange(0, 4);
			if (slowRoll > 3) {
				return PirateScenarios.FLED_AND_ESCAPED;
			} else {
				return getFightOutcome(ship, false);
			}
		default:
			return getFightOutcome(ship, false);
		}
	}
	
	/**
	 * takes a player's {@link Ship} object and a boolean value to represent whether
	 * the fight is voluntary (will currently always be set to false). Determines the 
	 * outcome of the fight between the event's pirates and the player's Ship. 
	 * The more cannons equipped on the Ship, the greater the chances of defeating the
	 * pirates. The greater the difference between the number of cannnons on the player's
	 * Ship and the pirates' {@link pirateCannons} value, the more likely it is that
	 * the party with the highest number will win. Returns the outcome as a constant
	 * of type {@link PirateScenarios}.
	 * @param ship the player's Ship
	 * @param voluntary true if the player decided to fight, false otherwise
	 * @return the outcome of the event as a {@link PirateScenarios} constant
	 */
	public PirateScenarios getFightOutcome(Ship ship, boolean voluntary) {
		int shipCannons = ship.getCannons();
		if (shipCannons > pirateCannons) {
			int maxRoll = 2 * shipCannons;
			int threshold = pirateCannons;
			int randomRoll = getRandomInRange(1, maxRoll);
			if (randomRoll < threshold) {
				if (voluntary) {
					return PirateScenarios.FOUGHT_AND_LOST;
				} else {
					return PirateScenarios.FLED_AND_LOST;
				}
			} else if (voluntary) {
				return PirateScenarios.FOUGHT_AND_WON;
			} else {
				return PirateScenarios.FLED_AND_WON;
			}
		} else {
			int maxRoll = 2 * pirateCannons;
			int threshold = shipCannons;
			int randomRoll = getRandomInRange(1, maxRoll);
			if (randomRoll > threshold) {
				if (voluntary) {
					return PirateScenarios.FOUGHT_AND_LOST;
				} else {
					return PirateScenarios.FLED_AND_LOST;
				}
			} else if (voluntary) {
				return PirateScenarios.FOUGHT_AND_WON;
			} else {
				return PirateScenarios.FLED_AND_WON;
			}
		}
	}
	
	/**
	 * takes an event outcome of type {@link PirateScenarios} and returns a corresponding report
	 * String detailing the outcome of the event and the impact (if any) on the player.
	 * @param outcome the outcome of the event as a {@link PirateScenarios} constant
	 * @return a report String detailing the outcome and impacts (if any) of the event
	 */
	public String getReport(PirateScenarios outcome) {
		String reportString = "Oh no! You were beset by pirates!\n";
		switch (outcome) {
		case FLED_AND_ESCAPED:
			return reportString + "Don't worry; your ship was just fast enough to outrun the pirates this time!\n";
		case FLED_AND_LOST:
			return reportString + "Disaster! Your crew tried to flee but the pirates caught up with you, \ndefeated you in battle, and made off with all your cargo!\n" +
			"They also took " + this.pirateGreed + " coins.\n" +
			"Your cannons weren't enough to protect you this time.\n";
		case FLED_AND_WON:
			return reportString + "Wow, that was lucky! The pirates caught up with you but you defeated them in battle!\n"
					+ "Your cannons were enough to protect you this time...\n";
		case FOUGHT_AND_LOST:
			return reportString + "OOF! The pirates defeated you in battle and made off with all your cargo!!\n" +
			"They also took " + this.pirateGreed + " coins.\n" +
			"Your cannons weren't enough to protect you this time.\n";
		case FOUGHT_AND_WON:
			return reportString + "But don't worry; you defeated them in battle.\n" +
			"Your cannons were enough to protect you this time...\n";
		}
		return "Missing a PirateScenarios case!";
	}
	
	/**
	 * takes an event outcome of type {@link PirateScenarios} and returns true if the player lost the battle
	 * this time and will therefore be looted, and false if not.
	 * @param outcome the outcome of the event as a {@link PirateScenarios} constant
	 * @return a boolean value indicating whether the player is to be looted as a result of the event
	 */
	public boolean decideLooted(PirateScenarios outcome) {
		return (outcome == PirateScenarios.FLED_AND_LOST || outcome == PirateScenarios.FOUGHT_AND_LOST);
	}
	
	/**
	 * processes the impact of the triggered PirateEvent on the given {@link Trader}
	 * and returns a report detailing this impact to be displayed to the player.
	 * @return a report String detailing the impact of the event and to be displayed
	 * to the player 
	 */
	public String processImpact(Trader player) {
		Ship ship = player.getOwndedShip();
		PirateScenarios outcome = getChaseOutcome(ship);
		String reportString = getReport(outcome);
		if (decideLooted(outcome)) {
			int playerMoney = player.getOwnedMoney();
			player.getOwndedShip().emptyCargos();
			if (pirateGreed >= playerMoney) {
				player.setOwnedMoney(0);
				player.setKilledByPirates(true);
				reportString = "Oh no! You were beset by pirates!\n" +
						"They defeated you in battle, took everything of value, and made you and your crew walk the plank!\n" +
						"Better luck next time!";
			} else {
				player.subtractMoney(pirateGreed);
			}
		} return reportString;
	}
}
