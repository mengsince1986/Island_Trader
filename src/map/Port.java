package map;

public class Port {
	
	private int repairCostPerDamage;
	private int costPerCannon;
	
	public Port(int repairCost, int cannonCost) {
		this.repairCostPerDamage = repairCost;
		this.costPerCannon = cannonCost;
	}
	
	public int getRepairCost() {
		return repairCostPerDamage;
	}
	
	public int getcannonCost() {
		return costPerCannon;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}