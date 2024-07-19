package com.dddryinside.pages;

import com.dddryinside.models.Mood;
import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.service.DataBaseAccess;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.Collections;
import java.util.List;

public class MoodStatPage implements Page {
    private final VBox moodChart = new VBox();
    @Override
    public Scene getInterface() {
        getMoodChart(DataBaseAccess.getMoodHistory(30), "тест");

        Root root = new Root();
        root.setMenuBar();
        root.setToTopCenter(moodChart);
        return new Scene(root);
    }

    public void getMoodChart(List<Mood> moodHistory, String title) {
        moodChart.getChildren().clear();
        Collections.reverse(moodHistory);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(11);
        yAxis.setTickUnit(1);
        yAxis.setMinorTickVisible(false);

        yAxis.setTickLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Number object) {
                int value = object.intValue();
                return value <= 10 ? String.valueOf(value) : "";
            }

            @Override
            public Number fromString(String string) {
                return 0;
            }
        });

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(title);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(title);

        for (int i = 0; i < moodHistory.size(); i++) {
            series.getData().add(new XYChart.Data<>(i + moodHistory.get(i).getShortStringDate(),
                    moodHistory.get(i).getMood()));
        }

        lineChart.getData().add(series);

        moodChart.getChildren().add(lineChart);
    }
}
