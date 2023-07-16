package game.weapons;

import game.interfaces.Resettable;

/**
 * Staff
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see SorcererStaff
 * @see Resettable
 * @see RangeWeapon
 * @see GameWeaponItem
 */

public abstract class Staff extends RangeWeapon implements Resettable {

    /**
     * The sorceries of the Staff
     */
    private int sorceries;

    /**
     * The max of the sorceries of staff
     */
    private int maxSorceries;

    /**
     * Constructor of the Staff
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param souls       the soul of the weapon
     * @param sorceries   the sorceries of the weapon
     */
    public Staff(String name, char displayChar, int damage, String verb, int hitRate, int souls, int sorceries) {
        super(name, displayChar, damage, verb, hitRate, souls);
        this.sorceries = sorceries;
        this.maxSorceries = sorceries;
    }

    /**
     * Get the sorceries of the staff
     *
     * @return number of sorceries
     */
    public int getSorceries() {
        return this.sorceries;
    }

    /**
     * Decrease the sorceries
     */
    public void decreaseSorceries(){
        this.sorceries -= 10;
    }

    /**
     * Reset the sorceries
     */
    public void resetSorceries(){
        this.sorceries = maxSorceries;
    }

    /**
     * String name for the staff
     *
     * @return string of name
     */
    @Override
    public String toString(){
        return name + "(" + this.sorceries + "/" + this.maxSorceries +")";
    }

}
