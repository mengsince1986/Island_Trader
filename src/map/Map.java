package map;

import exceptions.IslandNotFoundException;
import java.util.ArrayList;

public class Map {
	
	private ArrayList<Island> islands = new ArrayList<>();
	
	public Map(){
	}
	
	public void addIsland(Island island) {
		islands.add(island);
	}
	
	public ArrayList<Island> getIslands() {
		return islands;
	}
	
	public Island getIsland(String name) {
		for (Island island : islands) {
			if (island.getName() == name) {
				return island;
			}
		}
		throw new IslandNotFoundException("No island with that name!");
	}
}
