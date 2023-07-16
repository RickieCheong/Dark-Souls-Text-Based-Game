package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Util;
import game.actions.ResetAction;
import game.enums.Status;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see edu.monash.fit2099.engine.Ground
 */

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	/**
	 * Constructor for the Valley class
	 */
	public Valley() {
		super('+');
	}

	/**
	 *
	 * Checks to see if an actor is able to enter this type of ground
	 *
	 * @param actor the Actor to check
	 * @return True if the actor is allowed to enter
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return actor.hasCapability(Status.FALL_TO_VALLEY);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);
		if(location.containsAnActor()) {
			if (location.getActor().hasCapability(Status.FALL_TO_VALLEY)) {
				location.getActor().addCapability(Status.VALLEY_DIE);
				location.getActor().hurt(1000);
				new ResetAction().execute(location.getActor(), location.map());
				Display display = new Display();
				display.println(new Util().displayValley());
			}
		}
	}

	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
