package com.dddryinside.tests;

import com.dddryinside.PageLoader;
import com.dddryinside.service.Colors;
import com.dddryinside.service.PatientDTO;
import com.dddryinside.service.RootPane;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YBOCS extends PageLoader implements Test {
    List<ToggleGroup> allToggles;
    VBox testBox;
    RootPane root;
    TabPane tabPane;
    Tab testTab;
    @Override
    public void initializeUI() {
        root = new RootPane();

        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // TEST------------------------------------------------------------------------------------------
        testTab = new Tab("Тест");
        testBox = new VBox();
        testBox.setSpacing(10);
        testBox.setMinWidth(840);
        testTab.setClosable(false);

        Label testName = new Label("Шкала Йеля-Брауна для оценки симптомов ОКР (Y-BOCS)");
        testName.setWrapText(true);
        testName.setMaxWidth(400);
        testName.getStyleClass().add("bold-text");
        testBox.getChildren().add(testName);

        allToggles = new ArrayList<>();
        createTest();

        Button submitButton = new Button("Получить результаты");
        submitButton.setOnAction(event -> submit());
        testBox.getChildren().add(submitButton);

        testTab.setContent(testBox);

        String filePath = getClass().getResource("/tests/ybocs/ybocs-description.html").toExternalForm();
        WebView webView =  new WebView();
        webView.getEngine().load(filePath);

        Tab descriptionTab = new Tab("Описание");
        descriptionTab.setContent(webView);

        tabPane.getTabs().addAll(testTab, descriptionTab);
        root.setContent(tabPane);
    }

    private void submit() {
        int totalValue = 0;
        for (int i = 0; i < allToggles.size(); i++) {
            RadioButton selectedOption = (RadioButton) allToggles.get(i).getSelectedToggle();
            if (selectedOption != null) {
                totalValue += (int)selectedOption.getUserData();
                int selectedValue = (int) selectedOption.getUserData();
                System.out.println("Выбрана опция: " + selectedValue);
            } else {
                int questionNumber = i + 1;
                errorNotification("Вы не ответили на вопрос №" + questionNumber);
                break;
            }
        }

        getResults(totalValue);


    }

    private void getResults(int totalValue) {
        Label title = new Label("Результаты теста");
        title.getStyleClass().add("special-text");

        Label result;
        if (totalValue <= 7) {
            result = new Label("Обсессивно-компульсивное расстройство не проявляется");
            result.setStyle(Colors.GREEN.STYLE);
        } else if (totalValue > 8 && totalValue <= 15) {
            result = new Label("Обсессивно-компульсивное расстройство проявляется слабо");
            result.setStyle(Colors.YELLOW.STYLE);
        } else if (totalValue > 16 && totalValue <= 23) {
            result = new Label("Обсессивно-компульсивное расстройство средней тяжести");
            result.setStyle(Colors.LIGHTRED.STYLE);
        } else if (totalValue > 24 && totalValue <= 31) {
            result = new Label("Обсессивно-компульсивное расстройство сильной тяжести");
            result.setStyle(Colors.STRONGRED.STYLE);
        } else {
            result = new Label("Обсессивно-компульсивное расстройство чрезвычайной силы");
            result.setStyle(Colors.STRONGRED.STYLE);
        }

        Label resultValue = new Label(totalValue + " баллов из 40 возможных");

        HBox buttonsBox = new HBox();
        Button saveButton = new Button("Сохранить результаты");
        saveButton.setOnAction(event -> saveResults());
        Button exitButton = new Button("Выйти");
        exitButton.setOnAction(event -> loadAllTestsPage());
        buttonsBox.getChildren().addAll(saveButton, exitButton);
        buttonsBox.setSpacing(10);

        VBox container = new VBox(title, result, resultValue, buttonsBox);
        container.setSpacing(5);
        testBox.getChildren().clear();
        testBox.getChildren().add(container);
        tabPane.setMaxHeight(550);
    }

    private void createTest() {
        List<String> answersList_1 = Arrays.asList("не наблюдаются вообще", "по совокупности меньше часа",
                "по совокупности 1-3 часа в течение дня", "по совокупности 3-8 часов в течение дня",
                "по совокупности более 8 часов в течение дня");
        createQuestion(answersList_1, "1. Общая продолжительность Ваших навязчивых мыслей (обсессий) в течение суток составляет:");

        List<String> answersList_2 = Arrays.asList("совсем не нарушена", "нарушена слабо",
                "чувствуется негативное влияние, но образ жизни прежний", "сильно нарушен повседневный образ жизни",
                "образ жизни полностью нарушен");
        createQuestion(answersList_2,"2. Степень нарушения повседневной жизни вследствие наличия навязчивых мыслей:");

        List<String> answersList_3 = Arrays.asList("не испытываю вообще", "испытываю слабый дискомфорт",
                "испытываю сильный дискомфорт, но в общем, чувствую себя хорошо", "испытываю сильный дискомфорт и это сказывается на моем самочувствии",
                "практически весь день испытываю очень сильный дискомфорт");
        createQuestion(answersList_3,"3. Уровень психологического дискомфорта вследствие навязчивых мыслей:");

        List<String> answersList_4 = Arrays.asList("в состоянии им сопротивляться практически всегда", "могу оказать сопротивление большей части обсессий",
                "иногда я могу оказать им хорошее сопротивление", "чаще всего я не могу сопротивляться им",
                "не в состоянии сопротивляться обсессиям");
        createQuestion(answersList_4,"4. Сопротивление обсессиям:");

        List<String> answersList_5 = Arrays.asList("обсессии полностью находятся под моим контролем", "в большинстве случаев я контролирую их",
                "иногда мне удается контролировать обсессии", "могу контролировать их незначительно",
                "мои обсессии неконтролируемы");
        createQuestion(answersList_5,"5. Степень контроля над обсессиями:");

        List<String> answersList_6 = Arrays.asList("не наблюдаются вообще (по совокупности меньше часа)", "по совокупности час или меньше часа",
                "по совокупности 1-3 часа в течение дня", "по совокупности 3-8 часов в течение дня",
                "по совокупности более 8 часов в течение дня");
        createQuestion(answersList_6,"6. Ваша продолжительность навязчивых действий, ритуалов (компульсий) в течении суток:");

        List<String> answersList_7 = Arrays.asList("совсем не нарушают", "оказывают слабое влияние",
                "чувствуется негативное влияние, но образ жизни прежний", "сильно нарушают повседневный образ жизни",
                "образ жизни полностью нарушен");
        createQuestion(answersList_7,"7. Степень нарушения повседневной жизни:");

        List<String> answersList_8 = Arrays.asList("не испытываю вообще", "испытываю слабый дискомфорт",
                "испытываю сильный дискомфорт, но в общем, чувствую себя хорошо", "испытываю сильный дискомфорт и это сказывается на моем самочувствии",
                "практически весь день испытываю очень сильный дискомфорт");
        createQuestion(answersList_8,"8. Уровень психологического дискомфорта:");

        List<String> answersList_9 = Arrays.asList("в состоянии им сопротивляться практически всегда", "могу оказать сопротивление большей части компульсий",
                "иногда я могу оказать им хорошее сопротивление", "чаще всего я не могу сопротивляться им",
                "не в состоянии сопротивляться компульсиям");
        createQuestion(answersList_9,"9. Сопротивление компульсиям:");

        List<String> answersList_10 = Arrays.asList("компульсии полностью находятся под моим контролем", "в большинстве случаев я контролирую их",
                "иногда мне удается контролировать компульсии", "могу контролировать их незначительно",
                "мои компульсии неконтролируемы");
        createQuestion(answersList_10,"10. Степень контроля над компульсиями:");
    }

    private void createQuestion(List<String> answers, String question) {
        Label questionLabel = new Label(question);
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(400);
        testBox.getChildren().add(questionLabel);

        ToggleGroup toggleGroup = new ToggleGroup();

        for (int i = 0; i < answers.size(); i++) {
            RadioButton option = new RadioButton(answers.get(i));
            option.setUserData(i);
            option.setToggleGroup(toggleGroup);
            testBox.getChildren().add(option);
        }

        allToggles.add(toggleGroup);
    }

    private void saveResults() {

    }

    @Override
    public LineChart<String, Number> showStatistics(PatientDTO patient) {
        return null;
    }

    @Override
    public Parent getRoot() {
        return root;
    }
}
