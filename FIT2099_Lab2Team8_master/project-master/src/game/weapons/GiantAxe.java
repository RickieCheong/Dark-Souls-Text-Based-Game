package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.SpinAttackAction;
import game.actions.SwapWeaponAction;
import game.enums.Status;
import game.interfaces.Purchasable;

/**
 * Giant Axe Weapon
 *
 * @author Soh Jin Huei
 * @version 1.0
 * @see GameWeaponItem
 * @see Purchasable
 * @see SpinAttackAction
 */
public class GiantAxe extends GameWeaponItem implements Purchasable {

    /**
     * Constructor for the Giant Axe class
     */
    public GiantAxe() {
        super("Giant Axe", 'X', 50, "swings", 80, 1000);
        this.allowableActions.add(new SpinAttackAction(this));
    }

    /**
     * Gets the active skill of the Giant Axe that will be used against a target.
     *
     * @param target    the target actor
     * @param direction the direction of target, e.g. "north"
     * @return Returns Spin Attack
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
//        if (target.hasCapability(Status.DEAD)) {
        return new SpinAttackAction(this);
//        }
//        return null;
    }

    /**
     * Gets the damage of the weapon depending on the different skills
     *
     * @return Returns the amount of damage inflicted by the weapon
     */
    @Override
    public int damage() {
        if (this.hasCapability(Status.SPINNING)){
            this.removeCapability(Status.SPINNING);
            return damage / 2;
        } else {
            return damage;
        }
    }


    /**
     * Gets the number of souls of the weapon
     *
     * @return Returns the number of souls of the weapon
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
//        actor.addItemToInventory(this);
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
