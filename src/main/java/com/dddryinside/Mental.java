package com.dddryinside;

import com.dddryinside.contracts.Page;
import com.dddryinside.models.Locale;
import com.dddryinside.pages.LogInPage;
import com.dddryinside.service.PageManager;
import com.dddryinside.service.ResourceManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Mental extends Application {
    private final static int WINDOW_HEIGHT = 660;
    private final static int WINDOW_WIDTH = 960;
    public final static Locale APP_LOCALE = Locale.EN;
    @Override
    public void start(Stage stage) {
        stage.setTitle("Mental");

        Image icon = new Image(ResourceManager.loadImage("/img/icon.png"));
        stage.getIcons().add(icon);

        Page.localeRes = ResourceBundle.getBundle(APP_LOCALE.getFile());

        PageManager.setStage(stage);
        PageManager.setWindowSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        PageManager.loadPage(new LogInPage());
    }

    public static void main(String[] args) {
        launch();
    }
}