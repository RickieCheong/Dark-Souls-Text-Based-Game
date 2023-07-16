package game.actions;

import edu.monash.fit2099.engine.*;
import game.grounds.Fire;
import game.weapons.YhormsGreatMachete;


/**
 * @author Soh Jin Huei
 * @version 1.0
 * @see WeaponAction
 * @see YhormsGreatMachete
 */

public class BurnGroundAction extends WeaponAction {

    /**
     * Constructor for the BurnGroundAction Class
     */
    public BurnGroundAction() {
        super(new YhormsGreatMachete());
    }

    /**
     * Performs the action of executing the Wind Slash action of the weapon Storm Ruler
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a description to let the user know that the action has taken place.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // getting the surroundings of the actor and the list of actions of the surroundings
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if(!destination.getGround().blocksThrownObjects()) {
                destination.addItem(new Fire());
            }
        }
        return "The surroundings are engulfed by fire.";
    }

    /**
     * Returns a description to let the user know that the ground surrounding Yhorm the Giant is burning
     *
     * @param actor The actor performing the action.
     * @return Returns the text shown on the menu to let the user know that an action has taken place
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Yhorm the Giant is burning the ground.";
    }

}

