package com.dddryinside.elements;

import com.dddryinside.models.User;
import com.dddryinside.service.SecurityManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Profile extends VBox {
    public Profile(User user) {
        SuperLabel fioLabel = new SuperLabel(user.getFio());
        fioLabel.setWrapText(true);
        fioLabel.makeBold();

        Label usernameLabel = new Label("Имя пользователя: " + user.getUsername());
        Label birthDateLabel = new Label("Дата рождения: " + user.getStringBirthDate());
        Label genderLabel = new Label("Пол: " + user.getStringGender());

        Button editProfileButton = new Button("Редактировать профиль");
        //editProfileButton.setOnAction(event -> PageManager.loadPage(new UpdateUserPage()));
        Button logOutButton = new Button("Выйти");
        logOutButton.setOnAction(event -> SecurityManager.logOut());

        HBox buttonsBox = new HBox(editProfileButton, logOutButton);
        buttonsBox.setSpacing(10);

        this.getChildren().addAll(fioLabel, usernameLabel, birthDateLabel, genderLabel, buttonsBox);
        this.setSpacing(5);
        this.setMaxWidth(350);
        this.setMinWidth(350);
        this.setWidth(350);

/*        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
        this.setBackground(DEFAULT_BACKGROUND);*/
    }
}
