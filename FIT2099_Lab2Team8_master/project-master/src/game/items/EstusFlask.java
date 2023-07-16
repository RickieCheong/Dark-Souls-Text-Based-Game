package game.items;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actions.DrinkAction;
import game.interfaces.Drinkable;
import game.interfaces.Resettable;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Item
 * @see Resettable
 * @see Drinkable
 * @see DrinkAction
 */

public class EstusFlask extends Item implements Resettable, Drinkable {

    /**
     * Attribute: An integer representing the maximum number of times an actor can drink
     */
    private int maxDrinkTime = 3;

    /***
     * Constructor for the Estus Flask class
     */
    public EstusFlask() {
        super("Estus Flask", 'E', false);
        this.allowableActions.add(new DrinkAction(this, maxDrinkTime, 40));
        registerInstance();
    }

    /**
     * Resets the number of drink times
     *
     * @param map Map of where the reset instance takes place
     */
    @Override
    public void resetInstance(GameMap map) {
        this.allowableActions.clear();
        this.allowableActions.add(new DrinkAction(this, maxDrinkTime, 40));
    }

    /**
     * Checks to see if this item exists
     *
     * @return Returns true
     */
    @Override
    public boolean isExist() {
        return true;
    }

}
