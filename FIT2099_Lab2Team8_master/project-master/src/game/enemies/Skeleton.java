package game.enemies;


import edu.monash.fit2099.engine.*;
import game.actions.ResurrectAction;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

import java.util.Random;

/**
 * Skeleton
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Enemy
 * @see Actor
 * @see Abilities
 * @see Status
 */
public class Skeleton extends Enemy{
    /**
     * Soul of the Skeleton
     */
    private int soul;

    /**
     * Resurrect Time of the Skeleton
     */
    private int resurrectTime;

    /**
     * Respawn Location of Skeleton
     */
    private Location respawnLocation;

    /**
     * Constructor.of the Skeleton
     *
     */
    public Skeleton() {
        super("Skeleton", 'S', 100);
        this.resurrectTime = 1;
        this.soul = 250;
        this.addCapability(Abilities.REVIVE);
        this.inventory.add(randomWeapon());
        this.behaviours.add(new WanderBehaviour());
        registerInstance();
    }

    /**
     *  Get the Action from each behaviour with priority
     *
     * @param actions    collection of possible Actions for this Enemy
     * @param lastAction The Action this Enemy took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Enemy
     * @param display    the I/O object to which messages may be written
     * @return Action that the Enemy can execute
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            return new ResurrectAction(resurrectTime, 2);
        }

        if (this.hasCapability(Status.RESET)) {
            this.removeCapability(Status.RESET);
            map.moveActor(this, map.at(this.respawnLocation.x(),this.respawnLocation.y()));
            return new DoNothingAction();
        }
        // loop through all behaviours
         return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Store the respawn location of the Skeleton
     *
     * @param map The game map
     * @param x The x position
     * @param y The y position
     */
    public void addLocation(GameMap map, int x, int y){
        this.respawnLocation = new Location(map, x, y);
    }

    /**
     * Transfer the Soul to another Soul Object
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soul);
    }

    /**
     * Random Choose of the Weapon for Skeleton
     *
     * @return Weapon Item for the Skeleton
     */
    public WeaponItem randomWeapon(){
        Random rand = new Random();
        WeaponItem skeletonWeapon;
        int int_random = rand.nextInt(2);
        if (int_random == 1) {
            skeletonWeapon = new Broadsword();
        }else{
            skeletonWeapon = new GiantAxe();
        }
        return skeletonWeapon;
    }

    /**
     * Reset the Skeleton when game is reset
     *
     * @param map The game map
     */
    @Override
    public void resetInstance(GameMap map) {
        this.heal(1000);
        this.addCapability(Status.RESET);
        super.resetInstance(map);
    }

    /**
     * The Description of the Skeleton
     *
     * @return A string of description of Skeleton
     */
    @Override
    public String toString() {
        return name + " (" + this.getWeapon() + ")" + " (" + this.hitPoints + "/" + this.maxHitPoints + ")";
    }
}
