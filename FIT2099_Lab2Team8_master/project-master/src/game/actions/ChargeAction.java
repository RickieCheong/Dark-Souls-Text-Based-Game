package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;

/**
 * @author Soh Jin Huei
 * @version 1.0
 * @see WeaponAction
 * @see game.weapons.StormRuler
 * @see Status
 */

public class ChargeAction extends WeaponAction {

    /**
     * Attribute: WeaponItem type called weapon
     */
    private WeaponItem weapon;

    /**
     * Attribute: An integer representing the maximum number of charges
     */
    private int maxNumberOfCharge;

    /**
     * Attribute: An ineteger representing the number of charges
     */
    private int numberOfCharge;

    /**
     * Constructor for the ChargeAction class
     */
    public ChargeAction(WeaponItem weapon, int maxNumberOfCharge) {
        super(weapon);
        this.weapon = weapon;
        this.numberOfCharge = 0;
        this.maxNumberOfCharge = maxNumberOfCharge;
    }

    /**
     * Performs the action of charging the Storm Ruler
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description to let the user know what action has taken place.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.weapon.addCapability(Status.ACTIVATE);
        actor.addCapability(Status.DISARMED);
        return actor + " is charging" + this.weapon;
    }

    /**
     * Returns a descriptive string to let the user know how many charges has been made
     *
     * @param actor The actor performing the action.
     * @return The text shown on the menu to let the user know that an action can be taken place
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " charge the " + this.weapon + " (" + this.numberOfCharge + "/" + this.maxNumberOfCharge + ")";
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @return The character "c" in order to use this action
     */
    @Override
    public String hotkey() {
        return "c";
    }
}
