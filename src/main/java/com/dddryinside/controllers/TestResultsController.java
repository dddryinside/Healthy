package com.dddryinside.controllers;

import com.dddryinside.service.PatientDTO;
import com.dddryinside.tests.Test;
import com.dddryinside.PageLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TestResultsController extends PageLoader {
    @FXML VBox chart;

    public void setInfo(Test test, PatientDTO patient) {
        chart.getChildren().add(test.showStatistics(patient));
    }
}
