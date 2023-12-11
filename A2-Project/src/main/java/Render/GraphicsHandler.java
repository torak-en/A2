package Render;
import Entities.Actors.Actor;
import Entities.Actors.Player;
import Entities.Items.Item;
import Entities.Tiles.ChipSocket;
import Entities.Tiles.Tile;
import Game.Game;
import Highscore.Highscore;
import Highscore.HighscoreHandler;
import Level.Level;
import Enum.Direction;
import Enum.EntityType;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import Profile.Profile;
import Profile.ProfileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GraphicsHandler manages the graphical user interface elements and rendering.
 * It handles various UI screens, level selection, profile management, and game rendering.
 */

public class GraphicsHandler {
    private static int canvasWidth = 500;
    private static int canvasHeight = 500;
    private Canvas paneCanvas = new Canvas(canvasWidth, canvasHeight);
    private Image backgroundImage = new Image("file:UserInterface/path_background.png");
    private Image dirtBackgroundImage = new Image("file:UserInterface/dirt_background.png");
    private Image crackedBackgroundImage = new Image("file:UserInterface/cracked_path_background.png");
    private AnimationTimer timer = null;
    private boolean running = true;
    private final int ns = 1000000000;
    private final int ticksPerSecond = 60;
    private final int nsPerTick = ns / ticksPerSecond;
    private double progress = 0;
    private long startTime = System.nanoTime();
    private GraphicsContext gc;
    private Level curLevel;
    boolean goUp, goDown, goRight, goLeft;
    private int playerMoveCooldown = 0;
    private static String currentProfileName;
    private final int maxLevelPermitted = 7;
    private Direction nextInput = Direction.NONE;
    private Image fog;

    //Magic Number Solver.

    private int minHeightVal = 200;
    private int minWidthVal = 200;
    private int maxHeightVal = 500;
    private int maxWidthVal = 500;
    private int spacingVal = 10;
    private int fitTitleHeight = 150;
    private int fitTitleWidth = 190;
    private int listViewMax = 350;
    private int minWidthButton = 100;


     /**
     * Displays the main menu screen UI.
     * @param stage The JavaFX stage to display the menu on.
     * @return The JavaFX stage with the menu screen UI displayed.
     */

