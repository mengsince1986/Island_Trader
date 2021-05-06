package io;
import java.util.*;

public abstract class IO {
	
	/**
	 * read type: String
	 * return: (String command, String argument)
	 */
	
	private ArrayList<String> updates;

	public abstract ArrayList<String> read();
	
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
