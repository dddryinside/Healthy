package com.dddryinside.elements;

import com.dddryinside.models.Note;
import com.dddryinside.pages.DiaryPage;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.PageManager;
import com.dddryinside.service.SecurityManager;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.List;

public class Diary extends VBox {
    private final VBox notesBox = new VBox(5);

    public Diary() {
        SuperLabel title = new SuperLabel("Дневник");
        title.makeTitle();

        TextArea diaryTextArea = new TextArea();
        diaryTextArea.setWrapText(true);

        Hyperlink saveButton = new Hyperlink("Сохранить");
        saveButton.setOnAction(event -> {
            if (diaryTextArea.getText() != null) {
                Note newNote = new Note(SecurityManager.getUser(), diaryTextArea.getText(), LocalDate.now());
                DataBaseAccess.saveNote(newNote);
                diaryTextArea.setText(null);
                updateNotesBox();
            }
        });
        Hyperlink clearButton = new Hyperlink("Очистить");
        clearButton.setOnAction(event -> diaryTextArea.setText(null));

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(saveButton, clearButton);

        updateNotesBox();

        this.setSpacing(5);
        this.setMaxWidth(500);
        this.getChildren().addAll(title, diaryTextArea, buttonsBox, notesBox);

/*        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, null, null));
        this.setBackground(DEFAULT_BACKGROUND);*/
    }

    private void updateNotesBox() {
        List<Note> notes = DataBaseAccess.getNotes(3);
        notesBox.getChildren().clear();
        VBox.setMargin(notesBox, new Insets(10, 0, 0, 0));
        notesBox.setSpacing(10);

        for (int i = 0; i < notes.size(); i++) {
            if (i == 2) {
                Hyperlink seeMoreButton = new Hyperlink("Смотреть все записи");
                seeMoreButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(1)));
                notesBox.getChildren().add(seeMoreButton);
                break;
            } else {
                Note currentNote = notes.get(i);

                GridPane gridPane = new GridPane();
                gridPane.setHgap(10);

                SuperLabel date = new SuperLabel(currentNote.getStringDate());
                date.makeGrey();

                SuperLabel title = new SuperLabel(currentNote.getShortTitle());
                title.setPrefWidth(350);
                Hyperlink watch = new Hyperlink("Смотреть");

                gridPane.add(date, 0, 0);
                gridPane.add(title , 1, 0);
                gridPane.add(watch , 2, 0);

                Box box = new Box(gridPane);
                //box.setPadding(new Insets(5));
                notesBox.getChildren().add(box);
            }
        }
    }
}
