package io;

import java.util.ArrayList;
import java.util.HashMap;

import map.Island;
import map.World;

public class NewPlayerHomeIO extends StrIO {
	
	private ArrayList<String> islandsList = new ArrayList<String>();

	public NewPlayerHomeIO(World newWorld) {
		
		super(newWorld);
		HashMap<String, Island> islands = getWorld().getIslands(); 
		for (String islandName : islands.keySet()) {
			super.addCommand(islandName);
			islandsList.add(islandName);
			
		}
		super.addCommand("Cancel");
		
		
	}
	
	@Override
	public void processPlayerInput(String playerChoice) {
		
		String chosenIslandName = this.islandsList.get(Integer.parseInt(playerChoice));
		super.addCommandArgument(chosenIslandName);
		NewShipIO newShip = new NewShipIO(getWorld());
		newShip.readCommandArguments("Please choose your ship:");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
