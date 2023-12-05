package Render;

import Entities.Actors.Actor;
import Entities.Actors.Player;
import Entities.Items.Item;
import Entities.Tiles.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Game.Game;
import Level.Level;
import Enum.Direction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Render extends Application{
	private String state = "Level";
	private boolean running = true;
	private final int ns = 1000000000;
	private final int ticksPerSecond = 60;
	private final int nsPerTick = ns / ticksPerSecond;
	private double progress = 0;
	private long startTime = System.nanoTime();

	private Game game;
	private GraphicsContext gc;
	private Level curLevel;
	boolean goUp, goDown, goRight, goLeft;
	private int playerMoveCooldown = 0;
	private GraphicsHandler gameGraphicEngine = new GraphicsHandler();

	@Override
	public void start(Stage stage) throws Exception {
		//initUI(stage);
		gameGraphicEngine.MenuScreenUI(stage);
		game = new Game();
	}

	private void initUI(Stage stage){
		StackPane root = new StackPane();

		Canvas canvas = new Canvas(900, 900);
		gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long l) {

				if (running) {
					long currentTime = System.nanoTime();
					double diff = currentTime-startTime;
					progress += diff / nsPerTick;
					if (progress >=1) {
						progress--;
						tick();
						startTime = currentTime;
					}
				}
			}
		};
		timer.start();

		Scene scene = getScene(root);

		stage.setTitle("AnimationTimer");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();


	}

	private Scene getScene(StackPane root) {
		Scene scene = new Scene(root, 900, 900);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()){
					case UP: goUp = true;
					case W: goUp = true;
					case DOWN: goDown = true;
					case S: goDown = true;
					case RIGHT: goRight = true;
					case D: goRight = true;
					case LEFT: goLeft = true;
					case A: goLeft = true;
					default:
				}
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				switch (keyEvent.getCode()){
					case UP: goUp = false;
					case W: goUp = false;
					case DOWN: goDown = false;
					case S: goDown = false;
					case RIGHT: goRight = false;
					case D: goRight = false;
					case LEFT: goLeft = false;
					case A: goLeft = false;
				}
			}
		});
		return scene;
	}

	private void tick(){
		if (state.equals("Level")) {
			Player p = game.getLevel().getPlayer();
			if (p.getPendingDirection() == null) {
				if (p.checkLocation(inputToDirection(), curLevel)) {
					p.setPendingDirection(inputToDirection());
				}
			}
			System.out.println(p.getPendingDirection());
			p.applyMove();

			playerMoveCooldown--;
			renderLevel();
			game.tick();
		}
	}


	public void renderLevel() {
		curLevel = game.getLevel();
		if (curLevel != null) {
			// Render the current level
			Tile[][] tiles = curLevel.getTileLayer();
			List<Item> items = curLevel.getItemList();
			List<Actor> actors = curLevel.getActorList();

			// Assuming tileSize is known
			int tileSize = 100; // Adjust as needed
			Player player = curLevel.getPlayer();

			int x = player.getX();
			int y = player.getY();
			int minActualX = 0;
			int maxActualX = 0;
			int minActualY = 0;
			int maxActualY = 0;
			int minPossibleX = x-4;
			int maxPossibleX = x+4;
			int minPossibleY = y-4;
			int maxPossibleY = y+4;
			int counterX = 0;
			int counterY = 0;


			for (int i = 1; i < 5; i++) {
				if (x-i >= 0){
					minActualX = x-i;
				}
				if (x+i < tiles.length){
					maxActualX = x+i;
				}
				if (y-i >= 0){
					minActualY = y-i;
				}
				if (y+i < tiles[0].length){
					maxActualY = y+i;
				}
			}

			if (minPossibleX != 0){
				counterX = minPossibleX * -1;
			}
			if (minPossibleY != 0){
				counterY = minPossibleY * -1;
			}

			Image background;
			try {
				background = new Image(new FileInputStream("Textures/black.png"));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
			renderTextures(0,0,1000,1000, background);

			setTileVisible(tiles, x, y);
			setItemsVisible(items, tiles, x, y);
			setActorsVisible(actors, tiles, x, y);

			for (int i = minPossibleX; i < maxPossibleX + 1; i++) {
				for (int j = minPossibleY; j < maxPossibleY + 1; j++) {
					if (i >= minActualX && i <= maxActualX && j >= minActualY && j <= maxActualY){
						Tile tile = tiles[i][j];
						if (tile != null){
							Image texture;
							if (tile.isVisible()) {
								texture = tile.getType().getImage();
							} else {
								try {
									texture = new Image(new FileInputStream("Textures/fog.png"));
								} catch (FileNotFoundException e) {
									throw new RuntimeException(e);
								}
							}
							renderTextures((i + counterX) * tileSize, (j + counterY) * tileSize, tileSize, tileSize, texture);
						}
					}
				}
			}

			for (Item i : items) {
				if (i.getX() >= minActualX && i.getX() <= maxActualX && i.getY() >= minActualY && i.getY() <= maxActualY){
					Image texture;
					if (i.isVisible()) {
						texture = i.getType().getImage();
					} else {
						try {
							texture = new Image(new FileInputStream("Textures/fog.png"));
						} catch (FileNotFoundException e) {
							throw new RuntimeException(e);
						}
					}
					renderTextures((i.getX() + counterX) * tileSize, (i.getY() + counterY) * tileSize, tileSize, tileSize, texture);
				}
			}

			for (Actor a : actors) {
				if (a.getX() >= minActualX && a.getX() <= maxActualX && a.getY() >= minActualY && a.getY() <= maxActualY){
					Image texture;
					if (a.isVisible()) {
						texture = a.getType().getImage();
					} else {
						try {
							texture = new Image(new FileInputStream("Textures/fog.png"));
						} catch (FileNotFoundException e) {
							throw new RuntimeException(e);
						}
					}
					renderTextures((a.getX() + counterX) * tileSize, (a.getY() + counterY)* tileSize, tileSize, tileSize, texture);
				}
			}
		}
	}

	public void renderTextures(int x, int y, int w, int h, Image texture) {
		// Render textures at specified position and size
		gc.drawImage(texture, x, y, w, h);
	}

	public void setTileVisible(Tile[][] tiles,int x, int y){
		for (Tile[] tileArray : tiles) {
			for (Tile t : tileArray) {
				if (t.getX() >= x-2 && t.getX() <= x+2 && t.getY() >= y-2 && t.getY() <= y+2 && ((t.getX() != x-2 && t.getY() != y-2) || (t.getX() != x+2 && t.getY() != y+2) || (t.getX() != x-2 && t.getY() != y+2) || (t.getX() != x+2 && t.getY() != y-2))){
					t.setVisible(true);
				}
			}
		}
	}

	public void setItemsVisible(List<Item> items, Tile[][] tiles, int x, int y) {
		for (Item i : items) {
			i.setVisible(tiles[i.getX()][i.getY()].isVisible());
		}
	}

	public void setActorsVisible(List<Actor> actors, Tile[][] tiles, int x, int y) {
		for (Actor a : actors) {
			a.setVisible(tiles[a.getX()][a.getY()].isVisible());
		}
	}

	public Direction inputToDirection(){
		if (goUp){
			return Direction.UP;
		} else if (goDown){
			return Direction.DOWN;
		} else if (goRight){
			return Direction.RIGHT;
		} else if (goLeft){
			return Direction.LEFT;
		} else {
			return Direction.NONE;
		}
	}
}


