package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.BurnGroundAction;
import game.enums.PassiveSkills;

/**
 * YhormsGreatMachete Class
 *
 * @author Soh Jin Huei
 * @version 1.0
 * @see GameWeaponItem
 * @see Weapon
 * @see BurnGroundAction
 */
public class YhormsGreatMachete extends Axe {

    /**
     * Constructor for YhormsGreatMachete class
     *
     */
    public YhormsGreatMachete() {
        super("Yhorm's Great Machete", '8', 95, "swings", 60, 0);
        this.addCapability(PassiveSkills.RAGE_MODE);
    }

    /**
     * The chance of YhormsGreatMachete to hit the target (this is a dividend part of percentage)
     *
     * @return Returns the success rate 90 if Passive Skill; RAGE_MODE is activated, else returns 60
     */
       @Override
    public int chanceToHit() {
        if (this.hasCapability(PassiveSkills.RAGE_MODE)) {
            return hitRate + 30;
       }
        else {
            return hitRate;
        }
    }

    /**
     * Gets the active skill of YhormsGreatMachete that will be used against a target which decreases its HP by 25
     *
     * @param target the target actor
     * @param direction the direction of target, e.g. "north"
     * @return Returns Burn Ground
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new BurnGroundAction();
    }
}

