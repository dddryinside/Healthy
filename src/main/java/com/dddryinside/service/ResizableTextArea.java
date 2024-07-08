package com.dddryinside.service;

import javafx.scene.Cursor;
import javafx.scene.control.TextArea;

public class ResizableTextArea extends TextArea {
    private double minHeight = 50;
    private double maxHeight = 300;
    private double resizeIncrement = 20;

    public ResizableTextArea() {
        setMinHeight(minHeight);
        setMaxHeight(maxHeight);

        setOnMouseEntered(event -> setCursor(Cursor.S_RESIZE));
        setOnMouseExited(event -> setCursor(Cursor.DEFAULT));
        setOnMouseDragged(event -> resizeTextArea(event.getY()));
    }

    private void resizeTextArea(double newHeight) {
        double currentHeight = getHeight();
        double targetHeight = currentHeight + (newHeight - getPrefHeight());

        if (targetHeight >= minHeight && targetHeight <= maxHeight) {
            setMinHeight(targetHeight);
            setMaxHeight(targetHeight);
            setPrefHeight(targetHeight);
        }
    }
}
