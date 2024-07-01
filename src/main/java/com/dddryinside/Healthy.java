package com.dddryinside;

import javafx.application.Application;
import javafx.stage.Stage;

public class Healthy extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Healthy");
        PageLoader.stage = stage;
        PageLoader pageLoader = new PageLoader();
        pageLoader.loadMainPage();
    }

    public static void main(String[] args) {
        launch();
    }
}