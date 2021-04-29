package ships;

public class BaoShip extends Ship {

	public BaoShip() {
		
		super("Empress", 16, 16, 2, 1800, 8, 75, 75, "normal");
	}
	
	public static void main(String[] args) {
		BaoShip empress = new BaoShip();
		System.out.println(empress);
	}

}
