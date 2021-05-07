package terminalPrinter;
import ships.*;
import trader.*;

public class StatusLine {
	
	private Trader player;
	private Ship ship;

	public StatusLine(Trader player, Ship ship) {
		this.player = player;
		this.ship = ship;
	}
	
	public void printStatusLine() {
		String divider = "---------------------------------------------";
		String remainingDays = "Remaining Days: " + this.player.getRemainingDays();
		String money = "Money : $" + this.player.getOwnedMoney();
		String traderName = "Trader: " + this.player.getName();
		String island = "Island: " + this.player.getCurrentIsland().getName();
		String location = "Location: " + this.player.getCurrentLocation();
		String status = divider + "\n" +
						remainingDays + "   " + money + "\n" +
						traderName + "     " + island + "\n" + 
						//location + "\n" +
						divider;
		System.out.println(status);
	}
}
