package commands;
import java.util.ArrayList;
import trader.*;
import ships.*;
import map.*;

public class Commands {
	
	// have been replaced by CommandHandler
	
	private Map map;
	private Trader player;
	private Ship ship;
	
	public Commands(Map map, Trader player, Ship ship) {
		this.map = map;
		this.player = player;
		this.ship = ship;
	}

	public String processCommands(ArrayList<String> command) {
		
		String report = new String();
		String key = command.get(0);
		String argument = command.get(1);
		
		if (key != "cancel" && argument != "cancel") { //move into switch block
			switch(key) {
			case "sail": 
				report = processSailCommand(argument);
				break;
			
			} 
		} else {
				report = "Cancelled!";
			}

		return report;	
	}
	
	public String processSailCommand(String destination) {
		Island island = this.map.getIsland(destination);
		ArrayList<String> reportList = this.ship.sailTo(island);
		String report = "Sailing ... ...\n";
		
		for (String event : reportList) {
			report += event;
		}
		
		return report;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
