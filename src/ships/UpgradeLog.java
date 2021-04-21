package ships;
import map.*;

public class UpgradeLog {

	private String upgradeLocation;
	private String upgradeService;
	private int upgradeCost;
	
	public UpgradeLog(Island location, String service, int cost) {
		this.upgradeLocation = location.getName();
		this.upgradeService = service;
		this.upgradeCost = cost;
	}
	
	public String toString() {
		
		String log = this.upgradeLocation + " " + 
				 this.upgradeService + " " +
				 this.upgradeCost;
		return log;
	}
	
}
