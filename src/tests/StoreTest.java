package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameClasses.Item;
import gameClasses.Store;

class StoreTest {
	
	Item[] toSell = new Item[0];
	Item[] toBuy = new Item[0];
	Store testStore = new Store(toSell, toBuy);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testForSale() {
		assertEquals(testStore.forSale(), "Items for sale:\n"
				+ "Nothing for sale at this store!\n");
	}

	@Test
	void testForPurchase() {
		assertEquals(testStore.forPurchase(), "Looking to buy:\n"
				+ "This store is not looking to buy anything!\n"); // TODO
	}

}
