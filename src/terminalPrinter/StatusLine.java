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
		String upperDivider = "---------------Trader Status------------------";
		String lowerDivider = "----------------------------------------------";
		String remainingDays = "Remaining Days: " + this.player.getRemainingDays();
		String money = "Money : $" + this.player.getOwnedMoney();
		String traderName = "Trader: " + this.player.getName();
		String island = "Island: " + this.player.getCurrentIsland().getName();
		String location = "Location: " + this.player.getCurrentLocation();
		String status = upperDivider + "\n" + "\n" +
						remainingDays + "   " + money + "\n" +
						traderName + "     " + island + "\n" + 
						"\n" +
						lowerDivider;
		System.out.println(status);
	}
}
