package map;

import java.util.ArrayList;

public class Island {
	
	private String name;
	private ArrayList<Route> routes;
	private Store store;
	private Port port;
	
	public Island(String name, ArrayList<Route> routes, Store store, Port port) {
		this.name = name;
		this.routes = routes;
		this.store = store;
		this.port = port;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Route> getRoutes() {
		return routes;
	}
	
	public String getRoutesString() {
		String routesString = "Routes available:\n";
		if (routes.size() > 0) {
			for (int i = 0; i < routes.size(); i++) {
				routesString += String.format("\nItem %s: %s", i, routes.get(i));
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
	
	//add daysToIsland(Island destination): int
	

	public static void main(String[] args) {

	}

}
