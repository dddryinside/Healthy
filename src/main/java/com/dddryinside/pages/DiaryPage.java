package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Box;
import com.dddryinside.elements.Root;
import com.dddryinside.elements.SuperLabel;
import com.dddryinside.models.Note;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.PageManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class DiaryPage implements Page {
    int pageNumber = 1;

    public DiaryPage(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public Scene getInterface() {
        List<Note> notes = DataBaseAccess.getNotes(pageNumber, 4);

        VBox notesContainer = new VBox(10);
        for (Note note : notes) {
            Box box = new Box();
            box.setSpacing(5);
            //box.setPadding(new Insets(10));

            SuperLabel date = new SuperLabel(note.getStringDate());
            date.makeGrey();
            SuperLabel title = new SuperLabel(note.getContent());
            Hyperlink watch = new Hyperlink("Смотреть");
            box.getChildren().addAll(date, title, watch);

            notesContainer.getChildren().add(box);
        }

        int pagesAmount = DataBaseAccess.getDiaryPagesAmount(4);
        HBox pagination = new HBox(5);
        for (int i = 1; i <= pagesAmount; i++) {
            Hyperlink hyperlinkPageNumber = new Hyperlink(String.valueOf(i));

            int temp = i;
            hyperlinkPageNumber.setOnAction(event -> PageManager.loadPage(new DiaryPage(temp)));
            if (pageNumber == i) {
                hyperlinkPageNumber.setUnderline(true);
            }
            pagination.getChildren().add(hyperlinkPageNumber);
        }

        BorderPane container = new BorderPane();
        VBox.setVgrow(container, Priority.ALWAYS);
        container.setTop(notesContainer);
        container.setBottom(pagination);

        Root root = new Root();
        root.setToTopCenter(container);
        root.setMenuBar();

        return new Scene(root);
    }
}
