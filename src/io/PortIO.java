package io;

import ships.Ship;
import trader.*;

public class PortIO extends IO {
	
	/**
	 * read type: get input for playing commands. e.g. commands that match Ship.sailTo(), Trader.buy()... 
	 * sailDestination: String
	 */
	
	public PortIO(Trader player) {
		
		super(player);
		this.addCommand("Sail to another Island"); //0
		this.addCommand("Go to store");            //1
		this.addCommand("Repair ship"); 		   //2
		this.addCommand("Upgrade cannons");        //3
		this.addCommand("View trading log");       //4
		this.addCommand("View ship details");	   //5
		super.addCommand("Quit the game");         //6

		
	}
	
	public void processPlayerInput(int playerChoice) {
	
		resetCommandArguments();
		//String keyWord = null; //MZ: update command directly
		String argument = null;

		switch(playerChoice) {
		case 0: //sail
			
			super.addCommandArgument("sail");
			Ship playerShip = getTrader().getOwndedShip();
			System.out.println(getTrader().getCurrentIsland().getRoutesString(playerShip));
			SailToIO sailTo = new SailToIO(getTrader());
			sailTo.readCommandArguments("Where do you wish to sail?");
			break;
			
		case 1: //visit store
			super.addCommandArgument("store");
			break;
			
		case 2: //repair ship
			super.addCommandArgument("repair");
			RepairIO repair = new RepairIO(getTrader());
			int repairCostPerDamage = super.getTrader().getCurrentIsland().getPort().getRepairCost();
			int currentDamage = super.getTrader().getOwndedShip().getDamage();
			int repairCost = currentDamage * repairCostPerDamage;
			repair.readCommandArguments("Hi, Captain! Wanna repair your ship?\n"
				       				  + "Only " + repairCostPerDamage + " coins for each damage.\n"
				       				  + "It'll cost you " + repairCost + " coins to fix all the cracks and holes.");
			break;
			
		case 3: //upgrade cannons
			super.addCommandArgument("upgrade");
			setGettingTransactionQuantity(true);
			UpgradeIO upgrade = new UpgradeIO(getTrader());
			int upgradeCannonFee = super.getTrader().getCurrentIsland().getPort().getcannonCost();
			upgrade.readCommandArguments("Yo, Captain! How many cannons do you want?\n" 
									   + upgradeCannonFee + " coins for one.\n");
			setGettingTransactionQuantity(false);
			break;
		case 4: //view trading logs
			addCommandArgument("logs");
			addCommandArgument(null);
			break;
		case 5: //view ship details
			addCommandArgument("ship");
			addCommandArgument(null);
			break;
		case 6: // quit game
			super.addCommandArgument("quit");
			break;
		}

		// MZ: in case there's only one element in commandArguments
		// as commandHandler always check the 2nd index of commandArguments
		if (super.getCommandArguments().size() == 1) {
			super.addCommandArgument(argument);
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		Map map;
>>>>>>> constructorIO
		Trader player;
		Ship ship;
		
		System.out.println("Welcome to the wolrd of Island Trader");
		
		// constructing a new map
		WorldConstructor newWorld = new WorldConstructor();
		map = newWorld.getMap();
		Island island1 = map.getIsland("Niawall Haven");
		System.out.println("Constructing game environment ...");
		System.out.println("A new world is created ... ");
		
		// create a new player
		String traderName = "Jon Snow"; // get user input + loop invoked by exception
		player = new Trader(30, traderName, 10000, 
							island1, "port");
		ship = new BalancedShip(); // get user input + loop invoked by exception
		ship.setCaptain(player);
		player.setOwnedShip(ship);
		PortIO portIO = new PortIO(player);
		portIO.processPlayerInput(0);
		*/

	}

}
