package io;

import map.World;

public class NewPlayerNameIO extends NewPlayerIO {

	public NewPlayerNameIO(World newWorld) {
		super(newWorld);
		super.setPromp("Please enter the name for the new trader:\n"
				     + "(The length must be between 3 and 15 characters\n"
				     + "and must not include numbers or special characters.)\n");
		super.addCommand("Cancel");
	}
	
	@Override
	public void processPlayerInput(String playerInput) {
		super.addCommandArgument(playerInput);
		NewPlayerTimeIO newTime = new NewPlayerTimeIO(getWorld());
		newTime.readCommandArguments();
	}

}
