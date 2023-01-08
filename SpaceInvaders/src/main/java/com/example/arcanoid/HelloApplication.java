package com.example.arcanoid;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainView;

import java.io.IOException;




public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainView manager = new MainView();
        stage = manager.getMainStage();
        stage.show();

    }
}