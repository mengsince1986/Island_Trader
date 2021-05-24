package trader;
import map.*;
import items.*;

/**
 * A simple class to represent a single transaction (buy or sell) at
 * an island's {@link Store}.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class TradingLog {
	
	/**
	 * the name String of the {@link Island} where a transaction is made.
	 */
	private String tradingLocation;
	
	/**
	 * the name String of the {@link Item} that was bought or sold.
	 */
	private String tradingItemName;
	
	/**
	 * the quantity of the {@link Item} that was bought or sold. 
	 */
	private int tradingQuantity;
	
	/**
	 * the total value of the {@link Item} that was bought or sold 
	 * (equal to unit price * quantity).
	 */
	private int tradingValue;
	
	/**
	 * the String representing whether the {@link Item} was bought or sold.
	 * It will take the values "Bought" or "Sold".
	 */
	private String soldOrBought;
	
	/**
	 * calculates the total {@link tradingValue} and 
	 * sets the values of all attributes.
	 * @param tradingIsland the island where the trade took place
	 * @param tradingItem the item that was traded
	 * @param soldOrBought "Sold" or "Bought"
	 */
	public TradingLog(Island tradingIsland, Item tradingItem, String soldOrBought) {
		this.tradingLocation = tradingIsland.getName();
		this.tradingItemName = tradingItem.getName();
		this.tradingQuantity = tradingItem.getQuantity();
		this.tradingValue = tradingItem.getPricePerUnit() * tradingItem.getQuantity();
		this.soldOrBought = soldOrBought;
	}
	
	/**
	 * @return a readable String representation of the log.
	 */
	public String toString() {
		String log = this.soldOrBought + " " +
					 this.tradingQuantity + " " +
					 this.tradingItemName + " at " +
					 this.tradingLocation + " for " +
					 this.tradingValue + " coins\n";
		return log;
	}
}
