package events;

import trader.Trader;
import ships.Ship;

public class WeatherEvent extends RandomEvent {
	
	private int maxDamage = 69;
	
	public WeatherEvent(int chanceLevel) {
		setChanceLevel(chanceLevel);
	}
	
	public int getDamage() {
		int damageDealt = getRandomInRange(1, maxDamage);
		return damageDealt;
	}

	public String getReport(int shipDamage) {
		return String.format(("Your ship struck a storm and took %d damage! " + 
				"You'll have to repair it at the next port.\n"), shipDamage);
	}
	
	public String processImpact(Trader player) {
		int shipDamage = getDamage();
		Ship ship = player.getOwndedShip();
		ship.sufferDamage(shipDamage);
		String reportString = getReport(shipDamage);
		return reportString;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}