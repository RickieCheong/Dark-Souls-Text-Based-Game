package game.grounds;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.Undead;

import java.util.Random;

/**
 * @author Chok Ming Jie
 * @version 1.0
 * @see Ground
 */

public class Cemetery extends Ground {

    /**
     * Attribute: An integer representing the spawn probability of an actor
     */
    private int spawnProbability = 4;
    /**
     * Constructor for the Cemetery class
     *
     */
    public Cemetery() {
        super('c');
    }

    /**
     * Performs the logic of the respawn ability of an actor
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {

        super.tick(location);
//        for (Exit exit : location.getExits()) {
//            Location destination = exit.getDestination();
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(location.getExits().size());

        Exit exit = location.getExits().get(index);
        Location destination = exit.getDestination();

        Random rand = new Random();
        int int_random = rand.nextInt(spawnProbability);
        if (int_random == 1 && !destination.containsAnActor()) {
            destination.addActor(new Undead());
        }

        }
}
