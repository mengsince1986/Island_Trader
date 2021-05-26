package items;

import map.Store;
import ships.Ship;
import trader.Trader;

/**
 * A basic class to represent the items that are traded between a {@link Trader}
 * and the various {@link Store}s in the game.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */
public class Item {
	
	/**
	 * the name of the Item.
	 */
	private String name;
	
	/**
	 * represents the amount of the Item that is in a {@link Ship}'s cargo hold,
	 * willing to be sold or bought by a Store, or was sold by a Trader.
	 */
	private int quantity;
	
	/**
	 * represents the price paid or received for one unit of the Item.
	 */
	private int pricePerUnit;
	
	/**
	 * constructs an Item object with the given {@link name} String, {@link quantity},
	 * and {@link pricePerUnit}.
	 * @param name the name String of the new Item
	 * @param quantity the amount of the Item that was/is to be sold/bought
	 * @param pricePerUnit the price for one unit of the Item
	 */
	public Item (String name, int quantity, int pricePerUnit) {
		this.name = name;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}
	
	/**
	 * @return the Item's {@link name} attribute
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the Item's {@link quantity} attribute
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @return the Item's {@link pricePerUnit} attribute
	 */
	public int getPricePerUnit() {
		return pricePerUnit;
	}
	
	/**
	 * increases the value of the Item's {@link quantity} attribute by the given amount
	 * @param amount the amount to be added
	 */
	public void addQuantity(int amount) {
		this.quantity += amount;
	}
	
	/**
	 * decreases the value of the Item's {@link quantity} attribute by the given amount
	 * @param amount the amount to be subtracted
	 */
	public void subtractQuantity(int amount) {
		this.quantity -= amount;
	}
	
	/**
	 * @return a readable generic String representation of an Item object
	 */
	public String toString() {
		return String.format(
				"%s\nQuantity: %s\nPrice per unit: %s\n", 
				this.name, 
				this.quantity, 
				this.pricePerUnit);
	}
	
	/**
	 * @return a readable String representation specifically of an Item currently in
	 * the player's {@link Ship}'s cargo hold
	 */
	public String getCargoItemString() {
		return String.format(
				"%s\nQuantity: %s\nPrice paid per unit: %s\n", 
				this.name, 
				this.quantity,
				this.pricePerUnit);
	}
}
