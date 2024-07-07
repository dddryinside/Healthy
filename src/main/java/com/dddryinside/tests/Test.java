package com.dddryinside.tests;

import com.dddryinside.service.PatientDTO;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;

public interface Test {
    LineChart<String, Number> showStatistics(PatientDTO patient);
    Parent initializeUI();
}
