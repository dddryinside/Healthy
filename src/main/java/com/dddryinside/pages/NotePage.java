package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.elements.SuperLabel;
import com.dddryinside.models.Note;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.PageManager;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class NotePage extends Page {
    private final Note note;
    public NotePage(Note note) {
        this.note = note;
    }
    @Override
    public Scene getInterface() {
        SuperLabel date = new SuperLabel(note.getStringDate());
        date.makeGrey();
        SuperLabel content = new SuperLabel(note.getContent());

        HBox buttons = new HBox(10);
        Hyperlink deleteButton = new Hyperlink("Delete");
        deleteButton.setOnAction(event -> {
            DataBaseAccess.deleteNote(note);

/*
            We decide which diary page to return the user to. If you came to an entry from a
            diary page that had only one entry (we just deleted it), then you need to redirect
            to the previous page. Also, if this was the last entry in the diary, then we redirect
            to the main one. If you came from the main page, then also to the main page
*/

            if (note.getDiaryPage() != null) {
                int pageNumber = note.getDiaryPage();

                List<Note> notes = DataBaseAccess.getNotes(pageNumber, 9);
                if (notes.size() == 0) {
                    if (pageNumber == 1) {
                        PageManager.loadPage(new UserPage());
                    } else {
                        PageManager.loadPage(new DiaryPage(pageNumber - 1));
                    }
                } else {
                    PageManager.loadPage(new DiaryPage(note.getDiaryPage()));
                }
            } else {
                PageManager.loadPage(new UserPage());
            }
        });


        if (note.getDiaryPage() != null) {
            Hyperlink backButton = new Hyperlink("Back");
            backButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(note.getDiaryPage())));
            buttons.getChildren().addAll(backButton, deleteButton);
        } else {
            buttons.getChildren().addAll(deleteButton);
        }

        VBox container = new VBox(date, content, buttons);
        container.setSpacing(10);

        Root root = new Root();
        root.setToTopCenter(container);
        root.setMenuBar();
        return new Scene(root);
    }
}
