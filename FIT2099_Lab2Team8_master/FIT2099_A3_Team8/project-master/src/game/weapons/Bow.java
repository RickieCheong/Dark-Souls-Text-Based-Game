package game.weapons;

/**
 * @author Soh Jin Huei
 * @version 1.0
 * @see RangeWeapon
 * @see GameWeaponItem
 * @see DarkmoonLongbow
 */

public abstract class Bow extends RangeWeapon {
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
    public Bow(String name, char displayChar, int damage, String verb, int hitRate, int souls) {
        super(name, displayChar, damage, verb, hitRate, souls);
    }

    /**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     */
    @Override
    public int damage() {
        return super.damage();
    }

    /**
     * Returns the chance to hit the target in integer. Use it altogether with nextInt() method.
     *
     * @return Integer of the given hitRate
     */
    @Override
    public int chanceToHit() {
        return super.chanceToHit();
    }
}
