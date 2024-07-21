package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.elements.SuperLabel;
import com.dddryinside.models.Note;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.PageManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class DiaryPage extends Page {
    private final int pageNumber;

    public DiaryPage(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public Scene getInterface() {
        int pagesAmount = DataBaseAccess.getDiaryPagesAmount(9);
        HBox pagination = new HBox(15);

        if (pagesAmount != 1) {
            if (pageNumber == 1) {
                Hyperlink nextButton = new Hyperlink(localeRes.getString("forward"));
                nextButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(pageNumber + 1)));
                pagination.getChildren().add(nextButton);
            } else if (pageNumber == pagesAmount) {
                Hyperlink backButton = new Hyperlink(localeRes.getString("back"));
                backButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(pageNumber - 1)));
                pagination.getChildren().add(backButton);
            } else {
                Hyperlink backButton = new Hyperlink(localeRes.getString("back"));
                backButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(pageNumber - 1)));

                Hyperlink nextButton = new Hyperlink(localeRes.getString("forward"));
                nextButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(pageNumber + 1)));

                pagination.getChildren().addAll(backButton, nextButton);
            }
        }
        VBox.setMargin(pagination, new Insets(0, 0, 20, 0));

        List<Note> notes = DataBaseAccess.getNotes(pageNumber, 9);

        VBox notesContainer = new VBox(10);
        for (Note note : notes) {
            VBox box = new VBox();
            box.setSpacing(5);

            note.setDiaryPage(pageNumber);

            SuperLabel date = new SuperLabel(note.getStringDate());
            date.makeGrey();
            SuperLabel title = new SuperLabel(note.getShortTitle());
            Hyperlink watch = new Hyperlink(localeRes.getString("view"));
            watch.setOnAction(event -> PageManager.loadPage(new NotePage(note)));
            box.getChildren().addAll(date, title, watch);

            notesContainer.getChildren().add(box);
        }

        SuperLabel title = new SuperLabel(localeRes.getString("diary"));
        title.makeTitle();

        SuperLabel pageNumber = new SuperLabel(localeRes.getString("page") + " №" + this.pageNumber);
        pageNumber.makeGrey();

        VBox container = new VBox(title, pageNumber, pagination, notesContainer);
        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setFitToWidth(true);

        Root root = new Root();
        root.setToTopCenter(scrollPane);
        root.setMenuBar();

        return new Scene(root);
    }
}
