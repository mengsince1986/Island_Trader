package io;

import java.util.ArrayList;
import java.util.HashMap;

import map.World;
import ships.Ship;

public class NewPlayerShipIO extends NewPlayerIO {
	
	private ArrayList<String> shipsList = new ArrayList<String>();

	public NewPlayerShipIO(World newWorld) {

		super(newWorld);
		super.setPromp("Please choose your ship:\n");
		
		HashMap<String, Ship> ships = getWorld().getShips();
		for (String shipName : ships.keySet()) {
			super.addCommand(shipName);
			this.shipsList.add(shipName);
		}
		
		super.addCommand("Cancel"); //4

	}

	@Override
	public void processPlayerInput(String playerChoice) {
		

		if (Integer.parseInt(playerChoice) == shipsList.size()) {
			
			super.addCommandArgument("cancel");
			
		} else {
			
			String chosenIslandName = this.shipsList.get(Integer.parseInt(playerChoice));
			super.addCommandArgument(chosenIslandName);
			
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
