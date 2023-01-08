package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameViewManger {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage menuStage;
//    public GameViewManger() {
//        initializeStage();
//    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,600,600);
        gameStage = new Stage();
        gameStage.setTitle("SpaceInvaders");
        gameStage.setScene(gameScene);

    }
    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        gameStage.show();
    }
}
