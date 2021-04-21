package events;

import trader.Trader;

public class RescueEvent extends RandomEvent {
	
	private int maxSailorGenerosity;

	public RescueEvent(int chanceLevel, int maxSailorGenerosity) {
		setChanceLevel(chanceLevel);
		this.maxSailorGenerosity =  maxSailorGenerosity;
	}
	
	//add random reward generator

	public String getReport(int reward) {
		return String.format(("Some good news: your crew rescued some sailors!\n" +
				"They rewarded you with %d coins!"), reward);
	}

	//Needs an increaseBalance method or something?
	public void processImpact(Trader player) {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}