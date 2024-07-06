package com.dddryinside.controllers;

import com.dddryinside.service.Patient;
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
        String sex;
        if (male.isSelected()) {
            sex = "Male";
        } else if (female.isSelected()) {
            sex = "Female";
        } else {
            sex = null;
        }

        try {
            Patient patient = new Patient(name.getText(), secondName.getText(), thirdName.getText(), birthDate.getValue(), sex, password.getText());
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
