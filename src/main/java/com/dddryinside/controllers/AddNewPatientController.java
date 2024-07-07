package com.dddryinside.controllers;

import com.dddryinside.service.PatientDTO;
import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddNewPatientController extends PageLoader {
    @FXML TextField name;
    @FXML TextField secondName;
    @FXML TextField thirdName;
    @FXML DatePicker birthDate;
    @FXML ToggleGroup sexToggleGroup;
    @FXML RadioButton male;
    @FXML RadioButton female;
    @FXML TextField password;

    public void saveNewPatient() {
        if (male.isSelected()) {
            saveToDB(true);
        } else if (female.isSelected()) {
            saveToDB(false);
        } else {
            errorNotification("Пол не выбран!");
        }
    }

    private void saveToDB(boolean sex) {
        try {
            PatientDTO patient = new PatientDTO(name.getText(), secondName.getText(), thirdName.getText(), birthDate.getValue(), sex, password.getText());
            if (patient.isCorrect()) {
                try {
                    DataBaseAccess.savePatient(patient);
                } catch (Exception e) {
                    errorNotification(e.getMessage());
                }
            }
        } catch (IllegalArgumentException e) {
            errorNotification(e.getMessage());
        }
    }
}
