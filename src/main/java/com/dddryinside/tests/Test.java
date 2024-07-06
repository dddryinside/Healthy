package com.dddryinside.tests;

import com.dddryinside.service.Patient;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;

public interface Test {
    LineChart<String, Number> showStatistics(Patient patient);
    Parent initializeUI();
}
