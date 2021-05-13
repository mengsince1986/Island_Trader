package io;

import map.World;

public class NewPlayerConstructorIO extends NewPlayerIO {

	public NewPlayerConstructorIO(World newWorld) {
		
		super(newWorld);
		super.setPromp("Welcome to the wolrd of Island Trader!\n"
			  	     + "Before we start,\n"
                     + "Let's first create a new player.\n\n"
				     + "Choose a command in the \"Commands List\",\n"
				     + "Input the number before the command and press \"Enter\" key\n");
		super.addCommand("Create a new player"); //0
		super.addCommand("Quit");                //1
		
	}

	@Override
	public void processPlayerInput(String playerChoice) {
		
		resetCommandArguments();

		switch (playerChoice) {
		case "0": // new player
			NewPlayerNameIO newName = new NewPlayerNameIO(getWorld());
			newName.readCommandArguments();
			break;

		case "1": // quit
			super.addCommandArgument("quit");
			break;

		}
		
	}
	
	

}
