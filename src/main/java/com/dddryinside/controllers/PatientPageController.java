package com.dddryinside.controllers;

import com.dddryinside.DTO.Patient;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.TestsEnum;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class PatientPageController {
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

            List<TestsEnum> tests = DataBaseAccess.getAllTestsOfPatient(patient);
            for (TestsEnum test : tests) {
                Hyperlink hyperlink = new Hyperlink(test.getFullName());
                allResearches.getChildren().add(hyperlink);
            }
        }
    }
}
