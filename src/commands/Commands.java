package commands;
import java.util.*;
import trader.*;
import ships.*;

public class Commands {
	
	private Trader player;
	private Ship ship;
	
	public Commands(Trader player, Ship ship) {
		
	}

	public String processCommands(ArrayList<String> command) {
		String commandReport = "Nothing happens!";
		
		String key = command.get(0);
		String argument = command.get(1);
		
		switch(key) {
		case "sail": 
			processSailCommand(argument);
		}
		
		return commandReport;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
