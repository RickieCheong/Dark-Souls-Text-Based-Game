package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 */
public class SellAction extends Action {

    /**
     * Performs the selling action performed by an actor
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a descriptive message to welcome the player
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " say Welcome! Welcome!";
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
