package map;

import events.PirateEvent;
import events.RescueEvent;
import events.WeatherEvent;
import items.Item;
import ships.Ship;

public class WorldConstructor {
	private World newWorld;
	//private ArrayList<Ship> ships;

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
		
		Item marbleLow = new Item("Marble", 1000, 4);
		Item marbleHigh = new Item("Marble", 1000, 7);
		
		Item silverLow = new Item("Silver", 1000, 15);
		Item silverHigh = new Item("Silver", 1000, 19);
		
		Item goldLow = new Item("Gold", 1000, 30);
		Item goldHigh = new Item("Gold", 1000, 40);
		
		Item[] saleList1 = {wineHigh, teaHigh, goldHigh};
		Item[] purchaseList1 = {wineLow, teaLow, goldLow};
		
		Item[] saleList2 = {silkLow, marbleLow, silverHigh};
		Item[] purchaseList2 = {wineHigh, teaHigh, goldLow};
		
		Item[] saleList3 = {silverHigh, silkLow, furLow};
		Item[] purchaseList3 = {marbleHigh, teaLow, goldHigh};
		
		// Create stores
		Store store1 = new Store(saleList1, purchaseList1);
		Store store2 = new Store(saleList2, purchaseList2);
		Store store3 = new Store(saleList3, purchaseList3);
		
		// Create ports
		Port port1 = new Port(10, 30);
		Port port2 = new Port(15, 20);
		Port port3 = new Port(8, 50);
		
		// Create Islands
		Island island1 = new Island("Niawall Haven", store1, port1);
		Island island2 = new Island("The Lobster Key", store2, port2);
		Island island3 = new Island("The Calm Reef", store3, port3);
		
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
		Route route2to3 = new Route(5, "danger");
		route2to3.setSource(island2);
		route2to3.setDest(island3);
		route2to3.addEvent(pirate1);
		Route route3to2 = new Route(8, "safe");
		route3to2.setSource(island3);
		route3to2.setDest(island2);
		route3to2.addEvent(rescueEvent1);
		route3to2.addEvent(storm1);
		
		island1.addRoute(route1to2);
		island2.addRoute(route2to1);
		island2.addRoute(route2to3);
		island3.addRoute(route3to2);
		
		// Create Ships
		Ship fastShip = new Ship("Black Pearl", 10, 2, 1500, 6, 70, "fast");
		Ship balancedShip = new Ship("Redcoasts", 15, 2, 1500, 8, 75, "normal");
		Ship battleShip = new Ship("Endeavour", 20, 2, 1000, 18, 90, "slow");
		Ship baoShip = new Ship("Empress", 16, 2, 1800, 8, 75, "normal");
		
		// Create a new Map
		World newWorld1 = new World();
		
		// add islands to new Map
		newWorld1.addIsland(island1);
		newWorld1.addIsland(island2);
		newWorld1.addIsland(island3);
		
		// add ships to new Map
		newWorld1.addShip(fastShip);
		newWorld1.addShip(balancedShip);
		newWorld1.addShip(battleShip);
		newWorld1.addShip(baoShip);
		
		this.newWorld = newWorld1;
	}
	
	public World getMap() {
		return this.newWorld;
	}
	
	public static void main(String[] args) {
		WorldConstructor newWorld = new WorldConstructor();
		//System.out.println(newWorld.getMap().getIslands().get(0).getRoutesString(0));

	}

}
