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
import model.InvadersButton;
import model.InvadersSubScene;
import model.InvadersButton;
import model.InvadersSubScene;

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
    private InvadersSubScene settingsSubScene;
    private InvadersSubScene rulesSubScene;

    private InvadersSubScene sceneToHide;
    String rules1 ="Правила достаточно просты:" + "\n" +
            " необходимо уничтожать вражеские " + "\n" + " корабли, которые движутся на корабль" +
            "\n" + "игрока." + "\n" + "\n" + "Главная задача — не дать вражеским " +
            "\n" + "кораблям не столкнуться с вами." + "\n" +"Если они каснуться вас - Game Over" + "\n" + "\n" +
            "При достижении некоторого количества" + "\n"+
            "очков, ваше оружие будет улучшаться!";


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

    private void showSubScene(InvadersSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    //сцены
    private void createSubScene() {

        settingsSubScene = new InvadersSubScene();
        mainPane.getChildren().add(settingsSubScene);


    }

    public void createRulesSubcene() {
        rulesSubScene = new InvadersSubScene();
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
        InvadersButton play = new InvadersButton("Play");
        addMenuButtons(play);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    GameView game = new GameView();
                    game.start(mainStage);
                } catch (Exception e ) {
                    System.out.println(e);
                }
            }
        });
    }

    private void createSettingsButton() {
        InvadersButton settings = new InvadersButton("Settings");
        addMenuButtons(settings);
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(settingsSubScene);
            }
        });
    }

    private void createRulesButton() {
        InvadersButton rules = new InvadersButton("Rules");
        addMenuButtons(rules);

        rules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(rulesSubScene);
            }
        });
    }

    private void createExitButton() {
        InvadersButton exit = new InvadersButton("Exit");
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
