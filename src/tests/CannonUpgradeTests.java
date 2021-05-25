package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import map.Island;
import map.WorldConstructor;
import ships.Ship;
import trader.Trader;

class CannonUpgradeTests {
	
	private WorldConstructor newWorld;
	private Island home;
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
		testShip1 = newWorld.getMap().getShip("Redcoasts");
		testShip2 = newWorld.getMap().getShip("Black Pearl");
		
		playingDays = 50;
		defaultOwnedMoney = 50000;
		testPlayer1 = new Trader(playingDays, "Alice", defaultOwnedMoney, home, "port");
		testPlayer2 = new Trader(playingDays, "Jon", defaultOwnedMoney, home, "port");
		
		testShip1.setCaptain(testPlayer1);
		testPlayer1.setOwnedShip(testShip1);
		
		testShip2.setCaptain(testPlayer2);
		testPlayer2.setOwnedShip(testShip2);
		
		}

	@Test
	void testUpgradeCannons() {
		
		int addedCannons = 5;
		int cannonsBeforeUpgrade = testShip1.getCannons();
		String report = testPlayer1.upgradeCannons(addedCannons);
		
		// check if cannons upgraded
		assertEquals(addedCannons, testShip1.getCannons()-cannonsBeforeUpgrade);
		
		// check if cost subtracted
		int cost = addedCannons * testPlayer1.getCurrentIsland().getPort().getcannonCost();
		assertEquals(cost, defaultOwnedMoney-testPlayer1.getOwnedMoney());
				
		// check if right report returned
		assertEquals("You now have " + addedCannons + " more cannons equipped on your ship.", 
				      report);
		
		// check if logged into upgradeLogs
				String log = "Upgrade log:\n\n" + "Cannon Upgrade" + " at " 
				           + testPlayer1.getCurrentIsland().getName() 
						   + " for " + cost + " coins" + "\n";
				assertEquals(log, testShip1.getUpgradeLogString());
	}
	
	@Test
	void testAlreadyMaxUpgradeCannons() {
		
		int addedCannons = 5;
		testShip2.setCannons(testShip2.getMaxCannons());
		String report = testPlayer2.upgradeCannons(addedCannons);
		
		// check if cannons upgraded
		assertEquals(testShip2.getMaxCannons(), testShip2.getCannons());
		
		// check if cost subtracted
		int cost = 0;
		assertEquals(cost, defaultOwnedMoney-testPlayer2.getOwnedMoney());
				
		// check if right report returned
		assertEquals("You've got enough cannons on your ship. No space for any more!", 
				     report);
		
		// check if logged into upgradeLogs
	    assertEquals(0, testShip2.getUpgradeLogs().size());
	}
	
	@Test
	void testTooManyUpgradeCannons() {
		
		int addedCannons = 50;
		int cannonsBeforeUpgrade = testShip2.getCannons();
		String report = testPlayer2.upgradeCannons(addedCannons);
		
		// check if cannons upgraded
		assertEquals(cannonsBeforeUpgrade, testShip2.getCannons());
		
		// check if cost subtracted
		int cost = 0;
		assertEquals(cost, defaultOwnedMoney-testPlayer2.getOwnedMoney());
				
		// check if right report returned
		assertEquals("Captain, you can't equip so many cannons on your ship!\n" +
				     "You can add at most " + 
				     (testShip2.getMaxCannons() - testShip2.getCannons()) +
				     " more.", 
				     report);
		
		// check if logged into upgradeLogs
	    assertEquals(0, testShip2.getUpgradeLogs().size());
	}
	
	@Test
	void testFailedUpgradeCannonsForLackOfMoney() {
		
		int addedCannons = 10;
		int lessMoney = 1;
		testPlayer2.setOwnedMoney(lessMoney);
		int cannonsBeforeUpgrade = testShip2.getCannons();
		String report = testPlayer2.upgradeCannons(addedCannons);
		
		// check if cannons upgraded
		assertEquals(cannonsBeforeUpgrade, testShip2.getCannons());
		
		// check if cost subtracted
		int cost = 0;
		assertEquals(cost, lessMoney-testPlayer2.getOwnedMoney());
				
		// check if right report returned
		assertEquals("Sorry, you don't have enough money to complete this upgrade.", 
				     report);
		
		// check if logged into upgradeLogs
	    assertEquals(0, testShip2.getUpgradeLogs().size());
	}

}


