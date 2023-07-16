package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.items.Poison;

/**
 * ApplyPoisonClass
 * Apply Poison for attacking other Actors.
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see edu.monash.fit2099.engine.Action
 * @see game.actions.PoisonAction
 */

public class ApplyPoisonAction extends WeaponAction {

    /**
     * The location of the target
     */
    private Location enemyLocation;

    /**
     * The number of magic
     */
    private int magic;

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The Weapon Item to have this action
     */
    private WeaponItem weaponItem;

    /**
     * Constructor of the ApplyPoisonAction
     *
     * @param weaponItem The weapon item
     * @param target The target to apply poison on
     * @param maxMagic The maximum magic for this weapon item
     * @param enemyLocation The target location
     */
    public ApplyPoisonAction(WeaponItem weaponItem, Actor target, int maxMagic, Location enemyLocation) {
        super(weaponItem);
        this.weaponItem = weaponItem;
        this.target = target;
        this.magic = maxMagic;
        this.enemyLocation = enemyLocation;
    }

    /**
     * Perform the apply poison action onto the target
     *
     * @param actor The actor that use this action
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(this.magic > 0){
            this.weaponItem.addCapability(Status.MAGIC);
            this.weaponItem.addCapability(Status.USE_MAGIC);
            for(Item item: target.getInventory()){
                if(item.hasCapability(Status.ONE_POISON)){
                    target.removeItemFromInventory(item);
                }
            }
            target.addItemToInventory(new Poison(actor));
            Display display = new Display();
            display.println(actor + " poison the " + target + " with the power of magic");
            return new AttackAction(target, "(" + map.locationOf(target).x() + "," + map.locationOf(target).y() + ")").execute(actor, map);
        }else{
            return "No more magic";
        }
    }

    /**
     * Returns a menu description to let the user for execute this apply poison action
     *
     * @param actor The actor performing the action.
     * @return Returns a menu descriptive message
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activate Magic by using " + this.weaponItem.toString() + this.target + " at (" + this.enemyLocation.x() + "," + this.enemyLocation.y() + ")" ;
    }


}
