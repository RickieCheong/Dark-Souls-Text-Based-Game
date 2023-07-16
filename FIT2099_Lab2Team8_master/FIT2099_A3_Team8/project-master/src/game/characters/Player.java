package game.characters;

import edu.monash.fit2099.engine.*;
import game.actions.DieAction;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.items.EstusFlask;
import game.weapons.Broadsword;


/**
 * Class representing the Player.

 * @author Chok Ming Jie
 * @version 1.0
 * @see Actor
 * @see Soul
 * @see Resettable
 * @see Action
 *
 */
public class Player extends Actor implements Soul, Resettable {
	/**
	 * Menu for the Player to choose
	 */
	private final Menu menu = new Menu();
	/**
	 * The soul of Player
	 */
	private int soul = 0;
	/**
	 * The respawn Location of Player
	 */
	protected Location respawnLocation;
	/**
	 * The previous Location of the Player
	 */
	private Location previousPlace;

	/**
	 * Constructor for the Player Class
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.FALL_TO_VALLEY);
		addItemToInventory(new EstusFlask());
		addItemToInventory(new Broadsword());
		registerInstance();
	}

	private static Player player = new Player("Unkindled (Player)", '@', 100);

	/**
	 * Singleton method
	 *
	 * @return an instance of player class
	 */
	public static Player getInstance(){
		return player;
	}

	/**
	 * The PlayTurn for the Player to be load when game is running
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return Action for Player to choose
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (this.hasCapability(Status.RESET) || !this.isConscious()) {
			new DieAction(this.previousPlace, this.respawnLocation).execute(this,map);
			return new DoNothingAction();
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		display.println(name + " (" + this.hitPoints + "/" + this.maxHitPoints + ")," + " holding " + this.getWeapon() + ", " + soul + " souls");
		// return/print the console menu
		this.previousPlace = map.locationOf(this);
//		actions.add(this.getWeapon().getActiveSkill(this, ""));
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Location for storing the Player Location
	 *
	 */
	public void setRespawnLocation(Location location){
		this.respawnLocation = location;
	}

	public Location getRespawnLocation(){
		return this.respawnLocation;
	}

	/**
	 * Heal the Player
	 *
	 * @param healPercentage The healPercentage of Player
	 */
	@Override
	public void heal(int healPercentage) {
		super.heal(this.maxHitPoints * healPercentage / 100);
	}

	/**
	 * To transfer player soul to others
	 *
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		//TODO: transfer Player's souls to another Soul's instance.
		soulObject.addSouls(soul);
		soul = 0;
	}

	/**
	 * Add soul to Player
	 *
	 * @param souls number of souls to be incremented.
	 * @return true for able add soul
	 */
	@Override
	public boolean addSouls(int souls) {
		soul += souls;
		return true;
	}

	/**
	 * The reset for the Player
	 *
	 * @param map: Map of the game
	 */
	@Override
	public void resetInstance(GameMap map) {
		this.hitPoints = maxHitPoints;
		this.addCapability(Status.RESET);
	}

	/**
	 * The Actor that will exist forever
	 *
	 * @return true is exist forever
	 */
	@Override
	public boolean isExist() {
		return true;
	}

	/**
	 * Make Player as a Soul Object
	 *
	 * @return A Soul Object
	 */
	@Override
	public Soul asSoul() {
		return super.asSoul();
	}

	@Override
	public boolean subtractSouls(int souls) {
		if(souls < this.soul) {
			this.soul -= souls;
			return true;
		}
		return false;
	}


}
