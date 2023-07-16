package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.items.Token;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see edu.monash.fit2099.engine.Action
 * @see Token
 */

public class DieAction extends Action {

    /**
     * Attribute: Location type
     */
    private Location tokenLocation;

    /**
     * The Location that the actor will be respawned
     */
    private Location respawnLocation;

    /**
     * Constructor of the Die Action class
     *
     * @param tokenLocation The location to put the token
     */
    public DieAction(Location tokenLocation, Location respawnLocation) {
        this.tokenLocation = tokenLocation;
        this.respawnLocation = respawnLocation;
    }

    /**
     * Allow the token to be created and the actor will be moved to respawn location
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A descriptive String of the game is reset
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.VALLEY_DIE)) {
            actor.removeCapability(Status.VALLEY_DIE);
            Token token = new Token(actor.asSoul());
            token.addCapability(Status.EXIST_TOKEN);
            map.at(this.tokenLocation.x(), this.tokenLocation.y()).addItem(token);
            actor.asSoul().transferSouls(token.asSoul());
        }else if(!actor.hasCapability(Status.REST)) {
            Token token = new Token(actor.asSoul());
            token.addCapability(Status.EXIST_TOKEN);
            map.locationOf(actor).addItem(token);
            actor.asSoul().transferSouls(token.asSoul());
        }else{
            actor.removeCapability(Status.REST);
        }
        actor.removeCapability(Status.RESET);
        map.moveActor(actor, respawnLocation.map().at(this.respawnLocation.x(), this.respawnLocation.y()));
        return "The Game is Reset";
    }

    /**
     * The description of the menu for the player
     *
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
