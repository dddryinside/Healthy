package com.dddryinside.service;

import javafx.scene.control.TextArea;

public class ResizableTextArea extends TextArea {
    private static final int standardHeight = 300;
    private static final int standardWidth = 450;
    private double scale;

    public ResizableTextArea() {
        this.scale = 1.0;
        updateSize();
    }

    public void makeBigger() {
        if (scale < 2.0) {
            this.scale += 0.25;
            updateSize();
        }
    }

    public void makeSmaller() {
        if (scale > 1.0) {
            this.scale -= 0.25;
            updateSize();
        }
    }

    private void updateSize() {
        this.setPrefHeight(scale * standardHeight);
        this.setMaxWidth(scale * standardWidth);
    }
}
