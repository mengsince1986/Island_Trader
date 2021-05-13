package io;

import java.util.ArrayList;
import java.util.HashMap;

import map.World;
import ships.Ship;

public class NewShipIO extends StrIO {
	
	private ArrayList<String> shipsList = new ArrayList<String>();

	public NewShipIO(World newWorld) {

		super(newWorld);
		HashMap<String, Ship> ships = getWorld().getShips();
		for (String shipName : ships.keySet()) {
			super.addCommand(shipName);
			this.shipsList.add(shipName);
		}
		super.addCommand("Cancel");

	}

	@Override
	public void processPlayerInput(String playerChoice) {
		
		String chosenIslandName = this.shipsList.get(Integer.parseInt(playerChoice));
		super.addCommandArgument(chosenIslandName);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
