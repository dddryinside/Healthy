package com.dddryinside.elements;

import com.dddryinside.models.Note;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.SecurityManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;

public class Diary extends VBox {
    private final VBox notesBox = new VBox();

    public Diary() {
        SuperLabel title = new SuperLabel("Дневник");
        title.makeTitle();

        TextArea diaryTextArea = new TextArea();
        diaryTextArea.setPromptText("Жизнь прекрасна!");

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> {
            if (diaryTextArea.getText() != null) {
                Note newNote = new Note(SecurityManager.getUser(), diaryTextArea.getText(), LocalDate.now());
                DataBaseAccess.saveNote(newNote);
                diaryTextArea.setText(null);
                updateNotesBox();
            }
        });
        Button clearButton = new Button("Очистить");
        clearButton.setOnAction(event -> diaryTextArea.setText(null));

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(saveButton, clearButton);

        SuperLabel prevNotesTitle = new SuperLabel("Предыдущие записи");
        VBox.setMargin(prevNotesTitle, new Insets(10, 0, 0, 0));
        prevNotesTitle.makeTitle();
        updateNotesBox();

        this.setSpacing(5);
        this.setMinWidth(550);
        this.getChildren().addAll(title, diaryTextArea, buttonsBox, prevNotesTitle, notesBox);

/*        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, null, null));
        this.setBackground(DEFAULT_BACKGROUND);*/
    }

    private void updateNotesBox() {
        List<Note> notes = DataBaseAccess.getNotes(4);
        notesBox.getChildren().clear();

        for (int i = 0; i < notes.size(); i++) {
            if (i == 3) {
                Button seeMoreButton = new Button("Смотреть все записи");
                VBox.setMargin(seeMoreButton, new Insets(10, 0, 0, 0));
                notesBox.getChildren().add(seeMoreButton);
                break;
            } else {
                Hyperlink link = new Hyperlink(notes.get(i).getShortTitle());
                notesBox.getChildren().add(link);
            }
        }
    }
}
