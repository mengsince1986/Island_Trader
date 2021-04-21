package ships;

import map.Island;

public class BaoShip extends Ship {

	public BaoShip() {
		
		super();
		super.setName("Empress");
		super.setCrewNumber(16);
		super.setMinimumCrewNumber(16);
		super.setCostPerDay(1*16); // 1 gold per sailor
		super.setCapacity(1800);
		super.setCannons(8);
		super.setDurability(75);
		super.setDefaultDurability(75);
		super.setSpeed("normal");
	}
	
	public void sailTo(Island destination) {
		if (super.readyToSail(destination)) {

			// update remaining days
			int daysToDestination = super.getCaptain().getCurrentIsland().daysToIsland(destination);
			int remainingDays = super.getCaptain().getRemainingDays();
			super.getCaptain().setRemainingDays(remainingDays - daysToDestination);
			
			// pay crew and update captain ownedMoney
			int costToDestination = super.getCostPerDay() * daysToDestination;
			//int moneyAvailable =  super.getCaptain().getOwnedMoney();
			super.getCaptain().subtractMoney(costToDestination);

			// random event
			// ...
			
			// update captain currentIsland and currentLocation
			super.getCaptain().setCurrentIsland(destination);
			super.getCaptain().setCurrentLocation("port");
		}
	}
	
	//public void battle(String strategy, Pirate enemy) {
		//...
	}
}
