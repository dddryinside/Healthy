package com.dddryinside.controllers;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public interface Controller {
    public void initializeUI();
    public Parent getRoot();
}

