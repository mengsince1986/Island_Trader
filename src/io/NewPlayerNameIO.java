package io;

import map.World;

public class NewPlayerNameIO extends NewPlayerIO {

	public NewPlayerNameIO(World newWorld) {
		super(newWorld);
		super.setPrompt("Please enter the name for the new trader:\n"
				     + "(The length must be between 3 and 15 characters\n"
				     + "and must not include numbers or special characters.)\n");
		super.addCommand("Cancel"); //0
	}
	
	@Override
	public void processPlayerInput(String playerInput) {
		
		if (playerInput.matches("0") && Integer.parseInt(playerInput) == 0) {
			
			super.addCommandArgument("cancel");
			setGettingName(false);
			
		} else {
			
			super.addCommandArgument(playerInput);
			setGettingName(false);
			
			setGettingPlayingTime(true);
			NewPlayerTimeIO newTime = new NewPlayerTimeIO(getWorld());
			newTime.readCommandArguments();
			
		}
	}

}
