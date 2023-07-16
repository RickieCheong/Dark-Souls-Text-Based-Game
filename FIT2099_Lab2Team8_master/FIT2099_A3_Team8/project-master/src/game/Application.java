package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.characters.Eevee;
import game.characters.Player;
import game.characters.Vendor;
import game.enemies.AldrichTheDevourer;
import game.enemies.Skeleton;
import game.enemies.YhormTheGiant;
import game.enums.Status;
import game.grounds.*;
import game.managers.BonfireManager;
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

			List<String> profaneCapitalMap = Arrays.asList(
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
			GameMap gameMap = new GameMap(groundFactory, profaneCapitalMap);
			world.addGameMap(gameMap);

			List<String> anorLondoMap = Arrays.asList(
					"..........+++..........................................+++......",
					"........+..++.........................................++++......",
					"...........+++..........................................+++.....",
					"...........................................................++...",
					"...............................................++............+..",
					"............................+...................................",
					".........+++.......................+++..............+++.........",
					"................................................................",
					".................................................++.............",
					".............+..................................................",
					".........+.........##___########################................",
					"...................#..............._....#......#................",
					".....+++...........#....#....#............+.#..#................",
					"...................#._.........................#................",
					".......++..........#....#...................#..#........++......",
					"...................#..........#.........__.....#.......+........",
					".++++..............##___########################................",
					"................................................................",
					"......++++......................................+++.............",
					"...++...........................................................");
			GameMap anorLondo = new GameMap(groundFactory, anorLondoMap);
			world.addGameMap(anorLondo);

			Player player = Player.getInstance();
			world.addPlayer(player, gameMap.at(38, 12));
			player.setRespawnLocation(gameMap.at(38, 11));

//			Place the Pet in the map
			Eevee eevee = new Eevee(player);
			gameMap.at(39, 12).addActor(eevee);
			eevee.addLocation(gameMap, 39, 12);

			// Place Yhorm the Giant/boss in the map
			YhormTheGiant yhormTheGiant = new YhormTheGiant();
			gameMap.at(6, 25).addActor(yhormTheGiant);
			yhormTheGiant.addLocation(gameMap,6,25);
			gameMap.at(7, 25).addItem(new StormRuler());

			//Place Bonfire
			BonfireManager bonfireManager = BonfireManager.getInstance();
			bonfireManager.setPet(eevee);
			Bonfire profaneCapitalBonfire = new Bonfire("Profane Capital");
			gameMap.at(38, 11).setGround(profaneCapitalBonfire);
			profaneCapitalBonfire.setLitBonfire(true);
			profaneCapitalBonfire.addCapability(Status.BONFIRE_LIT);
			bonfireManager.addBonfireLocations(gameMap.at(38, 11));

			Bonfire anorLondoBonfire = new Bonfire("Anor Londo");
			anorLondo.at(41, 1).setGround(anorLondoBonfire);

//			Place Vendor
			gameMap.at(37,11).addActor(new Vendor());

//			Place FogDoor at Profane Capital
			FogDoor doorAtProfaneCapital = new FogDoor(eevee);
			doorAtProfaneCapital.addLocation(anorLondo.at(38, 0), "to Anor Londo!");
			gameMap.at(38, 25).setGround(doorAtProfaneCapital);

//			Place cemetery
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

//			Place Skeleton
			Skeleton skeleton = new Skeleton();
			gameMap.at(44,4).addActor(skeleton);
			skeleton.addLocation(gameMap,44,4);

			Skeleton skeleton1 = new Skeleton();
			gameMap.at(52,9).addActor(skeleton1);
			skeleton1.addLocation(gameMap,52,9);

			Skeleton skeleton2 = new Skeleton();
			gameMap.at(20,6).addActor(skeleton2);
			skeleton2.addLocation(gameMap,20,6);

			Skeleton skeleton3 = new Skeleton();
			gameMap.at(42,18).addActor(skeleton3);
			skeleton3.addLocation(gameMap,42,18);

			Skeleton skeleton4 = new Skeleton();
			gameMap.at(4,10).addActor(skeleton4);
			skeleton4.addLocation(gameMap,4,10);

			Skeleton skeleton5 = new Skeleton();
			gameMap.at(50,16).addActor(skeleton5);
			skeleton5.addLocation(gameMap,50,16);

			Skeleton skeleton6 = new Skeleton();
			gameMap.at(55,9).addActor(skeleton6);
			skeleton6.addLocation(gameMap,55,9);

			Skeleton skeleton7 = new Skeleton();
			gameMap.at(33,2).addActor(skeleton7);
			skeleton7.addLocation(gameMap,33,2);

			Skeleton skeleton8 = new Skeleton();
			gameMap.at(38,8).addActor(skeleton8);
			skeleton8.addLocation(gameMap,38,8);

			Skeleton skeleton9 = new Skeleton();
			gameMap.at(22,16).addActor(skeleton9);
			skeleton9.addLocation(gameMap,22,16);

//			Place FogDoor at Anor Londo
			FogDoor doorAtAnorLondo = new FogDoor(eevee);
			doorAtAnorLondo.addLocation(gameMap.at(38, 25), "to Profane Capital!");
			anorLondo.at(38, 0).setGround(doorAtAnorLondo);

//			 Place Aldrich the Devourer in Anor Londo
			AldrichTheDevourer aldrichTheDevourer = new AldrichTheDevourer();
			anorLondo.at(34, 14).addActor(aldrichTheDevourer);
			aldrichTheDevourer.addLocation(anorLondo, 35, 14);

			Skeleton skeleton10 = new Skeleton();
			anorLondo.at(30,4).addActor(skeleton10);
			skeleton10.addLocation(anorLondo,30,4);

			Skeleton skeleton11 = new Skeleton();
			anorLondo.at(43,9).addActor(skeleton11);
			skeleton11.addLocation(anorLondo,43,9);

			Skeleton skeleton12 = new Skeleton();
			anorLondo.at(20,6).addActor(skeleton12);
			skeleton12.addLocation(anorLondo,20,6);

			Skeleton skeleton13 = new Skeleton();
			anorLondo.at(44,18).addActor(skeleton13);
			skeleton13.addLocation(anorLondo,44,18);

			Skeleton skeleton14 = new Skeleton();
			anorLondo.at(4,10).addActor(skeleton14);
			skeleton14.addLocation(anorLondo,4,10);

			Skeleton skeleton15 = new Skeleton();
			anorLondo.at(46,19).addActor(skeleton15);
			skeleton15.addLocation(anorLondo,46,19);

			Skeleton skeleton16 = new Skeleton();
			anorLondo.at(40,9).addActor(skeleton16);
			skeleton16.addLocation(anorLondo,40,9);

			Skeleton skeleton17 = new Skeleton();
			anorLondo.at(33,2).addActor(skeleton17);
			skeleton17.addLocation(anorLondo,33,2);

			Skeleton skeleton18 = new Skeleton();
			anorLondo.at(38,8).addActor(skeleton18);
			skeleton18.addLocation(anorLondo,38,8);

			Skeleton skeleton19 = new Skeleton();
			anorLondo.at(15,16).addActor(skeleton19);
			skeleton19.addLocation(anorLondo,15,16);

			anorLondo.at(44, 8).setGround(new Cemetery());
			anorLondo.at(12, 10).setGround(new Cemetery());
			anorLondo.at(33, 4).setGround(new Cemetery());
			anorLondo.at(30, 9).setGround(new Cemetery());
			anorLondo.at(2, 5).setGround(new Cemetery());
			anorLondo.at(10, 19).setGround(new Cemetery());


		world.run();

	}
}
