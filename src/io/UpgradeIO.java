package io;
import trader.*;

public class UpgradeIO extends IO {

	public UpgradeIO(Trader player) {
		
		super(player);
		
		int upgradeCannonFee = super.getTrader().getCurrentIsland().getPort().getcannonCost();
		super.setPromp("Yo, Captain! How many cannons do you want?\n" +
				       upgradeCannonFee + " dollars for one.\n"+
				       "We can install at most two.");

		super.addCommand("Just one more cannon. please.");
		super.addCommand("Give me two!");
		super.addCommand("Not now");
	}
	
	@Override
	public void processPlayerInput(int playerChoice) {
		
		String argument = getCommandsList().get(playerChoice);
		System.out.println(argument);
		if (argument == "Not now") {
			argument = "cancel";
		} else if (argument == "Just one more cannon. please.") {
			argument = "1";
		} else if (argument == "Give me two!"){
			argument = "2";
		}
		
		super.addCommandArgument(argument);
		System.out.println(super.getCommandArguments());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
