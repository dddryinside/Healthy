package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import com.dddryinside.service.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class UserPageController extends PageLoader implements Controller {
    private VBox root;
    private VBox notes = new VBox();
    private VBox rightVBox;
    final PatientDTO user = DataBaseAccess.patient;

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

        VBox mainVBox = new VBox();
        mainVBox.setPadding(new Insets(20));
        root.getChildren().add(mainVBox);

        // Create the left VBox
        VBox leftVBox = new VBox(5);

        // Add labels for user information
        Label fioLabel = new Label(user.getFio());
        fioLabel.getStyleClass().add("bold-text");
        Label birthDateLabel = new Label("Дата рождения: " + user.getStringBirthDate());
        Label sexLabel = new Label("Пол: " + user.getStringSex());

        // Add buttons for profile actions
        HBox profileButtonsBox = new HBox(10);
        Button editProfileButton = new Button("Редактировать профиль");
        editProfileButton.setOnAction(event -> loadEditProfilePage());
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
        ResizableTextArea diaryTextArea = new ResizableTextArea();
        diaryTextArea.setPromptText("Жизнь прекрасна!");
        diaryTextArea.setMaxWidth(500);
        diaryTextArea.setPrefHeight(200);
        HBox diaryButtonsBox = new HBox(10);
        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> {
            String text = diaryTextArea.getText();
            if (text != null) {
                try {
                    DataBaseAccess.saveDiaryNote(text, user);
                    diaryTextArea.setText(null);
                    refreshNotesView();
                } catch (Exception e) {
                    errorNotification(e.getMessage());
                }
            }
        });
        Button clearButton = new Button("Очистить");
        clearButton.setOnAction(event -> diaryTextArea.setText(null));

/*        ImageView imageView = new ImageView(new Image("brush.png"));
        clearButton.setGraphic(imageView);

        imageView.setFitHeight(30); // Установите желаемую высоту
        imageView.setPreserveRatio(true); // Сохраните пропорции картинки*/

        diaryButtonsBox.getChildren().addAll(saveButton, clearButton);

        leftVBox.getChildren().addAll(fioLabel, birthDateLabel, sexLabel, profileButtonsBox,
                personalDiaryLabel, diaryInstructionsLabel, diaryTextArea, diaryButtonsBox);

        // Create the right VBox for research information
        rightVBox = new VBox();
        Label researchesLabel = new Label("Ваши исследования");
        researchesLabel.getStyleClass().add("special-text");
        rightVBox.getChildren().add(researchesLabel);

        List<AllTests> tests = DataBaseAccess.getAllTestsOfPatient(user);
        if (tests.size() != 0) {
            for (AllTests test : tests) {
                Hyperlink hyperlink = new Hyperlink(test.getFullName());
                hyperlink.setOnAction(event -> loadTestResultsPage(test, user));
                rightVBox.getChildren().add(hyperlink);
            }
        } else {
            Label noResearchesLabel = new Label("У вас нет активных исследований");
            rightVBox.getChildren().add(noResearchesLabel);
        }

        notes = new VBox(); // Инициализация notes
        Label notesLabel = new Label("Записи дневника");
        notesLabel.setPadding(new Insets(40, 0, 0, 0));
        notesLabel.getStyleClass().add("special-text");
        rightVBox.getChildren().add(notesLabel);
        rightVBox.getChildren().add(notes); // Добавление notes один раз

        refreshNotesView();

        // Add the left and right VBoxes to the main VBox
        HBox contentBox = new HBox(20);
        contentBox.getChildren().addAll(leftVBox, rightVBox);
        mainVBox.getChildren().add(contentBox);

        // Set the stylesheet
        mainVBox.getStylesheets().add("main.css");
    }

    private void refreshNotesView() {
        notes.getChildren().clear();

        List<DiaryNoteDTO> allNotes = DataBaseAccess.loadDiaryNotes(user);
        Collections.reverse(allNotes);
        if (allNotes.size() > 0) {
            for (int i = 0; i < allNotes.size(); i++) {
                String text = StringUtils.abbreviate(allNotes.get(i).getText(), 50);
                Hyperlink labelNote = new Hyperlink(text);
                DiaryNoteDTO note = allNotes.get(i);
                labelNote.setOnAction(event -> loadPage(new DiaryNoteController(note)));
                notes.getChildren().add(labelNote);
                if (i == 2) {
                    Button viewFullDiaryButton = new Button("Смотреть всё");
                    notes.getChildren().add(viewFullDiaryButton);
                    break;
                }
            }
        } else {
            Label noNotesLabel = new Label("У вас нет записей в дневнике");
            notes.getChildren().add(noNotesLabel);
        }
    }

    public Parent getRoot() {
        return root;
    }
}
