package com.dddryinside.controllers;

import com.dddryinside.DTO.Patient;
import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.DTO.Tests;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class PatientPageController extends PageLoader {
    @FXML Label fio;
    @FXML Label birthDate;
    @FXML Label sex;
    @FXML VBox allResearches;

    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
        updateUI();
    }

    private void updateUI() {
        if (patient != null) {
            fio.setText(patient.getFio());
            birthDate.setText("Дата рождения: " + patient.getStringBirthDate());
            sex.setText("Пол: " + patient.getStringSex());

            List<Tests> tests = DataBaseAccess.getAllTestsOfPatient(patient);
            for (Tests test : tests) {
                Hyperlink hyperlink = new Hyperlink(test.getFullName());
                hyperlink.setOnAction(event -> {
                    loadTestResultsPage(test, patient);
                });
                allResearches.getChildren().add(hyperlink);
            }
        }
    }
}
