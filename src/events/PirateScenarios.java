package events;

/**
 * An enum representing the possible outcomes of a {@link PirateEvent}.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 *
 */
public enum PirateScenarios {
	/**
	 * a constant representing that the player's Ship defeated the pirates in battle
	 */
	FOUGHT_AND_WON, 
	
	/**
	 * a constant representing that the player's Ship was defeated in battle by the pirates
	 */
	FOUGHT_AND_LOST, 
	
	/**
	 * a constant representing that the player's Ship was fast enough to outrun the
	 * pirates
	 */
	FLED_AND_ESCAPED,
	
	/**
	 * a constant representing that the player's Ship failed to outrun the pirates
	 * and was defeated by them
	 */
	FLED_AND_LOST, 
	
	/**
	 * a constant representing that the player's Ship failed to outrun the pirates
	 * but managed to defeat them in battle anyway
	 */
	FLED_AND_WON
}
