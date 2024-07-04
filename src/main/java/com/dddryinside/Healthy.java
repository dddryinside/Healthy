package com.dddryinside;

import com.dddryinside.DTO.Patient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class Healthy extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Healthy");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setWidth(900);
        stage.setHeight(600);
        PageLoader.stage = stage;
        PageLoader pageLoader = new PageLoader();
        pageLoader.loadMainPage();
    }

    public static void main(String[] args) {
        launch();
    }
}