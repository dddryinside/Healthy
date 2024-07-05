package com.dddryinside.DTO;

import java.time.LocalDate;
import java.util.List;

import com.dddryinside.service.TestsDataBaseAccess;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DASS21 implements Test {
    private LocalDate date;
    private int depression;
    private int anxiety;
    private int stress;

    @Override
    public LineChart<String, Number> showResults(Patient patient) {
        // Создание осей
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        // Создание LineChart
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle(Tests.DASS21.getFullName());

        // Настройка осей
        yAxis.setLabel("Значения");
        yAxis.setTickUnit(1.0); // Установка шага равным 1
        yAxis.setMinorTickVisible(false); // Скрытие малых делений на оси
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("%d", object.intValue());
            }

            @Override
            public Number fromString(String string) {
                return Integer.parseInt(string);
            }
        }); // Форматирование меток на оси Y как целые числа


        List<DASS21> dass21List = TestsDataBaseAccess.getDASS21Results(patient);
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Депрессия");
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Тревожность");
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Стресс");
        for (int i = 0; i < dass21List.size(); i++) {
            DASS21 test = dass21List.get(i);
            series1.getData().add(new XYChart.Data<>(i + " (" + test.getDate().toString() + ")" , test.getDepression()));
            series2.getData().add(new XYChart.Data<>(i + " (" + test.getDate().toString() + ")", test.getAnxiety()));
            series3.getData().add(new XYChart.Data<>(i + " (" + test.getDate().toString() + ")", test.getStress()));
        }

        // Добавление серий в LineChart
        lineChart.getData().addAll(series1, series2, series3);

        return lineChart;
    }
}
