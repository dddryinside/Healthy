package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.DiaryNoteDTO;
import com.dddryinside.service.RootPane;
import com.dddryinside.service.Service;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class AllDiaryNotesController extends PageLoader implements Controller {
    RootPane root;
    @Override
    public void initializeUI() {
        root = new RootPane();

        VBox container = new VBox(10);
        Label myDairyLabel = new Label("Мой дневник");
        myDairyLabel.getStyleClass().add("special-text");
        container.getChildren().add(myDairyLabel);

        List<DiaryNoteDTO> diaryNotesList = DataBaseAccess.loadDiaryNotes(DataBaseAccess.patient);

        BorderStroke borderStroke = new BorderStroke(
                Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID,
                new CornerRadii(3.0),
                new BorderWidths(1.0)
        );

        Border border = new Border(borderStroke);

        for (DiaryNoteDTO note : diaryNotesList) {
            Label dateTime = new Label(Service.formatDateTime(note.getLocalDateTime()));
            Label text = new Label(note.getText());
            text.setWrapText(true);
            text.setMinWidth(800);
            text.setMaxWidth(800);
            Hyperlink hyperlink = new Hyperlink("Подробнее");
            hyperlink.setOnAction(event -> loadPage(new DiaryNoteController(note)));

            VBox vBox = new VBox();
            vBox.getChildren().addAll(dateTime, text, hyperlink);
            vBox.setPadding(new Insets(10));

            Pane pane = new Pane();
            pane.getChildren().add(vBox);
            pane.setBorder(border);
            container.getChildren().add(pane);
        }

        root.setContent(container);
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
