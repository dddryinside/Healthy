package com.dddryinside.DTO;

import javafx.scene.Parent;
import javafx.scene.chart.LineChart;

public interface Test {
    LineChart<String, Number> showStatistics(Patient patient);
    Parent initializeUI();
}
