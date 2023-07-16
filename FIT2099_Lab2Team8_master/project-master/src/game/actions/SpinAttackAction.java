package game.actions;


import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * @author Soh Jin Huei
 * @version 1.0
 * @see WeaponAction
 * @see AttackAction
 */

public class SpinAttackAction extends WeaponAction {


    /**
     * Constructor for the SpinAttackAction class
     */
    public SpinAttackAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    /**
     * Performs the action of detecting if an enemy is around the player
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a description to let the user know that the action has taken place.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.weapon.addCapability(Status.SPINNING);
        for (Exit exit: map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            // check if exit has an actor
            if (destination.containsAnActor()) {
                // deal damage by using AttackAction
                new AttackAction(destination.getActor(), exit.toString()).execute(actor, map);
            }
        }
        return "Spin Attack is activated and swung";
    }

    /**
     * Returns a descriptive string to let the user know that an action can be executed
     *
     * @param actor The actor performing the action.
     * @return Returns the text shown on the menu to let the user know that an action can take place
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " use Spin Attack" ;
    }

}
