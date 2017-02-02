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
 * This class represents the enemy which the player confronts randomly.
 * 
 * @author Diana Choi
 */
public class Enemy extends Character{
	
	/**
	 * This field represents whether the enemy drops an item upon death.
	 */
	private boolean itemDrop;
	
	/**
	 * This constructor uses the super constructor that takes in the custom location parameter
	 * so that it can be set to where the player is standing.
	 * @param chance determines which gun the enemy spawns with.
	 */
	public Enemy(double chance, int position){
		super(position);
		if(chance < 0.5){
			setGun(new Gun("Pistol"));
		}else if(chance < 0.85){
			setGun(new Gun("Rifle"));
		}else{
			setGun(new Gun("Shotgun"));
		}	
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.prog_assgmnt_2.Character#moveForward()
	 */
	@Override
	public void moveForward() {
		
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.prog_assgmnt_2.Character#retreat()
	 */
	@Override
	public boolean retreat() {
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.prog_assgmnt_2.Character#heal(int)
	 */
	@Override
	public void heal(int n) {
		n = 0;
	}
	
	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.prog_assgmnt_2.Character#isAlive()
	 */
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		if(this.getCurrentHealth() <= 0){
			itemDrop = true;
			super.die();
		}
		return super.isAlive();
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.prog_assgmnt_2.Character#changeLocation(int)
	 */
	@Override
	public void changeLocation(int space) {
		
	}

	/**
	 * This method changes the fact that the enemy drops an item in the even that
	 * the player escaped successfully, in which case, the player wouldn't receive an item.
	 */
	public void changeItemDrop(){
		itemDrop = false;
	}
	
	/**
	 * This method returns whether the enemy drops an item.
	 * @return whether an enemy drops an item or not.
	 */
	public boolean dropsItem(){
		return itemDrop;
	}

}
