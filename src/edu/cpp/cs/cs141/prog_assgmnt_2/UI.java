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

import java.util.Scanner;
/**
 * This class simply represents the user interface of the game, in which all user inputs are taken in and
 * analyzed. This class represents all the printed dialogue between the player and the environment of the
 * game.
 * 
 * @author Diana Choi
 */
public class UI {

	/**
	 * This field is instantiated from the beginning because otherwise, the methods called from this class
	 * result in a Null-Pointer exception. This field's value is replaced within the constructor. This field
	 * introduces the game engine and all its public class members for access to this class.
	 */
	private static GameEngine ge = new GameEngine();
	
	/**
	 * This field is required to be able to take in and read user inputs. Each string that is read is then
	 * analyzed afterward, to see if it is a valid input. If not, the user is asked to input a new string
	 * to be checked again. The process repeats until the user inputs a string that is a properly recognized
	 * response.
	 */
	private static Scanner s = new Scanner(System.in);
	
	/**
	 * This constructor takes in a game engine object so that it can request information about the game.
	 * @param g The game engine that is to be used in the class in order to retrieve information about the game's
	 * current statistics and information.
	 */
	public UI(GameEngine g) {
		ge = g;
	}
	
	/**
	 * This method prints out the first message greeting the user to the game. It allows for the option to begin
	 * the game immediately or lead to a series of instructions describing the game further. It will not allow
	 * inputs other than "Y" and "N" and will continue to prompt the user for one of these two inputs before
	 * moving onto the intructions or the game itself.
	 */
	public static void welcomeMessage(){
		System.out.println("Welcome to Dungeon Escape Game. Please enter Y for instructions, and N to begin the game.");
		String a = s.nextLine();
		while(!a.equals("Y") && !a.equals("N")){
			System.out.println("Invalid input. Please try again: ");
			a = s.nextLine();
		}
		if(a.equals("Y")){
			instructions();
		}else if(a.equals("N")){
			startGame();
		}
	}
	
	/**
	 * This methods prints out all the instructions to the game, describing the rules for user inputs and the basic
	 * information about the player's character, such as the weapon, the health points, and the item system. It will
	 * then prompt the user to enter an input before starting the game.
	 */
	public static void instructions(){
		System.out.println("Instructions:\nEnter responses when prompted. Responses are case-sensitive.");
		System.out.println("If the choices offered to you are capitalized, enter exactly the one of the choices offered.");
		System.out.println("Otherwise, you will be asked to input a choice again.\n");
		System.out.println("You start out with 20 health points. You choose a gun when you begin the game.");
		System.out.println("You cannot change your gun during the game.");
		System.out.println("Every time you step forward, there is a chance you will encounter an enemy.");
		System.out.println("If you encounter an enemy, you will either try to shoot it, or escape.");
		System.out.println("Depending on your gun's accuracy, you can damage the enemy. Enemies have 5 health points.");
		System.out.println("If you successfully escape, you will return to your previous step.");
		System.out.println("If you fail to escape, the combat will continue until you or the enemy is dead.");
		System.out.println("If you survive combat, an item will drop.");
		System.out.println("The item will either be a health pack (restores 5 health points) or an ammo magazine (reloads all ammo).");
		System.out.println("If your health points drops to, or below, 0, you die and lose the game.");
		System.out.println("If you reach the end of the hallway (10 steps) before dying, you win the game!");
		System.out.println("Enter S to start the game now.");
		String input = s.nextLine();
		while(!input.equals("S")){
			System.out.println("Invalid input. Please try again: ");
			input = s.nextLine();
		}
		startGame();
	}
	
	/**
	 * This method is the story introduction of the game, and prints out a brief backstory before leading the character
	 * to the weapon selection stage. It outlines how far the player is from the exit and the concept of masked men, which
	 * are the enemies the player faces in random encounters.
	 */
	public static void startGame(){
		System.out.println("You wake up in a dark hallway with a badly injured leg. You can only move one step at a time.");
		System.out.println("You can barely make out a door that you think leads outside, but it is 10 steps away.");
		System.out.println("You vaguely remember masked men attacking you before blacking out.");
		chooseGun();
		playerInfo();
	}
	
