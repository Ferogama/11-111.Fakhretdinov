package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
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

    Image image22 = new Image("-fx-background-image: url('/model/resources/button.png'");

    List<Button> menuButtons;

    public MainView() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, 800, 600);
        mainStage = new Stage();

        mainStage.setScene(mainScene);
        crateButtons();
        //createBackground();
        //createLogo();


    }

    private void addMenuButtons(Button button) {
        button.setLayoutX(menuButtonsX);
        button.setLayoutY(menuButtonsY + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }
    private void crateButtons() {
        createPlaysButton();
        createSettingsButton();
        createRulesButton();
        createExitButton();
    }

    private void createPlaysButton() {
        Button play = new Button("Play");
        play.setPrefWidth(100);
        play.setPrefHeight(50);
        play.setStyle("-fx-background-image: image22");
        addMenuButtons(play);
    }

    private void createSettingsButton() {
        Button settings = new Button("Settings");
        settings.setPrefWidth(100);
        settings.setPrefHeight(50);
        addMenuButtons(settings);
    }

    private void createRulesButton() {
        Button rules = new Button("Rules");
        rules.setPrefWidth(100);
        rules.setPrefHeight(50);
        addMenuButtons(rules);
    }

    private void createExitButton() {
        Button exit = new Button("Exit");
        exit.setPrefWidth(100);
        exit.setPrefHeight(50);
        addMenuButtons(exit);
    }

    public Stage getMainStage() {
        return mainStage;
    }

//    private void createBackground() {
//        BackgroundImage backgroundImage = new Image(new Image("https://clck.ru/33CeXo"),
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//        mainPane.setBackground(backgroundImage);
//    }

//        private void createLogo() {
//        Image image = new Image(new FileInputStream("view/resources/ArkanoidLogo.png"));
//        ImageView logo = new ImageView(image);
//        logo.setLayoutX(400);
//        logo.setLayoutY(50);
//        mainPane.getChildren().add(logo);
}
