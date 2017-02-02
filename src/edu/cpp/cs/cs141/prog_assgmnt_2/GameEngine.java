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
 * This class represents the logic behind the game as well as a constantly updated
 * catalog of all relevant information, such as player states and information, enemy
 * information, location, and others.
 * 
 * @author Diana Choi
 */
public class GameEngine {

	/**
	 * This field represents the hallway, that is the dungeon. In the constructor,
	 * the length is initialized as 11 because there are 10 steps between the player
	 * and the exit, and index 0 marks the beginning position of the player.
	 */
	private static int[] map;
	
	/**
	 * This field represents the Player object that the user controls through
	 * inputs.
	 */
	private static Player p1;
	
	/**
	 * This field represents the Enemy object that acts as the antagonist
	 * against the player, and automatically attacks during the combat mini-game.
	 */
	private static Enemy enemy;
	
	/**
	 * This field represents whether the enemy was defeated in battle (true value) or
	 * if the enemy was evaded through retreat (false value). This value changes when
	 * the enemy is marked as dead, but is initialized as true.
	 */
	private Item droppedItem;
	
	/**
	 * This field represents the current state of the game, whether the player is alive
	 * and/or if the player has reached the end of the hallway/dungeon. It is initialized
	 * as false because when the game engine is created, it is assumed that the game has
	 * not yet begun or is about to begin.
	 */
	private boolean gameOver = false;
	
	/**
	 * This field represents all the weapon choices a player can choose from. All three
	 * types of guns are created and able to be linked to the player as soon as the user
	 * inputs his/her choice.
	 */
	private Gun[] weapons;
	
	/**
	 * This field represents whether the game is in the combat mini-game phase or not.
	 * If this value is true, then different questions will be asked of the user than if
	 * it is false. This field is initialized as false because the game begins with the
	 * player trying to step forward, and is given the value true only if an enemy object
	 * is created by the random encounter.
	 */
	private boolean inCombat = false;
	
	/**
	 * This field represents whose turn it is in the combat mini-game. If this field is
	 * a positive 1, then it is the player's turn, and if it is negative 1, it is the
	 * enemy's turn.
	 */
	private int turn;
	
	/**
	 * This constructor initializes the player object, the map length, the turn value, and
	 * the gun objects in the weapons array. The constructor doesn't take in any parameters
	 * since all its fields have a default value based on the initial state of the game.
	 */
	public GameEngine() {
		p1 = new Player();
		map = new int[11];
		turn = 1;
		updateMap();
		weapons = new Gun[3];
		weapons[0] = new Gun("Pistol");
		weapons[1] = new Gun("Rifle");
		weapons[2] = new Gun("Shotgun");
	}
	
	/**
	 * This method creates a new enemy object the first time a random encounter occurs.
	 * If it creates a new enemy object because of a second random encounter, the enemy
	 * field is given the value of a new enemy object, so that the player is not facing
	 * off an already-dead foe. This method determines what gun the enemy is equipped with.
	 */
	public void spawnEnemy(){
		double gunChance = Math.random();
		enemy = new Enemy(gunChance, p1.getLocation());
	}
	
	/**
	 * This method determines whether an enemy will appear on the position the player is
	 * on, or not. There is a 15& chance an enemy will spawn. If the randomly generated
	 * double is not within the 15% range, nothing happens.
	 */
	public void randomEncounter(){
		double chance = Math.random();
		if(chance < 0.15){
			spawnEnemy();
		}
	}
	
	/**
	 * This method represents when the player has successfully defeated the enemy during
	 * a random encounter and which item is dropped as a result. This method also resets
	 * the value of inCombatMode to false, so that the state of the game is changed to be
	 * not in the combat mini-game phase.
	 */
	public void survived(){
		double dropChance = Math.random();
		if(dropChance < 0.3){
			droppedItem = new Item();
			droppedItem.use(p1);
		}else{
			droppedItem = new Item(p1.getGun());
			droppedItem.use(p1.getGun());
		}
		exitCombatMode();
	}
	
	/**
	 * This method represents when the player has successfully escaped the random
	 * encounter. The player is moved back 1 step and the enemy is marked as dead,
	 * but unable to drop an item since it was not defeated, only evaded.
	 */
	public void retreated(){
		if(p1.retreat()){
			p1.changeLocation(p1.getLocation() - 1);
			enemy.changeItemDrop();
		}
		exitCombatMode();
	}
	
