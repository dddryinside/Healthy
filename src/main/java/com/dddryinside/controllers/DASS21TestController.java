package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class DASS21TestController extends PageLoader {
    @FXML  ToggleGroup question_1;
    @FXML  ToggleGroup question_2;
    @FXML  ToggleGroup question_3;
    @FXML  ToggleGroup question_4;
    @FXML  ToggleGroup question_5;
    @FXML  ToggleGroup question_6;
    @FXML  ToggleGroup question_7;
    @FXML  ToggleGroup question_8;
    @FXML  ToggleGroup question_9;
    @FXML  ToggleGroup question_10;
    @FXML  ToggleGroup question_11;
    @FXML  ToggleGroup question_12;
    @FXML  ToggleGroup question_13;
    @FXML  ToggleGroup question_14;
    @FXML  ToggleGroup question_15;
    @FXML  ToggleGroup question_16;
    @FXML  ToggleGroup question_17;
    @FXML  ToggleGroup question_18;
    @FXML  ToggleGroup question_19;
    @FXML  ToggleGroup question_20;
    @FXML  ToggleGroup question_21;
    @FXML  WebView description;
    @FXML  VBox resultsBox;

    public void initialize() {
        String filePath = getClass().getResource("/html/dass-21-test-description.html").toExternalForm();
        description.getEngine().load(filePath);
    }

    public void submit() {
        RadioButton[] selectedRadios = new RadioButton[]{
                (RadioButton) question_1.getSelectedToggle(),
                (RadioButton) question_2.getSelectedToggle(),
                (RadioButton) question_3.getSelectedToggle(),
                (RadioButton) question_4.getSelectedToggle(),
                (RadioButton) question_5.getSelectedToggle(),
                (RadioButton) question_6.getSelectedToggle(),
                (RadioButton) question_7.getSelectedToggle(),
                (RadioButton) question_8.getSelectedToggle(),
                (RadioButton) question_9.getSelectedToggle(),
                (RadioButton) question_10.getSelectedToggle(),
                (RadioButton) question_11.getSelectedToggle(),
                (RadioButton) question_12.getSelectedToggle(),
                (RadioButton) question_13.getSelectedToggle(),
                (RadioButton) question_14.getSelectedToggle(),
                (RadioButton) question_15.getSelectedToggle(),
                (RadioButton) question_16.getSelectedToggle(),
                (RadioButton) question_17.getSelectedToggle(),
                (RadioButton) question_18.getSelectedToggle(),
                (RadioButton) question_19.getSelectedToggle(),
                (RadioButton) question_20.getSelectedToggle(),
                (RadioButton) question_21.getSelectedToggle(),
        };

        int depression = 0;
        int anxiety = 0;
        int stress = 0;
        boolean checkDone = true;

        for (int i = 0; i < selectedRadios.length; i++) {
            if (selectedRadios[i] != null) {
                String answer = (String) selectedRadios[i].getUserData();

                int checkValue = i + 1;
                if (checkValue == 3 || checkValue == 5 || checkValue == 10 || checkValue == 13
                     || checkValue == 16 || checkValue == 17 || checkValue == 21) {
                    depression += Integer.parseInt(answer);
                } else if (checkValue == 2 || checkValue == 4 || checkValue == 7 || checkValue == 9
                     || checkValue == 15 || checkValue == 19 || checkValue == 20) {
                    anxiety += Integer.parseInt(answer);
                } else {
                    stress += Integer.parseInt(answer);
                }
            } else {
                errorNotification("Вы не ответили на " + (i + 1) + " вопрос");
                checkDone = false;
                break;
            }
        }

        if (checkDone) {
            loadResults(depression, anxiety, stress);
        }
    }

    private void loadResults(Integer depression, Integer anxiety, Integer stress) {
        Label depressionLabel = new Label();
        depressionLabel.getStyleClass().add("bold-text");

        if (depression <= 4) {
            depressionLabel.setText("Депрессия отсутствует");
            depressionLabel.setStyle("-fx-text-fill: green;");
        } else if (depression <= 6) {
            depressionLabel.setText("Лёгкая форма депрессии");
            depressionLabel.setStyle("-fx-text-fill: #FF8C00;");
        } else if (depression <= 10) {
            depressionLabel.setText("Умеренная форма депрессии");
            depressionLabel.setStyle("-fx-text-fill: #FF8C00;");
        } else if (depression <= 13) {
            depressionLabel.setText("Тяжёлая форма депрессии");
            depressionLabel.setStyle("-fx-text-fill: red;");
        } else {
            depressionLabel.setText("Очень тяжёлая форма депрессии");
            depressionLabel.setStyle("-fx-text-fill: red;");
        }

        resultsBox.getChildren().add(depressionLabel);

        Label depressionDescriptionLabel = new Label();
        depressionDescriptionLabel.setText("Значение депрессии: " + depression);
        resultsBox.getChildren().add(depressionDescriptionLabel);

        Label anxietyLabel = new Label();
        anxietyLabel.getStyleClass().add("bold-text");

        if (anxiety <= 3) {
            anxietyLabel.setText("Тревога отсутствует");
            anxietyLabel.setStyle("-fx-text-fill: green;");
        } else if (anxiety <= 5) {
            anxietyLabel.setText("Лёгкая тревога");
            anxietyLabel.setStyle("-fx-text-fill: #FF8C00;");
        } else if (anxiety <= 7) {
            anxietyLabel.setText("Умеренная тревога");
            anxietyLabel.setStyle("-fx-text-fill: #FF8C00;");
        } else if (anxiety <= 9) {
            anxietyLabel.setText("Тяжёлая тревога");
            anxietyLabel.setStyle("-fx-text-fill: red;");
        } else {
            anxietyLabel.setText("Очень тяжёлая тревога");
            anxietyLabel.setStyle("-fx-text-fill: red;");
        }

        resultsBox.getChildren().add(anxietyLabel);

        Label anxietyDescriptionLabel = new Label();
        anxietyDescriptionLabel.setText("Значение тревожности: " + anxiety);
        resultsBox.getChildren().add(anxietyDescriptionLabel);

        Label stressLabel = new Label();
        stressLabel.getStyleClass().add("bold-text");

        if (anxiety <= 3) {
            stressLabel.setText("Стресс отсутствует");
            stressLabel.setStyle("-fx-text-fill: green;");
        } else if (anxiety <= 5) {
            stressLabel.setText("Лёгкий стресс");
            stressLabel.setStyle("-fx-text-fill: #FF8C00;");
        } else if (anxiety <= 7) {
            stressLabel.setText("Умеренный стресс");
            stressLabel.setStyle("-fx-text-fill: #FF8C00;");
        } else if (anxiety <= 9) {
            stressLabel.setText("Тяжёлый стресс");
            stressLabel.setStyle("-fx-text-fill: red;");
        } else {
            stressLabel.setText("Очень тяжёлый стресс");
            stressLabel.setStyle("-fx-text-fill: red;");
        }

        resultsBox.getChildren().add(stressLabel);

        Label stressDescriptionLabel = new Label();
        stressDescriptionLabel.setText("Значение стресса: " + stress);
        resultsBox.getChildren().add(stressDescriptionLabel);
    }
}
