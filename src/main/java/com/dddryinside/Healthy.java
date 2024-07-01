package com.dddryinside;

import com.dddryinside.DTO.Patient;
import com.dddryinside.service.DataBaseAccess;
import javafx.application.Application;
import javafx.stage.Stage;

public class Healthy extends Application {
    @Override
    public void start(Stage stage) {
        startProcedure();
        stage.setTitle("Healthy");
        PageLoader.stage = stage;
        PageLoader pageLoader = new PageLoader();
        pageLoader.loadMainPage();
    }

    public void startProcedure() {
        DataBaseAccess.savePatient(new Patient("Иван", "Иванов", "Иванович"));
    }

    public static void main(String[] args) {
        launch();
    }
}