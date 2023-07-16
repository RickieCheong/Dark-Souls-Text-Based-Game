package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see edu.monash.fit2099.engine.Action
 * @see game.items.Poison
 */

public class PoisonAction extends Action {

    /**
     * The Damage of the poison
     */
    private int poisonDamage;
    /**
     * The actor that apply the poison
     */
    private Actor player;

    /**
     * Constructor of the PoisonAction
     *
     * @param player The actor that apply the poison
     */
    public PoisonAction(Actor player, int damage) {
        this.player = player;
        this.poisonDamage = damage;
    }

    /**
     * The poison action will be executed
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A word of descriptive sentences of the action that has executed by the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.hurt(this.poisonDamage);
        if (!actor.isConscious() && actor.hasCapability(Status.DEAD)) {
            if (player.asSoul() != null && actor.asSoul() != null) {
                // transfer current target oxygen level to the actor if possible.
                actor.asSoul().transferSouls(player.asSoul());
                map.removeActor(actor);
                Display display = new Display();
                display.println(actor + " is poisoned till die.");
                return actor + " is poisoned till die.";
            }
        }
        Display display = new Display();
        display.println(actor + " is poisoned");
        return actor + " is poisoned";
    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
