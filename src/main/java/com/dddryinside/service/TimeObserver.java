package com.dddryinside.service;

import com.dddryinside.contracts.Widget;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeObserver {
    private final List<Widget> widgets = new ArrayList<>();

    public void addWidget(Widget widget) {
        widgets.add(widget);
    }

    public void startTimerTask() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.minutes(1), event -> checkTimeAndNotify())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void checkTimeAndNotify() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.getHour() == 0 && currentTime.getMinute() == 0) {
            notifyAboutMidnight();
        }

        if (currentTime.getHour() == 20 && currentTime.getMinute() == 0) {
            notifyAboutEvening();
        }
    }

    private void notifyAboutMidnight() {
        for (Widget widget : widgets) {
            widget.onMidnightReached();
        }
    }

    private void notifyAboutEvening() {
        for (Widget widget : widgets) {
            widget.onEveningReached();
        }
    }
}
