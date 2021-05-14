package io;
import map.World;

public class NewPlayerTimeIO extends NewPlayerIO {

	public NewPlayerTimeIO(World newWorld) {
		
		super(newWorld);
		super.setPromp("Please enter the days you want to play for:\n"
				     + "The number should be between 10 to 100.\n");
		super.addCommand("Cancel");
	}
	
	@Override
	public void processPlayerInput(String playerInput) {
		
		if (playerInput.matches("0") && Integer.parseInt(playerInput) == 0) {
			
			super.addCommandArgument("cancel");
			setGettingPlayingTime(false);
			
		} else {
			
			super.addCommandArgument(playerInput);
			setGettingPlayingTime(false);
			
			NewPlayerHomeIO newHome = new NewPlayerHomeIO(getWorld());
			newHome.readCommandArguments();
		}
		
	}

}
