package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import map.Island;
import map.WorldConstructor;
import ships.Ship;
import trader.Trader;

class SailingTests {
	
	private WorldConstructor newWorld;
	private Island home;
	private Island destination1;
	private Island destination2;
	private Ship testShip1;
	private Ship testShip2;
	private Trader testPlayer1;
	private Trader testPlayer2;
	private int playingDays;
	private int defaultOwnedMoney;

	@BeforeEach
	void init() {
		
		newWorld = new WorldConstructor();
		home = newWorld.getMap().getIsland("Mecca Merchantia");
		destination1 = newWorld.getMap().getIsland("Ceylon");
		destination2 = newWorld.getMap().getIsland("The Isle of Dwarves");
		testShip1 = newWorld.getMap().getShip("Redcoasts");
		testShip2 = newWorld.getMap().getShip("Black Pearl");
		
		playingDays = 50;
		defaultOwnedMoney = 20000;
		testPlayer1 = new Trader(playingDays, "Alice", defaultOwnedMoney, home, "port");
		testPlayer2 = new Trader(playingDays, "Jon", defaultOwnedMoney, home, "port");
		
		testShip1.setCaptain(testPlayer1);
		testPlayer1.setOwnedShip(testShip1);
		
		testShip2.setCaptain(testPlayer2);
		testPlayer2.setOwnedShip(testShip2);
		
	}
	
	@Test
	void testReadyToSail() {
		
		boolean result = testShip1.readyToSail(destination1);
		assertEquals(result, true);
	}
	
	@Test
	void testFailedReadyToSailForDamage() {
		
		testShip1.setDurability(testShip1.getDefaultDurability() - 10);
		boolean result = testShip1.readyToSail(destination2);
		assertEquals(result, false);
		
	}
	
	@Test
	void testFailedReadyToSailForLackOfMoney() {
		
		testPlayer1.setOwnedMoney(0);
		boolean result = testShip1.readyToSail(destination2);
		assertEquals(result, false);
		
	}
	
	@Test
	void testFailedReadyToSailForNoTime() {
		
		testPlayer1.setRemainingDays(0);
		boolean result = testShip1.readyToSail(destination2);
		assertEquals(result, false);
		
	}



	@Test
	void testSucessfulSailTo() {
		
		int daysToDestination1Normal = home.daysToIsland(destination1, testShip1);
		int daysToDestination1Fast = home.daysToIsland(destination1, testShip2);
		
		ArrayList<String> reports1 = testShip1.sailTo(destination1);
		ArrayList<String> reports2 = testShip2.sailTo(destination1);
		
		// check current island
		assertEquals(testPlayer1.getCurrentIsland().getName(), "Ceylon");
		assertEquals(testPlayer2.getCurrentIsland().getName(), "Ceylon");
		
		// check current location
		assertEquals(testPlayer1.getCurrentLocation(), "port");
		assertEquals(testPlayer2.getCurrentLocation(), "port");
		
		// check remaining days
		assertEquals(testPlayer1.getRemainingDays(), 
				     playingDays-daysToDestination1Normal);
		assertEquals(testPlayer2.getRemainingDays(), 
			     playingDays-daysToDestination1Fast);
		
		// check if report ArrayList is empty
		assertTrue(reports1.size() > 0);
		assertTrue(reports2.size() > 0);
		
		// check if testShip2 is 1 day faster than testShip1
		assertEquals(testPlayer1.getRemainingDays(), testPlayer2.getRemainingDays()-1);
		
	}
	
	@Test
	void testFailedSailToForDamage() {
		
		testShip1.setDurability(testShip1.getDefaultDurability() - 10);
		String targetReport = "You need to fix your ship first!";
		String report = testShip1.sailTo(destination2).get(0);
		
		assertEquals(report, targetReport);
		
	}
	
	@Test
	void testFailedSailToForLackOfMoney() {
		
		testPlayer1.setOwnedMoney(0);
		String targetReport = "Oh no! You don't have enough money to pay your crew.\n" +
				"You will have to sell some of your items!";
		String report = testShip1.sailTo(destination2).get(0);
		
		assertEquals(report, targetReport);
		
	}
	
	@Test
	void testFailedSailToForNoTime() {
		
		testPlayer1.setRemainingDays(0);
		String targetReport = "Oh no! You don't have enough time to sail to that island!";
		String report = testShip1.sailTo(destination2).get(0);
		
		assertEquals(report, targetReport);
		
	}



}