	/**
	 * This method is for when the player chooses a gun. All three gun choices' information lists are printed out for
	 * reference, and the user is prompted to select one. Just like the other times the user is prompted, an invalid
	 * response will return another request to input a valid response.
	 */
	public static void chooseGun(){
		System.out.println("You notice there are three guns beside you, a Pistol, a Rifle, and a Shotgun.");
		System.out.println("You figure, as self-defense, you should pick one. But because of your injury, you can't carry multiple guns.\n");
		for(int i = 0; i < 3; i++){
			System.out.println(ge.getGun(i).printInfo() + "\n");
		}
		System.out.println("Enter the name of the gun you choose: ");
		String answer = s.nextLine();
		while(!answer.equals("Pistol") && !answer.equals("Rifle") && !answer.equals("Shotgun")){
			System.out.println("Invalid input. Please try again: ");
			answer = s.nextLine();
		}
		if(answer.equals("Pistol")){
			ge.setPlayerGun(0);
		}else if(answer.equals("Rifle")){
			ge.setPlayerGun(1);
		}else if(answer.equals("Shotgun")){
			ge.setPlayerGun(2);
		}
	}
	
	/**
	 * This method is the central method called repeatedly throughout the game. It first checks if the player
	 * is in the middle of the combat mini-game, in which case, a different method is called. Otherwise, the
	 * user is asked if the player is to step forward. There is no penalty for not stepping forward. It simply
	 * wastes one turn. But since this game is not about how fast you escape the dungeon, but rather only
	 * focused on escaping, this method will simply be called once more afterward. The real purpose behind
	 * asking the user to step forward or not each time is to prevent an infinite while loop from printing
	 * infinite statements without ever stopping to let the user engage in the combat mini-game.
	 */
	public static void inGame(){
		if(ge.isInCombat()){
			inCombat();
		}else{
			System.out.println("Do you want to take a step forward? (Y/N): ");
			String a = s.nextLine();
			while(!a.equals("Y") && !a.equals("N")){
				System.out.println("Invalid input. Please try again: ");
				a = s.nextLine();
			}
			if(a.isEmpty() || a.equals("N")){
				System.out.println("You rest for a moment.\n");
			}else if(a.equals("Y")){
				System.out.println("You take a painful step towards the door, closer to the exit.\n");
				stepForward();
			}
		}
	}
	
	/**
	 * This method is called when the player takes a step forward, and calculates whether a random
	 * encounter has occurred or not. In other words, this method calculates and tells the user whether
	 * an enemy has appeared, and if the player is now entering the combat mini-game. It also tells the
	 * user how many steps are left between the player and the exit. If an enemy has appeared, the enemy's
	 * weapon is printed out as well, to let the user be aware of how much damage the enemy has the potential
	 * of inflicting, as all gun stats were given at the beginning of the game.
	 */
	public static void stepForward(){
		ge.getPlayer().moveForward();
		if(ge.getPlayerSpace() < 10){
			System.out.println("Location : " + (10 - ge.getPlayerSpace()) + " step(s) away from the door.");
			ge.randomEncounter();
			if(ge.getEnemy() != null && ge.getEnemy().getLocation() == ge.getPlayer().getLocation()){
				System.out.println("A masked man appears as if materializing from the shadows themselves.");
				System.out.println("He points the barrel of a " + ge.getEnemy().getGun().getType() + " at you.");
				ge.enterCombatMode();
			}
		}
		
	}
	
