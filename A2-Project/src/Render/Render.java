package Render;

import Entities.Tiles.Tile;
import Level.Level;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Render extends Application {
	private Canvas canvas;
	private GraphicsContext graphicsContext;
	private Group root;
	private Image[] textures;
	private String gameState;
	private Level curLevel;

	// The dimensions of the window
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 500;

	// The dimensions of the canvas
	private static final int CANVAS_WIDTH = 600;
	private static final int CANVAS_HEIGHT = 400;

	// The width and height (in pixels) of each cell that makes up the game.
	private static final int GRID_CELL_WIDTH = 50;
	private static final int GRID_CELL_HEIGHT = 50;

	// The width of the grid in number of cells.
	private static final int GRID_WIDTH = 12;


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Create a scene from the GUI
		Group root = new Group();
		init(root,600,600);
	}

	public void init(Group root, int width, int height) {
		this.root = root;
		canvas = new Canvas(width, height);
		graphicsContext = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);

		// Initialize other necessary components
	}

	public void setLevel(Level level) {
		curLevel = level;
	}

	public void setGameState(String state) {
		gameState = state;
	}

	public void render() {
		// Main rendering logic
		// Calls to renderLevel, renderOverlay, or other rendering methods based on gameState
		renderLevel();
		renderOverlay();
		// Add more rendering based on game state
	}

	public void renderTextures(int x, int y, int w, int h, Image texture) {
		// Render textures at specified position and size
		graphicsContext.drawImage(texture, x, y, w, h);
	}

	public void renderLevel() {
		if (curLevel != null) {
			// Render the current level
			Tile[][] tiles = curLevel.getTileLayer();

			// Assuming tileSize is known
			int tileSize = 32; // Adjust as needed

			// Render tiles based on their positions and textures
			for (int y = 0; y < tiles.length; y++) {
				for (int x = 0; x < tiles[y].length; x++) {
					Tile tile = tiles[y][x];
					if (tile != null) {
						Image texture = tile.getType().getImage(); // Get texture of the tile
						renderTextures(x * tileSize, y * tileSize, tileSize, tileSize, texture);
					}
				}
			}
		}
	}

	public void renderOverlay() {
		// Render additional overlay elements (UI, menus, etc.)
		// Example: Render UI elements on top of the level
	}

	public void renderProfileMenu() {
		// Render the profile menu
		// Logic to display profile-related elements
	}

	public void renderVictoryScreen() {
		// Render the victory screen
		// Logic to display victory-related elements
	}
}
