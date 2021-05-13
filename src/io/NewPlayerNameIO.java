package io;

public class NewPlayerNameIO extends ConstructorIO {

	public NewPlayerNameIO() {
		super.setPromp("Please enter the name for the new trader:");
		super.addCommand("Quit");
	}
	
	@Override
	public void processPlayerInput(String playerInput) {
		super.addCommandArgument(playerInput);
		NewPlayerTimeIO newTime = new NewPlayerTimeIO();
		newTime.readCommandArguments();
	}

}
