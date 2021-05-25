package map;

import ships.Ship;

import java.util.*;

/**
 * A class that represents the game world, comprised of five {@link Island}s to sail
 * between and four {@link Ship}s for the player to choose from.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */
public class World {
	
	/**
	 * a HashMap to store the Island objects, accessible by their name Strings.
	 */
	private HashMap<String, Island> islands = new HashMap<String, Island>(); 
	
	/**
	 * a HashMap to store the Ship objects, accessible by their name Strings.
	 */
	private HashMap<String, Ship> ships = new HashMap<String, Ship>(); 
	
	/**
	 * instantiates the World object.
	 */
	public World(){
	}
	
	/**
	 * stores the given Island in the {@link islands} attribute.
	 * @param island the Island to be added
	 */
	public void addIsland(Island island) {
		this.islands.put(island.getName(), island);
	}
	
	/**
	 * stores the given Ship in the {@link ships} attribute.
	 * @param ship the Ship to be added
	 */
	public void addShip(Ship ship) {
		this.ships.put(ship.getName(), ship);
	}
	
	/**
	 * @return the {@link islands} attribute
	 */
	public HashMap<String, Island> getIslands() {
		return this.islands;
	}
	
	/**
	 * @return the {@link ships} attribute
	 */
	public HashMap<String, Ship> getShips() {
		return this.ships;
	}
	
	/**
	 * @param name the name String of the island object to be returned
	 * @return the Island with the given name (or null if the name doesn't match any Island)
	 */
	public Island getIsland(String name) {
		if (this.islands.containsKey(name)) {
			return this.islands.get(name);
		} else {
			return null;
		}
	}
	
	/**
	 * @param name the name String of the Ship object to be returned
	 * @return the Ship with the given name (or null if the name doesn't match any Ship)
	 */
	public Ship getShip(String name) {
		if (this.ships.containsKey(name)) {
			return this.ships.get(name);
		} else {
			return null;
		}
	}
}
