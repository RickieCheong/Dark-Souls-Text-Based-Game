package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class Wall extends Ground {

	/**
	 * Constructor for the Wall class
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Checks to see if an actor is able to enter this type of ground
	 *
	 * @param actor the Actor to check
	 * @return True if the actor is allowed to enter
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
	 *
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