	/**
	 * This is a getter method for the player's current health.
	 * @return player's curernt health.
	 */
	public int getPlayerHealth(){
		return p1.getCurrentHealth();
	}
	
	/**
	 * This is a getter method for the enemy's current health.
	 * @return enemy's current health.
	 */
	public int getEnemyHealth(){
		return enemy.getCurrentHealth();
	}
	
	/**
	 * This is a getter method for the player's current location on the map.
	 * @return player's position within the map.
	 */
	public int getPlayerSpace(){
		return p1.getLocation();
	}
	
	/**
	 * This is a method that allows other classes to access the player object referenced in this class.
	 * @return player object referenced in this class.
	 */
	public Player getPlayer(){
		return p1;
	}
	
	/**
	 * This is a method that allows other classes to access the enemy object referenced in this class.
	 * @return enemy object referenced in this class.
	 */
	public Enemy getEnemy(){
		return enemy;
	}
	
	/**
	 * This method updates where the player is on the map. If the value of an index is assigned anything other than
	 * 0, that index is where the player stands in relation to the starting position and end position.
	 */
	public void updateMap(){
		for(int i = 0; i < map.length; i++){
			if(i == p1.getLocation()){
				map[i] = 1;
			}else{
				map[i] = 0;
			}
		}
	}
	
	/**
	 * This method is a getter method for a gun within the weapons array, which represents all possible weapons the
	 * player can choose from.
	 * @param index the index of the gun desired.
	 * @return the gun object at the index given in the weapons array.
	 */
	public Gun getGun(int index){
		return weapons[index];
	}
	
	/**
	 * This method is for linking a gun object to the player object once the user inputs which gun the player is to
	 * use during the game.
	 * @param index the index of the gun desired.
	 */
	public void setPlayerGun(int index){
		p1.setGun(weapons[index]);
	}
	
	/**
	 * This method simply changes the state of the game to being in the combat mini-game phase.
	 */
	public void enterCombatMode(){
		inCombat = true;
	}
	
	/**
	 * This method simply changes the state of teh game to not being in the combat mini-game phase.This method
	 * also resets the turn value so that the player begins the combat in the next mini-game.
	 */
	public void exitCombatMode(){
		inCombat = false;
		turn = 1;
	}
	
	/**
	 * This method is a getter method for the value of whether the game is in combat or not.
	 * @return whether the game is in combat or not.
	 */
	public boolean isInCombat(){
		return inCombat;
	}
	
	/**
	 * This method is the central method for figuring out damage dealt by the player to the enemy, as well 
	 * as whether the player successfully escaped or not, depending on the choice of the user, which is the
	 * parameter passed in. The turn value is then changed to allow for the enemy's action.
	 * @param playerChoice the input of the user's choice of action.
	 */
	public void fight(String playerChoice){
		if(turn > 0){
			if(playerChoice.equals("Shoot") && p1.getGun().getAccuracy() > 0){
				p1.shoot(enemy);
			}else if(playerChoice.equals("Escape") && p1.retreat()){
				p1.changeLocation(p1.getLocation() - 1);
				enemy.changeItemDrop();
				enemy.die();
			}
		}
		turn *= -1;
	}
	
	/**
	 * This method represents the enemy's turn in the combat mini-game phase during which the enemy
	 * performs the only action it can, which is to shoot the player. The turn value is then changed
	 * to allow for the player action.
	 */
	public void fight(){
		if(turn < 0){
			enemy.shoot(p1);
		}
		turn *= -1;
	}
	
	/**
	 * This method is to check if the player has succeeded or failed in reaching the end of the dungeon.
	 * This method changes the value of gameOver to indicate a change in the state of the game, if necessary.
	 */
	public void checkGameOver(){
		if(p1.getCurrentHealth() <= 0 || p1.getLocation() == 10){
			gameOver = true;
		}
	}
	
	/**
	 * This method is a getter method for the truth value of if the game is over.
	 * @return if the game is over.
	 */
	public boolean isGameOver(){
		return gameOver;
	}
}