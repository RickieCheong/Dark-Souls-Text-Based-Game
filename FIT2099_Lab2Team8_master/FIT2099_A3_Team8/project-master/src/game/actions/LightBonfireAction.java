package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.managers.BonfireManager;
import game.characters.Player;
import game.Util;
import game.enums.Status;
import game.grounds.Bonfire;

/**
 * @author Rickie Cheong Jun Weng
 * @version 1.0
 * @see Action
 */

public class LightBonfireAction extends Action {

    /**
     * Instances of Bonfire that can be created
     */
    private Bonfire bonfire;

    /**
     * value of type Locations which stores the locations of the bonfires
     */
    private Location bonfireLocation;

    /**
     * Bonfire manager instance grabbed from BonfireManager class
     */
    private BonfireManager bonfireManager = BonfireManager.getInstance();

    /**
     * Player instance grabbed from Player class.
     */
    private Player player = Player.getInstance();


    /**
     * Constructor
     *
     * @param bonfire the bonfire that is on the map
     * @param location  location of the bonfire on the map
     */
    public LightBonfireAction(Bonfire bonfire, Location location) {
        this.bonfire = bonfire;
        this.bonfire.addCapability(Status.BONFIRE_LIT);
        bonfireLocation = location;

    }

    /**
     * Allows the player to light up the bonfires
     *
     * Overrides Action.execute()
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        bonfire.setLitBonfire(true);
        bonfireManager.addBonfireLocations(bonfireLocation);
        player.setRespawnLocation(bonfireLocation);
        return new Util().displayBonfireLit();
    }

    /**
     * returns a description that indicates which bonfire has been lit by the user
     *
     * @param actor The actor performing the action.
     * @return String sentence. Exp: Unkindled(Player) lights up the Anor Londo Bonfire
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " light up the " + this.bonfire.getBonfireName() + " bonfire";
    }
}
