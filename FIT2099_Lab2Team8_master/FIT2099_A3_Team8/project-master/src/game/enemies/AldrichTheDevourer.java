package game.enemies;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Soul;
import game.weapons.DarkmoonLongbow;

/**
 * Aldrich The Devourer
 *
 * @author Soh Jin Huei
 * @version 1.0
 * @see LordOfCinder
 * @see Soul
 * @see DarkmoonLongbow
 */

public class AldrichTheDevourer extends LordOfCinder{

    /**
     * Soul of Aldrich
     */
    private int soul = 5000;

    /**
     * Respawn Location of the Aldrich
     */
    private Location respawnLocation;


    /**
     * Constructor of the AldrichTheDevourer class
     */
    public AldrichTheDevourer() {
        super("Aldrich the Devourer", 'A', 350);
        inventory.add(new DarkmoonLongbow());
        this.addCapability(Status.BOSS);
        addCapability(Status.RAGE);
        registerInstance();
    }

    /**
     * The playturn of the Aldrich The Devourer
     *
     * @param actions    collection of possible Actions for this Enemy
     * @param lastAction The Action this Enemy took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Enemy
     * @param display    the I/O object to which messages may be written
     * @return Action done by this enemy
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESET)) {
            this.removeCapability(Status.RESET);
            map.moveActor(this, respawnLocation.map().at(this.respawnLocation.x(),this.respawnLocation.y()));
            return new DoNothingAction();
        }
        return super.playTurn(actions,lastAction,map,display);
    }


    /**
     * The allowable Action for the Aldrich the Devourer
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return Actions that allow surrounding can be Action
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return super.getAllowableActions(otherActor, direction, map);
    }


    /**
     * Transfer soul to another Soul Object
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soul);
    }

    /**
     * Store the respawn Location of the Aldrich
     *
     * @param map The game map
     * @param x x position
     * @param y y position
     */
    public void addLocation(GameMap map, int x, int y) {
        this.respawnLocation = new Location(map, x, y);
    }

    /**
     * The reset for the Aldrich
     *
     * @param map The game map
     */
    @Override
    public void resetInstance(GameMap map) {
        this.heal(10000);
        this.addCapability(Status.RESET);
        super.resetInstance(map);
    }

}
