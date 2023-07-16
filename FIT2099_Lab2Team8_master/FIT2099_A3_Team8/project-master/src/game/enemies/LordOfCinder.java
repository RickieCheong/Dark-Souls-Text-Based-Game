package game.enemies;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Soul;
import game.items.CinderOfLord;

/**
 * Lord Of Cinder Class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Enemy
 * @see YhormTheGiant
 */
public abstract class LordOfCinder extends Enemy{

    /**
     * Constructor.of the LordOfCinder
     *
     * @param name        the name of the LordOfCinder
     * @param displayChar the character that will represent the LordOfCinder in the display
     * @param hitPoints   the LordOfCinder's starting hit points
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        inventory.add(new CinderOfLord());
    }

    /**
     * The Allowable actions that can be done by the Player when get into the adjacent square of LordOfCinder
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return All the Actions that can be done by the Player
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return super.getAllowableActions(otherActor, direction, map);
    }

    /**
     * Make the class to become Soul Class
     *
     * @return Soul object
     */
    @Override
    public Soul asSoul() {
        return super.asSoul();
    }

    /**
     * Getter of the HitPoint
     *
     * @return HitPoint of LordOfCinder
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Getter of the MaxHitPoint
     *
     * @return MaxHitPoint of the LordOfCinder
     */
    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }
}

