package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.actions.ThunderBoltAction;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Behaviour
 * @see ThunderBoltAction
 */

public class ThunderBoltBehaviour implements Behaviour {

    /**
     * Status of the attack target
     */
    private Status thunderStatus;

    /**
     * Constructor of the ThunderBoltBehaviour
     *
     * @param status Status of the attack target
     */
    public ThunderBoltBehaviour(Status status) {
        this.thunderStatus = status;
    }

    /**
     * Gets the action of ThunderBoltAction the target
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return An Action that will be performed
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Random rand = new Random();
        int int_random = rand.nextInt(3);
        if(int_random == 1){
            if(map.locationOf(actor)!=null) {
                List<Exit> exits = new ArrayList<>(map.locationOf(actor).getExits());
                Collections.shuffle(exits);

                for (Exit exit : exits) {
                    if (!(exit.getDestination().containsAnActor())) {
                        continue;
                    }
                    if (exit.getDestination().getActor().hasCapability(thunderStatus)) {
                        return new ThunderBoltAction(exit.getDestination().getActor());
                    }
                }
            }
        }
        return null;
    }
}
