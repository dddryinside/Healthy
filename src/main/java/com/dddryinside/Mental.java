package com.dddryinside;

import com.dddryinside.pages.SecurityPage;
import com.dddryinside.service.PageManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Locale;

public class Mental extends Application {
    private final static int WINDOW_HEIGHT = 660;
    private final static int WINDOW_WIDTH = 960;

    @Override
    public void start(Stage stage) {
        Locale.setDefault(new Locale("ru", "RU"));
        stage.setTitle("Mental");
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        PageManager.setStage(stage);
        PageManager.setWindowSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        PageManager.loadPage(new SecurityPage());
    }

    public static void main(String[] args) {
        launch();
    }
}