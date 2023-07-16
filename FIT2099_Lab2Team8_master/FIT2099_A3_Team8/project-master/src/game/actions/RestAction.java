package game.actions;

import edu.monash.fit2099.engine.*;
import game.characters.Player;
import game.enums.Status;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 * @see ResetAction
 */

/**
 * @author Rickie Cheong Jun Weng
 * @version 1.1
 * updated classes
 *
 *
 */

public class RestAction extends Action {

    /**
     * Attribute: Location type
     */
    private Location location;

    /**
     * The name of the bonfire
     */
    private String bonfireName;

    /**
     * To have the instance of the Player
     */
    private Player player = Player.getInstance();

    /**
     * Constructor of the Rest Action class
     *
     *
     * @param location location of where the rest action takes place
     * @param name String value of the name of bonfire
     */
    public RestAction(Location location, String name) {
        this.location = location;
        bonfireName = name;
    }

    /**
     * Performs the rest action of the player when a user chooses to rest at the Bonfire
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a descriptive message to let the user know that the player is resting
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        player.setRespawnLocation(location);
        actor.addCapability(Status.REST);
        new ResetAction().execute(actor, map);
        return actor + " is resting at the bonfire";
    }

    /**
     * Returns a descriptive message to let the user know that it has the option of resting at the map's bonfire
     *
     * @param actor The actor performing the action.
     * @return Returns a prompt to the user to rest at the bonfire
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rest at the " + bonfireName + " bonfire";
    }
}
