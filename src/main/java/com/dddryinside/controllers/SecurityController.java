package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SecurityController extends PageLoader {
    @FXML PasswordField passwordField;

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            enter();
        }
    }

    public void enter() {
        if (passwordField != null) {
            if (DataBaseAccess.findUserByPassword(passwordField.getText()) == - 1) {
                errorNotification("Пароль не верный!");
            } else {
                loadUserPage();
            }
        }
    }
}
