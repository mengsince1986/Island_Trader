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
import map.Port;
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
	static Port testPort;
	static Island testIsland1;
	static Island testIsland2;
	static Route testRoute1;
	static Route testRoute2;
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
		testPort = new Port(10, 10);
		testIsland1 = new Island("Island1", testStore, testPort);
		testIsland2 = new Island("Island2", testStore, testPort);
		testRoute1 = new Route(10, "Looks safe");
		testRoute2 = new Route(10, "Looks safe");
		testRoute1.setSource(testIsland1);
		testRoute1.setDest(testIsland2);
		testRoute2.setSource(testIsland2);
		testRoute2.setDest(testIsland1);
		testIsland1.addRoute(testRoute1);
		testIsland2.addRoute(testRoute2);
		testTrader = new Trader(20, "Test Trader", 100000, testIsland1, "store");
		tradingTestShip = new Ship("Redcoasts", 15, 2, 5000, 8, 75, "normal");
		testTrader.setOwnedShip(tradingTestShip);
		tradingTestShip.setCaptain(testTrader);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void buyTest() {
		String buyReport = testTrader.buy(testIsland1, "Gunpowder", 5000);
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
		testTrader.buy(testIsland1, "Gunpowder", 5000);
		assertEquals(0, testStore.getToSell().size());
		assertEquals(5000, testStore.getItem("Gunpowder", "toBuy").getQuantity());
		String sellReport = testTrader.sell(testIsland1, "Gunpowder", 5000);
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
		testTrader.buy(testIsland1, "Gunpowder", 5000);
		String sellReport = testTrader.sell(testIsland1, "Gunpowder", 1000);
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
		testTrader = new Trader(10, "Test Trader", 0, testIsland1, "store");
		testTrader.setOwnedShip(tradingTestShip);
		
		String buyReport = testTrader.buy(testIsland1, "Gunpowder", 1);
		assertEquals(
				"You don't have enough funds to make that purchase!\n" +
				"Redirecting you to storefront...",
				buyReport);
	}
	
	@Test
	void buyTooManyTest() {
		String buyReport = testTrader.buy(testIsland1, "Gunpowder", 5001);
		assertEquals(
				"The store can't sell you that many!\n" +
				"Redirecting you to storefront...",
				buyReport);
	}
	
	@Test
	void notEnoughCapacityTest() {
		tradingTestShip = new Ship("Redcoasts", 15, 2, 0, 8, 75, "normal");
		testTrader.setOwnedShip(tradingTestShip);
		
		String buyReport = testTrader.buy(testIsland1, "Gunpowder", 1);
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
		testIsland1 = new Island("Island1", testStore, null);
		testTrader = new Trader(10, "Test Trader", 50000, testIsland1, "store");
		testTrader.setOwnedShip(tradingTestShip);
		
		testTrader.buy(testIsland1, "Gunpowder", 5000);
		String sellReport = testTrader.sell(testIsland1, "Gunpowder", 1);
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
		testIsland1 = new Island("Island1", testStore, null);
		testTrader = new Trader(10, "Test Trader", 50000, testIsland1, "store");
		testTrader.setOwnedShip(tradingTestShip);
		
		testTrader.buy(testIsland1, "Gunpowder", 5000);
		String sellReport = testTrader.sell(testIsland1, "Gunpowder", 5001);
		assertEquals(
				"You don't own that quantity of the item! Try again.\n" +
				"Redirecting you to storefront...",
				sellReport);
	}
	
	@Test
	void noMoneyToSailTest() {
		testTrader.buy(testIsland1, "Gunpowder", 15);
		tradingTestShip.sailTo(testIsland2);
		testTrader.setOwnedMoney(0);
		
		assertEquals(false, tradingTestShip.readyToSail(testIsland1));
		assertEquals(false, testTrader.noMoneyToSail());
		
		testTrader.sell(testIsland2, "Gunpowder", 15);
		
		assertEquals(true, tradingTestShip.readyToSail(testIsland1));
		assertEquals(false, testTrader.noMoneyToSail());
		
		tradingTestShip.sailTo(testIsland1);
		assertEquals(true, testTrader.noMoneyToSail());
	}
}