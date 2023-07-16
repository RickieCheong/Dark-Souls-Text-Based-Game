package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.AutoDeathBehaviour;
import game.behaviours.WanderBehaviour;
import game.interfaces.Soul;

/**
 * Undead class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Enemy
 * @see Actor
 * @see game.interfaces.Behaviour
 * @see Soul
 */
public class Undead extends Enemy {

    /**
     * Soul Of the Undead
     */
    private int soul =  50;

    /**
     * Constructor.
     *
     */
    public Undead() {
        super("Undead", 'u', 50);
//        behaviours.add(new AttackBehaviour(Status.HOSTILE_TO_ENEMY));
        behaviours.add(new AutoDeathBehaviour(10));
        behaviours.add(new WanderBehaviour());
        registerInstance();
    }

    /**
     * Creates and returns an intrinsic weapon for Undead
     *
     * @return a IntrinsicWeapon for Undead
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "punches");
    }

    /**
     * The PlayTurn for the Undead
     *
     * @param actions    collection of possible Actions for this Undead
     * @param lastAction The Action this Undead took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Undead
     * @param display    the I/O object to which messages may be written
     * @return The Action for the Undead
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * To transfer soul to Soul Object
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soul);
    }

    /**
     * The reset for the Undead
     *
     * @param map The game map
     */
    @Override
    public void resetInstance(GameMap map) {
//        this.hurt(10000);
        map.removeActor(this);
        super.resetInstance(map);
    }

    /**
     * No delete from the reset
     *
     * @return true for exist after reset
     */
    @Override
    public boolean isExist() {
        return false;
    }

    /**
     * The Description of the Undead
     *
     * @return The description of the Undead
     */
    @Override
    public String toString() {
        return name + " (No weapon) " + "(" + this.hitPoints + "/" + this.maxHitPoints +")";
    }

}
