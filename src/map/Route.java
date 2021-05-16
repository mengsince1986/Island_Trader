package map;

import events.RandomEvent;
import ships.Ship;

import java.util.ArrayList;

public class Route {
	
	private Island source;
	private Island destination;
	private int distance;
	private ArrayList<RandomEvent> events = new ArrayList<>();
	private String dangerLevel;
	
	//remove source and destination; add setters for Island objects
	public Route(int distance, String dangerLevel) {
		this.distance = distance;
		this.dangerLevel = dangerLevel;
	}
	
	public void addEvent(RandomEvent event) {
		events.add(event);
	}
	
	public void setSource(Island source) {
		this.source = source;
	}
	
	public void setDest(Island destination) {
		this.destination = destination;
	}
	
	public Island getSource() {
		return source;
	}
	
	public Island getDest() {
		return destination;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public ArrayList<RandomEvent> getEvents(){
		return events;
	}
	
	public int getSailingTime(Ship ship) {
		return Integer.max(1, this.distance - ship.getShipSailingModifier());
	}
	@Override
	public String toString() {
		return String.format("from %s to %s\n  Danger level: %s\n", source.getName(), destination.getName(), dangerLevel);
	}

	public static void main(String[] args) {
	
	}

}
