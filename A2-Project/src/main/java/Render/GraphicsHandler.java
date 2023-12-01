package Render;
import Game.Game;
import Render.Render;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphicsHandler {
    public void menuUI(Stage stage){
        Render renderEngine = new Render();

        //Change once a TitleImage has been made.
        Image userInterfaceImage = new Image("file:UserInterface/Placeholder_removeInFinal.png");

        BorderPane root = new BorderPane();

        //Setting up the GameTitle Image shown on the main menu.
        ImageView imageView = new ImageView();
        imageView.setImage(userInterfaceImage);
        imageView.setX(10);
        imageView.setY(10);

        //Positioning on Image
        imageView.setFitHeight(90);
        imageView.setFitWidth(90);
        imageView.setX(150);
        imageView.setY(90);

        root.getChildren().add(imageView);

        //Setup of Canvas.
        Canvas canvas = new Canvas(400, 400); //Temporary size (Looks good though)
        root.setCenter(canvas);

        //Title of Pane
        stage.setTitle("Main Menu (Test)");

        //HorizontalBox used to place buttons in a good position.
        HBox centralBar = new HBox();
        centralBar.setSpacing(15);
        centralBar.setPadding(new Insets(210, 20, 90, 30));
        root.setCenter(centralBar);

        //Buttons + functionality
        //Button loadPlayerButton = new Button("Load Player");
        Button loadPlayerButton = new Button("Esc Interface");
        centralBar.getChildren().add(loadPlayerButton);
        loadPlayerButton.setOnAction(e -> {
            //System.out.println("Open Load Player?");

            //Call Esc Interface for testing.
        });

        //Button newGameButton = new Button("New Game");
        Button newGameButton = new Button("Profile Select Interface");
        centralBar.getChildren().add(newGameButton);
        newGameButton.setOnAction(e -> {
            //System.out.println("Open New Game?");

            //Call Profile Selection Interface for testing.
        });

        //Button leaderboardButton = new Button("Leaderboard");
        Button leaderboardButton = new Button("Win Screen Interface");
        centralBar.getChildren().add(leaderboardButton);
        leaderboardButton.setOnAction(e -> {
            //System.out.println("Open Leaderboard?");
            WinScreenUI(stage);

            //Call Win Screen Interface for testing.
        });


        //Button quitButton = new Button("Quit");
        Button quitButton = new Button("Lose Screen Interface");
        centralBar.getChildren().add(quitButton);
        quitButton.setOnAction(e -> {
            //System.exit(0);

            //Call Lose Screen Interface.
        });

        //Final stage show.
        Scene scene = new Scene(root, 400, 400);

        stage.setScene(scene);
        stage.show();
    }

    //EscMenu Interface

    //Profile Select Interface

    //Win Screen Interface

    public void WinScreenUI(Stage stage) {
        BorderPane root = new BorderPane();
        stage.setTitle("Win Screen");

        Canvas canvas = new Canvas(400, 400); //Temporary size (Looks good though)
        root.setCenter(canvas);

        //Congratulations Label:
        Label congratulationsLabel = new Label("Congratulations, You Won!");
        congratulationsLabel.setMinWidth(150);

        //Label?
        Label informationLevelPerformanceLabel = new Label("Placeholder Text");
        informationLevelPerformanceLabel.setMinWidth(150);


        //Button to return to main menu:
        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setPrefWidth(Control.USE_COMPUTED_SIZE);
        returnToMainMenuButton.setMinWidth(150);

        returnToMainMenuButton.setOnAction(e -> {
            menuUI(stage);
        });


        //VBox Declaration
        VBox centralVBox = new VBox();
        centralVBox.setPadding(new Insets(70, 0, 0, 120));
        centralVBox.setSpacing(50);

        centralVBox.getChildren().addAll(congratulationsLabel, informationLevelPerformanceLabel, returnToMainMenuButton);
        root.getChildren().add(centralVBox);

        Scene scene = new Scene (root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    //Lose Screen Interface

    //Mouse Listener Method
}
