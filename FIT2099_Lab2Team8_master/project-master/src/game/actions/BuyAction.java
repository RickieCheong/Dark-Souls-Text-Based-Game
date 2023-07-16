package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;
import game.interfaces.Purchasable;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 * @see Actor
 * @see Purchasable
 */

public class BuyAction extends Action {

    /**
     * Attribute: Actor type called buyer
     */
    private Actor buyer;

    /**
     * Attribute: Purchasable type called item
     */
    private Purchasable item;

    /**
     * Constructor for the Buy Action class
     *
     * @param item Item that an actor wants to buy
     * @param buyer An actor acting as a buyer
     */
    public BuyAction(Purchasable item, Actor buyer) {
        this.item = item;
        this.buyer = buyer;
    }

    /**
     * Performs the action of buying an item from the vendor
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a descriptive message to show the status of the transaction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.asSoul().subtractSouls(item.getSoul())){
            Action swapAction = item.boughtBy(actor);
            if(swapAction != null){
                swapAction.execute(actor,map);
                actor.asSoul().subtractSouls(item.getSoul());
            }else{
                item.increaseHP(actor);
            }
            return actor + " bought the " + item.toString();
        }else{
            return "Not enough soul, Go sell your kidney.";
        }
    }

    /**
     * Returns a description to let the user know what item has been bought
     *
     * @param actor The actor performing the action.
     * @return Returns a description of what item has been bought
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item.toString() + " (" + item.getSoul() + ")";
    }

}
