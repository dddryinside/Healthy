package com.dddryinside.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ExtendedBorderPane extends BorderPane {
    public void setMenuBar(String link) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(link));
        try {
            Parent menuBarRoot = fxmlLoader.load();
            this.setTop(menuBarRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
