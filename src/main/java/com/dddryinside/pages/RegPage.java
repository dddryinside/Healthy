package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.models.User;
import com.dddryinside.service.PageManager;
import com.dddryinside.service.SecurityManager;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class RegPage implements Page {
    @Override
    public Scene getInterface() {
        Label secondNameLabel = new Label("Фамилия:");
        TextField secondNameInput = new TextField();

        Label nameLabel = new Label("Имя:");
        TextField nameInput = new TextField();

        Label additionalNameLabel = new Label("Отчество:");
        TextField additionalNameInput = new TextField();

        Label birthDateLabel = new Label("Дата рождения:");
        DatePicker birthDateInput = new DatePicker();
        birthDateInput.setMinWidth(300);

        Label genderLabel = new Label("Пол:");
        ToggleGroup genderInputToggle = new ToggleGroup();

        RadioButton maleOption = new RadioButton("Мужчина");
        RadioButton femaleOption = new RadioButton("Женщина");
        maleOption.setToggleGroup(genderInputToggle);
        maleOption.setUserData(1);
        femaleOption.setToggleGroup(genderInputToggle);
        femaleOption.setUserData(0);
        HBox genderToggles = new HBox(maleOption, femaleOption);
        genderToggles.setSpacing(10);

        Label usernameLabel = new Label("Имя пользователя (username):");
        TextField usernameInput = new TextField();

        Label passwordLabel = new Label("Пароль:");
        TextField passwordInput = new TextField();

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> saveUser(nameInput.getText(), secondNameInput.getText(),
                additionalNameInput.getText(), birthDateInput.getValue(), genderInputToggle.getSelectedToggle(),
                usernameInput.getText(), passwordInput.getText()));

        Button exitButton = new Button("Выйти");
        exitButton.setOnAction(event -> PageManager.loadPage(new LogInPage()));

        HBox buttonsBox = new HBox(saveButton, exitButton);
        buttonsBox.setSpacing(10);

        VBox container = new VBox(secondNameLabel, secondNameInput, nameLabel, nameInput,
                additionalNameLabel, additionalNameInput, birthDateLabel, birthDateInput,
                genderLabel, genderToggles, usernameLabel, usernameInput,
                passwordLabel, passwordInput, buttonsBox);
        container.setSpacing(10);
        container.setMaxWidth(300);

        Root root = new Root();
        root.setToCenter(container);

        return new Scene(root);
    }

    private void saveUser(String name, String secondName, String additionalName,
                          LocalDate birthDate, Toggle gender, String username, String password) {
        if (secondName == null) {
            PageManager.showNotification("Кажется, вы забыли ввести фамилию!");
        } else if (name == null) {
            PageManager.showNotification("Кажется, вы забыли ввести имя!");
        } else if (additionalName == null) {
            PageManager.showNotification("Кажется, вы забыли ввести отчество!");
        } else if (birthDate == null) {
            PageManager.showNotification("Кажется, вы забыли ввести дату рождения!");
        } else if (!gender.isSelected()){
            PageManager.showNotification("Кажется, вы не ввели свой пол!");
        } else if (username == null) {
            PageManager.showNotification("Кажется, вы забыли ввести имя пользователя пользователя!");
        } else if (!SecurityManager.isUsernameAvailable(username)) {
            PageManager.showNotification("Имя пользователя «" + username + "» уже занято, нужно придумать другое!");
        } else if (password == null || password.length() < 8){
            PageManager.showNotification("Пароль не должен быть короче 8-ми символов!");
        } else {
            User user = new User(name, secondName, additionalName, birthDate, (int) gender.getUserData(), username, password);
            try {
                SecurityManager.saveUser(user);
                PageManager.loadPage(new UserPage());
            } catch (Exception e) {
                PageManager.showNotification(e.getMessage());
            }
        }
    }
}
