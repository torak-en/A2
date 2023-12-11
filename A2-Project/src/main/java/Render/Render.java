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

/**
 * Main class, that extends Application allowing for it to Override start.
 */
public class Render extends Application{
	private GraphicsHandler gameGraphicEngine = new GraphicsHandler();

	/**
	 * Start-up logic for our program.
	 * @param stage the stage that will be used for creating windows and graphics for our game
	 */
	@Override
	public void start(Stage stage) throws Exception {
		gameGraphicEngine.menuScreenUI(stage);
	}
}
