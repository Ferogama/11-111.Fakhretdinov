package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ArkanoidButton;
import model.ArkanoidSubScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        mainScene = new Scene(mainPane, 800, 600);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        crateButtons();
        createLogo();

        ArkanoidSubScene subScene = new ArkanoidSubScene();
        subScene.setLayoutX(40);
        subScene.setLayoutY(40);
        mainPane.getChildren().add(subScene);



    }

    public Stage getMainStage() {
        return mainStage;
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
        ArkanoidButton play = new ArkanoidButton("Play");
        addMenuButtons(play);
    }

    private void createSettingsButton() {
        ArkanoidButton settings = new ArkanoidButton("Settings");
        addMenuButtons(settings);
    }

    private void createRulesButton() {
        ArkanoidButton rules = new ArkanoidButton("Rules");
        addMenuButtons(rules);
    }

    private void createExitButton() {
        ArkanoidButton exit = new ArkanoidButton("Exit");
        addMenuButtons(exit);
    }
    public void createLogo() {
        ImageView logo = new ImageView("https://clck.ru/33CujF");
        logo.setFitWidth(350);
        logo.setFitHeight(100);
        logo.setLayoutX(400);
        logo.setLayoutY(10);
        mainPane.getChildren().add(logo);
    }
}
