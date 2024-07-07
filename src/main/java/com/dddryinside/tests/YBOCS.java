package com.dddryinside.tests;

import com.dddryinside.service.PatientDTO;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;

public class YBOCS implements Test {
    @Override
    public Parent initializeUI() {
        return null;
    }

    @Override
    public LineChart<String, Number> showStatistics(PatientDTO patient) {
        return null;
    }
}
