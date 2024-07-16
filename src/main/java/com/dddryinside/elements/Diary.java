package com.dddryinside.elements;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Diary extends VBox {
    public Diary() {
        SuperLabel title = new SuperLabel("Дневник");
        title.makeSpecial();

        TextArea diaryTextArea = new TextArea();
        diaryTextArea.setPromptText("Жизнь прекрасна!");

        Button saveButton = new Button("Сохранить");
        Button clearButton = new Button("Очистить");

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(saveButton, clearButton);

        this.setSpacing(5);
        this.setMinWidth(550);
        this.getChildren().addAll(title, diaryTextArea, buttonsBox);

        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, null, null));
        this.setBackground(DEFAULT_BACKGROUND);
    }
}
