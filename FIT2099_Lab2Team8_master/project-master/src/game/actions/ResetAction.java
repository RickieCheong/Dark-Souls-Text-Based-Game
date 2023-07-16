package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ResetManager;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 * @see ResetManager
 */

public class ResetAction extends Action {

    /**
     * Performs the action of resetting an actor
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a message to indicate that the game has reset
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run(map);
        return menuDescription(actor);

    }

    /**
     * Returns a descriptive message to let the user know that the game has been reset
     *
     * @param actor The actor performing the action.
     * @return Returns a message to indicate that the game has reset
     */
    @Override
    public String menuDescription(Actor actor) {
        return "The Game is Reset";
    }
}
