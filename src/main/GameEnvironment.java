package main;
import java.util.*;
import trader.Trader;
import map.Map;

/**
 * <h1> SENG201 Project: Island Trader </h1>
 * <br>
 * @author Finn van Dorsser
 * @author Meng Zhang 
 */

public class GameEnvironment {
	
	private static Trader player;
	private static Map defaultMap;
	
	
   /**
   * This is the main method which creates a new player and starts the game.
   * @param args Unused.
   * @return Nothing.
   */
	
	public static void main(String[] args) {
		
		boolean gameOver = false;
		boolean constructionOver = false;
		Scanner playerCommands = new Scanner(System.in);
		
		System.out.println("Welcome to the wolrd of Island Trader");
		
		// constructing game
		while (!constructionOver) {
					
			System.out.println("Constructing game environment ...");
			//defaultMap = new Map();
			System.out.println("Map is ready ... ");
			//player = new Trader();
			System.out.println("Trader is ready ... ");
			constructionOver = true;
			System.out.println("===== All Set! =====");
			System.out.println("");
		}
		
		// playing game while !gameOver && 
		//                     this.player.getRemainingDays>0 &&
		// 					   this.player.getOwnedMoney >0
		while (!gameOver) {
			
			System.out.println("What's next?");
			System.out.println("1. Continue");
			System.out.println("2. Quit");
			System.out.println("Enter 1 or 2");
			
			int command =  playerCommands.nextInt();
			
			if (command == 1) {
				System.out.println("Let's go sailing!");
			} else if (command == 2) {
				System.out.println("Good bye");
				gameOver = true;
			} else {
				System.out.println("invalid input");
				continue;
			}
			
			System.out.println("OOPs");
		}
		
		playerCommands.close();

	}

}
