package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;
import game.items.Token;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 * @see Token
 * @see TokenDropAction
 */

public class TokenDropAction extends Action {

    /**
     * Attribute: Location type
     */
    private Location tokenLocation;

    /**
     * Constructor of the Token Drop Action class
     *
     * @param tokenLocation The location to put the token
     */
    public TokenDropAction(Location tokenLocation) {
        this.tokenLocation = tokenLocation;
    }

    /**
     * Performs the action of dropping a token when a player has died
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a descriptive message that a token has been dropped/created
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Token token = new Token(actor.asSoul());
        token.addCapability(Status.EXIST_TOKEN);
        map.at(this.tokenLocation.x(), this.tokenLocation.y()).addItem(token);
        actor.asSoul().transferSouls(token.asSoul());
        return "Token Of Soul " + "has created";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return Returns null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
