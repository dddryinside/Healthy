package com.dddryinside.service;

public enum Colors {
    GREEN("-fx-text-fill: green;"),
    YELLOW("-fx-text-fill: #FF8C00"),
    LIGHTRED("-fx-text-fill: #ff7081"),
    STRONGRED("-fx-text-fill: #cd1c18");

    public final String STYLE;

    Colors(String STYLE) {
        this.STYLE = STYLE;
    }
}
