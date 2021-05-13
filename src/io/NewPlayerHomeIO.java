package io;

import java.util.HashMap;

import map.Island;
import map.Map;

public class NewPlayerHomeIO extends StrIO {

	public NewPlayerHomeIO(Map newWorld) {
		
		super(newWorld);
		HashMap<String, Island> islands = getWorld().getIslands(); 
		for (String islandName : islands.keySet()) {
			super.addCommand(islandName);
		}
		super.addCommand("Cancel");
		
		
	}
	
	@Override
	public void processPlayerInput(String playerChoice) {
		
		super.addCommandArgument(playerChoice);
		NewShipIO newShip = new NewShipIO(getWorld());
		newShip.readCommandArguments("Please choose your ship:");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
