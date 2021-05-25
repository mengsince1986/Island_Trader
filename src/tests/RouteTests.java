package tests;

import map.Route; import map.Island;
import events.*;
import ships.*;
import trader.Trader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;



class RouteTests {
	
	static Island island1 = new Island("Island1");
	static Island island2 = new Island("Island2");
	static Route route1 = new Route(10, "Looks dicey");
	static Route route2 = new Route(10, "Looks safe");
	static Ship normalShip = new Ship("Redcoasts", 15, 2, 1500, 8, 75, "normal");
	static Ship fastShip = new Ship("Black Pearl", 10, 2, 1500, 6, 70, "fast");
	static Ship slowShip = new Ship("Endeavour", 20, 2, 1000, 18, 90, "slow");
	static Trader testTrader = new Trader(15, "Test Trader", island1);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		route1.setSource(island1);
		route1.setDest(island2);
		route2.setSource(island2);
		route2.setDest(island1);
			
		PirateEvent route1Pirates = new PirateEvent(10, 10, 100);
		route1.addEvent(route1Pirates);
		WeatherEvent route1Weather = new WeatherEvent(10);
		route1.addEvent(route1Weather);
		RescueEvent route1Rescue = new RescueEvent(10, 100);
		route1.addEvent(route1Rescue);
		
		island1.addRoute(route1);
		island2.addRoute(route2);
		
		testTrader.setOwnedShip(normalShip);
		normalShip.setCaptain(testTrader);
		
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
	
	@RepeatedTest(100)
	void eventTriggerTest() {
		PirateEvent route1Pirates = (PirateEvent)route1.getEvents().get(0);
		WeatherEvent route1Weather = (WeatherEvent)route1.getEvents().get(1);
		route1Weather.setChanceLevel(0);
		
		assertNotEquals(route1Pirates.getTriggered(), route1Weather.getTriggered());
		
	}
	
	@RepeatedTest(100)
	void fightOutcomeTest() {
		normalShip.setCannons(0);
		
		PirateEvent route1Pirates = (PirateEvent)route1.getEvents().get(0);
		
		assertEquals(PirateScenarios.FOUGHT_AND_LOST, route1Pirates.getFightOutcome(normalShip, true));
	}
	
	@RepeatedTest(100)
	void normalShipPirateTest() {
		PirateEvent route1Pirates = (PirateEvent)route1.getEvents().get(0);
		
		normalShip.setCannons(0);
		
		PirateScenarios normalShipOutcome = route1Pirates.getChaseOutcome(normalShip);
		assertEquals(true, (normalShipOutcome == PirateScenarios.FLED_AND_ESCAPED |
				normalShipOutcome == PirateScenarios.FLED_AND_LOST));
		
	}
	
	@RepeatedTest(100)
	void fastShipPirateTest() {
		PirateEvent route1Pirates = (PirateEvent)route1.getEvents().get(0);
		
		fastShip.setCannons(0);
		
		PirateScenarios fastShipOutcome = route1Pirates.getChaseOutcome(fastShip);
		assertEquals(true, (fastShipOutcome == PirateScenarios.FLED_AND_ESCAPED |
				fastShipOutcome == PirateScenarios.FLED_AND_LOST));
	}
	
	@RepeatedTest(100)
	void slowShipPirateTest() {
		PirateEvent route1Pirates = (PirateEvent)route1.getEvents().get(0);

		slowShip.setCannons(0);

		PirateScenarios slowShipOutcome = route1Pirates.getChaseOutcome(slowShip);
		assertEquals(true, (slowShipOutcome == PirateScenarios.FLED_AND_ESCAPED |
				slowShipOutcome == PirateScenarios.FLED_AND_LOST));
	}
	
	@RepeatedTest(100)
	void weatherTest() {
		WeatherEvent route1Weather = (WeatherEvent)route1.getEvents().get(1);
		
		route1Weather.processImpact(testTrader);
		
		Ship testShip = testTrader.getOwndedShip();
		assertNotEquals(testShip.getDefaultDurability(), testShip.getDurability());
		
		route1Weather.setChanceLevel(0);
		testShip.setDurability(testShip.getDefaultDurability());
		
		if (route1Weather.getTriggered()) {
			route1Weather.processImpact(testTrader);
		}
		assertEquals(testShip.getDefaultDurability(), testShip.getDurability());
		
		
	}
	
	@RepeatedTest(100)
	void rescueTest() {
		RescueEvent route1Rescue = (RescueEvent)route1.getEvents().get(2);
		
		route1Rescue.processImpact(testTrader);
		
		assertNotEquals(testTrader.getStartingMoney(), testTrader.getOwnedMoney());
		
		route1Rescue.setChanceLevel(0);
		testTrader.setOwnedMoney(testTrader.getStartingMoney());
		
		if (route1Rescue.getTriggered()) {
			route1Rescue.processImpact(testTrader);
		}
		
		assertEquals(testTrader.getStartingMoney(), testTrader.getOwnedMoney());
	}
	
	@Test
	void noTimeTest() {
		normalShip.sailTo(island2);
		
		assertEquals(true, testTrader.noTimeToSail());
	}
}
