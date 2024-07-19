package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.elements.SuperLabel;
import com.dddryinside.service.PageManager;
import com.dddryinside.service.SecurityManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LogInPage implements Page {
    @Override
    public Scene getInterface() {
        SuperLabel title = new SuperLabel("Mental");
        title.makeBold();

        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Имя пользователя (username):");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Пароль:");

        HBox buttonsBlock = new HBox();
        Hyperlink createNewProfileButton = new Hyperlink("Создать новый профиль");
        createNewProfileButton.setOnAction(event -> PageManager.loadPage(new RegistrationPage()));

        Hyperlink enterButton = new Hyperlink("Войти");
        enterButton.setOnAction(event -> {
            if (usernameInput.getText().isEmpty()) {
                PageManager.showNotification("Кажется, вы забыли ввести имя пользователя!");
            } else if (passwordInput.getText().isEmpty()) {
                PageManager.showNotification("Кажется, вы забыли ввести пароль!");
            } else {
                SecurityManager.logIn(usernameInput.getText(), passwordInput.getText());

                if (SecurityManager.getUser() != null) {
                    PageManager.loadPage(new UserPage());
                }
            }
        });

        buttonsBlock.getChildren().addAll(createNewProfileButton, enterButton);
        buttonsBlock.setSpacing(10);

        VBox container = new VBox(title, usernameInput, passwordInput, buttonsBlock);
        container.setAlignment(Pos.CENTER);
        container.setMaxWidth(300);
        container.setSpacing(10);

        Root root = new Root();
        root.setToCenter(container);

        return new Scene(root);
    }
}

