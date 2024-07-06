package com.dddryinside.controllers;

import com.dddryinside.service.AllTests;
import com.dddryinside.PageLoader;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPageController extends PageLoader implements Controller {
    private VBox root;
    @Override
    public void initializeUI() {
        root = new VBox();

        // Include the menu bar
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu-bar.fxml"));
        try {
            Parent menuBarRoot = fxmlLoader.load();
            root.getChildren().add(menuBarRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        VBox mainVBox  = new VBox();
        mainVBox.setPadding(new Insets(20));
        mainVBox.setSpacing(20);

        Label instructions = new Label("Вы можете проходить любые тесты по желанию. После " +
                "прохождения теста вам будет предложено сохранить результаты. Если вы решите их сохранить, " +
                "то автоматически начнёте исследование по выбранному тесту, все текущие исследования " +
                "отображаются на главной странице. Так вы сможете наблюдать за своим самочувствием на " +
                "протяжении длительного времени и видеть изменения.");
        instructions.setWrapText(true);

        VBox panesVBox = new VBox();
        panesVBox.setSpacing(10);
        panesVBox.setPrefWidth(860);
        for (AllTests test : AllTests.values()) {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(test.getFullName());

            VBox vBox = new VBox();
            vBox.setSpacing(5);
            Label description = new Label(test.getDescription());
            description.setWrapText(true);
            Label questionsAmount = new Label();
            Button runTest = new Button("Пройти");
            runTest.setOnAction(event -> {
                loadTestPage(test);
            });
            vBox.getChildren().addAll(description, questionsAmount, runTest);

            titledPane.setContent(vBox);
            panesVBox.getChildren().add(titledPane);
        }

        mainVBox.getChildren().add(instructions);

        ScrollPane scrollPane = new ScrollPane(panesVBox);
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.setFitToWidth(true);

        mainVBox.getChildren().add(scrollPane);
        mainVBox.getStylesheets().add("main.css");
        root.getChildren().add(mainVBox);
    }

    public Parent getRoot() {
        return root;
    }
}