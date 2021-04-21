package map;

import events.RandomEvent;
import java.util.ArrayList;

public class Route {
	
	private Island source;
	private Island destination;
	private int distance;
	private ArrayList<RandomEvent> events;
	private String dangerLevel;
	
	//remove source and destination; add setters for Island objects
	public Route(int distance, ArrayList<RandomEvent> events, String dangerLevel) {
		this.distance = distance;
		this.events = events;
		this.dangerLevel = dangerLevel;
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
	
	public ArrayList<RandomEvent> getEvents(){
		return events;
	}
	
	@Override
	public String toString() {
		return String.format("from %s to %s\n Distance: %s\n Danger: %s\n", source, destination, distance, dangerLevel);
	}

	public static void main(String[] args) {
	
	}

}
