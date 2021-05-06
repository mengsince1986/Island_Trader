package map;

import events.PirateEvent;
import events.RescueEvent;
import events.WeatherEvent;
import items.Item;

public class WorldConstructor {
	private Map map;

	public WorldConstructor() {
		// Create items
		Item wineLow = new Item("Wine", 1000, 8);
		Item wineHigh = new Item("Wine", 1000, 10);
		
		Item teaLow = new Item("Tea", 1000, 6);
		Item teaHigh = new Item("Tea", 1000, 8);
		
		Item furLow = new Item("Fur", 1000, 5);
		Item furHigh = new Item("Fur", 1000, 7);
		
		Item silkLow = new Item("Silk", 1000, 10);
		Item silkHigh = new Item("Silk", 1000, 15);
		
		Item timberLow = new Item("Timber", 1000, 2);
		Item timberHigh = new Item("Timber", 1000, 3);
		
		Item marbelLow = new Item("Marbel", 1000, 4);
		Item marbelHigh = new Item("Marbel", 1000, 7);
		
		Item silverLow = new Item("Silver", 1000, 15);
		Item silverHigh = new Item("Silver", 1000, 19);
		
		Item goldLow = new Item("Gold", 1000, 30);
		Item goldHigh = new Item("Gold", 1000, 40);
		
		Item[] saleList1 = {wineLow, teaLow, goldHigh};
		Item[] purchaseList1 = {silkHigh, marbelHigh, silverLow};
		
		Item[] saleList2 = {silkLow, marbelLow, silverHigh};
		Item[] purchaseList2 = {wineHigh, teaHigh, goldLow};
		
		// Create stores
		Store store1 = new Store(saleList1, purchaseList1);
		Store store2 = new Store(saleList2, purchaseList2);
		
		// Create ports
		Port port1 = new Port(10, 30);
		Port port2 = new Port(15, 20);
		
		// Create Islands
		Island island1 = new Island("Niawall Haven", store1, port1);
		Island island2 = new Island("The Lobster Key", store2, port2);
		
		// Create Random events
		PirateEvent pirate1 = new PirateEvent(5, 8, 50);
		WeatherEvent storm1 = new WeatherEvent(5);
		RescueEvent rescueEvent1 = new RescueEvent(6, 20);
		
		// Create Routes
		Route route1to2 = new Route(7, "danger");
		route1to2.setSource(island1);
		route1to2.setDest(island2);
		route1to2.addEvent(pirate1);
		route1to2.addEvent(storm1);
		Route route2to1 = new Route(6, "safe");
		route2to1.setSource(island2);
		route2to1.setDest(island1);
		route2to1.addEvent(rescueEvent1);
		
		island1.addRoute(route1to2);
		island2.addRoute(route2to1);
		
		// Create Map
		Map map1 = new Map();
		map1.addIsland(island1);
		map1.addIsland(island2);
		
		this.map = map1;
	}
	
	public Map getMap() {
		return this.map;
	}
	
	public static void main(String[] args) {
		WorldConstructor newWorld = new WorldConstructor();
		//System.out.println(newWorld.getMap().getIslands().get(0).getRoutesString(0));

	}

}
