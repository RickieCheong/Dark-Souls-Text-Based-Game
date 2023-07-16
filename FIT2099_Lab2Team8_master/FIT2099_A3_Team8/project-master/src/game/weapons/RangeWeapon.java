package game.weapons;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

import java.util.ArrayList;

/**
 * RangeWeapon
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see GameWeaponItem
 * @see Number
 * @see Staff
 * @see Bow
 */

public abstract class RangeWeapon extends GameWeaponItem{

    /**
     * Attribute: An array list containing targets
     */
    protected ArrayList<Actor> targets;

    /**
     * Attribute: An array list containing targets
     */
    private ArrayList<Location> locations;

    /**
     * Constructor of RangeWeapon
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     * @param souls       the soul of the weapon
     */
    public RangeWeapon(String name, char displayChar, int damage, String verb, int hitRate, int souls) {
        super(name, displayChar, damage, verb, hitRate, souls);
    }

    /**
     * This method allows a weapon to attack from a given range
     *
     * @param map Current map of where the actor is
     * @param actor The actor currently in possession of the weapon
     * @param range Number of 'squares' that the weapon can attack from
     * @param status Status of the target
     */
    public void getRangeAttack(GameMap map, Actor actor, int range, Status status) {
        ArrayList<Location> locations = new ArrayList<>();
        ArrayList<Actor> targets = new ArrayList<>();
        int xActor = map.locationOf(actor).x();
        int yActor = map.locationOf(actor).y();
        //Looks at depth of shooting
        for (int y2 = range; y2 >= -range; y2--) {
            int y3 = yActor + y2;
            //Looks at the spread of shooting.
            for (int x2 = range; x2 >= -range; x2--) {
                int x3 = xActor + x2;
                if (map.getXRange().contains(x3) && map.getYRange().contains(y3)) {
                    Location placeToShoot = map.at(x3, y3);
                    if (!(locations.contains(placeToShoot))) {
                        locations.add(placeToShoot);
                        if (map.isAnActorAt(placeToShoot)) {
                            Actor a = map.getActorAt(placeToShoot);
                            if (a != actor && a.hasCapability(status)) {
                                targets.add(a);
                            }
                        }
                    }
                }
            }
        }
        this.locations = locations;
        this.targets = targets;
    }

    /**
     * This method blocks the attack of a weapon when an object is in front of the target
     *
     * @param actor The actor currently in possession of the weapon
     * @param target Target to be attacked
     * @param map Current map of where the actor is
     * @param action The action to be performed by the actor
     * @return Returns the action to be performed by the actor
     */
    public Action getAction(Actor actor, Actor target, GameMap map, Action action){
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        NumberRange xs, ys;
        xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
        ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

        for (int x : xs) {
            for (int y : ys) {
                if(map.at(x, y).getGround().blocksThrownObjects())
                    return null;
            }
        }
        return action;
    }

}
