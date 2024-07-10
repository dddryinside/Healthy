package com.dddryinside.controllers;

import com.dddryinside.tests.Test;
import com.dddryinside.service.AllTests;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

/*public class TestController {
    private VBox root;

    public void initializeUI(AllTests testEnum) {
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
}*/
