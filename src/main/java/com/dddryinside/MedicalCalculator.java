package com.dddryinside;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicalCalculator extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Медицинский калькулятор");
        PageLoader.stage = stage;
        PageLoader pageLoader = new PageLoader();
        pageLoader.loadMainPage();
    }

    public static void main(String[] args) {
        launch();
    }
}