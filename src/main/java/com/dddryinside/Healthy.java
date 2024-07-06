package com.dddryinside;

import com.dddryinside.service.DataBaseAccess;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Healthy extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Healthy");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setWidth(900);
        stage.setHeight(600);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        PageLoader.stage = stage;
        PageLoader pageLoader = new PageLoader();

        DataBaseAccess.checkPatientsTableExist();
        pageLoader.loadSecurityPage();
    }

    public static void main(String[] args) {
        launch();
    }
}