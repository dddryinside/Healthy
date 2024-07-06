package com.dddryinside;

import com.dddryinside.DTO.DASS21;
import com.dddryinside.DTO.Patient;
import com.dddryinside.DTO.Test;
import com.dddryinside.controllers.PatientPageController;
import com.dddryinside.controllers.UserPageController;
import com.dddryinside.controllers.tests.TestResultsController;
import com.dddryinside.DTO.Tests;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PageLoader {
    public static Stage stage;

    public void loadSecurityPage() {
        loadPage("/security-page.fxml");
    }

    public void loadMainPage() {
        loadPage("/main-page.fxml");
    }

    public void loadPatientsPage() {
        loadPage("/patients.fxml");
    }

    public void loadAddNewPatientPage() {
        loadPage("/add-new-patient.fxml");
    }

    public void loadDASS21TestPage() {
        loadPage("/tests/dass21/dass21-test.fxml");
    }

    public void loadAboutPage() {
        loadPage("/about.fxml");
    }

    private void loadPage(String FXMLFileName) {
        try {
            // Сохранение текущих размеров и позиции окна
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            double currentX = stage.getX();
            double currentY = stage.getY();
            boolean isMaximized = stage.isMaximized();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLFileName));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);

            // Восстановление размеров и позиции окна
            if (!isMaximized) {
                stage.setWidth(currentWidth);
                stage.setHeight(currentHeight);
                stage.setX(currentX);
                stage.setY(currentY);
            } else {
                stage.setMaximized(true);
            }

            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorNotification();
        }
    }

    public void loadUserPage() {
        UserPageController userPageController = new UserPageController();
        userPageController.initializeUI();
        Scene scene = new Scene(userPageController.getView());

        stage.setScene(scene);
        stage.show();
    }

    public void loadPatientPage(Patient patient) {
        try {
            // Сохранение текущих размеров и позиции окна
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            double currentX = stage.getX();
            double currentY = stage.getY();
            boolean isMaximized = stage.isMaximized();

            // Создаем Task для загрузки страницы
            Task<Scene> loadPageTask = new Task<>() {
                @Override
                protected Scene call() throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/patient.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());

                    PatientPageController controller = fxmlLoader.getController();
                    controller.setPatient(patient);

                    return scene;
                }
            };

            // Запускаем Task и ждем его завершения
            loadPageTask.setOnSucceeded(event -> {
                Scene scene = loadPageTask.getValue();
                stage.setScene(scene);

                // Восстановление размеров и позиции окна
                if (!isMaximized) {
                    stage.setWidth(currentWidth);
                    stage.setHeight(currentHeight);
                    stage.setX(currentX);
                    stage.setY(currentY);
                } else {
                    stage.setMaximized(true);
                }

                stage.show();
            });

            loadPageTask.setOnFailed(event -> {
                System.out.println(loadPageTask.getException().getMessage());
                errorNotification();
            });

            new Thread(loadPageTask).start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorNotification();
        }
    }

    public void loadTestResultsPage(Tests test, Patient patient) {
        try {
            // Сохранение текущих размеров и позиции окна
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            double currentX = stage.getX();
            double currentY = stage.getY();
            boolean isMaximized = stage.isMaximized();

            // Создаем Task для загрузки страницы
            Task<Scene> loadPageTask = new Task<>() {
                @Override
                protected Scene call() throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tests/" + test.getName() + "/" + test.getName() + "-results.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());

                    TestResultsController controller = fxmlLoader.getController();
                    controller.setInfo(test.getTest(), patient);

                    return scene;
                }
            };

            // Запускаем Task и ждем его завершения
            loadPageTask.setOnSucceeded(event -> {
                Scene scene = loadPageTask.getValue();
                stage.setScene(scene);

                // Восстановление размеров и позиции окна
                if (!isMaximized) {
                    stage.setWidth(currentWidth);
                    stage.setHeight(currentHeight);
                    stage.setX(currentX);
                    stage.setY(currentY);
                } else {
                    stage.setMaximized(true);
                }

                stage.show();
            });

            loadPageTask.setOnFailed(event -> {
                System.out.println(loadPageTask.getException().getMessage());
                errorNotification();
            });

            new Thread(loadPageTask).start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorNotification();
        }
    }

    protected void errorNotification() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText("Ошибка загрузки страницы!");
        alert.showAndWait();
    }

    protected void errorNotification(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}