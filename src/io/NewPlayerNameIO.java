package io;

import map.World;
import trader.Trader;

/**
 * The NewPlayerNameIO is a {@link NewPlayerIO} sub-class to create 
 * a command line interface for setting the name of the new {@link Trader}.
 * <p>
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */


public class NewPlayerNameIO extends NewPlayerIO {

	/**
	 * This constructor sets the world attribute with a World object,
	 * creates and formats a string for the prompt attribute and adds all the 
	 * available command options for this interface to attribute commandsList.
	 * 
	 * @param newWorld a World object
	 */
	public NewPlayerNameIO(World newWorld) {
		super(newWorld);
		super.setPrompt("Please enter the name for the new trader:\n"
				     + "(The length must be between 3 and 15 characters\n"
				     + "and must not include numbers or special characters.)\n");
		super.addCommand("Cancel"); //0
	}
	
	/**
	 * This method processes a string name entered by users or a cancel command 
	 * input. It adds the name users input or the cancel command to attribute 
	 * commandArguments. This method also initializes a {@link NewPlayerTimeIO} 
	 * object after users input a valid string name.
	 * 
	 * @param playerInput a string of "0" or a string name
	 */
	@Override
	public void processPlayerInput(String playerInput) {
		
		if (playerInput.matches("0") && Integer.parseInt(playerInput) == 0) {
			
			super.addCommandArgument("cancel");
			setGettingName(false);
			
		} else {
			
			super.addCommandArgument(playerInput);
			setGettingName(false);
			
			setGettingPlayingTime(true);
			NewPlayerTimeIO newTime = new NewPlayerTimeIO(getWorld());
			newTime.readCommandArguments();
			
		}
	}

}
