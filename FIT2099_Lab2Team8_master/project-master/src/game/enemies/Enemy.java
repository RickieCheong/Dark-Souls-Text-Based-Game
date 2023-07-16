package game.enemies;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

/**
 * Enemy Class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see Actor
 * @see Soul
 * @see Resettable
 * @see Skeleton
 * @see Behaviour
 * @see FollowBehaviour
 */
public abstract class Enemy extends Actor implements Soul, Resettable {
    /**
     * Attribute: List of Behaviours of the Enemy
     */
    ArrayList<Behaviour> behaviours = new ArrayList<>();

    /**
     * Attribute: FollowBehaviour for the Enemy
     */
    private FollowBehaviour followBehaviour;

    /**
     * Constructor.for Enemy Class
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.DEAD);
    }

    /**
     * Get the Action from each behaviour with priority
     *
     * @param actions    collection of possible Actions for this Enemy
     * @param lastAction The Action this Enemy took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Enemy
     * @param display    the I/O object to which messages may be written
     * @return Action that the Enemy can execute
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        for(game.interfaces.Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The Allowable actions that can be done by the Player when get into the adjacent square of Enemy
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return All the Actions that can be done by the Player
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if(followBehaviour == null){
                this.followBehaviour = new FollowBehaviour(otherActor);
                this.behaviours.add(0, this.followBehaviour);
                this.behaviours.add(1, new AttackBehaviour(Status.HOSTILE_TO_ENEMY));
            }
            Weapon weapon = otherActor.getWeapon();
            WeaponAction weaponAction = weapon.getActiveSkill(this, direction);
            if(weaponAction != null){
                actions.add(weaponAction);
            }
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * The toString for all the Enemy
     *
     * @return A string that describe the Enemy
     */
    @Override
    public String toString(){
        return name + "(" + this.hitPoints + "/" + this.maxHitPoints +")";
    }

    /**
     * Check that the instance of this class will be existed even after reset
     *
     * @return true for existed
     */
    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * Make the class to become Soul Class
     *
     * @return Soul object
     */
    @Override
    public Soul asSoul() {
        return super.asSoul();
    }

    /**
     * This will be run when game is reset
     *
     * @param map The game map
     */
    @Override
    public void resetInstance(GameMap map) {
        if (this.behaviours.contains(followBehaviour)) {
            behaviours.remove(followBehaviour);
        }
    }
}
