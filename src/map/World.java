package map;

import exceptions.IslandNotFoundException;
import ships.Ship;

import java.util.*;

public class World {
	
	// MZ: a HashMap data structure? {"island name": Island object}. 
	// Quick for getting island object
	//private ArrayList<Island> islands = new ArrayList<>(); 
	private HashMap<String, Island> islands = new HashMap<String, Island>(); 
	private HashMap<String, Ship> ships = new HashMap<String, Ship>(); 
	
	public World(){
	}
	
	/*
	public void addIsland(Island island) {
		islands.add(island);
	}
	*/
	public void addIsland(Island island) {
		this.islands.put(island.getName(), island);
	}
	
	public void addShip(Ship ship) {
		this.ships.put(ship.getName(), ship);
	}
	
	/*
	public ArrayList<Island> getIslands() {
		return islands;
	}
	*/
	public HashMap<String, Island> getIslands() {
		return this.islands;
	}
	
	public HashMap<String, Ship> getShips() {
		return this.ships;
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
			return this.islands.get(name);
		} else {
			throw new IslandNotFoundException("No island with this name!");
		}
	}
	
	public Ship getShip(String name) {
		if (this.ships.containsKey(name)) {
			return this.ships.get(name);
		} else {
			throw new IslandNotFoundException("No ship with this name!");
		}
	}
}
