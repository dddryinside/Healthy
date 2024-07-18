package com.dddryinside.elements;

import com.dddryinside.contracts.Widget;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.PageManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalTime;

public class Mood extends Panel implements Widget {
    private final VBox surveyBox;

    public Mood() {
        SuperLabel title = new SuperLabel("Среднее настроение");
        title.makeTitle();

        double avgMood = DataBaseAccess.getAverageMood();
        SuperLabel avgMoodLabel = new SuperLabel(String.valueOf(avgMood));
        avgMoodLabel.makeTitle();
        defineColor(avgMoodLabel, avgMood);

        double avgMonthMood = DataBaseAccess.getAverageMood(30);
        SuperLabel avgMonthMoodLabel = new SuperLabel(String.valueOf(avgMonthMood));
        avgMonthMoodLabel.makeTitle();
        defineColor(avgMonthMoodLabel, avgMonthMood);

        surveyBox = new VBox();
        surveyBox.setSpacing(5);
        fillTheSurveyBox();
        this.getChildren().addAll(title, avgMoodLabel, surveyBox);

        this.setPadding(new Insets(10));
        this.setMinWidth(330);
    }

    private void fillTheSurveyBox() {
        if (!DataBaseAccess.isDailySurveyCompleted() && surveyBox.getChildren().isEmpty() && isEveningTime()) {
            Label moodInputLabel = new Label("Оцените ваше среднее настроение за день");

            Spinner<Integer> moodInput = new Spinner<>();
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
            moodInput.setValueFactory(valueFactory);
            moodInput.setEditable(true);

            Button saveButton = new Button("ОК");
            saveButton.setOnAction(event -> saveResult(moodInput.getValueFactory().getValue()));

            HBox inputBox = new HBox(moodInput, saveButton);
            inputBox.setSpacing(10);
            surveyBox.getChildren().addAll(moodInputLabel, inputBox);
        }
    }

    private void saveResult(Integer value) {
        if (value == null || value < 0 || value > 10) {
            PageManager.showNotification("Оцените настроение от 0 до 10!");
        } else {
            DataBaseAccess.saveDailySurvey(value);
        }
    }

    private void defineColor(SuperLabel label, double value) {
        if (value != 0) {
            if (value > 7) {
                label.makeGreen();
            } else if (value > 5) {
                label.makeYellow();
            } else if (value != 0){
                label.makeRed();
            }
        }
    }

    @Override
    public void onEveningReached() {
        fillTheSurveyBox();
    }

    @Override
    public void onMidnightReached() {
        if (!surveyBox.getChildren().isEmpty()) {
            surveyBox.getChildren().clear();
        }
    }

    private static boolean isEveningTime() {
        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = LocalTime.of(19, 59);
        LocalTime endTime = LocalTime.of(23, 59);

        return currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
    }
}
