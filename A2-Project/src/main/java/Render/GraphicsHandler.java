package Render;
import Game.Game;
import Render.Render;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        Button loadPlayerButton = new Button("Load Player");
        centralBar.getChildren().add(loadPlayerButton);
        loadPlayerButton.setOnAction(e -> {
            System.out.println("Open Load Player?");
        });

        Button newGameButton = new Button("New Game");
        centralBar.getChildren().add(newGameButton);
        newGameButton.setOnAction(e -> {
            System.out.println("Open New Game?");
        });

        Button leaderboardButton = new Button("Leaderboard");
        centralBar.getChildren().add(leaderboardButton);
        leaderboardButton.setOnAction(e -> {
            System.out.println("Open Leaderboard?");
        });


        Button quitButton = new Button("Quit");
        centralBar.getChildren().add(quitButton);
        quitButton.setOnAction(e -> {
            System.exit(0);
        });

        //Final stage show.
        Scene scene = new Scene(root, 400, 400);

        stage.setScene(scene);
        stage.show();
    }

    //EscMenu Interface

    //Profile Select Interface

    //Win Screen Interface

    //Lose Screen Interface

    //Mouse Listener Method
}
