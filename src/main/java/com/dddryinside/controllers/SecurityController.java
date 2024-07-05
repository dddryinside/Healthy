package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class SecurityController extends PageLoader {
    @FXML PasswordField passwordField;

    public void enter() {
        if (passwordField != null) {
            if (DataBaseAccess.findUserByPassword(passwordField.getText()) == - 1) {
                errorNotification("Пароль не верный!");
            } else {
                loadMainPage();
            }
        }
    }
}
