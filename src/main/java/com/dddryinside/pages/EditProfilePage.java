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

public class EditProfilePage extends Page {
    private final User user;
    public EditProfilePage() {
        this.user = AccountManager.getUser();
    }

    @Override
    public Scene getInterface() {
        SuperLabel secondNameLabel = new SuperLabel(localeRes.getString("surname"));
        TextField secondNameInput = new TextField(user.getSecondName());

        SuperLabel nameLabel = new SuperLabel(localeRes.getString("name"));
        TextField nameInput = new TextField(user.getName());

        SuperLabel additionalNameLabel = new SuperLabel(localeRes.getString("additional_name"));
        TextField additionalNameInput = new TextField(user.getAdditionalName());

        Hyperlink saveButton = new Hyperlink(localeRes.getString("save"));
        saveButton.setOnAction(event -> updateUser(nameInput.getText(), secondNameInput.getText(),
                additionalNameInput.getText()));

        Hyperlink exitButton = new Hyperlink(localeRes.getString("exit"));
        exitButton.setOnAction(event -> PageManager.loadPage(new UserPage()));

        HBox buttonsBox = new HBox(saveButton, exitButton);
        buttonsBox.setSpacing(10);

        VBox container = new VBox(secondNameLabel, secondNameInput, nameLabel, nameInput,
                additionalNameLabel, additionalNameInput, buttonsBox);
        container.setSpacing(10);
        container.setMaxWidth(300);

        Root root = new Root();
        root.setToCenter(container);
        root.setMenuBar();

        return new Scene(root);
    }

    private void updateUser(String name, String secondName, String additionalName) {
        if (name.isEmpty()) {
            PageManager.showNotification(localeRes.getString("forgot_name"));
        } else {
            User user = new User(name, secondName, additionalName, this.user.getUsername(), this.user.getPassword());
            try {
                AccountManager.updateUser(user);
                PageManager.loadPage(new UserPage());
            } catch (Exception e) {
                PageManager.showNotification(e.getMessage());
            }
        }
    }
}
