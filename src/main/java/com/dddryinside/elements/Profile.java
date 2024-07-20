package com.dddryinside.elements;

import com.dddryinside.contracts.Page;
import com.dddryinside.models.User;
import com.dddryinside.service.PageManager;
import com.dddryinside.service.ResourceManager;
import com.dddryinside.service.SecurityManager;
import javafx.scene.Cursor;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class Profile extends VBox {
    ImageView avatar = new ImageView();

    public Profile(User user) {
        loadAvatar();

        SuperLabel fioLabel = new SuperLabel(user.getFio());
        fioLabel.setWrapText(true);
        fioLabel.makeBold();

        SuperLabel username = new SuperLabel("@" + user.getUsername());
        username.makeTitle();
        username.makeGrey();

        Hyperlink editProfileButton = new Hyperlink(Page.localeRes.getString("edit"));
        //editProfileButton.setOnAction(event -> PageManager.loadPage(new UpdateUserPage()));
        Hyperlink logOutButton = new Hyperlink(Page.localeRes.getString("exit"));
        logOutButton.setOnAction(event -> SecurityManager.logOut());

        HBox buttonsBox = new HBox(editProfileButton, logOutButton);
        buttonsBox.setSpacing(10);

        VBox infoContainer = new VBox(fioLabel, username, buttonsBox);

        HBox container = new HBox(avatar, infoContainer);
        container.setSpacing(10);

        avatar.setOnMouseClicked(event -> importImage());

        this.getChildren().addAll(container);
        this.setMaxWidth(500);
        this.setMinWidth(500);

/*        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
        this.setBackground(DEFAULT_BACKGROUND);*/
    }

    private void loadAvatar() {
        Image image = new Image(ResourceManager.loadResource("/img/icon.png"));
        avatar.setImage(image);
        avatar.setCursor(Cursor.HAND);

        avatar.setFitWidth(100);
        avatar.setFitHeight(100);

        Circle clip = new Circle(50, 50, 50);
        avatar.setClip(clip);
    }

    private void importImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите аватарку");
        File selectedFile = fileChooser.showOpenDialog(PageManager.getStage());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            avatar.setImage(image);
            try {
                ResourceManager.saveImageToResources(image, "dddryinside.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
