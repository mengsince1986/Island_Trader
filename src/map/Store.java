package map;

import items.Item;
import ships.Ship;
import java.util.ArrayList;

/**
 * Represents the game's stores, where trading takes place——one per {@link Island}.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */
public class Store {

	/**
	 * an ArrayList to contain the {@link Item} objects on sale.
	 */
	private ArrayList<Item> toSell = new ArrayList<Item>();
	
	/**
	 * an ArrayList to contain the Item objects the Store wants to buy.
	 */
	private ArrayList<Item> toBuy = new ArrayList<Item>();

	/**
	 * instantiates a new Store and adds the given {@link Item}s to be sold and
	 * Items to be bought to the {@link toSell} list and {@link toBuy} lists
	 * respectively.
	 * @param toSell an array of the Item objects to be sold by the Store
	 * @param toBuy an array of the Item objects to be bought by the Store
	 */
	public Store(Item[] toSell, Item[] toBuy) {
		for (Item item : toSell) {
			this.toSell.add(item);
		}
		for (Item item : toBuy) {
			this.toBuy.add(item);
		}
	}
	
	/**
	 * @return the ArrayList of Item objects for sale in the Store
	 */
	public ArrayList<Item> getToSell() {
		return this.toSell;
	}
	
	/**
	 * @return the ArrayList of Item objects the Store is willing to buy
	 */
	public ArrayList<Item> getToBuy() {
		return this.toBuy;
	}
	
	/**
	 * gets the {@link Item} with the given name String from the desired Store
	 * list ({@link toSell} or {@link toBuy}).
	 * @param itemName the name String of the desired Item
	 * @param whichList "toSell" or "toBuy", to designate the desired Store list
	 * @return the Item whose name String matches that given, else null if no Item is matched
	 */
	public Item getItem(String itemName, String whichList) {
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
	
	/**
	 * gets the unit price of the {@link Item} with the given name String from the 
	 * desired store list ({@link toSell} or {@link toBuy}).
	 * @param itemName the name String of the desired Item
	 * @param whichList "toSell" or "toBuy", to designate the desired Store list
	 * @return the price per unit of the Item whose name String matches that given,
	 * else -1 if no Item is matched
	 */
	public int checkItemPrice(String itemName, String whichList) {
		Item item = getItem(itemName, whichList);
		if (item != null) {
			return item.getPricePerUnit();
		}
		return -1;
	}
	
	/**
	 * takes the name String and quantity of an {@link Item} that has been sold by the Store,
	 * updates the Store's {@link toSell} list, and returns a new Item to be stored
	 * in the player's cargo hold which reflects the quantity bought and unit price paid.
	 * @param itemName the name String of the sold Item
	 * @param quantity the quantity of the Item that was sold
	 * @return the sold Item to be stored in the player's cargo hold
	 */
	public Item soldItem(String itemName, int quantity) {
		int itemPrice = checkItemPrice(itemName, "toSell");
		Item itemToSell = getItem(itemName, "toSell");
		if (quantity == itemToSell.getQuantity()) {
			this.toSell.remove(itemToSell);
		} else {
			getItem(itemName, "toSell").subtractQuantity(quantity); //update toSell list
		}
		Item itemSold = new Item(itemName, quantity, itemPrice);
		return itemSold;
	}
	
	/**
	 * takes the name String and quantity of an {@link Item} that has been bought by the Store,
	 * removes the given quantity from the corresponding Item in the Store's {@link toBuy} list, 
	 * adds the given quantity to the corresponding Item in the Store's {@link toSell} list,
	 * and returns a new item to be added to a TradingLog that reflects the quantity sold
	 * and the unit price received.
	 * @param itemName the name String of the bought Item
	 * @param quantity the quantity of the Item that was bought
	 * @return the bought Item to be stored in the player's trading logs
	 */
	public Item boughtItem(String itemName, int quantity) {
		int itemPrice = checkItemPrice(itemName, "toBuy");
		Item itemToBuy = getItem(itemName, "toBuy");
		if (quantity == itemToBuy.getQuantity()) {
			this.toBuy.remove(itemToBuy);
		} else {
			itemToBuy.subtractQuantity(quantity); //update toBuy list
		}
		if (getItem(itemName, "toSell") instanceof Item) {
			getItem(itemName, "toSell").addQuantity(quantity); //update toSell list
		}
		Item itemBought = new Item(itemName, quantity, itemPrice);
		return itemBought;
	}
	
	/**
	 * @return a String representation of the Item objects for sale at the Store
	 */
	public String forSale() {
		String itemString = "Items for sale:\n";
		if (toSell.size() > 0) {
			for (int i = 0; i < toSell.size(); i++) {
				itemString += String.format("\nItem %s: %s", i+1, toSell.get(i));
			}
			return itemString;
		} else {
			itemString += "Nothing for sale at this store!\n";
			return itemString;
		}
	}

	/**
	 * @return a String representation of the Item objects the Store is willing to buy
	 */
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
	
	/**
	 * takes the player's {@link Ship}, ascertains, based on the Item objects in its cargo hold,
	 * which of these (if any) are sellable at the current Store, and returns an ArrayList
	 * of type Item that reflects the quantity able to be sold and the unit price receivable
	 * for each Item.
	 * @param ship the player's Ship
	 * @return an ArrayList of the Item objects able to be sold to the current Store
	 */
	public ArrayList<Item> getSellablePlayerItems(Ship ship) {
		ArrayList<Item> cargoList = ship.getCargos();
		ArrayList<Item> sellablePlayerItems = new ArrayList<>();
		for (Item cargoItem : cargoList) {
			for (Item itemToBuy : this.toBuy) {
				String cargoItemName = cargoItem.getName();
				if (cargoItemName == itemToBuy.getName()) {
					int quantity = Integer.min(cargoItem.getQuantity(), itemToBuy.getQuantity());
					int price = itemToBuy.getPricePerUnit();
					sellablePlayerItems.add(
							new Item(cargoItemName, quantity, price));
				}
			}
		} return sellablePlayerItems;
	}

	/**
	 * takes the player's {@link Ship}, ascertains, based on the Item objects in its cargo hold,
	 * which of these (if any) are sellable at the current Store, and returns a String representation
	 * that reflects the quantity able to be sold and the unit price receivable
	 * for each Item.
	 * @param ship the player's Ship
	 * @return a String representation of the Item objects able to be sold to the current Store
	 */
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
}