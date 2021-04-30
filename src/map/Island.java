package map;

import java.util.ArrayList;

import events.*;
//import ships.Ship;
import exceptions.IslandNotFoundException;
import ships.Ship;

public class Island {
	
	private String name;
	private ArrayList<Route> routes = new ArrayList<>();
	private Store store;
	private Port port;
	
	
	//to help with MapTests.java
	public Island(String name) {
		this.name = name;
	}
	
	public Island(String name, Store store, Port port) {
		this.name = name;
		this.store = store;
		this.port = port;
	}
	
	public void addRoute(Route route) {
		routes.add(route);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	public String getRoutesString(Ship playerShip) {
		int shipSailingModifier = playerShip.getShipSailingModifier();
		int costPerDay = playerShip.getCostPerDay();
		String routesString = "Routes available:\n";
		if (routes.size() > 0) {
			for (int i = 0; i < routes.size(); i++) {
				int sailingTime = Integer.max(1, 
						(routes.get(i).getDistance() - shipSailingModifier));
				int totalCost = costPerDay * sailingTime;
				routesString += String.format("\nRoute %s:  %s  Sailing time: %s days\n  Cost: %s", 
						i, routes.get(i), sailingTime, totalCost);
			} 
			return routesString;
		} else {
			routesString += "No routes available; you're stuck here!";
			return routesString;
		}
	}
	
	public Store getStore() {
		return store;
	}
	
	public Port getPort() {
		return port;
	}
	
	public int daysToIsland(Island destination, Ship playerShip) {
		int shipSailingModifier = playerShip.getShipSailingModifier();
		for (Route route : routes) {
			if (route.getDest() == destination) {
				return Integer.max(1, (route.getDistance() - shipSailingModifier));
			}
		} throw new IslandNotFoundException();
	}
	
	public static void main(String[] args) {
		Map map = new Map();
		Island island1 = new Island("island1");
		Island island2 = new Island("island2");
		Route route1 = new Route(10, "Looks dicey");
		
		map.addIsland(island1);
		map.addIsland(island2);
		
		
		PirateEvent route1Pirates = new PirateEvent(5, 10, 100);
		route1.addEvent(route1Pirates);
		WeatherEvent route1Weather = new WeatherEvent(5);
		route1.addEvent(route1Weather);
		RescueEvent route1Rescue = new RescueEvent(5, 100);
		route1.addEvent(route1Rescue);
		
		island1.addRoute(route1);
		
		
	}

}