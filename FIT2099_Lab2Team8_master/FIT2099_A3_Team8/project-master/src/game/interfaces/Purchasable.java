package game.interfaces;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;

/**
 /**
 * @author Chok Ming Jie
 * @version 1.0
 * @see edu.monash.fit2099.engine.Ground
 */
public interface Purchasable {

    /**
     * Gets the number of souls of an item
     *
     */
    int getSoul();

    /**
     * This performs the action of an item being bought and replacing it with another item
     *
     * @param actor The actor performing the action.
     * @return Returns a Swap Weapon Action with the current item
     */
    Action boughtBy(Actor actor);

    /**
     * Checks to see if an item is a weapon
     *
     * @return Returns true if it is a weapon
     */
    boolean isWeapon();

    /**
     * Increases the HP of an actor
     *
     * @param actor The actor that wants its HP to increase
     * @return Returns false if the HP does not increase
     */
    boolean increaseHP(Actor actor);
}
