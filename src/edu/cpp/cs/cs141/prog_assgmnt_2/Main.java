/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #2
 *
 * Create a dungeon escape game with a combat mini-game that is triggered if a random encounter occurs.
 * Player is 10 steps away from the exit and is armed with a gun. Enemies are also equipped with guns
 * and can damage you during the turn-based mini-game. Items drop if player survives the encounter.
 *
 * Diana Choi
 * 
 */
package edu.cpp.cs.cs141.prog_assgmnt_2;

/**
 * This class represents the main body of the game, in which the user interface and game engine
 * are combined and bring about the basic structure of the program, as well as where the program
 * is launched from.
 * 
 * @author Diana Choi
 */
public class Main {

	/**
	 * This field represents the instantiated game engine to be used in the instantiated user interface.
	 * The game engine keeps track of the current state of the game and is required for the user interface
	 * to interact with user inputs in response to current values.
	 */
	private static GameEngine ge = new GameEngine();
	
	/**
	 * This field represents the user interface that is instantiated for this instance of the game.
	 * It takes in user input and combines it with data extracted from the game engine in order to
	 * output appropriate responses.
	 */
	private static UI ui;
	
	/**
	 * The constructor doesn't take in any values since the User Interface constructor already requires
	 * a game engine to be passed in as a parameter. This constructor simply links the two so that the
	 * methods called from the User Interface class can interact with data called from the game engine.
	 */
	public Main() {
		ui = new UI(ge);
	}

	/**
	 * This method calls the basic sequence of methods from the User Interface class while constantly
	 * checking if the game is over. In that case, an end-message is printed, where the appropriate
	 * message of victory or loss is given, depending on how the game was declared over.
	 */
	public static void main(String[] args) {
		ui.welcomeMessage();
		while(!ge.isGameOver()){
			ui.inGame();
			ge.checkGameOver();
		}
		ui.endMessage();
	}

}
