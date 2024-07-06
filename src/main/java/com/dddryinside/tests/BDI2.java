package com.dddryinside.tests;

import com.dddryinside.service.Patient;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;

public class BDI2 implements Test {
    @Override
    public Parent initializeUI() {
        return null;
    }

    @Override
    public LineChart<String, Number> showStatistics(Patient patient) {
        return null;
    }
}
