package com.dddryinside.controllers.calculators;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.WebView;

public class WeightCalculatorController {
    @FXML Spinner<Integer> currentWeightSpinner;
    @FXML Spinner<Integer> heightSpinner;
    @FXML ToggleGroup sexToggleGroup;
    @FXML Label mainResultText;
    @FXML Label totalResultText;
    @FXML WebView description;

    public void initialize() {
        String filePath = getClass().getResource("/calculators/normal-weight/weight-calculator-description.html").toExternalForm();
        description.getEngine().load(filePath);
    }


    public void calculate() {
        RadioButton sexOption = (RadioButton) sexToggleGroup.getSelectedToggle();
        if (sexOption != null) {
            int heightInCm = heightSpinner.getValue();
            double heightInMeters = heightInCm / 100.0;
            double IBW;
            if (sexOption.getText().equals("Мужчина")) {
                IBW = 22 * (heightInMeters) * (heightInMeters);
            } else {
                IBW = 22 * (heightInMeters - 0.1) * (heightInMeters - 0.1);
            }
            double roundedIBW = Math.round(IBW * 100.0) / 100.0;

            int weight = currentWeightSpinner.getValue();
            double BMI = weight / (double) heightInCm / (double) heightInCm * 10000;
            double roundedBMI = Math.round(BMI * 100.0) / 100.0;

            if (BMI < 18.5) {
                mainResultText.setStyle("-fx-text-fill: #FF8C00;");
                mainResultText.setText("Недобор веса");
            } else if (BMI > 18.5 && BMI < 24.9) {
                mainResultText.setStyle("-fx-text-fill: green;");
                mainResultText.setText("Нормальный вес");
            } else if (BMI > 24.9 && BMI < 29.9) {
                mainResultText.setStyle("-fx-text-fill: #FF8C00;");
                mainResultText.setText("Избыточный вес");
            } else {
                mainResultText.setStyle("-fx-text-fill: red;");
                mainResultText.setText("Ожирение");
            }

            totalResultText.setText("Индекс массы тела: " + roundedBMI + "кг/м²\n"
                    + "Рекомендуемый вес: " + roundedIBW + "кг");
        } else {
            System.out.println("Ни одна опция не выбрана.");
        }
    }
}
