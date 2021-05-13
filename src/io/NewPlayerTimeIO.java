package io;
import map.Map;

public class NewPlayerTimeIO extends StrIO {

	public NewPlayerTimeIO(Map newWorld) {
		
		super(newWorld);
		super.addCommand("Cancel");
	}
	
	@Override
	public void processPlayerInput(String playerInput) {
		
		super.addCommandArgument(playerInput);
		NewPlayerHomeIO newHome = new NewPlayerHomeIO(getWorld());
		newHome.readCommandArguments("Please choose your home island:");

	}

}
