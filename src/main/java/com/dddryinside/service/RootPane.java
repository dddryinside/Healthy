package com.dddryinside.service;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class RootPane extends BorderPane {
    public RootPane() {
        this.setMenuBar("/menu-bar.fxml");
        this.getStylesheets().add("main.css");
    }
    public void setMenuBar(String link) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(link));
        try {
            Parent menuBarRoot = fxmlLoader.load();
            this.setTop(menuBarRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setContent(Node node) {
        ScrollPane scrollPane = new ScrollPane(node);
        scrollPane.getStyleClass().add("scroll-pane");

        scrollPane.setMinWidth(860);


        HBox container = new HBox();
        container.getChildren().add(scrollPane);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(20, 0, 20, 0));

        this.setCenter(container);
    }
}
