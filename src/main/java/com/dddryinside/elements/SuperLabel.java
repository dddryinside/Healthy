package com.dddryinside.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class SuperLabel extends Label {
    public SuperLabel(String value) {
        super(value);
        this.setFont(Font.font("Arial", 14));
        this.setPadding(Insets.EMPTY);
    }

    public SuperLabel() {}

    public void makeSmall() { this.setFont(Font.font(10)); }

    public void makeTitle() {
        this.getStyleClass().add("title-text");
    }

    public void makeBold() {
        this.getStyleClass().add("bold-text");
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

    public void makeBlue() { this.setStyle("-fx-text-fill: #0095C8"); }

    public void makePurple() {this.setStyle("-fx-text-fill: #935CB5");}

    public void makeGrey() {this.setStyle("-fx-text-fill: grey");}
}
