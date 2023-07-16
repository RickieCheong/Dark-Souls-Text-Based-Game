package game.actions;

import edu.monash.fit2099.engine.*;
import game.Util;
import game.enums.Abilities;
import game.enums.Status;

import java.util.Random;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see edu.monash.fit2099.engine.Action
 */

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor for the Attack Action class
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Performs the Attack Action when an actor is detected
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			//TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
			if (target.hasCapability(Status.DEAD)){
				if(actor.asSoul() != null && target.asSoul() != null){
					// transfer current target oxygen level to the actor if possible.
					target.asSoul().transferSouls(actor.asSoul());
				}
				if(!target.hasCapability(Abilities.REVIVE)){
					if(target.hasCapability(Status.WEAK_TO_STORM_RULER)){
						Display display = new Display();
						display.println(new Util().displayLordOfCinderWord());
					}
					map.removeActor(target);
				}
			}
			else{
				new ResetAction().execute(target, map);
			}
			result += System.lineSeparator() + target + " is killed.";
		}
		return result;
	}


	/**
	 * Returns a description to let the user know that the attack action has been executed
	 *
	 * @param actor The actor performing the action.
	 * @return Returns a descriptive message
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
