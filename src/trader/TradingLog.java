package trader;
import map.*;
import items.*;

public class TradingLog {
	
	private String tradingLocation;
	private String tradingItemName;
	private int tradingSize;
	private int tradingBenefit;	//Finn: what's the 
	private String sellOrBuy;	//logic here? Profit & sold vs bought?
	private int totalValue;
	private int quantity;
	
	public TradingLog(Island tradingIsalnd, Item tradingItem, String sellOrBuy, int quantity) {
		this.tradingLocation = tradingIsalnd.getName();
		this.tradingItemName = tradingItem.getName();
		this.tradingSize = tradingItem.getCargoSize();
		this.totalValue = tradingItem.getPricePerUnit() * quantity;
		this.quantity = quantity;
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
