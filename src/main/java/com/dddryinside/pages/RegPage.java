package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.elements.SuperLabel;
import com.dddryinside.models.User;
import com.dddryinside.service.AccountManager;
import com.dddryinside.service.PageManager;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegPage extends Page {
    @Override
    public Scene getInterface() {
        SuperLabel secondNameLabel = new SuperLabel(localeRes.getString("surname"));
        TextField secondNameInput = new TextField();

        SuperLabel nameLabel = new SuperLabel(localeRes.getString("name"));
        TextField nameInput = new TextField();

        SuperLabel additionalNameLabel = new SuperLabel(localeRes.getString("additional_name"));
        TextField additionalNameInput = new TextField();

        SuperLabel usernameLabel = new SuperLabel(localeRes.getString("username_reg"));
        TextField usernameInput = new TextField();

        SuperLabel passwordLabel = new SuperLabel(localeRes.getString("password_reg"));
        TextField passwordInput = new TextField();

        Hyperlink saveButton = new Hyperlink(localeRes.getString("save"));
        saveButton.setOnAction(event -> saveUser(nameInput.getText(), secondNameInput.getText(),
                additionalNameInput.getText(), usernameInput.getText(), passwordInput.getText()));

        Hyperlink exitButton = new Hyperlink(localeRes.getString("exit"));
        exitButton.setOnAction(event -> PageManager.loadPage(new LogInPage()));

        HBox buttonsBox = new HBox(saveButton, exitButton);
        buttonsBox.setSpacing(10);

        VBox container = new VBox(secondNameLabel, secondNameInput, nameLabel, nameInput,
                additionalNameLabel, additionalNameInput, usernameLabel, usernameInput,
                passwordLabel, passwordInput, buttonsBox);
        container.setSpacing(10);
        container.setMaxWidth(300);

        Root root = new Root();
        root.setToCenter(container);

        return new Scene(root);
    }

    private void saveUser(String name, String secondName, String additionalName,
                          String username, String password) {
        if (name == null) {
            PageManager.showNotification(localeRes.getString("forgot_name"));
        } else if (username == null) {
            PageManager.showNotification(localeRes.getString("forgot_username_message"));
        } else if (!AccountManager.isUsernameAvailable(username)) {
            PageManager.showNotification(localeRes.getString("username_taken"));
        } else if (password == null || password.length() < 8){
            PageManager.showNotification(localeRes.getString("password_too_short"));
        } else {
            User user = new User(name, secondName, additionalName, username, password);
            try {
                AccountManager.saveUser(user);
                PageManager.loadPage(new UserPage());
            } catch (Exception e) {
                PageManager.showNotification(e.getMessage());
            }
        }
    }
}
