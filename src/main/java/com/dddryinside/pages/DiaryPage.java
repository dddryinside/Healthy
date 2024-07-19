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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class DiaryPage implements Page {
    private final int pageNumber;

    public DiaryPage(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public Scene getInterface() {
        int pagesAmount = DataBaseAccess.getDiaryPagesAmount(5);
        HBox pagination = new HBox(5);

        if (pagesAmount != 1) {
            if (pageNumber == 1) {
                Hyperlink nextButton = new Hyperlink("Вперёд");
                nextButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(pageNumber + 1)));
                pagination.getChildren().add(nextButton);
            } else if (pageNumber == pagesAmount) {
                Hyperlink backButton = new Hyperlink("Назад");
                backButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(pageNumber - 1)));
                pagination.getChildren().add(backButton);
            } else {
                Hyperlink backButton = new Hyperlink("Назад");
                backButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(pageNumber - 1)));

                Hyperlink nextButton = new Hyperlink("Вперёд");
                nextButton.setOnAction(event -> PageManager.loadPage(new DiaryPage(pageNumber + 1)));

                pagination.getChildren().addAll(backButton, nextButton);
            }
        }
        VBox.setMargin(pagination, new Insets(0, 0, 20, 0));

        List<Note> notes = DataBaseAccess.getNotes(pageNumber, 5);
        VBox notesContainer = new VBox(10);
        for (Note note : notes) {
            Box box = new Box();
            box.setSpacing(5);

            SuperLabel date = new SuperLabel(note.getStringDate());
            date.makeGrey();
            SuperLabel title = new SuperLabel(note.getContent());
            Hyperlink watch = new Hyperlink("Смотреть");
            box.getChildren().addAll(date, title, watch);

            notesContainer.getChildren().add(box);
        }

        SuperLabel title = new SuperLabel("Дневник");
        title.makeTitle();

        SuperLabel pageNumber = new SuperLabel("Страница №" + this.pageNumber);
        pageNumber.makeGrey();

        VBox container = new VBox(title, pageNumber, pagination, notesContainer);

        Root root = new Root();
        root.setToTopCenter(container);
        root.setMenuBar();

        return new Scene(root);
    }
}
