package com.dddryinside.tests;

import com.dddryinside.service.PatientDTO;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;

public class BDI2 implements Test {
    @Override
    public void initializeUI() {

    }

    @Override
    public LineChart<String, Number> showStatistics(PatientDTO patient) {
        return null;
    }

    @Override
    public Parent getRoot() {
        return null;
    }
}
