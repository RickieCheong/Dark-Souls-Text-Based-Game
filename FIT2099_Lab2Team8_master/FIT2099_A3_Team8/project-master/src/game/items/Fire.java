package game.items;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Item
 */

public class Fire extends Item {

    /**
     * Attribute that represents how many times a type of ground can burn
     */
    private int burns;

    /**
     * Constructor of the Ground class
     */
    public Fire() {
        super("Fire", 'V', false);
    }

    /**
     * If the number of burns exceeds 3, it will replace the type of ground with dirt, otherwise it continues to burn
     * (display fire)
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) { // will it affect the rest of the grounds (floor, valley)
        this.burns++;
        if (this.burns > 3) {
            location.removeItem(this); // which floor
        } else {
            if(location.containsAnActor()) {
                location.getActor().hurt(25);
            }
        }
    }


}
