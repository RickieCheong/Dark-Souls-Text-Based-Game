package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * @author Soh Jin Huei
 * @version 1.0
 * @see WeaponAction
 * @see AttackAction
 */

public class WindSlashAction extends WeaponAction {

    /**
     * Attribute: Actor type called target
     */
    private Actor target;

    /**
     * Attribute: WeaponItem type called weapon
     */
    private WeaponItem weapon;

    /**
     * Constructor of Wind Slash Action class
     */
    public WindSlashAction(WeaponItem weapon, Actor target) {
        super(weapon);
        this.weapon = weapon;
        this.target = target;
    }

    /**
     * Performs the action of the Wind Slash Action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a description to let the user know that the action has taken place.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.weapon.addCapability(Status.STRIKING);
        new StunAction().execute(target, map);
        // applying the STUN status to Yhorm the Giant
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            // check if exit has an actor
            if (destination.containsAnActor()) {
                // deal damage by using AttackAction
                new AttackAction(target, exit.getName()).execute(actor, map);
            }
        }
        this.weapon.removeCapability(Status.ACTIVATE);
        this.weapon.removeCapability(Status.CHARGED);
        return actor + "struck the " + target + " with Wind Slash and " + target + " is stunned";
//        return "Yhorm the Giant is struck with Wind Slash and is stunned.";
    }

    /**
     * Returns a descriptive message that lets the user know an action can be executed
     *
     * @param actor The actor performing the action.
     * @return Returns a prompt to let the user know that an action can take place
     */
    @Override
    public String menuDescription (Actor actor){
        return actor + " activate Wind Slash";
    }

}

