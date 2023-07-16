package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Abilities;

import java.util.Random;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 */

public class ResurrectAction extends Action {

    /**
     * Attribute: An integer representing the number of resurrections
     */
    private int resurrectTime;

    /**
     * Attribute: An integer representing the number of chances it takes for an actor to die
     */
    private int chancesToDie;

    /**
     * Constructor for the Resurrect Action class
     *
     * @param resurrectTime The number of resurrections
     * @param chancesToDie The number of chances it takes for an actor to die
     */
    public ResurrectAction(int resurrectTime, int chancesToDie) {
        this.resurrectTime = resurrectTime;
        this.chancesToDie = chancesToDie;
    }

    /**
     * Performs the resurrecting action of an actor after dying
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Returns a description to let the user know if the actor has resurrected or died
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random rand = new Random();
        int int_random = rand.nextInt(chancesToDie);
        if (int_random==1 && this.resurrectTime>0 && actor.hasCapability(Abilities.REVIVE)){
            actor.heal(1000);
            this.resurrectTime -= 1;
            return actor + " is resurrected.";
        }else{
            map.removeActor(actor);
            return actor + "is dead";
        }

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
