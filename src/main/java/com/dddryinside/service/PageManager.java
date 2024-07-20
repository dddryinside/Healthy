package com.dddryinside.service;

import com.dddryinside.contracts.Page;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class PageManager {
    private static Stage stage;
    public static void setStage(Stage stage) {
        PageManager.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void loadPage(Page page) {
        Scene scene = page.getInterface();

        String styles = Objects.requireNonNull(PageManager.class.getResource("/styles/styles.css")).toExternalForm();
        scene.getStylesheets().add(styles);

        String fonts = Objects.requireNonNull(PageManager.class.getResource("/styles/fonts.css")).toExternalForm();
        scene.getStylesheets().add(fonts);

        stage.setScene(scene);
        stage.show();
    }

    public static void setWindowSize(int height, int width) {
        stage.setMinHeight(height);
        stage.setMinWidth(width);
        stage.setHeight(height);
        stage.setWidth(width);
    }

    public static void showNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Page.localeRes.getString("message"));
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("img/icon.png"));
        alert.showAndWait();
    }
}
