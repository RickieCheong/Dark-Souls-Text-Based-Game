package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.enemies.Skeleton;
import game.enemies.YhormTheGiant;
import game.grounds.*;
import game.weapons.StormRuler;

import java.util.Arrays;
import java.util.List;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley());

			List<String> map = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"........................................................................++......",
					".........................................................................+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					".............................++.......+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...................+++...............",
					"..................................#_______#......................+++............",
					"...........++.....................#_______#.......................+.............",
					".........+++......................#_______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Player player = new Player("Unkindled (Player)", '@', 100);
			world.addPlayer(player, gameMap.at(38, 12));
			player.addLocation(gameMap, 38, 11);

			// Place Yhorm the Giant/boss in the map
			YhormTheGiant yhormTheGiant = new YhormTheGiant();
			gameMap.at(6, 25).addActor(yhormTheGiant);
			yhormTheGiant.addLocation(gameMap,6,25);
			gameMap.at(7, 25).addItem(new StormRuler());

			//Place Bonfire
			gameMap.at(38, 11).setGround(new Bonfire());

			//Place Vendor
			gameMap.at(37,11).addActor(new Vendor());

			//Place cemetery
			gameMap.at(5, 4).setGround(new Cemetery());
			gameMap.at(25, 14).setGround(new Cemetery());
			gameMap.at(55, 24).setGround(new Cemetery());
			gameMap.at(19, 23).setGround(new Cemetery());
			gameMap.at(44, 8).setGround(new Cemetery());
			gameMap.at(12, 10).setGround(new Cemetery());
			gameMap.at(33, 4).setGround(new Cemetery());
			gameMap.at(30, 9).setGround(new Cemetery());
			gameMap.at(50, 5).setGround(new Cemetery());
			gameMap.at(48, 20).setGround(new Cemetery());

			//Place Skeleton
			Skeleton skeleton = new Skeleton();
			gameMap.at(44,4).addActor(skeleton);
			skeleton.addLocation(gameMap,44, 12);

			Skeleton skeleton1 = new Skeleton();
			gameMap.at(52,9).addActor(skeleton1);
			skeleton1.addLocation(gameMap,16, 16);

			Skeleton skeleton2 = new Skeleton();
			gameMap.at(20,6).addActor(skeleton2);
			skeleton2.addLocation(gameMap,20,18);

			Skeleton skeleton3 = new Skeleton();
			gameMap.at(42,18).addActor(skeleton3);
			skeleton3.addLocation(gameMap,42,18);

			Skeleton skeleton4 = new Skeleton();
			gameMap.at(4,10).addActor(skeleton4);
			skeleton4.addLocation(gameMap,40,16);

			Skeleton skeleton5 = new Skeleton();
			gameMap.at(50,16).addActor(skeleton5);
			skeleton5.addLocation(gameMap,8,16);

			Skeleton skeleton6 = new Skeleton();
			gameMap.at(55,9).addActor(skeleton6);
			skeleton6.addLocation(gameMap,20,18);

			Skeleton skeleton7 = new Skeleton();
			gameMap.at(33,2).addActor(skeleton7);
			skeleton7.addLocation(gameMap,42,18);

			Skeleton skeleton8 = new Skeleton();
			gameMap.at(38,8).addActor(skeleton8);
			skeleton8.addLocation(gameMap,40,16);

			Skeleton skeleton9 = new Skeleton();
			gameMap.at(22,16).addActor(skeleton9);
			skeleton9.addLocation(gameMap,8,16);

			world.run();

	}
}
