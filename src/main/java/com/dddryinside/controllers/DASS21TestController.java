package com.dddryinside.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.WebView;

public class DASS21TestController {
    @FXML  ToggleGroup question_1;
    @FXML WebView description;

    public void initialize() {
        String filePath = getClass().getResource("/html/dass-21-test-description.html").toExternalForm();
        description.getEngine().load(filePath);
    }

    public void submit() {
        RadioButton selectedRadio = (RadioButton) question_1.getSelectedToggle();

        if (selectedRadio != null) {
            String selectedText = (String) selectedRadio.getUserData();
            System.out.println("Выбран вариант: " + selectedText);
        } else {
            System.out.println("Ничего не выбрано");
        }
    }
}
