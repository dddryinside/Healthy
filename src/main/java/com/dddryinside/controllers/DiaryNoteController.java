package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.DiaryNoteDTO;
import com.dddryinside.service.ExtendedBorderPane;
import com.dddryinside.service.Service;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DiaryNoteController extends PageLoader implements Controller {
    ExtendedBorderPane root;
    private final DiaryNoteDTO note;

    DiaryNoteController(DiaryNoteDTO note) {
        this.note = note;
    }

    public void initializeUI() {
        root = new ExtendedBorderPane();
        root.setMenuBar("/menu-bar.fxml");
        root.getStylesheets().add("main.css");

        ScrollPane scrollPane = new ScrollPane();
        VBox container = new VBox();

        container.setSpacing(10);
        container.setPadding(new Insets(20));

        Label dateLabel = new Label(Service.formatDateTime(note.getLocalDateTime()));
        dateLabel.getStyleClass().add("special-text");

        TextFlow textFlow = new TextFlow();
        Text text = new Text(note.getText());
        text.setWrappingWidth(scrollPane.getWidth() - 40); // Ширина для переноса текста
        textFlow.getChildren().add(text);

        Button deleteButton = new Button("Удалить запись");
        deleteButton.setOnAction(event -> {
            try {
                DataBaseAccess.deleteDiaryNote(note);
                loadUserPage();
            } catch (Exception e) {
                errorNotification(e.getMessage());
            }
        });

        container.getChildren().addAll(dateLabel, textFlow, deleteButton);
        scrollPane.setContent(container);
        root.setCenter(scrollPane);

        // Обновляем ширину текста при изменении размера окна
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            text.setWrappingWidth(newValue.doubleValue() - 40);
        });
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
