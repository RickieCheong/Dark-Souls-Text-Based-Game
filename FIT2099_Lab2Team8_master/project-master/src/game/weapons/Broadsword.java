package game.weapons;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import game.actions.SwapWeaponAction;
import game.enums.PassiveSkills;
import game.interfaces.Purchasable;

import java.util.List;
import java.util.Random;

/**
 * BroadSword
 *
 * @author Soh Jin Huei
 * @version 1.0
 * @see GameWeaponItem
 * @see Purchasable
 * @see SwapWeaponAction
 * @see PassiveSkills
 */
public class Broadsword extends GameWeaponItem implements Purchasable {

    /**
     * Constructor for the Broadsword class
     */
    public Broadsword()  {
        super("Broadsword", 'W', 30, "slashes", 80, 500);
        this.portable = false;
        this.addCapability(PassiveSkills.CRITICAL_STRIKE);
    }

    /**
     * The amount of damage Broadsword will inflict
     *
     * @return Returns damage of 60 when the passive skill Critical Strike is used, otherwise returns damage of 30
     */
    @Override
    public List<Action> getAllowableActions() {
        return super.getAllowableActions();
    }

    /**
     * The amount of damage Broadsword will inflict
     *
     * @return Returns damage of 60 when the passive skill Critical Strike is used, otherwise returns damage of 30
     */
    @Override
    public int damage() {
        Random rand = new Random();
        int chances = rand.nextInt(4);
        if (chances == 1 && this.hasCapability(PassiveSkills.CRITICAL_STRIKE)) {
            return damage * 2;

        } else {
            return damage;
        }
    }

    /***
     * Gets the souls of the broadsword
     *
     * @return Returns the number of souls for Broadsword
     */
    @Override
    public int getSoul() {
        return super.getSouls();
    }

    /**
     * This performs the action of an item being bought and replacing it with another item
     *
     * @param actor The actor performing the action.
     * @return Returns a Swap Weapon Action with the current item
     */
    @Override
    public Action boughtBy(Actor actor) {
        actor.addItemToInventory(this);
        return new SwapWeaponAction(this);
    }

    /**
     * Checks to see if an item is a weapon
     *
     * @return Returns true if an item is a weapon
     */
    @Override
    public boolean isWeapon() {
        return true;
    }

    /**
     * Increases the HP of an actor
     *
     * @param actor The actor that wants its HP to increase
     * @return Returns false if the HP does not increase
     */
    @Override
    public boolean increaseHP(Actor actor) {
        return false;
    }
}
