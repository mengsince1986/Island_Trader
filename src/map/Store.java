package map;

import items.Item;
import java.util.ArrayList;

public class Store {

	private ArrayList<Item> toSell = new ArrayList<Item>();
	private ArrayList<Item> toBuy = new ArrayList<Item>();

	public Store(Item[] toSell, Item[] toBuy) {
		for (Item item : toSell) {
			this.toSell.add(item);
		}
		for (Item item : toBuy) {
			this.toBuy.add(item);
		}
	}
	
	// by MZ
	public ArrayList<Item> getToSell() {
		return this.toSell;
	}
	
	// by MZ
	public ArrayList<Item> getToBuy() {
		return this.toBuy;
	}
	
	// by MZ
	public Item getItem(String itemName, String whichList) {
		// whichList: toSell | toBuy 
		ArrayList<Item> tradingList = getToSell();
		if (whichList == "toBuy") {
			tradingList = getToBuy();
		}
		
		for (Item item : tradingList) {
			if (itemName == item.getName()) {
				return item;
			}
		}
		return null;
	}
	
	// by MZ
	public int checkItemPrice(String itemName, String whichList) {
		// whichList: toSell | toBuy 
		// return the price per unit of the available item
		// if not available return -1
		Item item = getItem(itemName, whichList);
		if (item != null) {
			return item.getPricePerUnit();
		}
		return -1;
	}
	
	
	// by MZ
	public Item sell(String itemName, int itemSize) {
		int itemPrice = checkItemPrice(itemName, "toSell");
		getItem(itemName, "toSell").subtractCargoSize(itemSize); //update toSell list
		Item goodsToSell = new Item(itemName, itemSize, itemPrice);
		return goodsToSell;
	}
	
	// by MZ
	public Item buy(String itemName, int itemSize) {
		int itemPrice = checkItemPrice(itemName, "toBuy");
		getItem(itemName, "toBuy").addCargoSize(itemSize); //update toBuy list
		Item goodsToBuy = new Item(itemName, itemSize, itemPrice);
		return goodsToBuy;
	}
	
	
	public String forSale() {
		String itemString = "Items for sale:\n";
		if (toSell.size() > 0) {
			for (int i = 0; i < toSell.size(); i++) {
				itemString += String.format("\nItem %s: %s", i, toSell.get(i));
			}
			return itemString;
		} else {
			itemString += "Nothing for sale at this store!\n";
			return itemString;
		}
	}

	public String forPurchase() {
		String itemString = "Looking to buy:\n";
		if (toBuy.size() > 0) {
			for (int i = 0; i < toBuy.size(); i++) {
				itemString += String.format("\nItem %s: %s", i, toBuy.get(i));
			}
			return itemString;
		} else {
			itemString += "This store is not looking to buy anything!\n";
			return itemString;
		}
	}

	// purchase and sell methods might need to be in Trader?

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Item fur = new Item("fur", 3, 2);
		Item wine = new Item("wine", 2, 4);
		Item[] toSell = { fur, wine };
		//Item[] toBuy = new Item[0];
		Item[] toBuy = {fur, wine};
		//Store store = new Store(toSell, new Item[0]);
		Store store = new Store(toSell, toBuy);
		System.out.println(store.forSale());
		System.out.println(store.forPurchase());
	}

}