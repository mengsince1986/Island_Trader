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
		String log = this.tradingLocation + " " + 
					 this.tradingItemName + " " +
					 this.tradingQuantity + " " +
					 this.tradingPrice + " " +
					 this.soldOrBought;
		return log;
	}
	
	//readable repr
}
