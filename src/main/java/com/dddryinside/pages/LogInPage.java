package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.elements.SuperLabel;
import com.dddryinside.service.PageManager;
import com.dddryinside.service.AccountManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LogInPage extends Page {
    @Override
    public Scene getInterface() {
        SuperLabel title = new SuperLabel("Mental");
        title.makeBold();

        TextField usernameInput = new TextField();
        usernameInput.setPromptText(localeRes.getString("username"));
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText(localeRes.getString("password"));

        HBox buttonsBlock = new HBox();
        Hyperlink createNewProfileButton = new Hyperlink(localeRes.getString("create_new_profile"));
        createNewProfileButton.setOnAction(event -> PageManager.loadPage(new RegPage()));

        Hyperlink enterButton = new Hyperlink(localeRes.getString("enter"));
        enterButton.setOnAction(event -> {
            if (usernameInput.getText().isEmpty()) {
                PageManager.showNotification(localeRes.getString("forgot_username_message"));
            } else if (passwordInput.getText().isEmpty()) {
                PageManager.showNotification(localeRes.getString("forgot_password_message"));
            } else {
                try {
                    AccountManager.logIn(usernameInput.getText(), passwordInput.getText());
                } catch (Exception e) {
                    if (e.getMessage().equals("not_found")) {
                        PageManager.showNotification(localeRes.getString("not_found"));
                    } else {
                        PageManager.showNotification(e.getMessage());
                    }
                }

                // If user was found
                if (AccountManager.getUser() != null) {
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

