package Render;
import Profile.Profile;
import Profile.ProfileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphicsHandler {
    private int canvasWidth = 500;
    private int canvasHeight = 500;
    private Canvas paneCanvas = new Canvas(canvasWidth, canvasHeight);

    //Add Brick tile png to Main Menu Background (REPEATING) (Complete)
    //Add ScrollBarView (ListView) for Profile Selector
    //Create Levels (10 target, but 8 aimed)
    //Place level rendering in Graphics Handler(Alex)

    public void MenuScreenUI(Stage stage) {
        BorderPane root = new BorderPane();

        //NOTE: Change once a TitleImage has been made.
        Image gameTitleImage = new Image("file:UserInterface/InterimTitleImage1.png");
        Image originalImage = new Image("file:UserInterface/PathTileTexture.png");

        CreateMosaicBackground(root, originalImage);

        root.minHeight(200);
        root.minWidth(200);
        root.maxHeight(500);
        root.minWidth(500);

        //Setting up the GameTitle Image shown on the main menu.
        ImageView titleImageView = new ImageView();
        titleImageView.setImage(gameTitleImage);
        titleImageView.setPreserveRatio(true);
        titleImageView.maxHeight(500);
        titleImageView.maxWidth(500);

        //IMG Size
        titleImageView.setFitHeight(150);
        titleImageView.setFitWidth(190);

        //IMG Positioning
        titleImageView.setX(150);
        titleImageView.setY(150);

        root.getChildren().add(titleImageView);
        //root.setCenter(paneCanvas);

        //PANE Title
        stage.setTitle("Main Menu");

        //HorizontalBox used to place buttons in a good position.
        HBox centralBar = new HBox();
        centralBar.setSpacing(10);
        centralBar.setPadding(new Insets(300, 3, 90, 120));
        root.setCenter(centralBar);

        Button newPlayerGameButton = new Button("Create a new Profile");
        newPlayerGameButton.setOnAction(e -> {
            CreateNewProfileUI(stage);
        });

        Button loadPreviousPlayerButton = new Button("Load Profile");
        loadPreviousPlayerButton.setOnAction(e -> {
            LoadProfileSelectorUI(stage);
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

    private BorderPane CreateMosaicBackground(BorderPane root, Image originalImage) {
        for (int y = 0; y < canvasHeight; y += originalImage.getHeight()) {
            for (int x = 0; x < canvasWidth; x += originalImage.getWidth()) {
                ImageView imageView = new ImageView(originalImage);

                imageView.setTranslateX(x);
                imageView.setTranslateY(y);

                root.getChildren().add(imageView);
            }
        }
        return root;
    }

    public void TestScreenUI(Stage stage){;
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
            LoadProfileSelectorUI(stage);
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

        Scene scene = new Scene(root, canvasWidth, canvasHeight);
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

        Scene scene = new Scene (root, canvasWidth, canvasHeight);
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

        Scene scene = new Scene (root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.show();
    }

    public void LoadProfileSelectorUI(Stage stage) {
        ProfileHandler eX = new ProfileHandler();
        BorderPane root = new BorderPane();
        stage.setTitle("Profile Selector");

        VBox centralVBox = new VBox();
        centralVBox.setSpacing(10);
        centralVBox.setPadding(new Insets(50,50,0,75));
        root.setCenter(centralVBox);

        Label selectionLabel = new Label("Please select a Profile: ");

        ObservableList<String> profileNameData =
                FXCollections.observableArrayList(conversionMethod(eX.getProfiles()));

        ListView<String> listView = new ListView<>(profileNameData);
        listView.setMaxSize(350, 350);

        centralVBox.getChildren().addAll(selectionLabel, listView);





        HBox centralHBar = new HBox();
        centralHBar.setSpacing(10);
        centralHBar.setPadding(new Insets(50, 3, 90, 75));
        root.setBottom(centralHBar);

        Button selectProfileButton = new Button("Select Profile");
        selectProfileButton.setMinWidth(100);
        selectProfileButton.setDisable(true);

        Button deleteProfileButton = new Button("Delete Profile");
        deleteProfileButton.setMinWidth(100);
        deleteProfileButton.setDisable(true);

        //Button to return to main menu:
        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(100);

        returnToMainMenuButton.setOnAction(e -> {
            MenuScreenUI(stage);
        });

        selectProfileButton.setOnAction(e ->{
            //Call Profile and obtain the highest level unlocked.
            int maxLevelUnlocked = 3; //Get via getMaxLevelUnlocked method

            LevelSelectorUI(maxLevelUnlocked, stage);
        });

        centralHBar.getChildren().addAll(selectProfileButton, deleteProfileButton, returnToMainMenuButton);

        Scene scene = new Scene (root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.show();
    }

    private ArrayList<String> conversionMethod(List<Profile> profileList) {
        ArrayList<String> profileData = new ArrayList<>();

        for (int i = 0; i < profileList.size(); i++) {
            String profileDataString = profileList.get(i).getProfileName()
                    + " [MaxLevelUnlocked: " + profileList.get(i).getMaxLevelNumUnlcoked() + "]";
            profileData.add(profileDataString);
        }

        return profileData;
    }

    //Create New Profile
    public void CreateNewProfileUI(Stage stage) {
        BorderPane root = new BorderPane();
        stage.setTitle("Create New Profile");

        Label profileSelectorInformationDisplay = new Label("Please enter a name:");
        profileSelectorInformationDisplay.setMinWidth(150);

        TextField nameEntryField = new TextField();

        nameEntryField.setMaxSize(150,100);

        //VBox Declaration
        VBox centralVBox = new VBox();
        centralVBox.setPadding(new Insets(100, 3, 90, 75));
        centralVBox.setSpacing(5);
        root.setTop(centralVBox);

        HBox centralHBar = new HBox();
        centralHBar.setSpacing(10);
        centralHBar.setPadding(new Insets(0, 3, 90, 75));
        root.setBottom(centralHBar);

        Button createProfileButton = new Button("Start");
        createProfileButton.setPrefWidth(Control.USE_COMPUTED_SIZE);
        createProfileButton.setMinWidth(100);

        //Button to return to main menu:
        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(100);

        returnToMainMenuButton.setOnAction(e -> {
            MenuScreenUI(stage);
        });

        centralHBar.getChildren().addAll(createProfileButton, returnToMainMenuButton);

        centralVBox.getChildren().addAll(profileSelectorInformationDisplay, nameEntryField);

        Scene scene = new Scene (root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.show();
    }

    //Level Selector
    public void LevelSelectorUI(int maxLevel, Stage stage) {
        BorderPane root = new BorderPane();
        stage.setTitle("Level Selector");

        Label selectLevelLabel = new Label("Please select a level: ");
        selectLevelLabel.setMinWidth(500);

        HBox selectBar = new HBox();
        selectBar.setSpacing(0);
        selectBar.setPadding(new Insets(100, 3, 90, 125));
        root.setBottom(selectBar);

        HBox levelBar = new HBox();
        levelBar.setSpacing(10);
        levelBar.setPadding(new Insets(150, 3, 90, 125));
        root.setBottom(levelBar);

        HBox centralBar = new HBox();
        centralBar.setSpacing(0);
        centralBar.setPadding(new Insets(canvasWidth /2, 3, 90, 190));
        root.setBottom(centralBar);

        //Implement level access blocking via .setDisable method and loop?
        Button levelOneButton = new Button("1");
        Button levelTwoButton = new Button("2");
        levelTwoButton.setDisable(true);
        Button levelThreeButton = new Button("3");
        levelThreeButton.setDisable(true);

        //Subject to removal?
        Button randomlevelButton = new Button("R");
        randomlevelButton.setDisable(true);

        switch (maxLevel) {
            case (2):
                levelTwoButton.setDisable(false);
                randomlevelButton.setDisable(false);
            case (3):
                levelTwoButton.setDisable(false);
                levelThreeButton.setDisable(false);
                randomlevelButton.setDisable(false);
        }

        levelOneButton.setOnAction(e -> {
            //Create Level 1
        });

        levelTwoButton.setOnAction(e -> {
            //Create Level 2
        });

        levelThreeButton.setOnAction(e -> {
            //Create Level 3
        });


        //Remove if not enough time is given.
        randomlevelButton.setOnAction(e -> {
            //Random number generator between 1 -> max level unlocked for profile.
            //Create the respective level.
        });


        //Button to return to main menu:
        Button returnToMainMenuButton = new Button("Return to Main menu");
        returnToMainMenuButton.setMinWidth(100);

        returnToMainMenuButton.setOnAction(e -> {
            MenuScreenUI(stage);
        });

        centralBar.getChildren().addAll(returnToMainMenuButton, selectLevelLabel);
        levelBar.getChildren().addAll(levelOneButton, levelTwoButton, levelThreeButton, randomlevelButton);
        selectBar.getChildren().add(selectLevelLabel);

        root.getChildren().addAll(selectBar, levelBar);

        Scene scene = new Scene (root, canvasWidth, canvasHeight);
        stage.setScene(scene);
        stage.show();
    }
}
