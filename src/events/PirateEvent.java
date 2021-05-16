package events;

import ships.Ship;
import trader.Trader;

public class PirateEvent extends RandomEvent {
	
	private int pirateCannons;
	private int pirateGreed;
	
	//chanceLevel range?
	public PirateEvent(int chanceLevel, int pirateCannons, int pirateGreed) {
		this.setChanceLevel(chanceLevel);
		this.pirateCannons = pirateCannons;
		this.pirateGreed = pirateGreed;
	}
	
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
	
	public PirateScenarios getFightOutcome(Ship ship, boolean voluntary) {
		int shipCannons = ship.getCannons();
		int maxRoll = 2 * Integer.max(shipCannons, pirateCannons);
		int threshold = Integer.min(shipCannons, pirateCannons);
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
	
	public String getReport(PirateScenarios outcome) {
		String reportString = "Oh no! You were beset by pirates!\n";
		switch (outcome) {
		case FLED_AND_ESCAPED:
			return reportString + "Don't worry; your ship was just fast enough to outrun the pirates this time!\n";
		case FLED_AND_LOST:
			return reportString + "Disaster! Your crew tried to flee but the pirates caught up with you, \ndefeated you in battle, and made off with all your cargo!\n" +
			"They also took " + this.pirateGreed + " coins.\n" +
			"Consider upgrading your cannons to have a better chance in future.\n";
		case FLED_AND_WON:
			return reportString + "Wow, that was lucky! The pirates caught up with you but you defeated them in battle!\n"
					+ "Your cannons were enough to protect you this time...\n";
		case FOUGHT_AND_LOST:
			return reportString + "OOF! The pirates defeated you in battle and made off with all your cargo!!\n" +
			"They also took " + this.pirateGreed + " coins.\n" +
			"Consider upgrading your cannons to have a better chance next time.\n";
		case FOUGHT_AND_WON:
			return reportString + "But don't worry; you defeated them in battle.\n" +
			"Your cannons were enough to protect you this time...\n";
		}
		return "Missing a PirateScenarios case!";
	}
	
	public boolean decideLooted(PirateScenarios outcome) {
		return (outcome == PirateScenarios.FLED_AND_LOST | outcome == PirateScenarios.FOUGHT_AND_LOST);
	}
	
	public String processImpact(Trader player) {
		Ship ship = player.getOwndedShip();
		PirateScenarios outcome = getChaseOutcome(ship);
		String reportString = getReport(outcome);
		if (decideLooted(outcome)) {
			int playerMoney = player.getOwnedMoney();
			player.getOwndedShip().emptyCargos();
			if (pirateGreed >= playerMoney) {
				player.setOwnedMoney(0);
				reportString = "Oh no! You were beset by pirates!\n" +
						"They defeated you in battle, took everything of value, and made you and your crew walk the plank!\n" +
						"Better luck next time!";
			} else {
				player.subtractMoney(pirateGreed);
			}
		} return reportString;
	}
	
		
	public static void main(String[] args) {
	
	}
}
