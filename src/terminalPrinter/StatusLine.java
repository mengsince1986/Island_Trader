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
		String money = "Money : " + this.player.getOwnedMoney() + " coins";
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
	
	public void printGameOverReport(String reason) {
		String upperDivider = "----------------Game Over!--------------------";
		String lowerDivider = "----------------------------------------------";
		int playerProfit = this.player.getOwnedMoney() - this.player.getStartingMoney();
		int daysPlayed = this.player.getSelectedDays() - this.player.getRemainingDays();
		int profitPerDay = Math.floorDiv(playerProfit, daysPlayed);
		String report = reason + "\n" + 
				"Trader name: " + this.player.getName() +
				"\nSelected game duration: " + 
				this.player.getSelectedDays() + " days" +
				"\nActual duration: " + daysPlayed + " days" +
				"\nYou made a profit of " + playerProfit + " coins\n";
		if (profitPerDay <= 0) {
			report += "Your score: 0/3 stars. Better luck next time!";
		} else if (profitPerDay <= 500) {
			report += "Score: 1/3 stars. Good effort! At least you managed to make something!";
		} else if (profitPerDay <= 1000) {
			report += "Score: 2/3 stars! Either you got lucky or are getting pretty good!";
		} else {
			report += "Score: 3/3 stars! Outstanding! You must be a god gamer!\n" +
					"Have you considered a career in arbitrage?";
		}
		String gameOverReport = upperDivider + "\n" + "\n" +
				report + "\n" + 
				"\n" +
				lowerDivider;
		System.out.println(gameOverReport);
	}
}