    public void menuScreenUI(Stage stage) {
        BorderPane root = new BorderPane();

        Image gameTitleImage = new Image("file:UserInterface/InterimTitleImage3.png");

        createMosaicBackground(root);

        root.minHeight(minHeightVal);
        root.minWidth(minWidthVal);
        root.maxHeight(maxHeightVal);
        root.minWidth(maxWidthVal);

        ImageView titleImageView = new ImageView();
        titleImageView.setImage(gameTitleImage);
        titleImageView.setPreserveRatio(true);
        titleImageView.maxHeight(maxHeightVal);
        titleImageView.maxWidth(maxWidthVal);

        titleImageView.setFitHeight(fitTitleHeight);
        titleImageView.setFitWidth(fitTitleWidth);

        titleImageView.setX(fitTitleHeight);
        titleImageView.setY(fitTitleHeight);

        root.getChildren().add(titleImageView);

        stage.setTitle("Main Menu");

        HBox centralBar = new HBox();
        centralBar.setSpacing(spacingVal);
        centralBar.setPadding(new Insets(300, 3, 90, 120));
        root.setCenter(centralBar);

        Button newPlayerGameButton = new Button("Create a new Profile");
        newPlayerGameButton.setOnAction(e -> {
            createNewProfileUI(stage);
        });

        Button loadPreviousPlayerButton = new Button("Load Profile");
        loadPreviousPlayerButton.setOnAction(e -> {
            loadProfileSelectorUI(stage);
        });


        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            System.exit(0);
        });

        centralBar.getChildren().addAll(newPlayerGameButton, loadPreviousPlayerButton, quitButton);

        Scene scene = new Scene(root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Creates a mosaic background for the UI.
     * @param root The root pane to add the mosaic background to.
     * @return The root pane with the mosaic background added.
     */

    private BorderPane createMosaicBackground(BorderPane root) {
        for (int y = 0; y < canvasHeight; y += backgroundImage.getHeight()) {
            for (int x = 0; x < canvasWidth; x += backgroundImage.getWidth()) {
                Random rand = new Random();
                int randomInt = rand.nextInt(10);

                ImageView imageView = null;
                if (randomInt < 4) {
                     imageView = new ImageView(backgroundImage);
                } else if (randomInt < 8){
                    imageView = new ImageView(crackedBackgroundImage);
                } else {
                    imageView = new ImageView(dirtBackgroundImage);
                }

                imageView.setTranslateX(x);
                imageView.setTranslateY(y);

                root.getChildren().add(imageView);
            }
        }
        return root;
    }

    /**
     * Displays the profile selector UI.
     * @param stage The JavaFX stage to display the profile selector on.
     * @return The JavaFX stage with the profile selector UI displayed.
     */

    public void loadProfileSelectorUI(Stage stage) {
        ProfileHandler profileHandler = new ProfileHandler();

        BorderPane root = new BorderPane();
        createMosaicBackground(root);
        stage.setTitle("Profile Selector");

        VBox centralVBox = new VBox();
        centralVBox.setSpacing(spacingVal);
        centralVBox.setPadding(new Insets(50, 50, 0, 75));
        root.setCenter(centralVBox);

        Label selectionLabel = new Label("Please select a Profile: ");
        selectionLabel.setTextFill(Color.color(1, 1, 1));

        ObservableList<String> profileNameData =
                FXCollections.observableArrayList(obtainProfileNameList(profileHandler.getProfiles()));

        ListView<String> listView = new ListView<>(profileNameData);
        listView.setMaxSize(listViewMax, listViewMax);

        centralVBox.getChildren().addAll(selectionLabel, listView);

        HBox centralHBar = new HBox();
        centralHBar.setSpacing(spacingVal);
        centralHBar.setPadding(new Insets(50, 3, 90, 75));
        root.setBottom(centralHBar);

        Button selectProfileButton = new Button("Select Profile");
        selectProfileButton.setMinWidth(minWidthButton);
        selectProfileButton.setDisable(true);

        Button deleteProfileButton = new Button("Delete Profile");
        deleteProfileButton.setMinWidth(minWidthButton);
        deleteProfileButton.setDisable(true);

        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(minWidthButton);

        deleteProfileButton.setOnAction(e -> {
            for (int i = 0; i < profileNameData.size(); i++) {
                if (profileNameData.get(i).equalsIgnoreCase(currentProfileName)) {
                    profileHandler.deleteProfile(profileNameData.get(i));
                    profileNameData.remove(i);
                    listView.refresh();
                }
            }
        });

        returnToMainMenuButton.setOnAction(e -> {
            menuScreenUI(stage);
        });

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                currentProfileName = obtainProfileName(listView);
                selectProfileButton.setDisable(false);
                deleteProfileButton.setDisable(false);
            }
        });

        selectProfileButton.setOnAction(e -> {
            for (int i = 0; i < profileHandler.getProfiles().size(); i++) {
                if (profileHandler.getProfiles().get(i).getProfileName().equalsIgnoreCase(currentProfileName)) {
                    Profile selectedProfile = profileHandler.getProfiles().get(i);
                    try {
                        levelSelectorUI(selectedProfile, stage);
                    } catch (Exception maxLevel) {
                        System.out.println("The selected profile has an unlocked level greater"
                                + " than the total number of levels unlocked. Please try again!");
                        System.exit(1);
                    }
                }
            }
        });

        centralHBar.getChildren().addAll(selectProfileButton, deleteProfileButton, returnToMainMenuButton);

        Scene scene = new Scene (root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Obtains the profile name from the profile selector UI.
     * @param listView The ListView containing the profile names.
     * @return The profile name selected.
     * @throws NullPointerException If no profile is selected.
     */

    private static String obtainProfileName(ListView<String> listView) {
        String selectedProfileName = listView.getSelectionModel().getSelectedItem();
        if (selectedProfileName != null) {
            return selectedProfileName;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Obtains the profile names from the profile selector UI.
     * @param profileList The list of profiles to obtain the names from.
     * @return The list of profile names.
     */

    private ArrayList<String> obtainProfileNameList(List<Profile> profileList) {
        ArrayList<String> profileData = new ArrayList<>();

        for (int i = 0; i < profileList.size(); i++) {
            String profileDataString = profileList.get(i).getProfileName();
            profileData.add(profileDataString);
        }

        return profileData;
    }

    /**
     * Displays the create new profile UI.
     * @param stage The JavaFX stage to display the create new profile UI on.
     * @return The JavaFX stage with the create new profile UI displayed.
     */

    public void createNewProfileUI(Stage stage) {
        ProfileHandler profileHandler = new ProfileHandler();
        BorderPane root = new BorderPane();
        createMosaicBackground(root);
        stage.setTitle("Create New Profile");

        Label profileSelectorInformationDisplay = new Label("Please enter a name:");
        profileSelectorInformationDisplay.setMinWidth(minWidthButton);
        profileSelectorInformationDisplay.setTextFill(Color.color(1, 1, 1));

        TextField nameEntryField = new TextField();

        nameEntryField.setMaxSize(150, 100);

        VBox centralVBox = new VBox();
        centralVBox.setPadding(new Insets(100, 3, 90, 125));
        centralVBox.setSpacing(spacingVal);
        root.setTop(centralVBox);

        HBox centralHBar = new HBox();
        centralHBar.setSpacing(spacingVal);
        centralHBar.setPadding(new Insets(100, 3, 90, 125));
        root.setBottom(centralHBar);

        Button createProfileNameButton = new Button("Start");
        createProfileNameButton.setMinWidth(minWidthButton);

        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(minWidthButton);

        Label creationErrorLabel = new Label("");
        creationErrorLabel.setMinWidth(minWidthButton);
        creationErrorLabel.setTextFill(Color.color(1,1,1));

        createProfileNameButton.setOnAction(e -> {
            String s = profileHandler.createNewProfile(nameEntryField.getText());
            if (s.equals("Profile Created")){
                loadProfileSelectorUI(stage);
            } else if (s .equals("Name can only be made of of characters. No Symbols, whitespaces, etc.")){
                creationErrorLabel.setText(s);
            } else if (s.equals("Error with profile creation, profile may already exist.")){
                creationErrorLabel.setText(s);
            }
        });

        returnToMainMenuButton.setOnAction(e -> {
            menuScreenUI(stage);
        });

        centralHBar.getChildren().addAll(createProfileNameButton, returnToMainMenuButton);

        centralVBox.getChildren().addAll(profileSelectorInformationDisplay, nameEntryField, creationErrorLabel);

        Scene scene = new Scene (root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays the level selector UI.
     * @param profileSelected The profile selected to display the level selector UI for.
     * @param stage The JavaFX stage to display the level selector UI on.
     * @return The JavaFX stage with the level selector UI displayed.
     * @throws Exception If the profile selected has an unlocked level greater than the total number of levels unlocked.
     */

    public void levelSelectorUI(Profile profileSelected, Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        createMosaicBackground(root);
        stage.setTitle("Level Selector");

        Label selectLevelLabel = new Label("Please select a level: ");
        selectLevelLabel.setMinWidth(500);
        selectLevelLabel.setTextFill(Color.color(1,1,1));

        HBox selectBar = new HBox();
        selectBar.setPadding(new Insets(100, 3, 90, 125));
        root.setBottom(selectBar);

        HBox levelBar = new HBox();
        levelBar.setSpacing(spacingVal);
        levelBar.setPadding(new Insets(150, 3, 90, 125));
        root.setBottom(levelBar);

        HBox centralBar = new HBox();
        centralBar.setPadding(new Insets(canvasWidth /2, 3, 90, 190));
        root.setBottom(centralBar);

        Button levelOneButton = new Button("1");
        Button levelTwoButton = new Button("2");
        levelTwoButton.setDisable(true);
        Button levelThreeButton = new Button("3");
        levelThreeButton.setDisable(true);
        Button levelFourButton = new Button("4");
        levelFourButton.setDisable(true);
        Button levelFiveButton = new Button("5");
        levelFiveButton.setDisable(true);
        Button levelSixButton = new Button("6");
        levelSixButton.setDisable(true);
        Button levelSevenButton = new Button("7");
        levelSevenButton.setDisable(true);

        int maxLevel = profileSelected.getMaxLevelNumUnlocked();

        if (maxLevel > maxLevelPermitted) {
            throw new Exception();
        }

        if (maxLevel > 1) {
            levelTwoButton.setDisable(false);
        }
        if (maxLevel > 2) {
            levelThreeButton.setDisable(false);
        }
        if (maxLevel > 3) {
            levelFourButton.setDisable(false);
        }
        if (maxLevel > 4) {
            levelFiveButton.setDisable(false);
        }
        if (maxLevel > 5) {
            levelSixButton.setDisable(false);
        }
        if (maxLevel > 6) {

            levelSevenButton.setDisable(false);
        }

        //Add additional cases

        Game game = new Game();

        levelOneButton.setOnAction(e -> {
            game.updateLevel(1);
            game.setCurrentProfile(profileSelected);
            gameUI(stage, game);
        });

        levelTwoButton.setOnAction(e -> {
            game.updateLevel(2);
            game.setCurrentProfile(profileSelected);
            gameUI(stage, game);
        });

        levelThreeButton.setOnAction(e -> {
            game.updateLevel(3);
            game.setCurrentProfile(profileSelected);
            gameUI(stage, game);
        });

        levelFourButton.setOnAction(e -> {
            game.updateLevel(4);
            game.setCurrentProfile(profileSelected);
            gameUI(stage, game);
        });

        levelFiveButton.setOnAction(e -> {
            game.updateLevel(5);
            game.setCurrentProfile(profileSelected);
            gameUI(stage, game);
        });

        levelSixButton.setOnAction(e -> {
            game.updateLevel(6);
            game.setCurrentProfile(profileSelected);
            gameUI(stage, game);
        });

        levelSevenButton.setOnAction(e -> {
            game.updateLevel(7);
            game.setCurrentProfile(profileSelected);
            gameUI(stage, game);
        });

        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(minWidthButton);

        returnToMainMenuButton.setOnAction(e -> {
            menuScreenUI(stage);
        });

        centralBar.getChildren().addAll(returnToMainMenuButton, selectLevelLabel);
        levelBar.getChildren().addAll(levelOneButton, levelTwoButton, levelThreeButton, levelFourButton,
                levelFiveButton, levelSixButton, levelSevenButton);
        selectBar.getChildren().add(selectLevelLabel);

        root.getChildren().addAll(selectBar, levelBar);

        Scene scene = new Scene (root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays the game UI.
     * @param stage The JavaFX stage to display the game UI on.
     * @param game The game to display the UI for.
     */
    public void gameUI(Stage stage, Game game){
        StackPane root = new StackPane();

        Canvas canvas = new Canvas(900, 900);
        gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        try {
            fog = new Image(new FileInputStream("Textures/fog.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        startTime = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if (running) {
                    long currentTime = System.nanoTime();
                    double diff = currentTime-startTime;
                    progress += diff / nsPerTick;
                    while (progress >= 1) {
                        progress--;
                        tick(stage, game);
                        gc.setFill(Color.WHITE);
                        gc.fillText(game.getLevel().getLevelName(), 20, 20);
                        gc.fillText(String.valueOf(game.getLevel().getCurrentTime()),850, 20);
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

    /**
     * Obtains the scene for the game UI.
     * @param root The root pane to add the scene to.
     * @return The scene for the game UI.
     */

    private Scene getScene(StackPane root) {
        Scene scene = new Scene(root, 900, 900);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (nextInput == Direction.NONE) {
                    switch (keyEvent.getCode()) {
                        case W:
                            goUp = true;
                        case S:
                            goDown = true;
                        case D:
                            goRight = true;
                        case A:
                            goLeft = true;
                        default:
                    }
                    if (goUp) {
                        nextInput = Direction.UP;
                    } else if (goDown) {
                        nextInput = Direction.DOWN;
                    } else if (goRight) {
                        nextInput = Direction.RIGHT;
                    } else if (goLeft) {
                        nextInput = Direction.LEFT;
                    }
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case W: goUp = false;
                    case S: goDown = false;
                    case D: goRight = false;
                    case A: goLeft = false;
                }

            }
        });
        return scene;
    }

    /**
     * Displays the win screen UI or lose screen UI.
     * @param stage The JavaFX stage to display the UI on.
     * @param game The game to display the UI for.
     */

    private void tick(Stage stage, Game game) {
        Player p = game.getLevel().getPlayer();
        if (playerMoveCooldown == 0) {
            if (p.getPendingDirection() == null) {
                if (p.playerCheckLocation(nextInput, curLevel)) {
                    p.setPendingDirection(nextInput);
                }
            }
            playerMoveCooldown = 20;
            nextInput = Direction.NONE;
            p.applyMove();
        }
        playerMoveCooldown--;
        renderLevel(game);
        game.tick();
        if (p.hasWon()){
            timer.stop();

            String name = game.getCurrentProfile().getProfileName();
            int timeTaken = game.getLevel().getTimeTaken();
            LocalDateTime time = LocalDateTime.now();
            int day = time.getDayOfMonth();
            int month = time.getMonthValue();
            int year = time.getYear();

            Highscore newHighscore = new Highscore(name, timeTaken, day, month, year);
            HighscoreHandler highscoreHandler = new HighscoreHandler();
            highscoreHandler.newHighscore(game.getLevelNum(), newHighscore);

            Profile currentProfile = game.getCurrentProfile();
            if (currentProfile.getMaxLevelNumUnlocked() < game.getLevelNum()
                    + 1 && game.getLevelNum() < maxLevelPermitted) {
                currentProfile.setMaxLevelNumUnlocked(game.getLevelNum() + 1);
                ProfileHandler profileHandler = new ProfileHandler();
                profileHandler.updateProfile(currentProfile);
            }

            winScreenUI(stage, game);
        } else if (!p.isAlive()){
            timer.stop();
            loseScreenUI(stage, game);
        }

    }

    /**
     * Renders the game level on the graphical canvas.
     * @param game The game object containing the current level.
     */

    public void renderLevel(Game game) {
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
            renderTextures(0, 0, 1000, 1000, background);

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
                                texture = tile.getTexture();
                            } else {
                                texture = fog;
                            }
                            renderTextures((i + counterX) * tileSize,
                                    (j + counterY) * tileSize, tileSize, tileSize, texture);
                            if (tile.getType() == EntityType.CHIP_SOCKET && tile.isVisible()) {
                                ChipSocket socket = (ChipSocket) tile;
                                gc.fillText(String.valueOf(socket.getNumOfGoldRequired()),
                                        ((i + counterX) * tileSize) + 47,
                                        ((j + counterY) * tileSize) + 53);
                            }
                        }
                    }
                }
            }

            for (Item i : items) {
                if (i.getX() >= minActualX && i.getX() <= maxActualX
                        && i.getY() >= minActualY && i.getY() <= maxActualY){
                    Image texture;
                    if (i.isVisible()) {
                        texture = i.getTexture();
                    } else {
                        texture = fog;
                    }
                    renderTextures((i.getX() + counterX) * tileSize, (i.getY() + counterY) * tileSize, tileSize, tileSize, texture);
                }
            }

            for (Actor a : actors) {
                if (a.getX() >= minActualX && a.getX()
                        <= maxActualX && a.getY() >= minActualY && a.getY() <= maxActualY){
                    Image texture;
                    if (a.isVisible()) {
                        texture = a.getTexture();
                    } else {
                        texture = fog;
                    }
                    renderTextures((a.getX() + counterX) * tileSize, (a.getY() + counterY)* tileSize, tileSize, tileSize, texture);
                }
            }
        }
    }

    /**
     * Renders the textures on the graphical canvas.
     * @param x The x coordinate to render the texture at.
     * @param y The y coordinate to render the texture at.
     * @param w The width of the texture.
     * @param h The height of the texture.
     * @param texture The texture to render.
     */

    public void renderTextures(int x, int y, int w, int h, Image texture) {
        // Render textures at specified position and size
        gc.drawImage(texture, x, y, w, h);
    }

    /**
     * Sets the tiles visible on the graphical canvas.
     * @param tiles The tiles to set visible.
     * @param x The x coordinate of the player.
     * @param y The y coordinate of the player.
     */

    public void setTileVisible(Tile[][] tiles, int x, int y){
        for (Tile[] tileArray : tiles) {
            for (Tile t : tileArray) {
                if (t.getX() >= x-2 && t.getX() <= x+2 && t.getY() >= y-2
                        && t.getY() <= y+2 && ((t.getX() != x-2
                        && t.getY() != y-2) || (t.getX() != x+2
                        && t.getY() != y+2) || (t.getX() != x-2
                        && t.getY() != y+2) || (t.getX() != x+2
                        && t.getY() != y-2))){
                    t.setVisible(true);
                }
            }
        }
    }

    /**
     * Sets the items visible on the graphical canvas.
     * @param items The items to set visible.
     * @param tiles The tiles to set visible.
     * @param x The x coordinate of the player.
     * @param y The y coordinate of the player.
     */

    public void setItemsVisible(List<Item> items, Tile[][] tiles, int x, int y) {
        for (Item i : items) {
            i.setVisible(tiles[i.getX()][i.getY()].isVisible());
        }
    }

    /**
     * Sets the actors visible on the graphical canvas.
     * @param actors The actors to set visible.
     * @param tiles The tiles to set visible.
     * @param x The x coordinate of the player.
     * @param y The y coordinate of the player.
     */

    public void setActorsVisible(List<Actor> actors, Tile[][] tiles, int x, int y) {
        for (Actor a : actors) {
            a.setVisible(tiles[a.getX()][a.getY()].isVisible());
        }
    }

    /**
     * Displays the win screen UI.
     * @param stage The JavaFX stage to display the win screen UI on.
     * @param game The game object.
     */

    public void winScreenUI(Stage stage, Game game) {
        BorderPane root = new BorderPane();
        createMosaicBackground(root);
        stage.setTitle("Win Screen");

        int levelBeat = game.getLevelNum();
        HighscoreHandler highScoreLevelHandler = new HighscoreHandler();

        Label winMessageLabel = new Label("Congratulations, you beat this level.");
        winMessageLabel.setMinWidth(minWidthButton);
        winMessageLabel.setTextFill(Color.color(1,1,1));

        VBox centralVBox = new VBox();
        centralVBox.setPadding(new Insets(75, 0, 0, 100));
        centralVBox.setSpacing(spacingVal);
        root.setCenter(centralVBox);

        ArrayList<Highscore> highscoreArrayList = obtainHighscoreData(highScoreLevelHandler.getHighscores(levelBeat));

        ListView<String> listView = getListViewData(highscoreArrayList);

        HBox centralBar = new HBox();
        centralBar.setSpacing(spacingVal);
        centralBar.setPadding(new Insets(40, 3, 90, 125));
        root.setBottom(centralBar);

        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(100);

        returnToMainMenuButton.setOnAction(e -> {
            menuScreenUI(stage);
        });

        Button nextLevelButton = new Button("Next Level");
        nextLevelButton.setMinWidth(100);

        if (game.getLevelNum() == maxLevelPermitted) {
            nextLevelButton.setDisable(true);
        }

        nextLevelButton.setOnAction(e -> {
            game.updateLevel(game.getLevelNum() + 1);
            gameUI(stage, game);
        });

        centralBar.getChildren().addAll(returnToMainMenuButton, nextLevelButton);

        centralVBox.getChildren().addAll(winMessageLabel, listView);


        Scene scene = new Scene (root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.show();
    }

    /***
     * Produces a ListView object that has all data from the ArrayList of highscores passed in.
     * @param highscoreArrayList The ArrayList of highscore objects.
     * @return A ListView object with data inserted.
     */

    private static ListView<String> getListViewData(ArrayList<Highscore> highscoreArrayList) {
        ObservableList<String> profileNameData = FXCollections.observableArrayList();

        if (highscoreArrayList.size() == 0) {
            profileNameData.add("No High scores present for the current level");
        } else {
            profileNameData.add("ProfileName / TimeTaken - Date (Day/Month/Year)");
            for (int i = 0; i < highscoreArrayList.size(); i++) {
                profileNameData.add(highscoreArrayList.get(i).getName() + " "
                        + highscoreArrayList.get(i).getTimeTaken() + "s "
                        + highscoreArrayList.get(i).getDay() + "/"
                        + highscoreArrayList.get(i).getMonth() +
                        "/" + highscoreArrayList.get(i).getYear());
            }
        }

        ListView<String> listView = new ListView<>(profileNameData);
        listView.setMaxSize(300,300);
        return listView;
    }

    /**
     * Produces an ArrayList of all Highscore objects found in the list of Highscore objects
     * @param highscoresList The List of highscore objects.
     * @return An ArrayList of highscore objects.
     */

    private ArrayList<Highscore> obtainHighscoreData(List<Highscore> highscoresList) {
        ArrayList<Highscore> highScoreArrayList = new ArrayList<>();

        for (int i = 0; i < highscoresList.size(); i++) {
            Highscore highScoreData = highscoresList.get(i);
            highScoreArrayList.add(highScoreData);
        }

        return highScoreArrayList;
    }

    /**
     * Displays the lose screen UI.
     * @param stage The JavaFX stage to display the lose screen UI on.
     * @param game The game object.
     */

    public void loseScreenUI(Stage stage, Game game) {
        BorderPane root = new BorderPane();
        createMosaicBackground(root);
        stage.setTitle("Lose Screen");

        Label loseMessageLabel = new Label("You died, Please try again!");
        loseMessageLabel.setMinWidth(150);
        loseMessageLabel.setTextFill(Color.color(1,1,1));

        VBox centralVBox = new VBox();
        centralVBox.setPadding(new Insets(40, 3, 90, 125));

        HBox centralBar = new HBox();
        centralBar.setPadding(new Insets(40, 3, 90, 125));
        centralBar.setSpacing(spacingVal);

        Button returnToMainMenuButton = new Button("Return to Main menu");

        returnToMainMenuButton.setOnAction(e -> {
            menuScreenUI(stage);

        });

        Button restartLevelButton = new Button("Retry Level");
        restartLevelButton.setMinWidth(100);

        restartLevelButton.setOnAction(e -> {
            game.updateLevel(game.getLevelNum());
            gameUI(stage, game);
        });

        centralBar.getChildren().addAll(restartLevelButton, returnToMainMenuButton);
        root.setBottom(centralBar);

        centralVBox.getChildren().add(loseMessageLabel);
        root.setTop(centralVBox);

        Scene scene = new Scene (root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.show();
    }
}
