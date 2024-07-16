package com.dddryinside.elements;

import com.dddryinside.models.Note;
import com.dddryinside.service.DataBaseAccess;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class DiaryNotes extends VBox {
    public DiaryNotes() {
        List<Note> notes = DataBaseAccess.getNotes(2);

        SuperLabel title = new SuperLabel("Записи дневника");
        title.makeSpecial();
        this.getChildren().add(title);

        for (Note note : notes) {
            Hyperlink link = new Hyperlink(note.getShortTitle());
            this.getChildren().add(link);
        }

        this.setMaxWidth(350);
        this.setMinWidth(350);
        this.setWidth(350);

        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTCYAN, null, null));
        this.setBackground(DEFAULT_BACKGROUND);
    }
}
