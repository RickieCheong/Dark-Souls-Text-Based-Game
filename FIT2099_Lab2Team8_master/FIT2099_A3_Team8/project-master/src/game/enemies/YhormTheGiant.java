package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.EmberFormBehaviour;
import game.enums.Status;
import game.interfaces.Soul;
import game.weapons.YhormsGreatMachete;

/**
 * YhormTheGiant Class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Enemy
 * @see LordOfCinder
 * @see EmberFormBehaviour
 * @see YhormsGreatMachete
 */
public class YhormTheGiant extends LordOfCinder {

    /**
     * Soul of YhormTheGiant
     */
    private int soul = 5000;

    /**
     * Respawn Location of the YhormTheGiant
     */
    private Location respawnLocation;

    /**
     * Constructor of the YhormTheGiant
     *
     */
    public YhormTheGiant() {
        super("Yhorm the Giant", 'Y',500);
        behaviours.add(0, new EmberFormBehaviour(this));
        inventory.add(new YhormsGreatMachete());
        addCapability(Status.BOSS);
        addCapability(Status.RAGE);
        addCapability(Status.WEAK_TO_STORM_RULER);
        registerInstance();
    }

    /**
     * The PlayTurn of the YhormTheGiant
     *
     * @param actions    collection of possible Actions for this YhormTheGiant
     * @param lastAction The Action this YhormTheGiant took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the YhormTheGiant
     * @param display    the I/O object to which messages may be written
     * @return action for the YhormTheGiant
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.STUNNED)){
            this.removeCapability(Status.STUNNED);
            return new DoNothingAction();
        }

        if (this.hasCapability(Status.RESET)){
            this.removeCapability(Status.RESET);
            map.moveActor(this, respawnLocation.map().at(this.respawnLocation.x(),this.respawnLocation.y()));
            return new DoNothingAction();
        }
        return super.playTurn(actions,lastAction,map,display);
    }

    /**
     * The allowable Action for the YhormTheGiant
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
     * Store the respawn Location of the YhormTheGiant
     *
     * @param map The game map
     * @param x x position
     * @param y y position
     */
    public void addLocation(GameMap map, int x, int y){
        this.respawnLocation = new Location(map, x, y);
    }

    /**
     * The reset for the YhormTheGiant
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
