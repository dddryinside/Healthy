package com.dddryinside.controllers.tests;

import com.dddryinside.DTO.Patient;
import com.dddryinside.DTO.Test;
import com.dddryinside.PageLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TestResultsController extends PageLoader {
    @FXML VBox chart;

    public void setInfo(Test test, Patient patient) {
        chart.getChildren().add(test.showResults(patient));
    }
}
