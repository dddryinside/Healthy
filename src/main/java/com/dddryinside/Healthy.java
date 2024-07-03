package com.dddryinside;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Healthy extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Healthy");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        PageLoader.stage = stage;
        PageLoader pageLoader = new PageLoader();
        pageLoader.loadMainPage();
    }

    public static void main(String[] args) {
        launch();
    }
}