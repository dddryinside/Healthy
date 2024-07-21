package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Diary;
import com.dddryinside.elements.Mood;
import com.dddryinside.elements.Profile;
import com.dddryinside.elements.Root;
import com.dddryinside.models.User;
import com.dddryinside.service.AccountManager;
import com.dddryinside.service.TimeObserver;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserPage extends Page {
    private final User user = AccountManager.getUser();

    @Override
    public Scene getInterface() {
        Profile profile = new Profile(user);
        Diary diary = new Diary();

        Mood mood = new Mood();
        TimeObserver timeObserver = new TimeObserver();
        timeObserver.addWidget(mood);
        timeObserver.startTimerTask();

        VBox leftContainer = new VBox(profile, diary);
        leftContainer.setSpacing(20);

        VBox rightContainer = new VBox(mood);
        rightContainer.setSpacing(20);

        HBox container = new HBox(leftContainer, rightContainer);
        container.setSpacing(70);

        Root root = new Root();
        root.setToTopCenter(container);
        root.setMenuBar();

        return new Scene(root);
    }
}
