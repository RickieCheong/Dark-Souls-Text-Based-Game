package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Game Weapon Item
 *
 * @author Soh Jin Huei
 * @version 1.0
 * @see WeaponItem
 * @see DropItemAction
 */
public class GameWeaponItem extends WeaponItem {

    /**
     * Attribute: An integer representing the number of souls
     */
    private int souls;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public GameWeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int souls) {
        super(name, displayChar, damage, verb, hitRate);
        this.souls = souls;
    }


    /**
     * In this game,
     * @param actor an actor that will interact with this item
     * @return null because
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getSouls() {
        return souls;
    }

}
