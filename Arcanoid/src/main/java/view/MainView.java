package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainView {
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;

    private final static int menuButtonsX = 100;
    private final static int menuButtonsY = 150;

    List<Button> menuButtons;

    public MainView() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createPlaysButton();
        createSettingsButton();
        createRulesButton();
        createExitButton();


        //createBackground();



    }
    private void addMenuButtons(Button button) {
        button.setLayoutX(menuButtonsX);
        button.setLayoutY(menuButtonsY + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }
//    private void crateButtons() {
//        createPlaysButton();
//        createSettingsButton();
//        createRulesButton();
//        createExitButton();
//    }

    private void createPlaysButton() {
        Button play = new Button("Play");
        addMenuButtons(play);
    }
    private void createSettingsButton() {
        Button settings = new Button("Settings");
        addMenuButtons(settings);
    }
    private void createRulesButton() {
        Button rules = new Button("Rules");
        addMenuButtons(rules);
    }
    private void createExitButton() {
        Button exit = new Button("EXIT");
        addMenuButtons(exit);
    }

    public Stage getMainStage() {
        return mainStage;
    }

//    private void createBackground() {
//        Image backgroundImage = new Image("view/resources/background.png",256,256,false,true);
//        BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
//        mainPane.setBackground(new Background(background));
//    }
}
