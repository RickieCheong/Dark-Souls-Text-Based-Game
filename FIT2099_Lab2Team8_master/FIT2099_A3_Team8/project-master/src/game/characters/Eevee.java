package game.characters;

import edu.monash.fit2099.engine.*;
import game.behaviours.ThunderBoltBehaviour;
import game.enums.Status;
import game.interfaces.Soul;

/**
 * Class representing the Eevee.

 * @author Chok Ming Jie
 * @version 1.0
 * @see Actor
 * @see game.interfaces.Behaviour
 * @see Action
 *
 */

public class Eevee extends Pet{

    /**
     * The soul of the Eevee
     */
    private int soul;

    /**
     * The master actor of the Eevee
     */
    private Player master;

    /**
     * The location for the pet to respawn
     */
    private Location respawnLocation;

    /**
     * Constructor of the Eevee
     *
     * @param actor The master of the Eevee
     */
    public Eevee(Player actor) {
        super("Eevee", 'E', 80, actor);
        this.soul = 0;
        this.master = actor;
        this.behaviours.add(new ThunderBoltBehaviour(Status.DEAD));
        registerInstance();
    }



    /**
     * Location for storing the Player Location
     *
     * @param map game Map
     * @param x x position
     * @param y y position
     */
    public void addLocation(GameMap map, int x, int y){
        this.respawnLocation = new Location(map, x, y);
    }

    /**
     * The PlayTurn for the Eevee to be load when game is running
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action for Eevee to choose
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.asSoul().transferSouls(this.master.asSoul());
        this.setPetRespawnLocation();
        if(this.hasCapability(Status.PET_RESET)){
            this.removeCapability(Status.PET_RESET);
            map.moveActor(this, respawnLocation.map().at(respawnLocation.x() + 1 ,respawnLocation.y()));
            return new DoNothingAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * The normal attack by Eevee
     *
     * @return An Intrinsic weapon of Eevee
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "headbutt");
    }

    /**
     * To transfer Eevee soul to others
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(soul);
        soul = 0;
    }

    /**
     * Add soul to Eevee
     *
     * @param souls number of souls to be incremented.
     * @return true for able add soul
     */
    @Override
    public boolean addSouls(int souls) {
        soul += souls;
        return true;
    }

    /**
     * The Actor that will exist forever
     *
     * @return true is exist forever
     */
    @Override
    public boolean isExist() {
        return false;
    }

    public void setPetRespawnLocation(){
        this.respawnLocation = new Location(this.master.getRespawnLocation().map(), this.master.getRespawnLocation().x(), this.master.getRespawnLocation().y());
    }
    /**
     * The reset for the Eevee
     *
     * @param map: Map of the game
     */
    @Override
    public void resetInstance(GameMap map) {
        if(!this.isConscious()){
            map.moveActor(this, respawnLocation.map().at(respawnLocation.x() + 1 ,respawnLocation.y()));
        }
        this.heal(1000);
        this.addCapability(Status.PET_RESET);
    }

}
