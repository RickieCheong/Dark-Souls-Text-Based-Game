package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.ApplyPoisonAction;
import game.actions.SwapWeaponAction;
import game.enums.Status;
import game.interfaces.Purchasable;

import java.util.ArrayList;

/**
 * SorcererStaff
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Staff
 * @see Purchasable
 * @see ApplyPoisonAction
 * @see game.interfaces.Resettable
 */

public class SorcererStaff extends Staff implements Purchasable {

    /**
     * The Range of the SorcererStaff
     */
    private int range;

    /**
     * The targets of the Sorcerer Staff
     */
    private ArrayList<Actor> targets;

    /**
     * Constructor of the SorcererStaff
     *
     */
    public SorcererStaff() {
        super("Sorcerer's Staff", 'R', 40, "magic", 80, 3000, 150);
        this.range = 3;
        registerInstance();
    }

    /**
     * The damage by this weapon
     *
     * @return A number of damage
     */
    @Override
    public int damage() {
        if(this.hasCapability(Status.MAGIC)){
            this.removeCapability(Status.MAGIC);
            return this.damage * 2;
        }else{
            return damage;
        }
    }

    /**
     * Chance to hit of this weapon
     *
     * @return A number of Chance to hit
     */
    @Override
    public int chanceToHit() {
        if(this.hasCapability(Status.MAGIC)){
            return hitRate + 40;
        }else{
            return hitRate;
        }
    }

    /**
     * Get the targets of the range
     *
     * @param map Current map of where the actor is
     * @param actor The actor currently in possession of the weapon
     * @param range Number of 'squares' that the weapon can attack from
     * @param status Status of the target
     */
    @Override
    public void getRangeAttack(GameMap map, Actor actor, int range, Status status) {
        super.getRangeAttack(map, actor, range, status);
        this.targets = super.targets;
    }

    /**
     * Get the Action by this weapon
     *
     * @param actor The actor currently in possession of the weapon
     * @param target Target to be attacked
     * @param map Current map of where the actor is
     * @param action The action to be performed by the actor
     * @return Action perform by this weapon
     */
    @Override
    public Action getAction(Actor actor, Actor target, GameMap map, Action action) {
        return super.getAction(actor, target, map, action);
    }

    /**
     * Tick run by this weapon
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(this.hasCapability(Status.USE_MAGIC)){
            this.decreaseSorceries();
            this.removeCapability(Status.USE_MAGIC);
        }
        Actions actions = new Actions();
        this.getRangeAttack(currentLocation.map(), actor, this.range, Status.DEAD);
        if (!this.targets.isEmpty() && super.getSorceries()>0) {
            for (Actor target : this.targets) {
                Action applyPoisonAction = new ApplyPoisonAction(this, target, this.getSorceries(), currentLocation.map().locationOf(target));
                Action resultAction = this.getAction(actor, target, currentLocation.map(), applyPoisonAction);
                if (resultAction != null) {
                    actions.add(resultAction);
                    this.allowableActions = actions;
                }
            }
        } else {
            this.allowableActions = actions;
        }
    }

    /**
     * Get the soul of this weapon
     *
     * @return A number of soul
     */
    @Override
    public int getSoul() {
        return super.getSouls();
    }

    /**
     * Action performed by this weapon in vendor
     *
     * @param actor The actor performing the action.
     * @return Action to perform
     */
    @Override
    public Action boughtBy(Actor actor) {
        return new SwapWeaponAction(this);
    }

    /**
     * Weapon type
     *
     * @return true if it is weapon
     */
    @Override
    public boolean isWeapon() {
        return true;
    }

    /**
     * Will it increase Hp
     *
     * @param actor The actor that wants its HP to increase
     * @return false for which not improve hp
     */
    @Override
    public boolean increaseHP(Actor actor) {
        return false;
    }

    /**
     * Reset for this weapon
     *
     * @param map GameMap
     */
    @Override
    public void resetInstance(GameMap map) {
        super.resetSorceries();
    }

    /**
     * Delete when reset
     *
     * @return true is exist forever
     */
    @Override
    public boolean isExist() {
        return false;
    }
}
