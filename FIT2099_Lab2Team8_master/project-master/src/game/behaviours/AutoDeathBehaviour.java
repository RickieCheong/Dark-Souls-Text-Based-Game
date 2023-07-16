package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.AutoDieAction;
import game.interfaces.Behaviour;

import java.util.Random;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Behaviour
 * @see AutoDieAction
 */
public class AutoDeathBehaviour implements Behaviour {

    /**
     * Attribute: An integer representing the number of chances it takes for an actor to die
     */
    private int chanceToDie;

    /**
     * Constructor for the Auto Death Behaviour Class
     *
     * @param chanceToDie The number of chances it takes for an actor to die
     */
    public AutoDeathBehaviour(int chanceToDie) {
        this.chanceToDie = chanceToDie;
    }

    /**
     * Creates a new action when a certain condition is met
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Returns an AutoDieAction or null depending on the condition met
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Random rand = new Random();
        int dieChance = rand.nextInt(this.chanceToDie);
        if(dieChance == 5){
            return new AutoDieAction();
        }
        return null;
    }

}
