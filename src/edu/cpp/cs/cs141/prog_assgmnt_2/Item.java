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
 * This class represents the items that could possibly drop from the random encounters, if the player survives.
 * The fields include the name and the amount restored to either health or ammo, and the methods include the
 * immediate effects of the item depending on its type.
 * 
 * @author Diana Choi
 */
public class Item {

	/**
	 * This field holds the name of the item, and is used to determine what kind of effect it will have on
	 * the object it is used on.
	 */
	private String name;
	
	/**
	 * This field holds the amount the item restores to the object it is used on.
	 */
	private int amount;
	
	/**
	 * This default constructor creates a Health Pack, in which the amount restored is 5 health points (HP).
	 */
	public Item() {
		name = "Health Pack";
		amount = 5;
	}
	
	/**
	 * This constructor requires the parameter of a gun because it restores ammo to a gun object, and the amount
	 * it restores to the gun object is dependent on the gun it is to be used on.
	 * @param g the gun the ammo mag is supposed to reload.
	 */
	public Item(Gun g){
		name = "Ammo Mag";
		amount = g.getMaxAmmo() - g.getAmmoLeft();
	}

	/**
	 * This method is specifically for the health packs, which restores a portion of the player's health.
	 * @param player the player object to be healed by the value of amount.
	 */
	public void use(Character player){
		player.heal(amount);
	}
	
	/**
	 * This method is specifically for the ammo magazines, which fully restores the gun's ammo to maximum
	 * capacity.
	 * @param g the gun object to be restored to full ammo capacity.
	 */
	public void use(Gun g){
		g.reloadAmmo(amount);
	}
}
