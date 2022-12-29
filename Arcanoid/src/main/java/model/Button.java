package model;


import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Button extends javafx.scene.control.Button {
    private final String Font_Path = "src/main/model/resoursec/puzzle/Graphic.swf";
    //private final String Button_Pressed_Style = new Image(new FileInputStream("src/main/java/model/resources/buttonSelected.png"));
    //private final String Button_Default_Style =  new Image(new FileInputStream("src/main/java/model/resources/button.png"));

    public Button(String text) {
        setText(text);
        setButtonFont();
        setPrefHeight(49);
        setPrefWidth(100);
        //setStyle(Button_Default_Style);
        initializeButtonListeners();

    }

    public void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(Font_Path), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana",100 ));
        }
    }

    private void setButtonPressedStyle() {
        //setStyle(Button_Pressed_Style);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        ///setStyle(Button_Default_Style);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });


        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }

        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }

        });
    }
}


