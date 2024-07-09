package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import com.dddryinside.service.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class UserPageController extends PageLoader implements Controller {
    private RootPane root;
    private VBox notes = new VBox();
    final PatientDTO user = DataBaseAccess.patient;

    public void initializeUI() {
        root = new RootPane();

/*        // Include the menu bar
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu-bar.fxml"));
        try {
            Parent menuBarRoot = fxmlLoader.load();
            root.getChildren().add(menuBarRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        root.setMenuBar("/menu-bar.fxml");

/*        VBox mainVBox = new VBox();
        mainVBox.setPadding(new Insets(20));
        mainVBox.setAlignment(Pos.CENTER);*/
        //root.setCenter(mainVBox);

        // LEFT PART --------------------------------------------------

        VBox leftVBox = new VBox();

        VBox profileInfoContainer = new VBox();
        profileInfoContainer.setSpacing(5);

        Label fioLabel = new Label(user.getFio());
        fioLabel.getStyleClass().add("bold-text");
        Label birthDateLabel = new Label("Дата рождения: " + user.getStringBirthDate());
        Label sexLabel = new Label("Пол: " + user.getStringSex());

        HBox profileButtonsBox = new HBox(10);
        Button editProfileButton = new Button("Редактировать профиль");
        editProfileButton.setOnAction(event -> loadEditProfilePage());
        Button logoutButton = new Button("Выйти");
        logoutButton.setOnAction(event -> {
            DataBaseAccess.patient = null;
            loadSecurityPage();
        });
        profileButtonsBox.getChildren().addAll(editProfileButton, logoutButton);
        profileInfoContainer.getChildren().addAll(fioLabel, birthDateLabel, sexLabel, profileButtonsBox);

        leftVBox.getChildren().add(profileInfoContainer);

        Label researchesLabel = new Label("Ваши исследования");
        researchesLabel.setPadding(new Insets(40, 0, 0, 0));
        researchesLabel.getStyleClass().add("special-text");
        leftVBox.getChildren().add(researchesLabel);

        List<AllTests> tests = DataBaseAccess.getAllTestsOfPatient(user);
        if (tests.size() != 0) {
            for (AllTests test : tests) {
                Hyperlink hyperlink = new Hyperlink(test.getFullName());
                hyperlink.setOnAction(event -> loadTestResultsPage(test, user));
                leftVBox.getChildren().add(hyperlink);
            }
        } else {
            Label noResearchesLabel = new Label("У вас нет активных исследований");
            leftVBox.getChildren().add(noResearchesLabel);
        }

        notes = new VBox();
        Label notesLabel = new Label("Записи дневника");
        notesLabel.setPadding(new Insets(40, 0, 0, 0));
        notesLabel.getStyleClass().add("special-text");
        leftVBox.getChildren().add(notesLabel);
        leftVBox.getChildren().add(notes);

        refreshNotesView();

        // RIGHT PART ----------------------------------------------------------

        VBox rightVBox = new VBox();
        rightVBox.setSpacing(5);

        Label personalDiaryLabel = new Label("Личный дневник");
        personalDiaryLabel.getStyleClass().add("special-text");
        Label diaryInstructionsLabel = new Label("Вы можете спокойно писать сюда всё что угодно. Эта информация сохраниться " +
                "на вашем компьютере в зашифрованном виде и только вы сможете её прочесть");
        diaryInstructionsLabel.setWrapText(true);
        diaryInstructionsLabel.setPrefWidth(450);

        TextArea diaryTextArea = new TextArea();
        diaryTextArea.setPrefHeight(250);
        diaryTextArea.setMaxWidth(500);

        diaryTextArea.setPromptText("Жизнь прекрасна!");
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

        diaryButtonsBox.getChildren().addAll(saveButton, clearButton);

        rightVBox.getChildren().addAll(personalDiaryLabel, diaryInstructionsLabel, diaryTextArea, diaryButtonsBox);

        // TOGETHER -------------------------------------------------------------------------------------

        HBox contentBox = new HBox();
        leftVBox.setMinWidth(350);
        contentBox.getChildren().addAll(leftVBox, rightVBox);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));
        root.setCenter(contentBox);
        //mainVBox.getChildren().add(contentBox);


        // Set the stylesheet
        root.getStylesheets().add("main.css");
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
                    if (!(allNotes.size() == 3)) {
                        Button viewFullDiaryButton = new Button("Смотреть всё");
                        viewFullDiaryButton.setOnAction(event -> loadPage(new AllDiaryNotesController()));
                        notes.getChildren().add(viewFullDiaryButton);
                    }
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
