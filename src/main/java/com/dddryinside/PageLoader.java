package com.dddryinside;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PageLoader {
    public static Stage stage;
    public void loadMainPage() {
        loadPage("/main-page.fxml");
    }

    public void loadPatientsPage() {
        loadPage("/patients.fxml");
    }

    public void loadAddNewPatientPage() {
        loadPage("/add-new-patient.fxml");
    }

    public void loadWeightCalculatorPage() {
        loadPage("/weight-calculator.fxml");
    }

    private void loadPage(String FXMLFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Healthy.class.getResource(FXMLFileName));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorNotification();
        }
    }

    private void errorNotification() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText("Ошибка загрузки страницы!");
        alert.showAndWait();
    }
}
