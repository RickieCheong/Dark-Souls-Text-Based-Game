package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.managers.BonfireManager;
import game.actions.LightBonfireAction;
import game.actions.RestAction;
import game.enums.Status;

/**
 * Bonfire ground class
 *
 * @author Chok Ming Jie
 * @version 1.0
 * @see edu.monash.fit2099.engine.Ground
 * @see game.actions.RestAction
 */

/**
 * @author Rickie Cheong Jun Weng
 * @version 1.1
 *
 * added changes to constructor and allowableActions
 * added everything related to bonfireName, and litBonfire
 *
 * @version 1.2
 * changed allowableActions class
 *
 */
public class Bonfire extends Ground {

    /**
     * Boolean value that indicates if a bonfire has been lit
     */
    private Boolean litBonfire;

    /**
     * String value that indicates the name of the bonfire
     */
    private final String bonfireName;

    /**
     * Bonfire manager instance grabbed from BonfireManager class
     */
    private BonfireManager bonfireManager = BonfireManager.getInstance();


    /**
     * Constructor for the Bonfire class
     *
     * @param bonfireName The name of the map that the bonfire is in
     */

    public Bonfire(String bonfireName) {
        super('B');
        litBonfire = false;
        this.bonfireName = bonfireName;
    }

    /**
     *  Return state of lit of the bonfire
     *
     * @return True or False value whether the bonfire is lit
     */
    public Boolean getLitBonfire() {
        return litBonfire;
    }

    /**
     * Setter for the litBonfire variable to indicate if bonfire has been lit
     *
     * @param litBonfire Boolean value of whether the bonfire has been lit or not
     */
    public void setLitBonfire(Boolean litBonfire) {
        this.litBonfire = litBonfire;
    }

    /**
     * getter for bonfireName variable
     *
     * @return String value of the name of the bonfire
     */
    public String getBonfireName() {
        return bonfireName;
    }

    /**
     * Gets a list of available actions that can be executed in this class
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Returns all possible actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
//        if (actor.hasCapability(Abilities.REST)){
        Actions actions = new Actions();

        if (getLitBonfire() && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new RestAction(location, this.bonfireName));
            actions.add(bonfireManager.getTeleportBonfire(location));
        }

        if (!getLitBonfire()) {
            actions.add(new LightBonfireAction(this, location));
        }

        return actions;
    }

    /**
     * The method only allow certain actor to step on it
     *
     * @param actor the Actor to check
     * @return true for able step on
     */

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
}
