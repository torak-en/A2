package Render;

import Entities.Tiles.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Game.Game;
import Level.Level;

public class Render extends Application{
	private boolean running = true;
	private final int ns = 1000000000;
	private final int ticksPerSecond = 60;
	private final int nsPerTick = ns / ticksPerSecond;
	private double progress = 0;
	private long startTime = System.nanoTime();

	private Game game;
	private GraphicsContext gc;
	private Level curLevel;

	@Override
	public void start(Stage stage) throws Exception {
		initUI(stage);
		game = new Game();
	}

	private void initUI(Stage stage){
		StackPane root = new StackPane();

		Canvas canvas = new Canvas(900, 400);
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

		Scene scene = new Scene(root, 900, 400);

		stage.setTitle("AnimationTimer");
		stage.setScene(scene);
		stage.show();

	}

	private void tick(){
		game.tick();
		renderLevel();

	}

	public void renderLevel() {
		curLevel = game.getLevel();
		if (curLevel != null) {
			// Render the current level
			Tile[][] tiles = curLevel.getTileLayer();

			// Assuming tileSize is known
			int tileSize = 100; // Adjust as needed

			// Render tiles based on their positions and textures
			for (int y = 0; y < tiles.length; y++) {
				for (int x = 0; x < tiles[y].length; x++) {
					Tile tile = tiles[y][x];
					if (tile != null) {
						Image texture = tile.getType().getImage(); // Get texture of the tile
						renderTextures(y * tileSize, x * tileSize, tileSize, tileSize, texture);
					}
				}
			}
		}
	}

	public void renderTextures(int x, int y, int w, int h, Image texture) {
		// Render textures at specified position and size
		gc.drawImage(texture, x, y, w, h);
	}

}


