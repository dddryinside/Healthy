package com.dddryinside.contracts;

import javafx.scene.Scene;

import java.util.ResourceBundle;

public abstract class Page {
    public static ResourceBundle localeRes;
    public abstract Scene getInterface();
}