package game.characters;

import edu.monash.fit2099.engine.*;
import game.Upgrade;
import game.actions.BuyAction;
import game.actions.SellAction;
import game.enums.Status;
import game.interfaces.Purchasable;
import game.interfaces.Soul;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;
import game.weapons.SorcererStaff;

import java.util.ArrayList;
import java.util.List;

/**
 * Vendor Class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Actor
 * @see Purchasable
 * @see game.weapons.GameWeaponItem
 * @see BuyAction
 * @see SellAction
 */
public class Vendor extends Actor{

    /**
     * List of Product to be sell by vendor
     */
    private List<Purchasable> product = new ArrayList<Purchasable>();

    /**
     * Constructor of the Vendor class
     *
     */
    public Vendor() {
        super("Fire Keeper", 'F', 100000);
        product.add(new Broadsword());
        product.add(new GiantAxe());
        product.add(new Upgrade(25));
        product.add(new SorcererStaff());
    }

    /** Select and return an action to perform with the Vendor, which is sellAction
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the action to be performed which is SellAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new SellAction();
    }

    /** Returns a list of actions that the current actor and other actor can do with each other. In this case,it is
     * which item is purchasable based on the requirements
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return List of possible actions between the 2 actors
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor,direction,map);
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            for (Purchasable item: this.product) {
                actions.add(new BuyAction(item, otherActor));
            }
        }
        return actions;
    }

    /**
     * Make the class to become Soul Class
     *
     * @return a reference to the current Item as type Soul
     */
    @Override
    public Soul asSoul() {
        return super.asSoul();
    }
}
