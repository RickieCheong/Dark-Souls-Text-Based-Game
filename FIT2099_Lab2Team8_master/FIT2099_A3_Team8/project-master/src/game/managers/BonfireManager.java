package game.managers;


import edu.monash.fit2099.engine.*;
import game.actions.MovePetPlayerAction;
import game.characters.Pet;
import game.enums.Status;
import game.grounds.Bonfire;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rickie Cheong Jun Weng
 * @version 1.0
 *
 * @version 1.1
 * updated BonfireManager to be static and use singleton method
 */

public class BonfireManager {

    /**
     * The List of the Bonfire Locations
     */
    private static List<Location> bonfireLocation; // location of bonfires

    /**
     * bonfire Manager instance created in BonfireManager class
     */
    private static BonfireManager bonfireManager = new BonfireManager();

    /**
     * The pet for manage in bonfire
     */
    private Pet pet;

    /**
     * Constructor of the bonfire Manager
     */
    private BonfireManager(){
        bonfireLocation = new ArrayList<>();
    }

    /**
     * adds the location of the bonfire into a list
     *
     * @param location location of the bonfire
     */
    public void addBonfireLocations(Location location){
        bonfireLocation.add(location);
    }

    /**
     * singleton of bonfire manager
     *
     * @return an instance of bonfire manager
     */
    public static BonfireManager getInstance(){
        return bonfireManager;
    }

    /**
     * Shows a list of locations that you can teleport to from bonfire
     *
     * @param location location of the bonfire
     * @return a list of possible actions for teleporting
     */
    public Actions getTeleportBonfire(Location location){

        Actions actions = new Actions();

        for (int i = 0; i < bonfireLocation.size(); i++){

            if (location.getGround() != bonfireLocation.get(i).getGround()){
                Ground ground = location.getGround();

                if(ground.hasCapability(Status.BONFIRE_LIT)) {
                    Bonfire temp = (((Bonfire) bonfireLocation.get(i).getGround()));
                    String teleportBonfireName = temp.getBonfireName();
//                    Action teleportAction = new MoveActorAction(bonfireLocation.get(i), teleportBonfireName);
                    Action teleportAction = new MovePetPlayerAction(this.pet, bonfireLocation.get(i), teleportBonfireName);
                    actions.add(teleportAction);
                }
            }
        }
        return actions;
    }

    /**
     * Set the pet in the manager
     *
     * @param pet The pet that in the game
     */


    public void setPet(Pet pet){
        this.pet = pet;
    }




}