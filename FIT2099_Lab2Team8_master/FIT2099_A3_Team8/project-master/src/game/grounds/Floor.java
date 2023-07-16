package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	/**
	 * Constructor of the Floor class
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Checks to see if an actor is able to enter this type of ground
	 *
	 * @param actor the Actor to check
	 * @return True if the actor is allowed to enter
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return (actor.hasCapability(Status.HOSTILE_TO_ENEMY) || actor.hasCapability(Status.PET));
	}

}
