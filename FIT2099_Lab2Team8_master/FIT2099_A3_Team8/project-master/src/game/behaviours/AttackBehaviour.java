package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see game.interfaces.Behaviour
 * @see game.actions.AttackAction
 */

/**
 * A class that generates an AttackAction if a current Actor is standing next to an
 * Actor that they are allowed to attack.
 */

public class AttackBehaviour implements Behaviour {

    /**
     * Attribute: Status type called attackableActor
     */
    private Status attackableActor;

    /**
     *  Constructor of Attack Behaviour class
     *  Sets the actor that the owner of this behaviour is allowed to attack.
     *
     * @param attackableActor Actor descriptor for Actors that can be attacked.
     */
    public AttackBehaviour(Status attackableActor) {
        this.attackableActor = attackableActor;
    }


    /**
     * This returns an attack action that attacks the adjacent areas of the attackable Actor.
     * The actors are attackable if their Status matches the "alive status" set.
     * It can only attack actors that are directly next to or diagonal to the actor.
     *
     * @return an AttackAction on an Actor that is still alive
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Checks to see if there is an attackable Actor near current Actor
        if(map.locationOf(actor)!=null) {
            List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
            Collections.shuffle(exits);

            for (Exit exit : exits) {
                if (!(exit.getDestination().containsAnActor())) {
                    continue;
                }
                if (exit.getDestination().getActor().hasCapability(attackableActor)) {
                    return new AttackAction(exit.getDestination().getActor(), "around");
                }
            }

            Actions actions = new Actions();
            for(Item item : actor.getInventory()){
                actions.add(item.getAllowableActions());
            }
            if(actions.size() != 0) return actions.get(0);
        }
        return null;
    }
}
