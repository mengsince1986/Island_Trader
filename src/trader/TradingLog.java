package trader;
import map.*;
import items.*;

public class TradingLog {
	
	private String tradingLocation;
	private String tradingItemName;
	private int tradingSize;
	private int tradingPrice;
	private String soldOrBought;
	
	//private int totalValue;
	
	public TradingLog(Island tradingIsalnd, Item tradingItem, String soldOrBought) {
		this.tradingLocation = tradingIsalnd.getName();
		this.tradingItemName = tradingItem.getName();
		this.tradingSize = tradingItem.getCargoSize();
		this.tradingPrice = tradingItem.getPricePerUnit() * tradingItem.getCargoSize();
		this.soldOrBought = soldOrBought;
	}
	
	public String toString() {
		String log = this.tradingLocation + " " + 
					 this.tradingItemName + " " +
					 this.tradingSize + " " +
					 this.tradingPrice + " " +
					 this.soldOrBought;
		return log;
	}
}
