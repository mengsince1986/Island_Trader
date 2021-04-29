package ships;

public class FastShip extends Ship {
	
	public FastShip() {
		
		super("Black Pearl", 10, 2, 1500, 6, 70, "fast");
	}
	
	public static void main(String[] args) {
		FastShip blackPearl = new FastShip();
		System.out.println(blackPearl);
	}

}