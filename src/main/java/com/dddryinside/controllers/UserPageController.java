package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class UserPageController extends PageLoader implements Controller {
    private VBox root;
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
        root.getChildren().add(mainVBox);

        // Create the left VBox
        VBox leftVBox = new VBox(5);

        // Add labels for user information
        Label fioLabel = new Label(DataBaseAccess.patient.getFio());
        fioLabel.getStyleClass().add("bold-text");
        Label birthDateLabel = new Label("Дата рождения: " + DataBaseAccess.patient.getStringBirthDate());
        Label sexLabel = new Label("Пол: " + DataBaseAccess.patient.getStringSex());

        // Add buttons for profile actions
        HBox profileButtonsBox = new HBox(10);
        Button editProfileButton = new Button("Редактировать профиль");
        editProfileButton.setOnAction(event -> {
            loadEditProfilePage();
        });
        Button logoutButton = new Button("Выйти");
        logoutButton.setOnAction(event -> {
            DataBaseAccess.patient = null;
            loadSecurityPage();
        });
        profileButtonsBox.getChildren().addAll(editProfileButton, logoutButton);

        // Add personal diary section
        Label personalDiaryLabel = new Label("Личный дневник");
        personalDiaryLabel.getStyleClass().add("special-text");
        personalDiaryLabel.setPadding(new Insets(40, 0, 0, 0));
        Label diaryInstructionsLabel = new Label("Вы можете спокойно писать сюда всё что угодно. Эта информация сохраниться " +
                "на вашем компьютере в зашифрованном виде и только вы сможете её прочесть");
        diaryInstructionsLabel.setWrapText(true);
        diaryInstructionsLabel.setPrefWidth(500);
        TextArea diaryTextArea = new TextArea();
        diaryTextArea.setPromptText("Жизнь прекрасна!");
        diaryTextArea.setMaxWidth(500);
        diaryTextArea.setPrefHeight(200);
        HBox diaryButtonsBox = new HBox(10);
        Button saveButton = new Button("Сохранить");
        Button clearButton = new Button("Очистить");

/*        ImageView imageView = new ImageView(new Image("brush.png"));
        clearButton.setGraphic(imageView);

        imageView.setFitHeight(30); // Установите желаемую высоту
        imageView.setPreserveRatio(true); // Сохраните пропорции картинки*/

        diaryButtonsBox.getChildren().addAll(saveButton, clearButton);

        leftVBox.getChildren().addAll(fioLabel, birthDateLabel, sexLabel, profileButtonsBox,
                personalDiaryLabel, diaryInstructionsLabel, diaryTextArea, diaryButtonsBox);

        // Create the right VBox for research information
        VBox researchesVBox = new VBox();
        Label researchesLabel = new Label("Ваши исследования");
        researchesLabel.getStyleClass().add("special-text");
        researchesVBox.getChildren().add(researchesLabel);

        // Add the left and right VBoxes to the main VBox
        HBox contentBox = new HBox(20);
        contentBox.getChildren().addAll(leftVBox, researchesVBox);
        mainVBox.getChildren().add(contentBox);

        // Set the stylesheet
        mainVBox.getStylesheets().add("main.css");
    }

    public Parent getRoot() {
        return root;
    }
}
