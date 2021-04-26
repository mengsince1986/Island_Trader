package events;

import trader.Trader;
import ships.Ship;

public class WeatherEvent extends RandomEvent {
	
	private int maxDamage = 69;
	
	public WeatherEvent(int chanceLevel) {
		setChanceLevel(chanceLevel);
	}
	
	public int getImpact() {
		int damageDealt = getRandomInRange(1, maxDamage);
		return damageDealt;
	}

	public String getReport(int shipDamage) {
		return String.format(("Your ship struck a storm and took %d damage!" + 
				"Repair at the next port."), shipDamage);
	}
	
	public void processImpact(Trader player, int shipDamage) {
		Ship ship = player.getOwndedShip();
		ship.sufferDamage(shipDamage);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}