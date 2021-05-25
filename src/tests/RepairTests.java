package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import map.Island;
import map.WorldConstructor;
import ships.Ship;
import trader.Trader;

class RepairTests {

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
		defaultOwnedMoney = 20000;
		testPlayer1 = new Trader(playingDays, "Alice", defaultOwnedMoney, home, "port");
		testPlayer2 = new Trader(playingDays, "Jon", defaultOwnedMoney, home, "port");
		
		testShip1.setCaptain(testPlayer1);
		testPlayer1.setOwnedShip(testShip1);
		
		testShip2.setCaptain(testPlayer2);
		testPlayer2.setOwnedShip(testShip2);
		
	}
	
	@Test
	void testRepairShip() {
		
		int damage = 10;
		testShip1.setDurability(testShip1.getDefaultDurability() - damage);
		String report = testPlayer1.repairShip();
		
		// check if durability recovers
		assertEquals(testShip1.getDurability(), testShip1.getDefaultDurability());
		
		// check if cost subtracted
		int repairCostPerDamage = testPlayer1.getCurrentIsland().getPort().getRepairCost();
		int repairCost = damage * repairCostPerDamage; 
		assertEquals(testPlayer1.getOwnedMoney(), 
				     defaultOwnedMoney - repairCost);
		
		// check if right report returned
		assertEquals(report, "The damage has been fixed!\nYour ship is ready to sail again!");
		
		// check if logged into upgradeLogs
		String log = "Upgrade log:\n\n" + "Damage Repair" + " at " 
		           + testPlayer1.getCurrentIsland().getName() 
				   + " for " + repairCost + " coins" + "\n";
		assertEquals(log, testShip1.getUpgradeLogString());
	}
	
	@Test
	void testUnnecessaryRepairShip() {
		
		int damage = 0;
		testShip1.setDurability(testShip1.getDefaultDurability() - damage);
		String report = testPlayer1.repairShip();
		
		// check if durability recovers
		assertEquals(testShip1.getDurability(), testShip1.getDefaultDurability());
		
		// check if cost subtracted
		int repairCostPerDamage = testPlayer1.getCurrentIsland().getPort().getRepairCost();
		int repairCost = damage * repairCostPerDamage; 
		assertEquals(testPlayer1.getOwnedMoney(), 
				     defaultOwnedMoney - repairCost);
		
		// check if right report returned
		assertEquals(report, "Your ship is fine. There's nothing to repair.");
	}
	
	@Test
	void testFailedRepairShipforLackOfMoney() {
		
		int damage = 5;
		int lessMoney = 1;
		testPlayer2.setOwnedMoney(lessMoney);
		testShip2.setDurability(testShip2.getDefaultDurability() - damage);
		String report = testPlayer2.repairShip();
		
		// check if durability recovers
		assertTrue(testShip2.getDurability() != testShip2.getDefaultDurability());
		
		// check if cost subtracted
		assertEquals(testPlayer2.getOwnedMoney(), 
				     lessMoney);
		
		// check if right report returned
		assertEquals(report, "Oh no! You don't have enough money for the repair!\n" + 
				"You'll need to sell some items!");
	}

}
