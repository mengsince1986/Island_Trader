package items;

public class Item {
	
	private String name;
	private int cargoSize;
	private int pricePerUnit;
	
	public Item (String name, int cargoSize, int pricePerUnit) {
		this.name = name;
		this.cargoSize = cargoSize;
		this.pricePerUnit = pricePerUnit;
	}
	
	public int getCargoSize() {
		return cargoSize;
	}
	
	public int getpricePerUnit() {
		return pricePerUnit;
	}
	
	@Override
	public String toString() {
		/*
		 * return "Item: " + name + "\n" + "Cargo space required: " + cargoSize + "\n" +
		 * "price per unit: " + pricePerUnit;
		 */
		return String.format(
				"%s\nCargo space required: %s\nPrice per unit: %s\n", 
				name, 
				cargoSize, 
				pricePerUnit);
	}
	

	public static void main(String[] args) {
		Item fur = new Item("fur", 3, 2);
		System.out.print(fur);

	}

}
