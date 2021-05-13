package io;
import trader.*;

public class UpgradeIO extends IO {

	public UpgradeIO(Trader player) {
		
		super(player);
		//super.addCommand("Just one more cannon. please.");
		//super.addCommand("Give me two!");
		super.addCommand("Not now");
	}
	
	@Override
	public void processPlayerInput(int playerChoice) {
		
		if (playerChoice == 0) {
			super.addCommandArgument("cancel");;
		} else {
			super.addCommandArgument(String.valueOf(playerChoice));
			
		}
		
		//String argument = getCommandsList().get(playerChoice);
		//System.out.println(argument);
		//else if (argument == "Just one more cannon. please.") {
		//	argument = "1";
		//} else if (argument == "Give me two!"){
		//	argument = "2";
		//}
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
