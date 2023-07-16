package game.grounds;

import edu.monash.fit2099.engine.*;
import game.actions.MovePetPlayerAction;
import game.characters.Pet;
import game.enums.Status;

/**
 * FogDoor ground class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Ground
 */

public class FogDoor extends Ground {

    /**
     * The pet to the transfer pass too
     */
    private  Pet pet;

    /**
     * The Location of the Actor will move to
     */
    private Location location;

    /**
     * The string of word of the location
     */
    private String direction;

    /**
     * Constructor of the FogDoor
     *
     * @param pet The pet to move too
     */
    public FogDoor(Pet pet) {
        super('=');
        this.pet = pet;
    }

    /**
     * Checks to see if an actor is able to enter this type of ground
     *
     * @param actor the Actor to check
     * @return True if the actor is allowed to enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.FALL_TO_VALLEY);
    }

    /**
     * The allowable actions that from this type of ground
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions for the player to choose
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if(location.map().isAnActorAt(location) && location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
            return new Actions(new MovePetPlayerAction(this.pet, this.location, this.direction));
        }
        return super.allowableActions(actor, location, direction);
    }

    /**
     * Save the location to pass to
     *
     * @param transferLocation The location to be transfer
     * @param direction String of the location
     */
    public void addLocation(Location transferLocation, String direction){
        this.location = transferLocation;
        this.direction = direction;
    }

}
