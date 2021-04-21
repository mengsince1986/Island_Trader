package events;

import ships.Ship;
import trader.Trader;

public class PirateEvent extends RandomEvent {
	
	private int pirateCannons;
	private int pirateGreed;
	
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
			if (fastRoll > 0) {
				return PirateScenarios.FLED_AND_ESCAPED;
			} else {
				return getFightOutcome(ship, false);
			}
		case "normal": 
			int normalRoll = getRandomInRange(0, 1);
			if (normalRoll == 1) {
				return PirateScenarios.FLED_AND_ESCAPED;
			} else {
				return getFightOutcome(ship, false);
			}
		case "slow":
			int slowRoll = getRandomInRange(0, 3);
			if (slowRoll < 1) {
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
		switch (outcome) {
		case FLED_AND_ESCAPED:
			return "Phew, you outran the pirates this time!";
		case FLED_AND_LOST:
			return "Disaster! The pirates caught up with you and defeated you in battle!\n" +
			"Consider upgrading your cannons to have a better chance next time.\n";
		case FLED_AND_WON:
			return "Wow, that was lucky! The pirates caught up with you but you defeated them in battle!\n" +
			"Your cannons were enough to protect you this time...\n";
		case FOUGHT_AND_LOST:
			return "OOF! The pirates got the better of you!\n" +
			"Consider upgrading your cannons to have a better chance next time.\n";
		case FOUGHT_AND_WON:
			return "Hooray! You defeated the pirates in battle.\n" +
			"Your cannons were enough to protect you this time...\n";
		}
		return "Missing a PirateScenarios case!";
	}
	
	
	// These require some agreement about implementation
	public void processImpact(Trader player, PirateScenarios outcome) {
		if ((outcome == PirateScenarios.FLED_AND_LOST) | (outcome == PirateScenarios.FOUGHT_AND_LOST)) {
			if (pirateGreed > player.getOwnedMoney()) {
				return false;
			}
		} else {
			return true;
		}
	}
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
