package map;

import java.util.ArrayList;

import events.*;
import ships.Ship;

/**
 * Represents the game's islands, which players sail between to conduct
 * their trading——five in total.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 *
 */
public class Island {
	
	/**
	 * the (extraordinarily creative) name of the Island.
	 */
	private String name;
	
	/**
	 * a list to contain the available {@link Route}s departing the Island.
	 */
	private ArrayList<Route> routes = new ArrayList<>();
	
	/**
	 * the Island's {@link Store} object, where trading takes place.
	 */
	private Store store;
	
	/**
	 * the Island's {@link Port} object, where Ship repairs and upgrade take place.
	 */
	private Port port;
	
	
	/**
	 * instantiates a new Island with just a name String. Convenient for testing.
	 * @param name the name String for the Island
	 */
	public Island(String name) {
		this.name = name;
	}
	
	/**
	 * instantiates a new Island and sets the {@link name}, {@link store}, and
	 * {@link port} attributes to the given name String, Store, and Port objects
	 * respectively.
	 * @param name the name String for the Island
	 * @param store the Island's Store
	 * @param port the Island's Port
	 */
	public Island(String name, Store store, Port port) {
		this.name = name;
		this.store = store;
		this.port = port;
	}
	
	/**
	 * adds a {@link Route} leaving the Island
	 * @param route the Route to be added
	 */
	public void addRoute(Route route) {
		routes.add(route);
	}
	
	/**
	 * @return the {@link name} String of the Island
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the ArrayList of available {@link Route} objects which have the Island
	 * as their source
	 */
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	/**
	 * searches the {@link routes} list for the Route whose destination is the
	 * given Island.
	 * @param destination the destination Island
	 * @return the {@link Route} whose destination attribute is set to the given
	 * Island, else null if no match
	 */
	public Route getRoute(Island destination) {
		ArrayList<Route> routes = getRoutes();
		for (Route route : routes) {
			if (route.getDest() == destination) {
				return route;
			}
		}
		return null;
	}
	
	/**
	 * takes a Ship and returns a String representation of all available Route options
	 * from the current Island, including the calculated sailing time and cost for each.
	 * @param playerShip the Ship to do the sailing
	 * @return a String representation of all the available Route options from the current
	 * Island and the cost of sailing them
	 */
	public String getRoutesString(Ship playerShip) {
		int costPerDay = playerShip.getCostPerDay();
		String routesString = "Routes available:\n";
		if (routes.size() > 0) {
			for (int i = 0; i < routes.size(); i++) {
				int sailingTime = routes.get(i).getSailingTime(playerShip);
				int totalCost = costPerDay * sailingTime;
				routesString += String.format("\nRoute %s: %sSailing time: %s days\nCost: %s\n", 
						i+1, routes.get(i), sailingTime, totalCost);
			} 
			return routesString;
		} else {
			routesString += "No routes available; you're stuck here!";
			return routesString;
		}
	}
	
	/**
	 * @return the current Island's {@link Store} object
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * @return the current Island's {@link Port} object
	 */
	public Port getPort() {
		return port;
	}
	
	/** 
	 * takes an Island destination and a Ship to calculate and return the number of days
	 * to sail on a Route to the given destination.
	 * @param destination the Island of interest
	 * @param playerShip the Ship to do the sailing
	 * @return the number of days to sail to the destination, else -1 if no match with 
	 * the available Route options
	 */
	public int daysToIsland(Island destination, Ship playerShip) {
		int shipSailingModifier = playerShip.getShipSailingModifier();
		for (Route route : routes) {
			if (route.getDest() == destination) {
				return Integer.max(1, (route.getDistance() - shipSailingModifier));
			}
		} return -1;
	}
}