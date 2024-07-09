package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.DiaryNoteDTO;
import com.dddryinside.service.RootPane;
import com.dddryinside.service.Service;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class DiaryNoteController extends PageLoader implements Controller {
    RootPane root;
    private final DiaryNoteDTO note;

    DiaryNoteController(DiaryNoteDTO note) {
        this.note = note;
    }

    public void initializeUI() {
        root = new RootPane();

        //ScrollPane scrollPane = new ScrollPane();
        VBox container = new VBox();

        container.setSpacing(10);

        Label dateLabel = new Label(Service.formatDateTime(note.getLocalDateTime()));
        dateLabel.getStyleClass().add("special-text");

        Label text = new Label(note.getText());
        text.setWrapText(true);

        text.setMinWidth(840);
        text.setMaxWidth(840);


        Button deleteButton = new Button("Удалить запись");
        deleteButton.setOnAction(event -> {
            try {
                if (confirmation("Удалить запись дневника?", "Это действие невозможно будет отменить. " +
                        "Вы уверены, что хотите удалить запись?")) {
                    DataBaseAccess.deleteDiaryNote(note);
                    loadUserPage();
                }
            } catch (Exception e) {
                errorNotification(e.getMessage());
            }
        });

        container.getChildren().addAll(dateLabel, text, deleteButton);
        //scrollPane.setContent(container);
        root.setContent(container);

/*        // Обновляем ширину текста при изменении размера окна
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            text.setWrappingWidth(newValue.doubleValue() - 40);
        });*/
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
