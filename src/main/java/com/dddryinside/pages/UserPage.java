package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.*;
import com.dddryinside.models.User;
import com.dddryinside.service.SecurityManager;
import com.dddryinside.service.TimeObserver;
import eu.hansolo.tilesfx.*;
import eu.hansolo.tilesfx.skins.ClockTileSkin;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import eu.hansolo.toolbox.time.*;

public class UserPage implements Page {
    private final User user = SecurityManager.getUser();

    @Override
    public Scene getInterface() {
        Profile profile = new Profile(user);
        Diary diary = new Diary();

        Mood mood = new Mood();
        TimeObserver timeObserver = new TimeObserver();
        timeObserver.addWidget(mood);
        timeObserver.startTimerTask();

        MoodChart moodChart = new MoodChart();

        VBox leftContainer = new VBox(profile, diary);
        leftContainer.setSpacing(20);

        VBox rightContainer = new VBox(mood, moodChart);
        rightContainer.setSpacing(20);

        HBox container = new HBox(leftContainer, rightContainer);
        container.setSpacing(70);

        Root root = new Root();
        root.setToTopCenter(container);
        root.setMenuBar();

        return new Scene(root);
    }
}
