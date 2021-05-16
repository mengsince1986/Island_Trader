package ships;

public class BalancedShip extends Ship {
	
	// this class is only for backend test
	
	public BalancedShip() {
		
		super("Redcoasts", 15, 2, 1500, 8, 75, "normal");
	}
	
	public static void main(String[] args) {
		BalancedShip redcoasts = new BalancedShip();
		System.out.println(redcoasts);
	}

}