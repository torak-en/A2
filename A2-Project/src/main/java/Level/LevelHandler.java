package Level;

import Entities.Actors.*;
import Entities.Entity;
import Entities.Items.ComputerChip;
import Entities.Items.Item;
import Entities.Items.Key;
import Entities.Tiles.*;
import Enum.Direction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LevelHandler {

	public Level createLevel(int levelNumber){
		Scanner sc = null;
		try {
			sc = new Scanner(new File("Levels/" + levelNumber + ".txt"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File not found (" + levelNumber + ".txt)");
		}
		String line = sc.nextLine();
		String[] args = line.split(",");

		System.out.println(line);

		String leveName = args[0];
		int levelTime = Integer.parseInt(args[1]);

		line = sc.nextLine();
		args = line.split(",");
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		Tile[][] tiles = new Tile[x][y];

		for (int i = 0; i < y; i++) {
			line = sc.nextLine();
			args = line.split(",");
			for (int j = 0; j < x; j++) {
				tiles[j][i] = checkTileType(args[j],j,i);
			}
		}

		for (Tile[] tiles1: tiles) {
			System.out.println(Arrays.toString(tiles1));
		}

		List<Item> items = new ArrayList<>();
		List<Actor> actors = new ArrayList<>();
		Player player = null;

		while (sc.hasNextLine()){
			line = sc.nextLine();
			Entity e = checkItemOrActorType(line);
			if (e instanceof Actor a){
				actors.add(a);
				if (a instanceof Player p){
					player = p;
				}
			} else if (e instanceof Item i){
				items.add(i);
			} else {
				throw new RuntimeException("Incorrect Class Instance");
			}
		}
		sc.close();
		return new Level(levelTime, leveName, tiles, items, actors,player);
	}


	public Tile checkTileType(String s, int x, int y){
		String[] sp = s.split("");
		return switch (sp[0]) {
			case "W" -> new Wall(x, y);
			case "P" -> new Path(x, y);
			case "D" -> new Dirt(x, y);
			case "E" -> new Exit(x, y);
			case "I" -> new Ice(x, y, sToDirection(sp[1]), sToDirection(sp[2]));
			case "B" -> new Button(x, y, Integer.parseInt(sp[1]));
			case "L" -> new LockedDoor(x, y, sp[1]);
			case "C" -> new ChipSocket(x, y, Integer.parseInt(sp[1]));
			case "A" -> new Water(x, y);
			case "T" -> new Trap(x,y,Integer.parseInt(sp[1]));
			case "#" -> new Empty(x,y);
			default -> throw new IllegalArgumentException("Not a valid String for Tile (" + s + ")");
		};
	}

	public Entity checkItemOrActorType(String s){
		String[] sp = s.split(",");


		final int x = Integer.parseInt(sp[1]);
		final int y = Integer.parseInt(sp[2]);
		return switch (sp[0]) {
			case "CC" -> new ComputerChip(x, y);
			case "K" -> new Key(x, y, sp[3]);
			case "Pl" -> new Player(x, y);
			case "F" -> new Frog(x, y);
			case "Bu" -> new Bug(x, y, sToDirection(sp[3]),sToDirection(sp[4]));
			case "Pi" -> new PinkBall(x, y, sToDirection(sp[3]));
			case "Bl" -> new Block(x, y);
			default -> throw new IllegalArgumentException("Not a valid String for Item or Actor (" + s + ")");
		};
	}

	public Direction sToDirection(String s){
		return switch (s) {
			case "u" -> Direction.UP;
			case "d" -> Direction.DOWN;
			case "r" -> Direction.RIGHT;
			case "l" -> Direction.LEFT;
			default -> throw new IllegalArgumentException("Not a valid direction  (" + s + ")");
		};
	}
}
