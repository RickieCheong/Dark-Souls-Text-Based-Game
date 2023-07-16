package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Action
 * @see Behaviour
 */

public class WanderBehaviour extends Action implements Behaviour {

	/**
	 * An instance of a Random object
	 */
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		if(map.locationOf(actor)!=null) {
			for (Exit exit : map.locationOf(actor).getExits()) {
				Location destination = exit.getDestination();
				if (destination.canActorEnter(actor)) {
					actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
				}
			}
		}
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

	/**
	 * Returns a descriptive message
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return Returns a message of the noise an actor makes
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 * Returns a message of the noise an actor makes
	 *
	 * @param actor The actor performing the action.
	 * @return Returns a message of the noise an actor makes
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
