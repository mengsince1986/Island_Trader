package io;

public class NewPlayerTimeIO extends ConstructorIO {

	public NewPlayerTimeIO() {
		
		super.setPromp("Please enter the days you want to play for:");
		super.addCommand("Quit");
	}
	
	@Override
	public void processPlayerInput(String playerInput) {
		super.addCommandArgument(playerInput);
		//NewPlayerHomeIO newHome = new NewPlayerHomeIO();
		//newHome.readCommandArguments();

	}

}
