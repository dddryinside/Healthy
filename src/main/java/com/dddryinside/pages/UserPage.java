package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.*;
import com.dddryinside.models.User;
import com.dddryinside.service.SecurityManager;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class UserPage implements Page {
    private final User user = SecurityManager.getUser();

    //private final User user = new User("Иванов", "Иван", "Иванович", LocalDate.now(), 1, "test", "123456789");
    @Override
    public Scene getInterface() {
        Profile profile = new Profile(user);
        DiaryNotes diaryNotes = new DiaryNotes();
        Diary diary = new Diary();
        Clock clock = new Clock();

        VBox leftContainer = new VBox(profile, diaryNotes);
        VBox rightContainer = new VBox(diary, clock);
        rightContainer.setSpacing(20);

        HBox container = new HBox(leftContainer, rightContainer);

        Root root = new Root();
        root.setToTopCenter(container);
        root.setMenuBar();

        return new Scene(root);
    }
}
