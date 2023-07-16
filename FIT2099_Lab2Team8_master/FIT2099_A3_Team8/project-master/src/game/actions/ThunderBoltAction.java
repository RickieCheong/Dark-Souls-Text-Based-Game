package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see edu.monash.fit2099.engine.Action
 * @see StunAction
 */

public class ThunderBoltAction extends Action {

    /**
     * The target to be thunderbolt
     */
    private Actor target;

    /**
     * Constructor of the ThunderBoltAction
     *
     * @param target The target to be thunderbolt
     */
    public ThunderBoltAction(Actor target) {
        this.target = target;
    }

    /**
     * Performs the action of ThunderBolt
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string of description about the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        new StunAction().execute(this.target, map);
        return "Thunder Bolt Action is Activate and " + this.target + " is stunned";
    }

    /**
     * Return null as it is for pet
     *
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
