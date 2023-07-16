package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 * @see ResetAction
 */

public class RestAction extends Action {

    /**
     * Attribute: Location type
     */
    private Location location;

    /**
     * Constructor of the Rest Action class
     *
     * @param location location of where the rest action takes place
     */
    public RestAction(Location location) {
        this.location = location;
    }

    /**
     * Performs the rest action of the player when a user chooses to rest at the Bonfire
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a descriptive message to let the user know that the player is resting
     */
    @Override
    public String execute(Actor actor, GameMap map) {
//        ResetManager.getInstance().run(map);
        actor.addCapability(Status.REST);
        new ResetAction().execute(actor, map);
        return actor + " is resting at the bonfire";
    }

    /**
     * Returns a descriptive message to let the user know that it has the option of resting at the bonfire
     *
     * @param actor The actor performing the action.
     * @return Returns a prompt to the user to rest at the bonfire
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rest at the bonfire";
    }
}
