package com.dddryinside.controllers;

import com.dddryinside.DTO.Test;
import com.dddryinside.DTO.Tests;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TestController {
    private VBox root;

    public void initializeUI(Tests testEnum) {
        root = new VBox();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu-bar.fxml"));
        try {
            Parent menuBarRoot = fxmlLoader.load();
            root.getChildren().add(menuBarRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Test test = testEnum.getTest();
        root.getChildren().add(test.initializeUI());
    }

    public Parent getRoot() {
        return root;
    }
}
