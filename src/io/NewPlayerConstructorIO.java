package io;

public class NewPlayerConstructorIO extends ConstructorIO {

	public NewPlayerConstructorIO() {
		
		super.setPromp("Welcome to the wolrd of Island Trader!\n"
					 + "Before we start,\n"
				     + "Let's first create a new player.");
		super.addCommand("Create a new player"); //0
		super.addCommand("Quit");                //1
		
	}
	
	@Override
	public void processPlayerInput(String playerChoice) {
		// Reset all previous commandArguments!!!
		resetCommandArguments();
		
		switch (playerChoice) {
		case "0": // new player
			NewPlayerNameIO newName = new NewPlayerNameIO();
			newName.readCommandArguments();
			break;

		case "1": // quit
			super.addCommandArgument("quit");
			break;

		}

	}

}
