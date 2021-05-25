package map;

import events.PirateEvent;
import events.RescueEvent;
import events.WeatherEvent;
import items.Item;
import ships.Ship;

/**
 * A creational class that constructs the game world, including all the {@link Item}s
 * to be sold in the {@link Store}s on each of the five {@link Island}s, the {@link Route}s
 * between these Islands, the {@link RandomEvent}s that can occur on these {@link Route}s, as well
 * as the four unique {@link Ship}s that a player can choose to captain.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 *
 */
public class WorldConstructor {
	
	/**
	 * the {@link World} object that will contain the objects that make up the game world.
	 */
	private World newWorld = new World();

	/**
	 * creates a new WorldConstructor to build the game world objects in turn
	 * and finally add them to the {@link newWorld}.
	 */
	public WorldConstructor() {
		// Create items
		Item gunpowderLow1 = new Item("Gunpowder", 5000, 10);
		Item gunpowderMed4 = new Item("Gunpowder", 5000, 20);
		Item gunpowderHigh5 = new Item("Gunpowder", 1000, 25);
		
		Item timberLow1 = new Item("Timber", 10000, 50);
		Item timberMed4 = new Item("Timber", 10000, 80);
		Item timberHigh5 = new Item("Timber", 5000, 120);
	
		Item whaleOilLow1 = new Item("Whale oil", 10000, 3);
		Item whaleOilMed4 = new Item("Whale oil", 10000, 5);
		Item whaleOilHigh5 = new Item("Whale oil", 5000, 8);

		Item wineLow3 = new Item("Wine", 5000, 7);
		Item wineMed2 = new Item("Wine", 5000, 10);
		Item wineHigh3 = new Item("Wine", 5000, 15);
		Item wineHigh4 = new Item("Wine", 5000, 15);

		Item teaLow3 = new Item("Tea", 5000, 1);
		Item teaMed2 = new Item("Tea", 5000, 3);
		Item teaHigh3 = new Item("Tea", 5000, 6);
		Item teaHigh4 = new Item("Tea", 5000, 6);
		
		Item silkLow3 = new Item("Silk", 10000, 10);
		Item silkMed2 = new Item("Silk", 10000, 12);
		Item silkHigh3 = new Item("Silk", 10000, 17);
		Item silkHigh4 = new Item("Silk", 10000, 18);
		
		Item furLow3 = new Item("Fur", 5000, 4);
		Item furMed4 = new Item("Fur", 5000, 6);
		Item furHigh1 = new Item("Fur", 5000, 10);
		Item furHigh3 = new Item("Fur", 5000, 10);
		
		Item marbleLow3 = new Item("Marble", 10000, 20);
		Item marbleMed4 = new Item("Marble", 10000, 23);
		Item marbleHigh1 = new Item("Marble", 10000, 45);
		Item marbleHigh3 = new Item("Marble", 10000, 46);
		
		Item silverLow3 = new Item("Silver", 10000, 50);
		Item silverMed4 = new Item("Silver", 10000, 60);
		Item silverHigh1 = new Item("Silver", 10000, 100);
		Item silverHigh3 = new Item("Silver", 10000, 110);
		
		Item goldLow3 = new Item("Gold", 10000, 240);
		Item goldMed4 = new Item("Gold", 10000, 250);
		Item goldHigh1 = new Item("Gold", 10000, 490);
		Item goldHigh3 = new Item("Gold", 10000, 500);
		
		Item spiceLow3 = new Item("Spice", 5000, 1);
		Item spiceLow5 = new Item("Spice", 5000, 1);
		Item spiceMed2 = new Item("Spice", 5000, 5);
		Item spiceHigh1 = new Item("Spice", 5000, 10);
		Item spiceHigh3 = new Item("Spice", 5000, 15);
		
		Item tobaccoLow3 = new Item("Tobacco", 5000, 1);
		Item tobaccoLow5 = new Item("Tobacco", 5000, 1);
		Item tobaccoMed2 = new Item("Tobacco", 5000, 5);
		Item tobaccoHigh1 = new Item("Tobacco", 5000, 10);
		Item tobaccoHigh3 = new Item("Tobacco", 5000, 15);
		
		Item coralLow3 = new Item("Coral", 500, 9);
		Item coralLow5 = new Item("Coral", 500, 10);
		Item coralMed2 = new Item("Coral", 500, 90);
		Item coralHigh1 = new Item("Coral", 500, 120);
		Item coralHigh3 = new Item("Coral", 500, 150);
		
		Item[] sellList1 = {gunpowderLow1, timberLow1, whaleOilLow1};
		Item[] buyList1 = {
				spiceHigh1, 
				tobaccoHigh1, 
				coralHigh1,
				furHigh1,
				marbleHigh1,
				silverHigh1,
				goldHigh1
				};
		
		Item[] sellList2 = {wineMed2, teaMed2, silkMed2};
		Item[] buyList2 = {spiceMed2, tobaccoMed2, coralMed2};
		
		Item[] sellList3 = {
				silkHigh3, 
				marbleHigh3, 
				silverHigh3,
				goldHigh3,
				spiceHigh3,
				tobaccoHigh3,
				coralHigh3,
				wineHigh3,
				teaHigh3,
				furHigh3,
				};
		Item[] buyList3 = {
				silkLow3, 
				marbleLow3, 
				silverLow3,
				goldLow3,
				spiceLow3,
				tobaccoLow3,
				coralLow3,
				wineLow3,
				teaLow3,
				furLow3,
				};
		
		Item[] sellList4 = {furMed4, marbleMed4, silverMed4, goldMed4};
		Item[] buyList4 = {
				wineHigh4,
				teaHigh4,
				silkHigh4,
				gunpowderMed4, 
				timberMed4, 
				whaleOilMed4};
				
		Item [] sellList5 = {spiceLow5, tobaccoLow5, coralLow5};
		Item [] buyList5 = {gunpowderHigh5, timberHigh5, whaleOilHigh5};
		
		// Create stores
		Store store1 = new Store(sellList1, buyList1);
		Store store2 = new Store(sellList2, buyList2);
		Store store3 = new Store(sellList3, buyList3);
		Store store4 = new Store(sellList4, buyList4);
		Store store5 = new Store(sellList5, buyList5);
		
		// Create ports
		Port port1 = new Port(5, 400);
		Port port2 = new Port(8, 860);
		Port port3 = new Port(25, 1000);
		Port port4 = new Port(10, 900);
		Port port5 = new Port(50, 3000);
		
		// Create islands
		Island island1 = new Island("Lord Matheson Island", store1, port1);
		Island island2 = new Island("Ceylon", store2, port2);
		Island island3 = new Island("Mecca Merchantia", store3, port3);
		Island island4 = new Island("The Isle of Dwarves", store4, port4);
		Island island5 = new Island("Niawall's Tear", store5, port5); 
		
		// Create random events
		RescueEvent rescue2 = new RescueEvent(4, 1000);
		WeatherEvent storm2 = new WeatherEvent(8);
		
		RescueEvent rescue3 = new RescueEvent(2, 1000);
		PirateEvent pirate3 = new PirateEvent(10, 10, 1000);
		WeatherEvent storm3 = new WeatherEvent(2);
		
		RescueEvent rescue4 = new RescueEvent(2, 2000);
		PirateEvent pirate4 = new PirateEvent(2, 17, 5000);
		WeatherEvent storm4 = new WeatherEvent(2);
		
		RescueEvent rescue5 = new RescueEvent(4, 500);
		PirateEvent pirate5 = new PirateEvent(7, 7, 2000);
		WeatherEvent storm5 = new WeatherEvent(1);
		
		RescueEvent rescue6 = new RescueEvent(9, 5000);
		PirateEvent pirate6 = new PirateEvent(10, 100, 10000);
		WeatherEvent storm6 = new WeatherEvent(8);
		
		// Create routes
		Route route1to2 = new Route(2, "The Empire is said to have eradicated the pirates from these waters.\n" + 
				"However, they are known to produce violent storms.");
		route1to2.setSource(island1);
		route1to2.setDest(island2);
		route1to2.addEvent(rescue2);
		route1to2.addEvent(storm2);
		
		Route route2to1 = new Route(3, "The Empire is said to have eradicated the pirates from these waters.\n" + 
				"However, they are known to produce violent storms.");
		route2to1.setSource(island2);
		route2to1.setDest(island1);
		route2to1.addEvent(rescue2);
		route2to1.addEvent(storm2);
		
		Route route2to3 = new Route(3, "Pirates have been known to prey upon poorly armed merchant ships in these waters!");
		route2to3.setSource(island2);
		route2to3.setDest(island3);
		route2to3.addEvent(rescue3);
		route2to3.addEvent(storm3);
		route2to3.addEvent(pirate3);
		
		
		Route route3to2 = new Route(4, "Pirates have been known to prey upon poorly armed merchant ships in these waters!");
		route3to2.setSource(island3);
		route3to2.setDest(island2);
		route3to2.addEvent(rescue3);
		route3to2.addEvent(storm3);
		route3to2.addEvent(pirate3);
		
		Route route3to4 = new Route(3, "Rumour has it that the dwarves have cut a deal with a few pirates\n" +
				"to steal back their precious materials from unsupecting traders.");
		route3to4.setSource(island3);
		route3to4.setDest(island4);
		route3to4.addEvent(rescue4);
		route3to4.addEvent(storm4);
		route3to4.addEvent(pirate4);
		
		Route route4to3 = new Route(5, "Rumour has it that the dwarves have cut a deal with a few pirates\n" +
				"to steal back their precious materials from unsupecting traders.");
		route4to3.setSource(island4);
		route4to3.setDest(island3);
		route4to3.addEvent(rescue4);
		route4to3.addEvent(storm4);
		route4to3.addEvent(pirate4);
		
		Route route3to5 = new Route(6, island3.getName() + " is awash with reports about the many sailors\n" + 
				"from " + island5.getName() + " who are buying cheap, ill-equipped vessels to get in on the buccaneering boom.");
		route3to5.setSource(island3);
		route3to5.setDest(island5);
		route3to5.addEvent(rescue5);
		route3to5.addEvent(storm5);
		route3to5.addEvent(pirate5);
		
		Route route5to3 = new Route(8, "Your crew remind you of the reports about the many sailors from\n" + 
				island5.getName() + " who are buying cheap, ill-equipped vessels to get in on the buccaneering boom.");
		route5to3.setSource(island5);
		route5to3.setDest(island3);
		route5to3.addEvent(rescue5);
		route5to3.addEvent(storm5);
		route5to3.addEvent(pirate5);
		
		Route route4to5 = new Route(10, "Only the bravest captains dare navigate these open waters,\n" + 
				"home to the most fearsome pirate lords of the Seventy Seas.");
		route4to5.setSource(island4);
		route4to5.setDest(island5);
		route4to5.addEvent(rescue6);
		route4to5.addEvent(storm6);
		route4to5.addEvent(pirate6);
		
		Route route5to4 = new Route(12, "Only the bravest captains dare navigate these open waters,\n" + 
				"home to the most fearsome pirate lords of the Seventy Seas.");
		route5to4.setSource(island5);
		route5to4.setDest(island4);
		route5to4.addEvent(rescue6);
		route5to4.addEvent(storm6);
		route5to4.addEvent(pirate6);
		
		// add routes
		island1.addRoute(route1to2);
		island2.addRoute(route2to1);
		island2.addRoute(route2to3);
		island3.addRoute(route3to2);
		island3.addRoute(route3to4);
		island3.addRoute(route3to5);
		island4.addRoute(route4to3);
		island4.addRoute(route4to5);
		island5.addRoute(route5to3);
		island5.addRoute(route5to4);
		
		// Create ships
		Ship fastShip = new Ship("Black Pearl", 10, 2, 1500, 6, 70, "fast");
		Ship balancedShip = new Ship("Redcoasts", 15, 2, 1500, 8, 75, "normal");
		Ship battleShip = new Ship("Endeavour", 20, 2, 1000, 18, 90, "slow");
		Ship baoShip = new Ship("Empress", 16, 2, 1800, 8, 75, "normal");
		
		
		// add islands to new world
		newWorld.addIsland(island1);
		newWorld.addIsland(island2);
		newWorld.addIsland(island3);
		newWorld.addIsland(island4);
		newWorld.addIsland(island5);
		
		// add ships to new world
		newWorld.addShip(fastShip);
		newWorld.addShip(balancedShip);
		newWorld.addShip(battleShip);
		newWorld.addShip(baoShip);
		
	}
	
	/**
	 * @return the World object that stores the objects that make up the game world
	 */
	public World getMap() {
		return this.newWorld;
	}
}
