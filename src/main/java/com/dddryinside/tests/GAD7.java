package com.dddryinside.tests;

import com.dddryinside.service.Patient;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE;

public class GAD7 implements Test {
    private VBox root;
    public Parent initializeUI() {
        root = new VBox();

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(UNAVAILABLE);
        Tab testTab = new Tab("Тест");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(460);
        scrollPane.setPadding(new Insets(20, 20, 20, 20));

        VBox vBox = new VBox();
        vBox.setSpacing(10);

        Label testName = new Label("Тест для оценки уровня генерализированного тревожного расстройства, GAD-7");
        testName.setWrapText(true);
        testName.setMaxWidth(400);
        testName.getStyleClass().add("bold-text");
        Label instructions = new Label("Как часто за последние 2 недели вас беспокоили следующие проблемы?");
        instructions.setWrapText(true);
        instructions.setPrefWidth(400);
        vBox.getChildren().addAll(testName, instructions);

        // Вопросы теста
        List<ToggleGroup> allQuestions = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                createQuestion(allQuestions, vBox, "1. Повышенная нервная возбудимость, беспокойство или раздражительность");
            } else if (i == 1) {
                createQuestion(allQuestions, vBox,"2. Неспособность справиться с волнением");
            } else if (i == 2) {
                createQuestion(allQuestions, vBox,"3. Чрезмерное беспокойство по разному поводу");
            } else if (i == 3) {
                createQuestion(allQuestions, vBox,"4. Неспособность расслабиться");
            } else if (i == 4) {
                createQuestion(allQuestions, vBox,"5. Крайняя степень беспокойства: «не могу найти себе места»");
            } else if (i == 5) {
                createQuestion(allQuestions, vBox,"6. Легко поддаюсь чувству беспокойства или раздражительности");
            } else {
                createQuestion(allQuestions, vBox,"7. Опасение чего-то страшного");
            }
        }

        Button submitButton = new Button("Получить результаты");
        submitButton.setOnAction(event -> {
            submit(allQuestions);
        });

        vBox.getChildren().add(submitButton);

        // Общая загрузка в корень
        scrollPane.setContent(vBox);
        testTab.setContent(scrollPane);
        tabPane.getTabs().add(testTab);
        root.getChildren().add(tabPane);

        return root;
    }

    private void createQuestion(List<ToggleGroup> allQuestions, VBox vBox, String questionName) {
        Label questionLabel = new Label(questionName);
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(400);
        ToggleGroup question = new ToggleGroup();

        RadioButton neverOption = new RadioButton("Никогда");
        neverOption.setUserData(0);
        RadioButton someDaysOption = new RadioButton("Несколько дней");
        someDaysOption.setUserData(1);
        RadioButton moreDaysOption = new RadioButton("Больше половины дней");
        moreDaysOption.setUserData(2);
        RadioButton almostDailyOption = new RadioButton("Почти каждый день");
        almostDailyOption.setUserData(3);

        neverOption.setToggleGroup(question);
        someDaysOption.setToggleGroup(question);
        moreDaysOption.setToggleGroup(question);
        almostDailyOption.setToggleGroup(question);

        allQuestions.add(question);
        vBox.getChildren().addAll(questionLabel, neverOption, someDaysOption, moreDaysOption, almostDailyOption);
    }

    private void submit(List<ToggleGroup> allQuestions) {
        for (int i = 0; i < allQuestions.size(); i++) {
            RadioButton selectedOption = (RadioButton) allQuestions.get(i).getSelectedToggle();
            if (selectedOption != null) {
                int selectedValue = (int) selectedOption.getUserData();
                System.out.println("Выбрана опция: " + selectedValue);
            } else {
                int questionNumber = i + 1;
                errorNotification("Вы не ответили на вопрос №" + questionNumber);
                break;
            }
        }
    }

    protected void errorNotification(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @Override
    public LineChart<String, Number> showStatistics(Patient patient) {
        return null;
    }
}
