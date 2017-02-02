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
 * This class represents the Gun object, which is the weapon the player uses in self-defense when faced
 * with enemies in random encounters. Each type of gun has a specific set of statistics and information
 * linked to it, and this is all instantiated within the constructor.
 * 
 * @author Diana Choi
 */
public class Gun {

	/**
	 * This field represents the name of the gun, and determines what stats are attributed to each instance
	 * of this object.
	 */
	private String type;
	
	/**
	 * This field represents the maximum value a randomized double can be if the gun is to deal damage. The
	 * higher this value is, the more likely the gun will deal damage, just like the accuracy of a real gun.
	 */
	private double accuracy;
	
	/**
	 * This field represents the current amount of ammo the gun is carrying. This value is initialized to equal
	 * the value of the maximimum ammo capacity of the gun.
	 */
	private int ammo;
	
	/**
	 * This field represents the maximum ammo capacity of the gun. This number cannot be changed once the object
	 * is instantiated.
	 */
	private int maxAmmo;
	
	/**
	 * This field represents the amount of damage the gun deals to the target if the randomly generated double
	 * falls within the range of 0 to the value of the gun's accuracy.
	 */
	private int damage;
	
	/**
	 * This default constructor initializes a gun object with the properties of a pistol. It calls the other
	 * constructor which takes in the name of the type of gun as a parameter.
	 */
	public Gun() {
		new Gun("Pistol");
	}
	
	/**
	 * This constructor accepts the parameter of the name of the type of gun the object is to be. Each type
	 * is tied to a specific set of information for the purposes of this project.
	 * @param type name of the type of gun.
	 */
	public Gun(String type){
		if(type.equals("Pistol")){
			this.type = type;
			accuracy = 0.75;
			maxAmmo = 15;
			ammo = maxAmmo;
			damage = 1;
		}else if(type.equals("Rifle")){
			this.type = type;
			accuracy = 0.65;
			maxAmmo = 10;
			ammo = maxAmmo;
			damage = 2;
		}else if(type.equals("Shotgun")){
			this.type = type;
			accuracy = 0.40;
			maxAmmo = 5;
			ammo = maxAmmo;
			damage = 5;
		}
	}
	
	/**
	 * This is a getter method for the type of gun the object is, or in other words, the name of the gun.
	 * @return name of type of gun.
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * This is a getter method for how much damage a gun deals if the shot was accurate.
	 * @return amount of damage gun deals.
	 */
	public int getDamage(){
		return damage;
	}
	
	/**
	 * This is a getter method for the likelihood of the gun dealing damage by hitting its target.
	 * @return the accuracy of the gun.
	 */
	public double getAccuracy(){
		return accuracy;
	}
	
	/**
	 * This is a getter method for how much ammo the gun has left in its magazine.
	 * @return how much ammo the gun still has.
	 */
	public int getAmmoLeft(){
		return ammo;
	}
	
	/**
	 * This is a getter method for the maximum amount of ammo the gun can carry at a time.
	 * @return maximum capacity for ammo of gun.
	 */
	public int getMaxAmmo(){
		return maxAmmo;
	}
	
	/**
	 * This is a method that represents a shot being fired. Regardless of whether the gun hit its mark
	 * or not, a bullet is lost in the action of the shot being fired.
	 */
	public void loseAmmo(){
		ammo--;
	}
	
	/**
	 * This is a method for restoring the current amount of ammo left in the gun to maximum capacity once
	 * more, in the even that an ammo magazine item drops from a defeated enemy.
	 * @param restoredAmmo amount of ammo to be reloaded back into gun.
	 */
	public void reloadAmmo(int restoredAmmo){
		ammo += restoredAmmo;
	}

	/**
	 * This method is a quick way to display all relevant information about the gun.
	 * @return information about gun, specifically name, accuracy, damage, and maximum ammo capacity.
	 */
	public String printInfo(){
		return type + "\nAccuracy: " + accuracy + "\nDamage: " + damage + "\nAmmo Capacity: " + maxAmmo;
	}
}