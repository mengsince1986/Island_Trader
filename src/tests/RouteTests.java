package tests;

import map.Route; import map.Island;
import events.*;
import ships.*;

import java.util.ArrayList;

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
	static Ship normalShip = new BalancedShip();
	static Ship fastShip = new FastShip();
	static Ship slowShip = new BattleShip();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		route1.setSource(island1);
		route1.setDest(island2);
			
		PirateEvent route1Pirates = new PirateEvent(10, 10, 100);
		route1.addEvent(route1Pirates);
		WeatherEvent route1Weather = new WeatherEvent(0);
		route1.addEvent(route1Weather);
		RescueEvent route1Rescue = new RescueEvent(1, 100);
		route1.addEvent(route1Rescue);
		
		island1.addRoute(route1);
		
		
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
	void toStringTest() {
		assertEquals("from Island1 to Island2\n  Danger level: Looks dicey\n", route1.toString());
		
	}
	
	@RepeatedTest(1000)
	void eventTriggerTest() {
		PirateEvent route1Pirates = (PirateEvent)route1.getEvents().get(0);
		WeatherEvent route1Weather = (WeatherEvent)route1.getEvents().get(1);
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
	
	
	
	
	
	
	
	
	
	
	
	

}
