package trader;
import map.*;
import items.*;

public class TradingLog {
	
	private String tradingLocation;
	private String tradingItemName;
	private int tradingSize;
	private int tradingBenefit;	//Finn: what's the 
	private String sellOrBuy;	//logic here? Profit & sold vs bought?
	
	//private int totalValue;
	
	public TradingLog(Island tradingIsalnd, Item tradingItem, String sellOrBuy) {
		this.tradingLocation = tradingIsalnd.getName();
		this.tradingItemName = tradingItem.getName();
		this.tradingSize = tradingItem.getCargoSize();
		this.tradingBenefit = tradingItem.getTotalPrice(); 
	}
	
	public String toString() {
		String log = this.tradingLocation + " " + 
					 this.tradingItemName + " " +
					 this.tradingSize + " " +
					 this.tradingBenefit + " " +
					 this.sellOrBuy;
		return log;
	}
}
