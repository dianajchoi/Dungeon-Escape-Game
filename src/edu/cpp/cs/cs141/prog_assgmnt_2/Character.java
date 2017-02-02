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
 * This class is the general template for the character class, which includes the player and the enemy, or
 * enemies. This class includes general methods such as shooting, which both player and enemy can perform, and
 * general stats such as health.
 * 
 * @author Diana Choi
 */
public class Character {

	/**
	 * This field represents the current amount of health the player has, and is to be
	 * constantly updated as the object is interacted with. This field is initialized to
	 * equal maximum health.
	 */
	private int health;
	
	/**
	 * This field represents the maximum amount of health a character can have at any
	 * given moment. This value cannot change once the object has been instantiated.
	 */
	private final int maxHealth;
	
	/**
	 * This field represents the gun the character owns, and the one that is linked
	 * to this character. This field is referenced when the character is shooting at
	 * a target. This field is randomized if the character is an enemy, and chosen if
	 * the character is a player.
	 */
	private Gun gun;
	
	/**
	 * This field represents the current index of the character along the dungeon map.
	 */
	private int location;
	
	/**
	 * This field represents if the character is alive or not. The importance of the
	 * value of this field depends on whether the character is a player or an enemy.
	 */
	private boolean alive = true;
	
	/**
	 * This field represents whether the character has sustained damage in the previous turn
	 * of combat.
	 */
	private boolean lostHealth;
	
	/**
	 * This default constructor is the one used by the player class, and represents the starting stats
	 * of a player object.
	 */
	public Character() {
		maxHealth = 20;
		health = maxHealth;
		location = 0;
	}
	
	/**
	 * This constructor represents the one used by the enemy class, in which the position is not
	 * initially 0, at the starting location as the player is, but rather a customized input, usually
	 * the current location of the player.
	 * @param position
	 */
	public Character(int position){
		maxHealth = 5;
		health = maxHealth;
		location = position;
	}
	
	/**
	 * This method is for when the character is in combat and wishes to use the gun to damage
	 * the opponent. It is determined whether the target character loses health depending on the
	 * used gun's accuracy, and the gun loses 1 ammo as a consequence of this method being used.
	 * @param c the target character to be damaged if the gunshot hits.
	 */
	public void shoot(Character c){
		double chance = Math.random();
		if(chance < gun.getAccuracy() && gun.getAmmoLeft() > 0){
			c.loseHealth(gun.getDamage());
		}
		gun.loseAmmo();
	}
	
	/**
	 * This method is for if the character received any damage as a result of a gunshot. This method also
	 * indicates that the character has been hurt, which helps the User Interface print out an appropriate
	 * response.
	 * @param damage the amount of health the character loses because of how much damage the gun inflicted.
	 */
	public void loseHealth(int damage){
		health -= damage;
		lostHealth = true;
	}
	
	/**
	 * This method is for moving the character one step forward.
	 */
	public void moveForward(){
		location++;
	}
	
	/**
	 * This method determines whether the character successfully retreated or didn't. In this superclass,
	 * the default return value is true, but this method is overridden in the sub-class Player.
	 * @return
	 */
	public boolean retreat(){
		return true;
	}
	
	/**
	 * This method is for when the player picks up a health pack as a result of defeating an enemy.
	 * @param restoredHealth the amount of health restored to the player.
	 */
	public void heal(int restoredHealth){
		health += restoredHealth;
		if(health > maxHealth){
			health = maxHealth;
		}
	}
	
	/**
	 * This method is for marking a character as dead. For a player, this means the game is over,
	 * and for an enemy, this method means one of two things: either the player defeated the enemy,
	 * or the player successfully retreated.
	 */
	public void die(){
		alive = false;
	}
	
	/**
	 * This method is for when the character changes its position, for example, if the character is
	 * moving backwards one space.
	 * @param space the location to which the character moves.
	 */
	public void changeLocation(int space){
		location = space;
	}

	/**
	 * This method is for linking a gun to the character.
	 * @param choice the gun to be used by the character.
	 */
	public void setGun(Gun choice){
		gun = choice;
	}
	
	/**
	 * This method is a getter method for accessing where the character is in the map of the dungeon.
	 * @return location of player.
	 */
	public int getLocation(){
		return location;
	}
	
	/**
	 * This method is a getter method for the character's current health. This method also prevents
	 * any negative values from being returned.
	 * @return the character's current health.
	 */
	public int getCurrentHealth(){
		if(health < 0){
			health = 0;
		}
		return health;
	}
	
	/**
	 * This method is a getter method for whether the character is alive or not.
	 * @return whether the character is alive.
	 */
	public boolean isAlive(){
		return alive;
	}
	
	/**
	 * This method is a getter method for which gun the character is linked to.
	 * @return the gun the character has equipped.
	 */
	public Gun getGun(){
		return gun;
	}
	
	/**
	 * This method is a getter method for whether the character has sustained damage.
	 * @return if the character was hurt in the last turn of combat.
	 */
	public boolean hasLostHealth(){
		return lostHealth;
	}
	
	/**
	 * This method resets whether the character was hurt for the new turn of combat.
	 */
	public void resetHurt(){
		lostHealth = false;
	}
}
