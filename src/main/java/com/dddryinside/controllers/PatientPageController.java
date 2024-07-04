package com.dddryinside.controllers;

import com.dddryinside.DTO.Patient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PatientPageController {
    @FXML Label fio;
    @FXML Label birthDate;
    @FXML Label sex;

    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
        updateUI();
    }

    private void updateUI() {
        if (patient != null) {
            fio.setText(patient.getFio());
            birthDate.setText(patient.getStringBirthDate()); // Предполагается, что birthDate - это Date или LocalDate
            sex.setText(patient.getStringSex());
        }
    }
}
