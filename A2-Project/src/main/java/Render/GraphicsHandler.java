package Render;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphicsHandler {
    private Canvas paneCanvas = new Canvas(400,600);

    public void MenuScreenUI(Stage stage){
        //Change once a TitleImage has been made.
        Image userInterfaceImage = new Image("file:UserInterface/InterimTitleImage1.png");

        BorderPane root = new BorderPane();

        //Setting up the GameTitle Image shown on the main menu.
        ImageView imageView = new ImageView();
        imageView.setImage(userInterfaceImage);
        imageView.setPreserveRatio(true);
        imageView.maxHeight(500);
        imageView.maxWidth(500);

        //Size
        imageView.setFitHeight(150);
        imageView.setFitWidth(190);

        //Positioning
        imageView.setX(90);
        imageView.setY(50);

        root.getChildren().add(imageView);
        root.setCenter(paneCanvas);

        //Title of Pane
        stage.setTitle("Main Menu (Test)");

        //HorizontalBox used to place buttons in a good position.
        HBox centralBar = new HBox();
        centralBar.setSpacing(15);
        centralBar.setPadding(new Insets(210, 20, 90, 30));
        root.setCenter(centralBar);

        Button newGameButton = new Button("Profile Select Interface");
        centralBar.getChildren().add(newGameButton);
        newGameButton.setOnAction(e -> {
            //Call Profile Selection Interface for testing.

        });

        Button leaderboardButton = new Button("Win Screen Interface");
        centralBar.getChildren().add(leaderboardButton);
        leaderboardButton.setOnAction(e -> {
            //Test
            WinScreenUI(stage);
        });

        Button button1 = new Button("Lose Screen Interface");
        centralBar.getChildren().add(button1);
        button1.setOnAction(e -> {
            LoseScreenUI(stage);
        });


        Button quitButton = new Button("Quit");
        centralBar.getChildren().add(quitButton);
        quitButton.setOnAction(e -> {
            System.exit(0);
        });

        Scene scene = new Scene(root, 400, 400);

        stage.setScene(scene);
        stage.show();
    }

    //Win Screen Interface
    public void WinScreenUI(Stage stage) {
        BorderPane root = new BorderPane();
        stage.setTitle("Win Screen");

        root.setCenter(paneCanvas);

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
            MenuScreenUI(stage);
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
    public void LoseScreenUI(Stage stage) {
        BorderPane root = new BorderPane();
        stage.setTitle("Lose Screen");

        root.setCenter(paneCanvas);

        //Congratulations Label:
        Label lossLabel = new Label("Game Over, you died!");
        lossLabel.setMinWidth(150);

        Label informationLevelPerformanceLabel = new Label("Placeholder Text");
        informationLevelPerformanceLabel.setMinWidth(150);


        //Button to return to main menu:
        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setPrefWidth(Control.USE_COMPUTED_SIZE);
        returnToMainMenuButton.setMinWidth(150);

        returnToMainMenuButton.setOnAction(e -> {
            MenuScreenUI(stage);
        });


        //VBox Declaration
        VBox centralVBox = new VBox();
        centralVBox.setPadding(new Insets(70, 0, 0, 120));
        centralVBox.setSpacing(50);

        centralVBox.getChildren().addAll(lossLabel, informationLevelPerformanceLabel, returnToMainMenuButton);
        root.getChildren().add(centralVBox);

        Scene scene = new Scene (root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    //Profile Selection Interface
    //- 3 Elements
    //  - Profile Name Label
    //  - Select Profile Button
    //  - Delete Profile Button
    //
    //- Fetches from ProfileHandler
}
