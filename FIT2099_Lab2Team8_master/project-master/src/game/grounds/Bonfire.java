package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.RestAction;

/**
 * Bonfire ground class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Ground
 * @see RestAction
 */
public class Bonfire extends Ground {
    /**
     * Constructor for the Bonfire class
     *
     */
    public Bonfire() {
        super('B');
    }

    /**
     * Gets a list of available actions that can be executed in this class
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Returns a Rest Action
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
//        if (actor.hasCapability(Abilities.REST)){
        return new Actions(new RestAction(location));
//        }
    }

}
