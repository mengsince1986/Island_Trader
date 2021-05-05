package events;

import java.util.Random;
import trader.Trader;

public abstract class RandomEvent {
	
	private Random randomGenerator = new Random();
	private int chanceLevel;
	
	public int getchanceLevel() {
		return chanceLevel;
	}

	public void setChanceLevel(int chanceLevel) {
		this.chanceLevel = chanceLevel;
	}

	public int getRandomInRange(int minRoll, int maxRoll) {
		return randomGenerator.nextInt((maxRoll - minRoll) + 1) + minRoll;
	}
	
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
	
	public abstract String processImpact(Trader player);	
	
}
