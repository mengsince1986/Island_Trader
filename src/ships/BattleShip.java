package ships;

import map.Island;

public class BattleShip extends Ship {
	
	public BattleShip() {
		
		super();
		super.setName("Endeavour");
		super.setCrewNumber(20);
		super.setMinimumCrewNumber(20);
		super.setCostPerDay(1*20); // 1 gold per sailor
		super.setCapacity(1000);
		super.setCannons(18);
		super.setDurability(90);
		super.setDefaultDurability(90);
		super.setSpeed("slow");
		super.setSailingDaysModifier(3);
	}
	
	public void sailTo(Island destination) {
		if (super.readyToSail(destination)) {

			// update remaining days
			// spend 1 more day for slow speed
			int daysToDestination = super.getCaptain().getCurrentIsland().daysToIsland(destination, this.getSailingDaysModifier());
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
	//}

}
