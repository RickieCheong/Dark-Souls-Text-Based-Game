package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Drinkable;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 */

public class DrinkAction extends Action {
    /**
     * The Drink Item
     */
    private Drinkable drinkItem;
    /**
     * Maximum Drink Time of the DrinkItem
     */
    private int maxDrinkTime;
    /**
     * DrinkTime of the drinkItem
     */
    private int drinkTime;
    /**
     * The healPercentage of the DrinkItem
     */
    private int healPercentage;

    /**
     * The Constructor of DrinkAction
     *
     * @param drinkItem DrinkTime of the drinkItem
     * @param maxDrinkTime Maximum Drink Time of the DrinkItem
     * @param healPercentage The healPercentage of the DrinkItem
     */
    public DrinkAction(Drinkable drinkItem, int maxDrinkTime, int healPercentage) {
        this.drinkItem = drinkItem;
        this.maxDrinkTime = maxDrinkTime;
        this.drinkTime = maxDrinkTime;
        this.healPercentage = healPercentage;
    }

    /**
     * To perform the Drinking Action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string of the actor drinking
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(this.drinkTime>0 && this.drinkTime<=this.maxDrinkTime){
            actor.heal(this.healPercentage);
            this.drink();
            return actor+ " drink the " + drinkItem;
        }
        else {
            return "No more times for drinking";
        }
    }

    /**
     * DrinkTime decrease
     */
    public void drink(){
        this.drinkTime -= 1;
    }

    /**
     * The description of the menu for the player
     *
     * @param actor The actor performing the action.
     * @return a string of menu description
     */
    @Override
    public String menuDescription(Actor actor) {
            return actor.toString() + " drinks Estus Flask (" + this.drinkTime+ "/" + this.maxDrinkTime + ")";

    }

    /**
     * The hot key for drinking in menu
     *
     * @return a string of hotkey
     */
    @Override
    public String hotkey() {
        return "a";
    }

}
