package ships;

public class BattleShip extends Ship {
	
	public BattleShip() {
		
		super("Endeavour", 20, 2, 1000, 18, 90, "slow");
	}
	
	public static void main(String[] args) {
		BattleShip endeavour = new BattleShip();
		System.out.println(endeavour);
	}

}