package com.dddryinside.elements;

import com.dddryinside.service.SVGStorage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Clock extends HBox {
    public Clock() {
        SVGPath icon;
        if (isDaytime()) {
            icon = SVGStorage.getDayIcon();
        } else {
            icon = SVGStorage.getNightIcon();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        SuperLabel timeLabel = new SuperLabel();
        timeLabel.makeBold();
        VBox.setMargin(timeLabel, new Insets(-10, 0, 0, 0));
        timeLabel.setText(LocalTime.now().format(formatter));

        SuperLabel weekDayLabel = new SuperLabel();
        //weekDayLabel.setFont(Font.font("Arial", FontWeight.LIGHT, 20));
        weekDayLabel.makeSpecial();
        weekDayLabel.setText(capitalizeFirstLetter(LocalDate.now().getDayOfWeek().
                getDisplayName(TextStyle.FULL, new Locale("ru"))));
        VBox.setMargin(weekDayLabel, new Insets(-5, 0, 0, 0));

        this.setPadding(new Insets(10, 0, 0, 0));
        this.setSpacing(10);
        this.setMinWidth(330);
        this.getChildren().addAll(icon, new VBox(timeLabel, weekDayLabel));
    }

    private static boolean isDaytime() {
        LocalTime currentTime = LocalTime.now();
        LocalTime morningStart = LocalTime.of(6, 0);
        LocalTime eveningEnd = LocalTime.of(20, 0);

        return currentTime.isAfter(morningStart) && currentTime.isBefore(eveningEnd);
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
