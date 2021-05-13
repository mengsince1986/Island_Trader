package io;

import map.Map;

public class NewPlayerConstructorIO extends StrIO {

	public NewPlayerConstructorIO(Map newWorld) {
		
		super(newWorld);
		super.addCommand("Create a new player"); //0
		super.addCommand("Quit");                //1
		
	}

	@Override
	public void processPlayerInput(String playerChoice) {
		
		resetCommandArguments();

		switch (playerChoice) {
		case "0": // new player
			NewPlayerNameIO newName = new NewPlayerNameIO(getWorld());
			newName.readCommandArguments("Please enter the name for the new trader:");
			break;

		case "1": // quit
			super.addCommandArgument("quit");
			break;

		}
		
	}

}
