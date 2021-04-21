package ships;

import map.*;

public class FastShip extends Ship {
	
	public FastShip() {
		
		super();
		super.setName("Black Pearl");
		super.setCrewNumber(10);
		super.setMinimumCrewNumber(10);
		super.setCostPerDay(1*10); // 1 gold per sailor
		super.setCapacity(1500);
		super.setCannons(6);
		super.setDurability(70);
		super.setDefaultDurability(70);
		super.setSpeed("fast");
	}
	
	public void sailTo(Island destination) {
		if (super.readyToSail(destination)) {

			// update remaining days
			// save 1 day for fast speed
			int daysToDestination = super.getCaptain().getCurrentIsland().daysToIsland(destination) - 1;
			int remainingDays = super.getCaptain().getRemainingDays();
			super.getCaptain().setRemainingDays(remainingDays - daysToDestination);
			
			// pay crew and update captain ownedMoney
			int costToDestination = super.getCostPerDay() * daysToDestination;
			//int moneyAvailable =  super.getCaptain().getOwnedMoney();
			super.getCaptain().subtractMoney(costToDestination);

			// random event
			// ... instanceOf Object
			
			// update captain currentIsland and currentLocation
			super.getCaptain().setCurrentIsland(destination);
			super.getCaptain().setCurrentLocation("port");
		}
	}
	
	/*
	public void battle(String strategy, Pirate enemy) {
		//...
	}
	*/

}
