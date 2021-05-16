package items;

public class Item {
	
	private String name;
	private int quantity;
	private int pricePerUnit;
	
	public Item (String name, int quantity, int pricePerUnit) {
		this.name = name;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	// by MZ
	public void addQuantity(int size) {
		this.quantity += size;
	}
	
	// by MZ
	public void subtractQuantity(int size) {
		this.quantity -= size;
	}
	
	public int getPricePerUnit() {
		return pricePerUnit;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		/*
		 * return "Item: " + name + "\n" + "Quantity: " + quantity + "\n" +
		 * "price per unit: " + pricePerUnit;
		 */
		return String.format(
				"%s\nQuantity: %s\nPrice per unit: %s\n", 
				name, 
				quantity, 
				pricePerUnit);
	}
	
	public String getCargoItemString() {
		return String.format(
				"%s\nQuantity: %s\n", 
				name, 
				quantity);
	}
	

	public static void main(String[] args) {
		Item fur = new Item("fur", 3, 2);
		System.out.print(fur);

	}

}
