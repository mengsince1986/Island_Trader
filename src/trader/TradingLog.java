package trader;
import map.*;
import items.*;

public class TradingLog {
	
	private String tradingLocation;
	private String tradingItemName;
	private int tradingQuantity;
	private int tradingPrice;
	private String soldOrBought;
	
	//private int totalValue;
	
	public TradingLog(Island tradingIsalnd, Item tradingItem, String soldOrBought) {
		this.tradingLocation = tradingIsalnd.getName();
		this.tradingItemName = tradingItem.getName();
		this.tradingQuantity = tradingItem.getQuantity();
		this.tradingPrice = tradingItem.getPricePerUnit() * tradingItem.getQuantity();
		this.soldOrBought = soldOrBought;
	}
	
	public String toString() {
		String log = this.soldOrBought + " " +
					 this.tradingQuantity + " " +
					 this.tradingItemName + " at " +
					 this.tradingLocation + " for " +
					 this.tradingPrice + " coins\n";
		return log;
	}
	
	//readable repr
}
