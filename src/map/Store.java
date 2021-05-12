package map;

import items.Item;
import ships.Ship;
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
	public Item itemToSell(String itemName, int quantity) {
		int itemPrice = checkItemPrice(itemName, "toSell");
		getItem(itemName, "toSell").subtractQuantity(quantity); //update toSell list
		Item goodsToSell = new Item(itemName, quantity, itemPrice);
		return goodsToSell;
	}
	
	// by MZ
	public void buyItem(String itemName, int quantity) {
		getItem(itemName, "toBuy").subtractQuantity(quantity); //update toBuy list
		if (getItem(itemName, "toSell") instanceof Item) {
			getItem(itemName, "toSell").addQuantity(quantity); //update toSell lest
		}
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
	
	public ArrayList<Item> getSellablePlayerItems(Ship ship) {
		ArrayList<Item> cargoList = ship.getCargos();
		ArrayList<Item> sellablePlayerItems = new ArrayList<>();
		for (Item cargoItem : cargoList) {
			for (Item offeredItem : this.toBuy) {
				String cargoItemName = cargoItem.getName();
				if (cargoItemName == offeredItem.getName()) {
					int quantity = cargoItem.getQuantity();
					int price = offeredItem.getPricePerUnit();
					sellablePlayerItems.add(
							new Item(cargoItemName, quantity, price));
				}
			}
		} return sellablePlayerItems;
	}

	public String getSellablePlayerItemsString(Ship ship) {
		ArrayList<Item> sellablePlayerItems = getSellablePlayerItems(ship);
		String itemString = "Items you can sell:\n";
		if (sellablePlayerItems.size() > 0) {
			for (int i = 0; i < sellablePlayerItems.size(); i++) {
				itemString += String.format("\nItem %s: %s", i, sellablePlayerItems.get(i));
			}
			return itemString;
		} else {
			itemString += "\nIt looks like you don't own anything this store is looking to buy!\n";
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