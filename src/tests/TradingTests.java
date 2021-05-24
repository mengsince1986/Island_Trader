package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import events.PirateEvent;
import events.PirateScenarios;
import events.RescueEvent;
import events.WeatherEvent;
import items.Item;
import map.Island;
import map.Route;
import map.Store;
import ships.Ship;
import trader.Trader;

class TradingTests {
	
	static Item testGunpowderLow;
	static Item testGunpowderHigh;
	static Item[] toSell;
	static Item[] toBuy;
	static Store testStore;
	static Island testIsland;
	static Ship tradingTestShip;
	static Trader testTrader;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testGunpowderLow = new Item("Gunpowder", 5000, 10);
		testGunpowderHigh = new Item("Gunpowder", 5000, 20);
		toSell = new Item[]{testGunpowderLow};
		toBuy = new Item[]{testGunpowderHigh};
		testStore = new Store(toSell, toBuy);
		testIsland = new Island("Island1", testStore, null);
		testTrader = new Trader(10, "Test Trader", 100000, testIsland, "store");
		tradingTestShip = new Ship("Redcoasts", 15, 2, 5000, 8, 75, "normal");
		testTrader.setOwnedShip(tradingTestShip);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void buyTest() {
		String buyReport = testTrader.buy(testIsland, "Gunpowder", 5000);
		assertEquals( 
				"Success!\n" +
				"Return to port to view your trading logs\n" +
				"Most recent: " +
				"Bought 5000 Gunpowder at Island1 for 50000 coins\n" +
				"\nRedirecting you to storefront...",
				buyReport);
		assertEquals(
				"Items for sale:\n"
				+ "Nothing for sale at this store!\n",
				testStore.forSale());
	}

	@Test
	void sellAllTest() {
		testTrader.buy(testIsland, "Gunpowder", 5000);
		assertEquals(0, testStore.getToSell().size());
		assertEquals(5000, testStore.getItem("Gunpowder", "toBuy").getQuantity());
		String sellReport = testTrader.sell(testIsland, "Gunpowder", 5000);
		assertEquals(
				"Success!\n" +
				"Return to port to view your trading logs\n" +
				"Most recent: " +
				"Sold 5000 Gunpowder at Island1 for 100000 coins\n" +
				"\nRedirecting you to storefront...",
				sellReport);
		assertEquals(
				"Looking to buy:\n" +
				"This store is not looking to buy anything!\n",
				testStore.forPurchase());
		assertEquals(
				"Items you can sell:\n" +
				"\nIt looks like you don't own anything this store is looking to buy!\n",
				testStore.getSellablePlayerItemsString(tradingTestShip));
	}
	
	@Test
	void sellSomeTest() {
		testTrader.buy(testIsland, "Gunpowder", 5000);
		String sellReport = testTrader.sell(testIsland, "Gunpowder", 1000);
		assertEquals(
				"Success!\n" +
				"Return to port to view your trading logs\n" +
				"Most recent: " +
				"Sold 1000 Gunpowder at Island1 for 20000 coins\n" +
				"\nRedirecting you to storefront...",
				sellReport);
		assertEquals(
				"Looking to buy:\n" +
				"\nItem 0: Gunpowder\n" +
				"Quantity: 4000\n" +
				"Price per unit: 20\n",
				testStore.forPurchase());
		assertEquals(
				"Items you can sell:\n" +
				"\nItem 0: Gunpowder\n" +
				"Quantity: 4000\n" +
				"Price per unit: 20\n",
				testStore.getSellablePlayerItemsString(tradingTestShip));
	}

	@Test
	void notEnoughMoneyTest() {
		testTrader = new Trader(10, "Test Trader", 0, testIsland, "store");
		testTrader.setOwnedShip(tradingTestShip);
		
		String buyReport = testTrader.buy(testIsland, "Gunpowder", 1);
		assertEquals(
				"You don't have enough funds to make that purchase!\n" +
				"Redirecting you to storefront...",
				buyReport);
	}
	
	@Test
	void buyTooManyTest() {
		String buyReport = testTrader.buy(testIsland, "Gunpowder", 5001);
		assertEquals(
				"The store can't sell you that many!\n" +
				"Redirecting you to storefront...",
				buyReport);
	}
	
	@Test
	void notEnoughCapacityTest() {
		tradingTestShip = new Ship("Redcoasts", 15, 2, 0, 8, 75, "normal");
		testTrader.setOwnedShip(tradingTestShip);
		
		String buyReport = testTrader.buy(testIsland, "Gunpowder", 1);
		assertEquals(
				"Your ship doesn't have enough remaining cargo capacity!\n" +
				"Redirecting you to storefront...",
				buyReport);
	}
	
	@Test
	void sellMoreThanStoreTest() {
		testGunpowderLow = new Item("Gunpowder", 5000, 10);
		testGunpowderHigh = new Item("Gunpowder", 0, 20);
		toSell = new Item[]{testGunpowderLow};
		toBuy = new Item[]{testGunpowderHigh};
		testStore = new Store(toSell, toBuy);
		testIsland = new Island("Island1", testStore, null);
		testTrader = new Trader(10, "Test Trader", 50000, testIsland, "store");
		testTrader.setOwnedShip(tradingTestShip);
		
		testTrader.buy(testIsland, "Gunpowder", 5000);
		String sellReport = testTrader.sell(testIsland, "Gunpowder", 1);
		assertEquals(
				"The store won't buy that many! Try again.\n" +
				"Redirecting you to storefront...",
				sellReport);
	}
	
	@Test
	void sellMoreThanCargoTest() {
		testGunpowderLow = new Item("Gunpowder", 5000, 10);
		testGunpowderHigh = new Item("Gunpowder", 10000, 20);
		toSell = new Item[]{testGunpowderLow};
		toBuy = new Item[]{testGunpowderHigh};
		testStore = new Store(toSell, toBuy);
		testIsland = new Island("Island1", testStore, null);
		testTrader = new Trader(10, "Test Trader", 50000, testIsland, "store");
		testTrader.setOwnedShip(tradingTestShip);
		
		testTrader.buy(testIsland, "Gunpowder", 5000);
		String sellReport = testTrader.sell(testIsland, "Gunpowder", 5001);
		assertEquals(
				"You don't own that quantity of the item! Try again.\n" +
				"Redirecting you to storefront...",
				sellReport);
		
	}
}