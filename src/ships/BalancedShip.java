package ships;

public class BalancedShip extends Ship {
	public BalancedShip() {
		
		super("Redcoasts", 15, 15, 2, 1500, 8, 75, 75, "normal");
	}
	
	public static void main(String[] args) {
		BalancedShip redcoasts = new BalancedShip();
		System.out.println(redcoasts);
	}

}