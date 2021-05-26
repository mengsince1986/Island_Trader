package terminalPrinter;

import trader.*;

/**
 * The StatusLine class is used to initialize an object to print a formated
 * status line and game over report in the command line interface.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */
public class StatusLine {
	
	/**
	 * Attribute player stores a {@link Trader} object which is used to get the
	 * information of the current player.
	 */
	private Trader player;

	/**
	 * This constructor sets the value of the player attribute.
	 * @param player a {@link Trader} object
	 */
	public StatusLine(Trader player) {
		
		this.player = player;
	
	}
	
	/**
	 * prints a formatted status line String containing basic player status information
	 * to the terminal
	 */
	public void printStatusLine() {
		
		String upperDivider = "---------------Trader Status------------------";
		String lowerDivider = "----------------------------------------------";
		String remainingDays = "Remaining Days: " + this.player.getRemainingDays();
		String money = "Money: " + this.player.getOwnedMoney() + " coins";
		String traderName = "Trader: " + this.player.getName();
		String island = "Island: " + this.player.getCurrentIsland().getName();
		
		String status = upperDivider + "\n" + "\n" +
						remainingDays + "   " + money + "\n" +
						traderName + "     " + island + "\n" + 
						"\n" +
						lowerDivider;
		
		System.out.println(status);
	}
	
	/**
	 * takes a String detailing the reason why the game has ended and returns a report String
	 * that includes the player's name, selected game duration, the actual duration, profit made, a
	 * congratulatory message, and a score out of 3 for the amount of profit they were able to make
	 * against the number of days played.
	 * @param reason a String detailing the reason why the game has ended
	 * @return a formatted game over report String, including a congratulatory message and a score
	 */
	public String getGameOverReport(String reason) {

		String upperDivider = "-----------------Game Over--------------------";
		String lowerDivider = "----------------------------------------------";
		int playerProfit = this.player.getOwnedMoney() - this.player.getStartingMoney();
		int daysPlayed = Integer.max(1, this.player.getSelectedDays() - this.player.getRemainingDays());
		int profitPerDay = Math.floorDiv(playerProfit, daysPlayed);

		String report = reason + "\n" + 
				"Trader name: " + this.player.getName() +
				"\nSelected game duration: " + 
				this.player.getSelectedDays() + " days" +
				"\nActual duration: " + daysPlayed + " day(s)" +
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
		
		return gameOverReport;
		
	}
}
