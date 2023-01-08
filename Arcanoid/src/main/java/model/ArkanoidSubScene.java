package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class ArkanoidSubScene extends SubScene {
    private static final String subceneImage = "https://clck.ru/33CuvC";
    private  boolean isHidden;
    public ArkanoidSubScene() {
        super(new AnchorPane(),600,400);
        BackgroundImage image = new BackgroundImage(new Image(subceneImage,500,400,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));
        isHidden = true;

        setLayoutX(1024);
        setLayoutY(180);

    }
    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(-750);
            transition.setToY(-50);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }
    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
