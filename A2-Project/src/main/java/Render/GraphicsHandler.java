package Render;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicsHandler {
    private int windowWidth = 500;
    private int windowHeight = 500;
    private Canvas paneCanvas = new Canvas(windowWidth,windowHeight);

    public void MenuScreenUI(Stage stage){;
        //NOTE: Change once a TitleImage has been made.
        Image userInterfaceImage = new Image("file:UserInterface/InterimTitleImage1.png");

        BorderPane root = new BorderPane();

        root.minHeight(200);
        root.minWidth(200);
        root.maxHeight(500);
        root.minWidth(500);

        //Setting up the GameTitle Image shown on the main menu.
        ImageView imageView = new ImageView();
        imageView.setImage(userInterfaceImage);
        imageView.setPreserveRatio(true);
        imageView.maxHeight(500);
        imageView.maxWidth(500);

        //IMG Size
        imageView.setFitHeight(150);
        imageView.setFitWidth(190);

        //IMG Positioning
        imageView.setX(150);
        imageView.setY(150);

        root.getChildren().add(imageView);
        root.setCenter(paneCanvas);

        //PANE Title
        stage.setTitle("Main Menu (Test)");

        //HorizontalBox used to place buttons in a good position.
        HBox centralBar = new HBox();
        centralBar.setSpacing(5);
        centralBar.setPadding(new Insets(300, 3, 90, 30));
        root.setCenter(centralBar);

        Button profileSelectScreenInterfaceButton = new Button("Profile Select Interface");
        profileSelectScreenInterfaceButton.setOnAction(e -> {
            //Call Profile Selection Interface for testing.
            ProfileSelectorUI(stage);
        });

        Button winScreenInterfaceButton = new Button("Win Screen Interface");
        winScreenInterfaceButton.setOnAction(e -> {
            //Test
            WinScreenUI(stage);
        });

        Button loseScreenInterfaceButton = new Button("Lose Screen Interface");
        loseScreenInterfaceButton.setOnAction(e -> {
            LoseScreenUI(stage);
        });


        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            System.exit(0);
        });

        centralBar.getChildren().addAll(profileSelectScreenInterfaceButton, winScreenInterfaceButton, loseScreenInterfaceButton, quitButton);

        Scene scene = new Scene(root, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    //Win Screen Interface
    public void WinScreenUI(Stage stage) {
        BorderPane root = new BorderPane();
        stage.setTitle("Win Screen");

        Label winMessageLabel = new Label("Congratulations, you beat this level.");
        winMessageLabel.setMinWidth(200);

        //VBox Declaration
        VBox centralVBox = new VBox();
        centralVBox.setPadding(new Insets(50, 3, 90, 200));
        centralVBox.setSpacing(50);

        HBox centralBar = new HBox();
        centralBar.setSpacing(50);
        centralBar.setPadding(new Insets(300, 3, 90, 30));
        root.setBottom(centralBar);

        //Button to return to main menu:
        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(100);

        returnToMainMenuButton.setOnAction(e -> {
            MenuScreenUI(stage);
        });

        centralBar.getChildren().addAll(returnToMainMenuButton);

        centralVBox.getChildren().add(winMessageLabel);

        root.getChildren().add(centralVBox);

        Scene scene = new Scene (root, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    //Lose Screen Interface
    public void LoseScreenUI(Stage stage) {
        BorderPane root = new BorderPane();
        stage.setTitle("Lose Screen");

        Label loseMessageLabel = new Label("You died, Please try again!");
        loseMessageLabel.setMinWidth(150);

        //VBox Declaration
        VBox centralVBox = new VBox();
        centralVBox.setPadding(new Insets(50, 3, 90, 200));
        centralVBox.setSpacing(50);

        HBox centralBar = new HBox();
        centralBar.setSpacing(50);
        centralBar.setPadding(new Insets(300, 3, 90, 30));
        root.setBottom(centralBar);

        //Button to return to main menu:
        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(100);

        returnToMainMenuButton.setOnAction(e -> {
            MenuScreenUI(stage);
        });

        centralBar.getChildren().addAll(returnToMainMenuButton);

        centralVBox.getChildren().add(loseMessageLabel);

        root.getChildren().add(centralVBox);

        Scene scene = new Scene (root, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    public void ProfileSelectorUI(Stage stage) {
        BorderPane root = new BorderPane();
        stage.setTitle("Profile Selector");

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        root.setBackground(background);

        Label profileSelectorInformationDisplay = new Label("Placeholder Text");
        profileSelectorInformationDisplay.setMinWidth(150);

        //VBox Declaration
        VBox centralVBox = new VBox();
        centralVBox.setPadding(new Insets(50, 3, 90, 200));
        centralVBox.setSpacing(50);

        HBox centralBar = new HBox();
        centralBar.setSpacing(50);
        centralBar.setPadding(new Insets(300, 3, 90, 30));
        root.setBottom(centralBar);

        Button selectProfileButton = new Button("Select Profile");
        selectProfileButton.setPrefWidth(Control.USE_COMPUTED_SIZE);
        selectProfileButton.setMinWidth(100);

        Button deleteProfileButton = new Button("Delete Profile");
        deleteProfileButton.setPrefWidth(Control.USE_COMPUTED_SIZE);
        deleteProfileButton.setMinWidth(100);

        //Button to return to main menu:
        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(100);

        returnToMainMenuButton.setOnAction(e -> {
            MenuScreenUI(stage);
        });

        centralBar.getChildren().addAll(selectProfileButton, deleteProfileButton, returnToMainMenuButton);

        centralVBox.getChildren().add(profileSelectorInformationDisplay);

        root.getChildren().add(centralVBox);

        Scene scene = new Scene (root, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }
}
