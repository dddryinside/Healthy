package com.dddryinside.elements;

import com.dddryinside.models.Note;
import com.dddryinside.pages.DiaryPage;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.PageManager;
import com.dddryinside.service.SecurityManager;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;

public class Diary extends VBox {
    private final VBox notesContainer = new VBox(5);

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

        VBox.setMargin(notesContainer, new Insets(10, 0, 0, 0));
        updateNotesBox();

        this.setSpacing(5);
        this.setMaxWidth(500);
        this.getChildren().addAll(title, diaryTextArea, buttonsBox, notesContainer);

/*        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, null, null));
        this.setBackground(DEFAULT_BACKGROUND);*/
    }

    private void updateNotesBox() {
        List<Note> notes = DataBaseAccess.getNotes(2);
        notesContainer.getChildren().clear();


/*        for (Note note : notes) {
            Box box = new Box();
            box.setSpacing(5);

            SuperLabel date = new SuperLabel(note.getStringDate());
            date.makeGrey();
            SuperLabel title = new SuperLabel(note.getContent());
            Hyperlink watch = new Hyperlink("Смотреть");
            box.getChildren().addAll(date, title, watch);

            notesContainer.getChildren().add(box);
        }*/

        for (int i = 0; i < 1; i++) {
            Box box = new Box();
            box.setSpacing(5);

            SuperLabel date = new SuperLabel(notes.get(i).getStringDate());
            date.makeGrey();
            SuperLabel title = new SuperLabel(notes.get(i).getContent());

            HBox buttons = new HBox(10);
            Hyperlink watch = new Hyperlink("Смотреть");
            buttons.getChildren().add(watch);

            if (notes.size() > 1) {
                Hyperlink seeMoreButton = new Hyperlink("Смотреть все записи");
                seeMoreButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(1)));
                buttons.getChildren().add(seeMoreButton);
            }

            box.getChildren().addAll(date, title, buttons);

            notesContainer.getChildren().add(box);
        }
    }
}
