package io;

import ships.Ship;
import trader.*;

/**
 * The PortIO is an IO sub-class to create command line interface for port.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class PortIO extends IO {

	/**
	 * This constructor sets the player attribute with the current Trader object and
	 * adds all the port command options to attribute commandsList.
	 * 
	 * @param trader the current Trader object
	 */
	public PortIO(Trader player) {

		super(player);
		this.addCommand("Sail to another Island"); // 0
		this.addCommand("Go to store"); // 1
		this.addCommand("Repair ship"); // 2
		this.addCommand("Upgrade cannons"); // 3
		this.addCommand("View trading log"); // 4
		this.addCommand("View ship details"); // 5
		super.addCommand("Quit the game"); // 6

	}

	@Override
	/**
	 * This method processes an input of a port command from users. It categorizes the
	 * commands in attribute commandList with a switch statement and adds the
	 * command matching users' input to attribute commandArguments. This method also
	 * initializes a new command line interface object if necessary.
	 * 
	 * @param playerChoice an integer which matches a command in this interface
	 */
	public void processPlayerInput(int playerChoice) {

		resetCommandArguments();
		String argument = null;

		switch (playerChoice) {
		case 0: // sail

			super.addCommandArgument("sail");
			Ship playerShip = getTrader().getOwndedShip();
			System.out.println(getTrader().getCurrentIsland().getRoutesString(playerShip));
			SailToIO sailTo = new SailToIO(getTrader());
			sailTo.readCommandArguments("Where do you wish to sail?");
			break;

		case 1: // visit store
			super.addCommandArgument("store");
			break;

		case 2: // repair ship
			super.addCommandArgument("repair");
			RepairIO repair = new RepairIO(getTrader());
			int repairCostPerDamage = super.getTrader().getCurrentIsland().getPort().getRepairCost();
			int currentDamage = super.getTrader().getOwndedShip().getDamage();
			int repairCost = currentDamage * repairCostPerDamage;
			repair.readCommandArguments("Hi, Captain! Wanna repair your ship?\n" + "Only " + repairCostPerDamage
					+ " coins for each damage.\n" + "It'll cost you " + repairCost
					+ " coins to fix all the cracks and holes.");
			break;

		case 3: // upgrade cannons
			super.addCommandArgument("upgrade");
			setGettingTransactionQuantity(true);
			UpgradeIO upgrade = new UpgradeIO(getTrader());
			int upgradeCannonFee = super.getTrader().getCurrentIsland().getPort().getcannonCost();
			upgrade.readCommandArguments(
					"Yo, Captain! How many cannons do you want?\n" + upgradeCannonFee + " coins for one.\n");
			setGettingTransactionQuantity(false);
			break;
			
		case 4: // view trading logs
			addCommandArgument("logs");
			addCommandArgument(null);
			break;
			
		case 5: // view ship details
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

}
