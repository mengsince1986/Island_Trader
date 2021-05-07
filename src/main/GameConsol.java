package main;
import java.util.*;
import trader.Trader;
import map.Island;
import map.Map;
import map.WorldConstructor;
import ships.BalancedShip;
import ships.Ship;


public class GameConsol {
	
   /**
   * Use this class to test the backend of the game
   */
	
	public static void main(String[] args) {
		
		// Initialize properties
		
		Map map;
		Trader player;
		Ship ship;
		Scanner playerCommands = new Scanner(System.in);
		
		System.out.println("Welcome to the wolrd of Island Trader");
		
		// constructing a new map
		WorldConstructor newWorld = new WorldConstructor();
		map = newWorld.getMap();
		Island island1 = map.getIsland("Niawall Haven");
		Island island2 = map.getIsland("The Lobster Key");
		System.out.println("Constructing game environment ...");
		System.out.println("A new world is created ... ");
		
		// create a new player
		String traderName = "Jon Snow"; // get user input + loop invoked by exception
		player = new Trader(30, traderName, 10000, 
							island1, "port");
		ship = new BalancedShip(); // get user input + loop invoked by exception
		ship.setCaptain(player);
		player.setOwnedShip(ship);
		System.out.println("A new Trader named " + player.getName() + " is created ... ");
		System.out.println("A new Ship named " + player.getOwndedShip().getName() + " is created ...");
		System.out.println("===== All Set! =====");
		System.out.println("");
	
		// Test commands below
		
		
		/*
		System.out.println("Test Ship.sailTo()");
		System.out.println("==================");
		System.out.println(player.getCurrentIsland().getName() + ":  " + player.getCurrentLocation());
		player.setCurrentLocation("store");
		player.buy(player.getCurrentIsland(), "Wine", 50);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("After trader bought 50 units Wine");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		*/
		
		// test random events
		player.setCurrentLocation("port");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Captain Pre-Report: ");
		System.out.println(player);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Ship Pre-Report: ");
		System.out.println(player.getOwndedShip());
		ArrayList<String> eventReports = player.getOwndedShip().sailTo(island2);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("After " + island1.daysToIsland(island2, player.getOwndedShip()) + " days ... ... ...");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Island: " + player.getCurrentIsland().getName() + " Location:  " + player.getCurrentLocation());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Events:");
		for (String report : eventReports) {
			System.out.println(report);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Captain Post-Report: ");
		System.out.println(player);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Ship Post-Report: ");
		System.out.println(player.getOwndedShip());
		System.out.println("==================");
		
		/*
		player.setCurrentLocation("store");
		System.out.println(player.getCurrentIsland().getName() + ":  " + player.getCurrentLocation());
		System.out.println(player.getCurrentIsland().getStore().forSale()); 
		System.out.println("Owned Money: " + player.getOwnedMoney());
		System.out.println("Ship Capacity: " + player.getOwndedShip().getCapacity());
		System.out.println("Ship Cargoes: " + player.getOwndedShip().getCargos());
		player.buy(player.getCurrentIsland(), "Silk", 20);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("After trader bought 20 units Silk");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		player.sell(player.getCurrentIsland(), "Wine", 50);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("After trader sold 25 units Wine");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(player.getCurrentIsland().getStore().forSale()); 
		System.out.println("Owned Money: " + player.getOwnedMoney());
		System.out.println("Trading logs: " + player.getTradingLogs());
		System.out.println("Ship Capacity: " + player.getOwndedShip().getCapacity());
		System.out.println("Ship Cargoes: " + player.getOwndedShip().getCargos());
		*/
		// Test commands above
		
		
		playerCommands.close();

	}

}