package map;

import events.RandomEvent;
import ships.Ship;

import java.util.ArrayList;

/**
 * A class to represent the routes between the game's {@link Island}s. These may
 * contain random events that either help or harm the player.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */
public class Route {
	
	/**
	 * the {@link Island} from which the Route originates.
	 */
	private Island source;
	
	/**
	 * the {@link Island} at which the Route terminates.
	 */
	private Island destination;
	
	/**
	 * the base distance (measured in days) it takes to sail the Route.
	 */
	private int distance;
	
	/**
	 * a list to contain the {@link RandomEvent}s that may befall the player
	 * while sailing the Route.
	 */
	private ArrayList<RandomEvent> events = new ArrayList<>();
	
	/**
	 * a String to be displayed to the player which contains a vague warning
	 * of the severest event that could happen on the Route.
	 */
	private String dangerLevel;
	
	/**
	 * initialises a new Route object to have the given sailing {@link distance} and
	 * {@link dangerLevel} String.
	 * @param distance the base sailing time for the Route
	 * @param dangerLevel a String to warn players of the Route's dangers
	 */
	public Route(int distance, String dangerLevel) {
		this.distance = distance;
		this.dangerLevel = dangerLevel;
	}
	
	/**
	 * adds a {@link RandomEvent} to the Route's {@link events} list.
	 * @param event the RandomEvent to be added
	 */
	public void addEvent(RandomEvent event) {
		events.add(event);
	}
	
	/**
	 * sets the Route's {@link source} attribute to the given {@link Island}.
	 * @param source the Island of the Route's origin
	 */
	public void setSource(Island source) {
		this.source = source;
	}
	
	/**
	 * sets the Route's {@link destination} attribute to the given {@link Island}.
	 * @param destination the Island of the Route's end point
	 */
	public void setDest(Island destination) {
		this.destination = destination;
	}
	
	/**
	 * @return the Island of the Route's origin
	 */
	public Island getSource() {
		return source;
	}
	
	/**
	 * @return the Island of the Route's end point
	 */
	public Island getDest() {
		return destination;
	}
	
	/**
	 * @return the base number of days to sail the Route
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * @return the ArrayList of RandomEvents that can occur on the route
	 */
	public ArrayList<RandomEvent> getEvents(){
		return events;
	}
	
	/**
	 * takes the a {@link Ship} and returns the calculated time to sail the route
	 * after the ship's speed modifier (@link shipSailingModifier} has been applied.
	 * @param ship the Ship to sail the Route
	 * @return the actual days for the Ship to sail the Route
	 */
	public int getSailingTime(Ship ship) {
		return Integer.max(1, this.distance - ship.getShipSailingModifier());
	}
	
	/**
	 * @return a String representation of the Route for display to the player
	 */
	public String toString() {
		return String.format("from %s to %s\nDescription: %s\n", source.getName(), destination.getName(), dangerLevel);
	}
}
