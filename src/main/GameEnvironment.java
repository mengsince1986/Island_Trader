package main;
import java.util.*;
import trader.Trader;
import map.Island;
import map.Map;
import map.WorldConstructor;
import ships.BalancedShip;
import ships.Ship;

/**
 * <h1> SENG201 Project: Island Trader </h1>
 * <br>
 * @author Finn van Dorsser
 * @author Meng Zhang 
 */

public class GameEnvironment {
	
	//private static Trader player;
	//private static Map map;
	
	
   /**
   * This is the main method which creates a new player and starts the game.
   * @param args Unused.
   * @return Nothing.
   */
	
	public static void main(String[] args) {
		
		// start a new Game
		//GameEnvironment newGame = new GameEnvironment();
		
		Map map;
		Trader player;
		Ship ship;
		boolean gameOver = false;
		boolean constructionOver = false;
		Scanner playerCommands = new Scanner(System.in);
		
		System.out.println("Welcome to the wolrd of Island Trader");
		
		// constructing game
		while (!constructionOver) {
			WorldConstructor newWorld = new WorldConstructor();
			map = newWorld.getMap();
			System.out.println("Constructing game environment ...");
			System.out.println("A new world is created ... ");
			
			String traderName = "Jon Snow";
			Island homeIsland = map.getIsland("Niawall Haven");
			player = new Trader(30, traderName, 10000, 
								homeIsland, homeIsland, "port");
			ship = new BalancedShip();
			ship.setCaptain(player);
			player.setOwnedShip(ship);
			System.out.println("A new Trader named " + player.getName() + " is created ... ");
			System.out.println("A new Ship named " + player.getOwndedShip().getName() + " is created ...");
			System.out.println("===== All Set! =====");
			System.out.println("");
			constructionOver = true;
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
