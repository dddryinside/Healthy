package com.dddryinside.controllers.tests;

import com.dddryinside.DTO.DASS21;
import com.dddryinside.DTO.Patient;
import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.TestsDataBaseAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import java.util.List;

public class DASS21Controller extends PageLoader {
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

    private boolean isSubmitted = false;

    public void initialize() {
        String filePath = getClass().getResource("/tests/dass21/dass21-description.html").toExternalForm();
        description.getEngine().load(filePath);
    }

    private RadioButton[] getAllRadios() {
        RadioButton[] allRadios = new RadioButton[]{
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

        return allRadios;
    }

    public void submit() {
        if (!isSubmitted) {
            int depression = 0;
            int anxiety = 0;
            int stress = 0;
            boolean checkDone = true;

            RadioButton[] allRadios = getAllRadios();
            for (int i = 0; i < allRadios.length; i++) {
                if (allRadios[i] != null) {
                    String answer = (String) allRadios[i].getUserData();

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
                isSubmitted = true;
                loadResults(depression, anxiety, stress);
            }
        }
    }

    public void clean() {
        question_1.selectToggle(null);
        question_2.selectToggle(null);
        question_3.selectToggle(null);
        question_4.selectToggle(null);
        question_5.selectToggle(null);
        question_6.selectToggle(null);
        question_7.selectToggle(null);
        question_8.selectToggle(null);
        question_9.selectToggle(null);
        question_10.selectToggle(null);
        question_11.selectToggle(null);
        question_12.selectToggle(null);
        question_13.selectToggle(null);
        question_14.selectToggle(null);
        question_15.selectToggle(null);
        question_16.selectToggle(null);
        question_17.selectToggle(null);
        question_18.selectToggle(null);
        question_19.selectToggle(null);
        question_20.selectToggle(null);
        question_21.selectToggle(null);

        resultsBox.getChildren().clear();
        isSubmitted = false;
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

        HBox buttons = new HBox();
        buttons.setPadding(new Insets(10, 0, 0, 0));
        buttons.setSpacing(10);


        ComboBox<Patient> patientListView = new ComboBox<>();
        patientListView.setMaxWidth(200);

        // Получаем список пациентов из БД
        List<Patient> patientList = DataBaseAccess.getAllPatients();

        // Преобразуем ArrayList в ObservableList
        ObservableList<Patient> patients = FXCollections.observableArrayList(patientList);

        // Устанавливаем список пациентов в ListView
        patientListView.setItems(patients);

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> {
            Patient patient = patientListView.getSelectionModel().getSelectedItem();
            if (patient != null) {
                try {
                    DASS21.saveTestResult(patient.getId(), depression, anxiety, stress);
                    clean();
                } catch (Error e) {
                    errorNotification("Ошибка записи!");
                }
            }
        });

        buttons.getChildren().add(saveButton);
        buttons.getChildren().add(patientListView);
        resultsBox.getChildren().add(buttons);
    }
}
