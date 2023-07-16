package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Corpse ground class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Ground
 */

public class Corpse extends Ground {

    /**
     * The durability of the corpse on the ground
     */
    private int durability;

    /**
     * Constructor of the Corpse
     *
     */
    public Corpse() {
        super('%');
        durability = 5;
    }

    /**
     * Checks to see if an actor is able to enter this type of ground
     *
     * @param actor the Actor to check
     * @return True if the actor is allowed to enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * The turn of this corpse will run
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        this.durability -= 1;
        if (this.durability < 0){
            location.setGround(new Dirt());
        }
    }
}
