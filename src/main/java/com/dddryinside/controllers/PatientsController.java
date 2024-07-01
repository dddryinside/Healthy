package com.dddryinside.controllers;

import com.dddryinside.DTO.Patient;
import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.skin.VirtualContainerBase;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.List;

public class PatientsController extends PageLoader {
    @FXML VBox mainBox;

    List<Patient> patientsList;

    public void initialize() {
        patientsList = DataBaseAccess.getAllPatients();

        if (patientsList.size() == 0) {
            Label noPatientsNotice = new Label("Ни одного пациента не найдено." +
                    " Добавление пациентов в локальную базу данных приложения позволяет проходить исследования не анонимно," +
                    " хранить результаты, собирать статистику");

            noPatientsNotice.getStyleClass().add("notification-text");
            mainBox.getChildren().add(noPatientsNotice);
        } else {
            VBox mainPatientsContainer = new VBox();
            mainPatientsContainer.setSpacing(10);

            for (Patient patient : patientsList) {
                TitledPane patientPane = new TitledPane();
                patientPane.setMaxWidth(400);
                patientPane.setText(patient.getFio());

                VBox info = new VBox();
                info.getChildren().add(new Label(patient.getFio()));
                info.getChildren().add(new Label(patient.getStringDate()));
                info.getChildren().add(new Label(patient.getStringSex()));

                patientPane.setContent(info);
                mainPatientsContainer.getChildren().add(patientPane);
            }

            mainBox.getChildren().add(mainPatientsContainer);
        }
    }

    public void addNewPatientPage() {
        loadAddNewPatientPage();
    }
}
