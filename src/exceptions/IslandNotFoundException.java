package exceptions;

import java.util.NoSuchElementException;

public class IslandNotFoundException extends NoSuchElementException {

	public IslandNotFoundException() {
	}
	
	public IslandNotFoundException(String message) {
		super(message);
	}


}