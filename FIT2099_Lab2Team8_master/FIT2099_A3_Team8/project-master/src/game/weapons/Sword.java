package game.weapons;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see GameWeaponItem
 * @see Broadsword
 * @see StormRuler
 */

public abstract class Sword extends GameWeaponItem{
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param souls       the soul of the weapon
     */
    public Sword(String name, char displayChar, int damage, String verb, int hitRate, int souls) {
        super(name, displayChar, damage, verb, hitRate, souls);
    }
}
