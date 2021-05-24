package io;
import map.World;
import trader.Trader;

/**
 * The NewPlayerTimeIO is a {@link NewPlayerIO} sub-class to create 
 * a command line interface for setting the playing days of the new 
 * {@link Trader}.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class NewPlayerTimeIO extends NewPlayerIO {

	/**
	 * This constructor sets the world attribute with a World object,
	 * creates and formats a string for the prompt attribute and adds all the 
	 * available command options for this interface to attribute commandsList.
	 * 
	 * @param newWorld a World object
	 */
	public NewPlayerTimeIO(World newWorld) {
		
		super(newWorld);
		super.setPrompt("Please enter the days you want to play for:\n"
				     + "The number should be between 10 to 100.\n");
		super.addCommand("Cancel");
	}
	
	/**
	 * This method processes a string integer entered by users or a cancel command 
	 * input. It adds the string integer users input or the cancel command to attribute 
	 * commandArguments. This method also initializes a {@link NewPlayerHomeIO} 
	 * object after users input a valid string integer.
	 * 
	 * @param playerInput a string of "0" or a string integer
	 */
	@Override
	public void processPlayerInput(String playerInput) {
		
		if (playerInput.matches("0") && Integer.parseInt(playerInput) == 0) {
			
			super.addCommandArgument("cancel");
			setGettingPlayingTime(false);
			
		} else {
			
			super.addCommandArgument(playerInput);
			setGettingPlayingTime(false);
			
			NewPlayerHomeIO newHome = new NewPlayerHomeIO(getWorld());
			newHome.readCommandArguments();
		}
		
	}

}
