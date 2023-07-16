package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enemies.LordOfCinder;
import game.enums.Status;
import game.interfaces.Behaviour;

/**
 * @author Soh Jin Huei
 * @version 1.0
 * @see Action
 * @see Behaviour
 * @see Status
 */

public class EmberFormBehaviour extends Action implements Behaviour {

    /**
     * Attribute that represents Yhorm the Giant
     */
    private LordOfCinder lordOfCinder;

    public EmberFormBehaviour(LordOfCinder lordOfCinder) {
        this.lordOfCinder = lordOfCinder;
    }

    /**
     * Gets the weapon action of the Yhorm's Great Machete after meeting the set conditions
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Returns the behaviour of this class or null depending on the situation
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.RAGE) && lordOfCinder.getHitPoints()<(lordOfCinder.getMaxHitPoints()/2)) { // hitPoints < 50%
            actor.getWeapon().getActiveSkill(actor, "around").execute(actor,map);
            actor.removeCapability(Status.RAGE);
            return this;
        }
       return null;
    }

    /**
     * Performs the action of displaying a message to the user
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns the menuDescription method.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string to let the user know that Yhorm the Giant's surrounding is burning
     *
     * @param actor The actor performing the action.
     * @return Returns the text shown on the menu to let the user know that an action can take place
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Raargh ~ Yhorm the Giant is engulfed in fire.";
    }




}
