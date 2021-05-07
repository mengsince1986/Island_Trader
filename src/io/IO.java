package io;
import java.util.*;
import trader.*;

public abstract class IO {
	
	/**
	 * read type: String
	 * return type: [String command, String argument]
	 */
	private Trader player;
	private ArrayList<String> commands;
	private ArrayList<String> updates;
	
	public IO(Trader player) {
		this.player = player;
		this.commands = new ArrayList<String>();
		this.updates = new ArrayList<String>();
	}
	
	public abstract ArrayList<String> read();
	
	public Trader getTrader() {
		return this.player;
	}
	
	public ArrayList<String> getCommands() {
		return this.commands;
	}
	
	public void addCommand(String command) {
		this.commands.add(command);
	}
	
	public String getCommandString() {
		String commandList = "=== Commands ===" + "\n";
		for (int i=0; i < this.commands.size(); i++) {
			commandList += i + ". " + this.commands.get(i) + "\n"; 
		}
		return commandList;
	}

	
	public ArrayList<String> getUpdates() {
		return this.updates;
	}
	
	public void addUpdate(String update) {
		this.updates.add(update);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
