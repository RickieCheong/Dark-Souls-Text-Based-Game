package game.items;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Item
 * @see Soul
 *
 */

public class Token extends Item implements Soul, Resettable {

    /**
     * Attribute: An integer representing the number of souls
     */
    private int soul;
    private Location previousToken;

    /***
     * Constructor for the Token class
     * @param soul The number of souls
     */
    public Token(Soul soul) {
        super("Token Of Soul", '$', true);
        registerInstance();
    }

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soul);
    }

    /**
     * Increase souls to current instance's souls.
     *
     * @param souls number of souls to be incremented.
     * @return Returns true if adding the number of souls is successful
     */
    @Override
    public boolean addSouls(int souls) {
        soul+=souls;
        return true;
    }

    /**
     * Allow other classes to deduct the number of this instance's souls
     *
     * @param souls number souls to be deducted
     * @return Returns false if deducting the number of souls is unsucessful
     */
    @Override
    public boolean subtractSouls(int souls) {
        soul -= souls;
        return false;
    }

    /**
     * Method to allow whether an item can be dropped or not
     *
     * @param actor an actor that will interact with this item
     * @return Returns null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public void tick(Location currentLocation) {
        if(!this.hasCapability(Status.EXIST_TOKEN)){
            currentLocation.removeItem(this);
        }
    }

    /**
     * Makes the current class to act as a Soul class
     *
     * @return Returns the asSoul()
     */
    @Override
    public Soul asSoul() {
        return super.asSoul();
    }

    @Override
    public void resetInstance(GameMap map) {
        if(this.hasCapability(Status.EXIST_TOKEN)){
            this.removeCapability(Status.EXIST_TOKEN);
        }
    }

    @Override
    public boolean isExist() {
        return false;
    }
}
