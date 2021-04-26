package events;

import trader.Trader;

public class RescueEvent extends RandomEvent {
	
	private int maxSailorGenerosity;

	public RescueEvent(int chanceLevel, int maxSailorGenerosity) {
		setChanceLevel(chanceLevel);
		this.maxSailorGenerosity =  maxSailorGenerosity;
	}
	
	public int getReward() {
		int reward = getRandomInRange(1, maxSailorGenerosity);
		return reward;
	}

	public String getReport(int reward) {
		return String.format(("Some good news: your crew rescued some sailors!\n" +
				"They rewarded you with %d coins!"), reward);
	}

	public void processImpact(Trader player, int reward) {
		player.addMoney(reward);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}