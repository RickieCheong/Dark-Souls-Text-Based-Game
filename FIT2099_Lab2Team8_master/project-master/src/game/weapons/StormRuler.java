package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.ChargeAction;
import game.actions.SwapWeaponAction;
import game.actions.WindSlashAction;
import game.enums.PassiveSkills;
import game.enums.Status;

import java.util.Random;

/**
 * Storm Ruler Weapon
 *
 * @author Soh Jin Huei
 * @version 1.0
 * @see GameWeaponItem
 * @see WindSlashAction
 * @see ChargeAction
 * @see PassiveSkills
 */
public class StormRuler extends GameWeaponItem {

    /**
     * Attribute: An integer representing Storm Ruler's maximum number of charges
     */
    public int maxNumberOfCharge;

    /**
     * Attribute: An integer representing Storm Ruler's number of charges
     */
    private int numberOfCharges;

    /**
     * Constructor for the StormRuler class
     */
    public StormRuler() {
        super("Storm Ruler", '7', 70, "strikes", 60, 2000);
        this.addCapability(PassiveSkills.CRITICAL_STRIKE);
//        this.allowableActions.add(new ChargeAction(this, this.maxNumberOfCharge));
        this.maxNumberOfCharge = 3;
        this.numberOfCharges = 0;
    }

    /**
     * The amount of damage Storm Ruler will inflict
     *
     * @return Returns damage of 140 when the passive skill Critical Strike is used, otherwise returns damage of 70
     */
    @Override
    public int damage() {
        Random rand = new Random();
        int chances = rand.nextInt(4);
        if (chances == 1 && this.hasCapability(PassiveSkills.CRITICAL_STRIKE)) {
            return damage * 2;
        } else if (this.hasCapability(Status.STRIKING)) {
            this.removeCapability(Status.STRIKING);
            return damage * 2;
        }
        if(!this.hasCapability(Status.WEAK_TO_STORM_RULER)){
            return damage/2;
        }
        return damage;
    }

    /**
     * The success rate of the weapon
     *
     * @return Returns the hitRate of the weapon depending on the weapon's skill
     */
    @Override
    public int chanceToHit() {
        if (this.hasCapability(Status.STRIKING)) {
            this.removeCapability(Status.STRIKING);
            return hitRate + 40;
        }
        return hitRate;
    }

    /**
     * Gets the active skill of Storm Ruler that will be used against a target
     *
     * @param target the target actor
     * @param direction the direction of target, e.g. "north"
     * @return Returns either Charge or Wind Slash depending on the situation.
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {

        if (this.hasCapability(Status.CHARGED)) {
            if (target.hasCapability(Status.WEAK_TO_STORM_RULER)) {
                return new WindSlashAction(this, target);
            } else {
                return null;
            }
        } else if(this.hasCapability(Status.ACTIVATE)){
            return null;
        }else{
            return new ChargeAction(this, this.maxNumberOfCharge);
        }
    }

    /**
     * Checks to see if the number of charges has reached its maximum and disarms the player
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(this.hasCapability(Status.ACTIVATE)){
            this.numberOfCharges++;
            if(this.numberOfCharges==this.maxNumberOfCharge){
                this.addCapability(Status.CHARGED);
                actor.addCapability(Status.HOSTILE_TO_ENEMY);
                actor.removeCapability(Status.DISARMED);
            }
        }else{
            this.numberOfCharges = 0;
            super.tick(currentLocation, actor);
        }
    }

    /**
     * Displays a descriptive message to let the user know that the Storm Ruler is fully charged or in the
     * process of charging
     *
     * @return Returns the status of the number of charges
     */
    @Override
    public String toString() {
        if (this.hasCapability(Status.CHARGED)){
            return name + " (Fully Charged)";
        }else if(this.hasCapability(Status.ACTIVATE)){
            return this.name + " (Charging)";
        }else{
            return this.name;
        }
    }

    /**
     * Swaps the weapon with another item
     *
     * @param actor an actor that will interact with this item
     * @return Returns a Swap Weapon Action
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new SwapWeaponAction(this);
    }

}

