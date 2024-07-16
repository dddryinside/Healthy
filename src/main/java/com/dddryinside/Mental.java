package com.dddryinside;

import com.dddryinside.service.PageManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Mental extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Mental");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        stage.setWidth(900);
        stage.setHeight(600);
        stage.setMinWidth(900);
        stage.setMinHeight(600);

        PageManager.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}