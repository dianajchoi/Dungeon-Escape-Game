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
 * This class represents the player which the user controls.
 * @author Diana Choi
 */
public class Player extends Character{

	/**
	 * This field represents the chance the player has to escape.
	 */
	private static final double escapeChance = 0.10;
	
	/**
	 * This constructor uses the default character constructor to create a basic player object.
	 */
	public Player() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.prog_assgmnt_2.Character#isAlive()
	 */
	@Override
	public boolean isAlive() {
		if(this.getCurrentHealth() <= 0){
			super.die();
		}
		return super.isAlive();
	}

	/**
	 * This method is to determine whether the player successfully escaped or not.
	 * @return whether the character escaped or not.
	 */
	public boolean retreat(){
		double chance = Math.random();
		if(chance < escapeChance){
			super.retreat();
		}
		return false;
	}

}
