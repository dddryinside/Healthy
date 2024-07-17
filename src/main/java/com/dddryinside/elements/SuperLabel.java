package com.dddryinside.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SuperLabel extends Label {
    public SuperLabel(String value) {
        super(value);
    }

    public SuperLabel() {}

    public void makeSpecial() {
        this.getStyleClass().add("special-text");
    }

    public void makeBold() {
        this.getStyleClass().add("bold-text");
    }

    public void makeBig() {
        this.getStyleClass().add("big-text");
    }

    public void makeGreen() {
        this.setStyle("-fx-text-fill: green;");
    }

    public void makeYellow() {
        this.setStyle("-fx-text-fill: yellow;");
    }

    public void makeRed() {
        this.setStyle("-fx-text-fill: red;");
    }
}
