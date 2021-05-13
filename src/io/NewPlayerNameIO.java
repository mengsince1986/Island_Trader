package io;

import map.Map;

public class NewPlayerNameIO extends StrIO {

	public NewPlayerNameIO(Map newWorld) {
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
