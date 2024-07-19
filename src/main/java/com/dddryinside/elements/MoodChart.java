package com.dddryinside.elements;

import com.dddryinside.DTO.MoodDTO;
import com.dddryinside.service.DataBaseAccess;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.List;

public class MoodChart extends VBox {
    public MoodChart() {
        this.getChildren().add(getMoodChart(DataBaseAccess.getMoodHistory(7)));
    }

    public LineChart<String, Number> getMoodChart(List<MoodDTO> moodHistory) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("График настроения за 7 дней");

        for (int i = 0; i < moodHistory.size(); i++) {
            series.getData().add(new XYChart.Data<>(i + " (" + moodHistory.get(i).getDate() + ")",
                    moodHistory.get(i).getMood()));
        }

        lineChart.getData().add(series);
        lineChart.setMaxWidth(330);
        lineChart.setMaxHeight(280);
        lineChart.setPadding(new Insets(0, 0, 0, -5));
        return lineChart;
    }
}
