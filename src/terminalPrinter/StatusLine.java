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
	 * This method prints out a formated status line to the terminal.
	 */
	public void printStatusLine() {
		
		String upperDivider = "---------------Trader Status------------------";
		String lowerDivider = "----------------------------------------------";
		String remainingDays = "Remaining Days: " + this.player.getRemainingDays();
		String money = "Money : " + this.player.getOwnedMoney() + " coins";
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
	 * This method prints out a formated game over report to the terminal.
	 */
	public void printGameOverReport() {
		
		String upperDivider = "----------------Game Over!--------------------";
		String lowerDivider = "----------------------------------------------";
		int playerProfit = this.player.getOwnedMoney() - this.player.getStartingMoney();
		int daysPlayed = this.player.getSelectedDays() - this.player.getRemainingDays();
		// MZ: Fixed bug. Update selected days in 2nd constructor of Trader class
		// or trader.selectedDays is 0 all the time
		int profitPerDay = Math.floorDiv(playerProfit, daysPlayed);
		
		String congratulation = "Trader name: " + this.player.getName() +
				"\nSelected game duration: " + 
				this.player.getSelectedDays() + " days" +
				"\nActual duration: " + daysPlayed + " days" +
				"\nYou made a profit of " + playerProfit + " coins\n";
		
		if (profitPerDay <= 0) {
			
			congratulation += "Your score: 0/3 stars. Better luck next time!";
			
		} else if (profitPerDay <= 500) {
			
			congratulation += "Score: 1/3 stars. Good effort! At least you managed to make something!";
			
		} else if (profitPerDay <= 1000) {
			
			congratulation += "Score: 2/3 stars! Either you got lucky or are getting pretty good!";
			
		} else {
			
			congratulation += "Score: 3/3 stars! Outstanding! You must be a god gamer!\n" +
					"Have you considered a career in arbitrage?";
			
		}
		
		String gameOverReport = upperDivider + "\n" + "\n" +
				congratulation + "\n" + 
				"\n" +
				lowerDivider;
		
		System.out.println(gameOverReport);
		
	}
}
