package map;

import java.util.ArrayList;

public class Route {
	
	private String source;
	private String destination;
	private int distance;
	private ArrayList<RandomEvent> events;
	private String dangerLevel;
	
	//remove source and destination; add setters for Island objects
	public Route(String source, String destination, int distance, ArrayList<RandomEvent> events, String dangerLevel) {
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		this.events = events;
		this.dangerLevel = dangerLevel;
	}
	
	public String getSource() {
		return source;
	}
	
	public String getDest() {
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
