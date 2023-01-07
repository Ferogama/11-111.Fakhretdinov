package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private final static int menuButtonsX = 100;
    private final static int menuButtonsY = 150;
    private static String backgroundimage = "https://www.c64-wiki.com/images/thumb/6/60/Arkanoid-title.png/300px-Arkanoid-title.png";
    private ArkanoidSubScene playSubScene;
    private ArkanoidSubScene settingsSubScene;
    private ArkanoidSubScene rulesSubScene;

    private ArkanoidSubScene sceneToHide;



    List<Button> menuButtons;

    public MainView() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, 800, 600);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createBackground();
        crateButtons();
        createSubScene();



    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void showSubScene(ArkanoidSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }
    //сцены
    private void createSubScene() {
        playSubScene = new ArkanoidSubScene();
        mainPane.getChildren().add(playSubScene);

        settingsSubScene = new ArkanoidSubScene();
        mainPane.getChildren().add(settingsSubScene);

        rulesSubScene = new ArkanoidSubScene();
        mainPane.getChildren().add(rulesSubScene);
    }
    //кнопки
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
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(playSubScene);
            }
        });
    }

    private void createSettingsButton() {
        ArkanoidButton settings = new ArkanoidButton("Settings");
        addMenuButtons(settings);
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(settingsSubScene);
            }
        });
    }

    private void createRulesButton() {
        ArkanoidButton rules = new ArkanoidButton("Rules");
        addMenuButtons(rules);

        rules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(rulesSubScene);
            }
        });
    }

    private void createExitButton() {
        ArkanoidButton exit = new ArkanoidButton("Exit");
        addMenuButtons(exit);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }
    //логотип игры
//    public void createLogo() {
//        ImageView logo = new ImageView("https://clck.ru/33CujF");
//        logo.setFitWidth(350);
//        logo.setFitHeight(100);
//        logo.setLayoutX(400);
//        logo.setLayoutY(10);
//        mainPane.getChildren().add(logo);
//    }
    //background
    public void createBackground() {
        BackgroundImage backi = new BackgroundImage(new Image(backgroundimage,800,700,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);

        mainPane.setBackground(new Background(backi));
    }
}
