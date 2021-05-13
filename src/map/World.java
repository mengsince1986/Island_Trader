package map;

import exceptions.IslandNotFoundException;
import java.util.*;

public class World {
	
	// MZ: a HashMap data structure? {"island name": Island object}. 
	// Quick for getting island object
	//private ArrayList<Island> islands = new ArrayList<>(); 
	private HashMap<String, Island> islands = new HashMap<String, Island>(); 
	
	
	public World(){
	}
	
	/*
	public void addIsland(Island island) {
		islands.add(island);
	}
	*/
	public void addIsland(Island island) {
		islands.put(island.getName(), island);
	}
	
	/*
	public ArrayList<Island> getIslands() {
		return islands;
	}
	*/
	public HashMap<String, Island> getIslands() {
		return islands;
	}
	
	/*
	public Island getIsland(String name) {
		for (Island island : islands) {
			if (island.getName() == name) {
				return island;
			}
		}
		throw new IslandNotFoundException("No island with that name!");
	}
	*/
	public Island getIsland(String name) {
		if (this.islands.containsKey(name)) {
			return islands.get(name);
		} else {
			throw new IslandNotFoundException("No island with that name!");
		}
	}
}
