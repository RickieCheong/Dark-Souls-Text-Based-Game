package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.PassiveSkills;
import game.enums.Status;

import java.util.ArrayList;

/**
 * DarkmoonLongbow
 *
 * @author Soh Jin Huei
 * @version 1.0
 * @see GameWeaponItem
 * @see Bow
 * @see AttackAction
 * @see PassiveSkills
 */

public class DarkmoonLongbow extends Bow {

    /**
     * Attribute: An integer representing a range the weapon can attack
     */
    private int range;

    /**
     * Attribute: An array list containing targets
     */
    private ArrayList<Actor> targets;


    /**
     * Constructor for DarkmoonLongbow class
     */
    public DarkmoonLongbow() {
        super("Darkmoon Longbow", 'D', 70, "shoots", 80, 0);
        this.addCapability(PassiveSkills.RANGED_WEAPON);
        this.addCapability(PassiveSkills.CRITICAL_HIT);
        this.range = 3;
    }

    /**
     * The chance of Darkmoon Longbow to hit the target (this is a dividend part of percentage)
     *
     * @return Returns the success rate 15% if Passive Skill; CRITICAL_HIT is activated, else returns 80
     */
    @Override
    public int chanceToHit() {
        if (this.hasCapability(PassiveSkills.CRITICAL_HIT)) {
            return hitRate - 65;
        } else {
            return hitRate;
        }
    }

    /**
     * The amount of damage Darkmoon Longbow will inflict
     *
     * @return Returns damage of 140 when the passive skill Critical Hit is used, otherwise returns damage of 70
     */
    @Override
    public int damage() {
        if (this.hasCapability(PassiveSkills.CRITICAL_HIT)) {
            this.removeCapability(PassiveSkills.CRITICAL_HIT);
            return damage * 2;
        } else {
            return damage;
        }
    }

    /**
     * This method allows a weapon to attack from a given range
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
     * This method blocks the attack of a weapon when an object is in front of the target
     *
     * @param actor The actor currently in possession of the weapon
     * @param target Target to be attacked
     * @param map Current map of where the actor is
     * @param action The action to be performed by the actor
     * @return action that will perform by this weapon
     */
    @Override
    public Action getAction(Actor actor, Actor target, GameMap map, Action action) {
        return super.getAction(actor, target, map, action);
    }

    /**
     * This method allows the weapon the attack at a range when an enemy is detected
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (this.hasCapability(PassiveSkills.RANGED_WEAPON)) {
            this.damage();
            this.removeCapability(PassiveSkills.RANGED_WEAPON);
        }
        Actions actions = new Actions();
        this.getRangeAttack(currentLocation.map(), actor, this.range, Status.HOSTILE_TO_ENEMY);
        if (!this.targets.isEmpty()) {
            for (Actor target: this.targets) {

                Action targetAction = new AttackAction(target, currentLocation.toString());
                Action resultAction = this.getAction(actor, target, currentLocation.map(), targetAction);
                if (resultAction != null) {
                    actor.getAllowableActions(target,"", currentLocation.map());
                    actions.add(resultAction);
                    this.allowableActions = actions;
                }
            }
        }
        else {
            this.allowableActions = actions;
        }

    }


}
