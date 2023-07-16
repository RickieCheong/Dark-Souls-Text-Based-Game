package game.characters;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

/**
 * Class representing the Pet.

 * @author Chok Ming Jie
 * @version 1.0
 * @see Actor
 * @see game.interfaces.Behaviour
 * @see Action
 *
 */

public abstract class Pet extends Actor implements Soul, Resettable {

    /**
     * The master of the Pet
     */
    private Actor master;

    /**
     * Attribute: List of Behaviours of the Enemy
     */
    ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * The follow behaviour for the pet to master
     */
    private FollowBehaviour followBehaviour;

    /**
     * Constructor of the Pet
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param master      the master of the pet
     */
    public Pet(String name, char displayChar, int hitPoints, Actor master) {
        super(name, displayChar, hitPoints);
        this.master = master;
        this.addCapability(Status.PET);
    }

    /**
     * Playturn for the Pet to take turn in the game
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return An Action for the Pet to execute
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        for(game.interfaces.Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The Allowable Actions for the pet to do
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return  Actions that the pet can be done
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if(followBehaviour == null){
                this.followBehaviour = new FollowBehaviour(otherActor);
                this.behaviours.add(new AttackBehaviour(Status.DEAD));
                this.behaviours.add(this.followBehaviour);
            }
        }
        return actions;
    }

    /**
     * Make Pet as a Soul Object
     *
     * @return A Soul Object
     */
    @Override
    public Soul asSoul() {
        return super.asSoul();
    }

    /**
     * The Description of the Pet
     *
     * @return A string of description of Pet
     */
    @Override
    public String toString() {
        return name + " (" + this.hitPoints + "/" + this.maxHitPoints + ")" ;
    }
}
