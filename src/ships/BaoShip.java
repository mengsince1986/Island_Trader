package ships;

public class BaoShip extends Ship {

	// this class is only for backend test
	
	public BaoShip() {
		
		super("Empress", 16, 2, 1800, 8, 75, "normal");
	}
	
	public static void main(String[] args) {
		BaoShip empress = new BaoShip();
		System.out.println(empress);
	}

}
