package map;

public class Port {
	
	private int repairCost;
	private int cannonCost;
	
	public Port(int repairCost, int cannonCost) {
		this.repairCost = repairCost;
		this.cannonCost = cannonCost;
	}
	
	public int getRepairCost() {
		return repairCost;
	}
	
	public int getcannonCost() {
		return cannonCost;
	}
	
	// repair and upgrade methods might need to be in Ship?

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}