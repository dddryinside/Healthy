package com.dddryinside.controllers;

import com.dddryinside.service.AllTests;
import com.dddryinside.PageLoader;
import com.dddryinside.service.RootPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AllTestsPageController extends PageLoader implements Controller {
    RootPane root;
    @Override
    public void initializeUI() {
        root = new RootPane();
        root.getStylesheets().add("main.css");
        root.setMenuBar("/menu-bar.fxml");

        VBox mainVBox  = new VBox();
        mainVBox.setPadding(new Insets(20));
        mainVBox.setSpacing(10);

        Label title = new Label("Тесты");
        title.getStyleClass().add("special-text");
        Label instructions = new Label("Вы можете проходить любые тесты по желанию. После " +
                "прохождения теста вам будет предложено сохранить результаты. Если вы решите их сохранить, " +
                "то автоматически начнёте исследование по выбранному тесту, все текущие исследования " +
                "отображаются на главной странице. Так вы сможете наблюдать за своим самочувствием на " +
                "протяжении длительного времени и видеть изменения.");
        instructions.setMaxWidth(860);
        instructions.setWrapText(true);

        mainVBox.getChildren().addAll(title, instructions);

        for (AllTests test : AllTests.values()) {
            TitledPane titledPane = new TitledPane();
            titledPane.setExpanded(false);
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
            mainVBox.getChildren().add(titledPane);
        }

        ScrollPane scrollPane = new ScrollPane(mainVBox);
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setMaxWidth(860);

        HBox mainContainer = new HBox();
        mainContainer.getChildren().add(scrollPane);
        mainContainer.setAlignment(Pos.CENTER);

        //mainVBox.getChildren().add(scrollPane);
        root.setCenter(mainContainer);
    }

    public Parent getRoot() {
        return root;
    }
}