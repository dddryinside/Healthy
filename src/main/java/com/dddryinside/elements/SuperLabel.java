package com.dddryinside.elements;

import javafx.scene.control.Label;

public class SuperLabel extends Label {
    public SuperLabel(String value) {
        super(value);
    }

    public SuperLabel() {}

    public void makeTitle() {
        this.getStyleClass().add("title-text");
    }

    public void makeInterfaceText() { this.getStyleClass().add("interface-text"); }

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

    public void makeBlue() { this.setStyle("-fx-text-fill:#0095C8"); }

    public void makePurple() {this.setStyle("-fx-text-fill:#935CB5");}
}
