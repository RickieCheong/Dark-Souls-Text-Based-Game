package game.actions;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 */

public class AutoDieAction extends Action {

    /**
     * Performs the auto death action of an actor by removing them from the map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a message letting the user know that an actor has died
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor.toString() + " is dead";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return Returns null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

}
