package io;

import map.World;

public class NewPlayerNameIO extends StrIO {

	public NewPlayerNameIO(World newWorld) {
		super(newWorld);
		super.addCommand("Cancel");
	}
	
	@Override
	public void processPlayerInput(String playerInput) {
		super.addCommandArgument(playerInput);
		NewPlayerTimeIO newTime = new NewPlayerTimeIO(getWorld());
		newTime.readCommandArguments("Please enter the days you want to play for:");
	}

}
