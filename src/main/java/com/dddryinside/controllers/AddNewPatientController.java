package com.dddryinside.controllers;

import com.dddryinside.DTO.Patient;
import com.dddryinside.service.DataBaseAccess;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddNewPatientController {
    @FXML TextField name;
    @FXML TextField secondName;
    @FXML TextField thirdName;
    @FXML DatePicker birthDate;
    @FXML ToggleGroup sexToggleGroup;
    @FXML RadioButton male;
    @FXML RadioButton female;

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
            Patient patient = new Patient(name.getText(), secondName.getText(), thirdName.getText(), birthDate.getValue(), sex);
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

    private void errorNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
