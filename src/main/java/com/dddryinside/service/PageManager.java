package com.dddryinside.service;

import com.dddryinside.contracts.Page;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PageManager {
    private static Stage stage;

    public static void setStage(Stage stage) {
        PageManager.stage = stage;
    }

    public static void loadPage(Page page) {
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.show();
    }

    public static void errorNotification(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();

        //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("icon.png"));
    }
}
