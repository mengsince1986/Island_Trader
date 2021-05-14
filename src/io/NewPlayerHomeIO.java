package io;

import java.util.ArrayList;
import java.util.HashMap;

import map.Island;
import map.World;

public class NewPlayerHomeIO extends NewPlayerIO {
	
	private ArrayList<String> islandsList = new ArrayList<String>();

	public NewPlayerHomeIO(World newWorld) {
		
		super(newWorld);
		super.setPromp("Please choose your home island:\n");
				
		HashMap<String, Island> islands = getWorld().getIslands(); 
		for (String islandName : islands.keySet()) {
			super.addCommand(islandName);
			islandsList.add(islandName);	
		}
		super.addCommand("Cancel"); // 2
		
		
	}
	
	@Override
	public void processPlayerInput(String playerChoice) {
		
		if (Integer.parseInt(playerChoice) == islandsList.size()) {
			
			super.addCommandArgument("cancel");
			
		} else {

			String chosenIslandName = this.islandsList.get(Integer.parseInt(playerChoice));
			super.addCommandArgument(chosenIslandName);
			NewPlayerShipIO newShip = new NewPlayerShipIO(getWorld());
			newShip.readCommandArguments();

		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
