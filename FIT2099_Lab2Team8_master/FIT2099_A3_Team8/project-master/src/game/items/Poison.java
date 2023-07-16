package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actions.PoisonAction;
import game.enums.Status;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Item
 * @see PoisonAction
 */

public class Poison extends Item {

    /**
     * The Player that put the poison
     */
    private Actor player;

    /**
     * The time of the poison
     */
    private int poison;

    /**
     * The Damage of the Poison
     */
    private int poisonDamage = 5;

    /**
     * Constructor of the Poison
     *
     * @param player The Player that put the poison
     */
    public Poison(Actor player) {
        super("Poison", '!', false);
        this.player = player;
        this.addCapability(Status.ONE_POISON);
    }

    /**
     * Turn run by the Poison
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.poison++;
        if (this.poison > 3) {
            actor.removeItemFromInventory(this); // which floor
        } else {
            new PoisonAction(this.player, this.poisonDamage).execute(actor, currentLocation.map());
        }
    }

}
