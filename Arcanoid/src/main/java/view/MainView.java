package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ArkanoidButton;
import model.ArkanoidSubScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class MainView {
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int menuButtonsX = 100;
    private final static int menuButtonsY = 150;
    private static String backgroundimage = "https://clck.ru/33Cy39";
    //private ArkanoidSubScene playSubScene;
    private ArkanoidSubScene settingsSubScene;
    private ArkanoidSubScene rulesSubScene;

    private ArkanoidSubScene sceneToHide;
    String rules1 = "Правила достаточно просты:" + "\n" +
            " геймеру необходимо контролировать" + "\n" + "небольшую платформу — отбивать шарик," +
            "\n" + "чтобы исключить его падение." + "\n" + "Главная задача — с помощью шара" +
            "\n" + "разбить все кирпичи, висящие сверху.";


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
        createRulesSubcene();


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

        settingsSubScene = new ArkanoidSubScene();
        mainPane.getChildren().add(settingsSubScene);


    }

    public void createRulesSubcene() {
        rulesSubScene = new ArkanoidSubScene();
        mainPane.getChildren().add(rulesSubScene);

        Label label = new Label("Правила игры");
        label.setLayoutX(100);
        label.setLayoutY(25);
        label.setFont(Font.font("Verdana", 40));
        label.setStyle("-fx-text-fill: #FFD700");
        rulesSubScene.getPane().getChildren().add(label);

        Label arkanoidRules = new Label(rules1);
        arkanoidRules.setLayoutX(40);
        arkanoidRules.setLayoutY(80);
        arkanoidRules.setFont(Font.font("Verdana", 20));
        arkanoidRules.setStyle("-fx-text-fill: FFD700");
        rulesSubScene.getPane().getChildren().add(arkanoidRules);


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
                GameViewManger gameManger = new GameViewManger();
                gameManger.createNewGame(mainStage);
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
    public void createBackground() {
        BackgroundImage backi = new BackgroundImage(new Image(backgroundimage, 800, 700, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        mainPane.setBackground(new Background(backi));
    }

}
