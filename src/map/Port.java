package map;

/**
 * A class to represent the game's ports, where ship repairs and upgrades
 * take place——one per {@link Island}.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 *
 */
public class Port {
	
	/**
	 * the amount of money required to repair one unit of Ship damage
	 * at the Port.
	 */
	private int repairCostPerDamage;
	
	/**
	 * the amount of money required to install one extra cannon on a Ship at 
	 * the Port.
	 */
	private int costPerCannon;
	
	/**
	 * initialises a new Port to have the given {@link repairCostPerDamage} and 
	 * {@link costPerCannon} values.
	 * @param repairCost the amount required to repair one unit of Ship damage
	 * @param cannonCost the amount required to install one extra cannon on a Ship
	 */
	public Port(int repairCost, int cannonCost) {
		this.repairCostPerDamage = repairCost;
		this.costPerCannon = cannonCost;
	}
	
	/**
	 * @return the amount required to repair one unit of Ship damage
	 */
	public int getRepairCost() {
		return repairCostPerDamage;
	}
	
	/**
	 * @return the amount required to install one extra cannon on a Ship
	 */
	public int getcannonCost() {
		return costPerCannon;
	}
}