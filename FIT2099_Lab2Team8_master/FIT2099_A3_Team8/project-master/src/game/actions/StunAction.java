package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;

/**
 * @author Soh Jin Huei
 * @version 1.0
 * @see Action
 * @see Status
 */
public class StunAction extends Action {

    /**
     * Performs the action of making Enemy stunned
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a description to let the user know that the action has taken place.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.STUNNED);
        return actor + " is stunned";
    }

    /**
     * Returns a descriptive string letting the user know that an action can be executed
     *
     * @param actor The actor performing the action.
     * @return Returns the text shown on the menu to let the user know that an action has taken place
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
