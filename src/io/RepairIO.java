package io;
import trader.*;

public class RepairIO extends IO {

	public RepairIO(Trader player) {
		
		super(player);
		
		int repairFee = super.getTrader().getCurrentIsland().getPort().getRepairCost();
		super.setPromp("Hi, Captain! Wanna repair your ship?\n"
				       + "Only " + repairFee + " dollars.");

		super.addCommand("Yes");
		super.addCommand("Maybe later");
	}
	
	@Override
	public void processPlayerInput(int playerChoice) {
		
		String argument = getCommandsList().get(playerChoice);
		if (argument == "Maybe later") {
			argument = "cancel";
		}
		super.addCommandArgument(argument);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
