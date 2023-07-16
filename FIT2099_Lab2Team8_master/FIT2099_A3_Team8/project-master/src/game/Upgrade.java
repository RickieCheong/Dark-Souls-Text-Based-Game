package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import game.interfaces.Purchasable;

/**
 * Upgrade Class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Purchasable
 * @see Actor
 * @see Action
 */
public class Upgrade implements Purchasable {

    /**
     * Attribute: An integer representing the number of souls
     */
    private int souls;

    /**
     * Attribute: An integer representing the number of health points
     */
    private int health;

    /**
     * Constructor for the Upgrade class
     *
     * @param health The number of health points
     */
    public Upgrade(int health) {
        this.souls = 200;
        this.health = health;
    }

    /**
     * Gets the number of souls
     *
     * @return Returns the number of souls
     */
    @Override
    public int getSoul() {
        return this.souls;
    }

    /**
     * This performs the action of an item being bought and replacing it with another item
     *
     * @param actor The actor performing the action.
     * @return Returns a Swap Weapon Action with the current item
     */
    @Override
    public Action boughtBy(Actor actor) {
        return null;
    }

    /**
     * Checks to see if an item is a weapon
     *
     * @return Returns true if an item is a weapon
     */
    @Override
    public boolean isWeapon() {
        return false;
    }

    /**
     * Increases the HP of an actor
     *
     * @param actor The actor that wants its HP to increase
     * @return Returns false if the HP does not increase
     */
    @Override
    public boolean increaseHP(Actor actor) {
        actor.increaseMaxHp(this.health);
        return true;
    }

    /**
     * Returns a descriptive message to let the that the HP has been increased
     *
     * @return Returns a message to allow the user know that the HP has been increased
     */
    @Override
    public String toString() {
        return "Upgrade" + " for increase " + this.health + " HP";
    }
}