	/**
	 * This method represents when the combat mini-game phase is active. It first checks if the enemy
	 * has died, and if so, if the enemy has been marked dead because the player defeated it, or if
	 * the player has successfully escaped, and so the program simply marked the enemy object as dead.
	 * If the player has defeated the enemy, the item is dropped, and its effects are immediately applied.
	 * The name of the item is not returned, but rather, immediately after the combat phase has ended, the
	 * user is shown the current stats of the player. If the combat phase is active and the enemy has not
	 * been vanquished or evaded, then the player's action is inputted by the user, and the enemy automatically
	 * performs its turn by doing the only thing it can do - shoot the player. The effects of each turn is
	 * displayed immediately after, to update the user on the stats of the enemy and of the player.
	 */
	public static void inCombat(){
		while(ge.isInCombat()){
			if(!ge.getEnemy().isAlive()){
				if(ge.getEnemy().dropsItem()){
					ge.survived();
					System.out.println("You defeated the enemy and picked up an item.\n");
				}else{
					ge.retreated();
					System.out.println("You managed to escape.\n");
				}
				ge.exitCombatMode();
				playerInfo();
			}else{
				while(ge.getEnemy().isAlive() && ge.getPlayer().isAlive()){
					System.out.println("Do you want to Shoot or Escape? ");
					String answer = s.nextLine();
					while(!answer.equals("Shoot") && !answer.equals("Escape")){
						System.out.println("Invalid input. Please try again: ");
						answer = s.nextLine();
					}
					ge.fight(answer);
					if(answer.equals("Shoot")){
						if(ge.getEnemy().hasLostHealth()){
							System.out.println("You shot him and dealt " + ge.getPlayer().getGun().getDamage() + " damage.");
							ge.getEnemy().resetHurt();
						}else{
							if(ge.getPlayer().getGun().getAmmoLeft() <= 0){
								System.out.println("Your gun clicks emptily.");
							}else{
								System.out.println("You missed your shot.");
							}
						}
					}else if(answer.equals("Escape")){
						System.out.println("You failed to escape, and are forced to continue fighting your foe.");
					}
					if(ge.getEnemy().isAlive()){
						System.out.println("He has " + ge.getEnemyHealth() + " health left.");
						System.out.println("You have " + ge.getPlayer().getGun().getAmmoLeft() + " bullets left.\n");
						ge.fight();
						if(ge.getPlayer().hasLostHealth()){
							System.out.println("He shot you and dealt "+ ge.getEnemy().getGun().getDamage() + " damage.");
							ge.getPlayer().resetHurt();
						}else{
							if(ge.getEnemy().getGun().getAmmoLeft() <= 0){
								System.out.println("His gun clicks emptily.");
							}else{
								System.out.println("He missed his shot.");
							}
						}
						System.out.println("You have " + ge.getPlayerHealth() + " health left.\n");
					}
					
				}
					
			}
		}
	}
	
	/**
	 * This method is simply a quick way to display all the relevant information about a player
	 * in a concise manner. This method is called when the game first begins and when an item
	 * drops. An abbreviated version is displayed separately when displaying the current position
	 * of the player, and during combat after each participant's turn.
	 */
	public static void playerInfo(){
		System.out.println("Your current stats: ");
		System.out.println("Health: " + ge.getPlayerHealth());
		System.out.println("Ammo: " + ge.getPlayer().getGun().getAmmoLeft());
		System.out.println("Location: " + (10 - ge.getPlayerSpace()) + " steps away from the door.");
	}
	
	/**
	 * This method prints out the victory or loss messages, as well as a congratulations on finishing
	 * the game and a gratitude for trying this game out. This method being printed marks the end of
	 * the game.
	 */
	public static void endMessage(){
		if(ge.getPlayerSpace() == 10){
			System.out.println("You reach your hand forward as you take the last step to freedom.");
			System.out.println("At last, the outside world awaits, and your injured leg can finally rest.");
			System.out.println("The door creaks open and the sunlight blinds you. You feel warmth for the first time in ages.");
			System.out.println("Congratulations on escaping the dungeon!");
		}else if(ge.getPlayerHealth() <= 0){
			System.out.println("You were gunned down while desperately reaching for the exit door.");
			System.out.println("It was no use. The masked men were too strong and overpowered you.");
			System.out.println("They leave you to bleed out in the darkness.");
			System.out.println("You have failed to escape the dungeon.");
		}
		System.out.println("Thank you for playing Dungeon Escape Game.");
	}
}
