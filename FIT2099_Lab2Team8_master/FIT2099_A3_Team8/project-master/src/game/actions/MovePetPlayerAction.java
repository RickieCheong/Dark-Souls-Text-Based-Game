package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.characters.Pet;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see edu.monash.fit2099.engine.Action
 */

public class MovePetPlayerAction extends Action {

    /**
     * The Pet that will be transfer to another map
     */
    private Pet pet;

    /**
     * The Location the actor will be move to
     */
    protected Location moveToLocation;

    /**
     * The word of the Direction
     */
    protected String direction;

    /**
     * Constructor to create an Action that will move the Actor and Pet to a Location in a given Direction.
     *
     * Note that this constructor does not check whether the supplied Location is actually in the given direction
     * from the Actor's current location.  This allows for (e.g.) teleporters, etc.
     *
     * @param moveToLocation Location to move to
     * @param direction String describing the direction to move in, e.g. "north"
     */
    public MovePetPlayerAction(Pet pet, Location moveToLocation, String direction) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.pet = pet;
    }

    /**
     * Allow the Actor and Pet to be moved.
     *
     * Overrides Action.execute()
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, moveToLocation);
        if(map.contains(this.pet)){
            map.moveActor(this.pet, moveToLocation.map().at(this.moveToLocation.x()+1, this.moveToLocation.y()));
        }
        return menuDescription(actor);
    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player moves east"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves " + direction;
    }

}
