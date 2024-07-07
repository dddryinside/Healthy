package com.dddryinside;

import com.dddryinside.controllers.*;
import com.dddryinside.service.PatientDTO;
import com.dddryinside.service.AllTests;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PageLoader {
    public static Stage stage;

    public Parent loadMenuBar(BorderPane root) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu-bar.fxml"));
        try {
            Parent menuBarRoot = fxmlLoader.load();
            root.setTop(menuBarRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return root;
    }

    public void loadSecurityPage() {
        loadPage("/security-page.fxml");
    }

    public void loadEditProfilePage() {
        Controller controller = new EditProfileController();
        controller.initializeUI();
        Scene scene = new Scene(controller.getRoot());

        stage.setScene(scene);
        stage.show();
    }

    public void loadMainPage() {
        //loadPage("/main-page.fxml");

        MainPageController mainPageController = new MainPageController();
        mainPageController.initializeUI();
        Scene scene = new Scene(mainPageController.getRoot());

        stage.setScene(scene);
        stage.show();
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

    public void loadTestPage(AllTests test) {
        TestController testController = new TestController();
        testController.initializeUI(test);
        Scene scene = new Scene(testController.getRoot());

        stage.setScene(scene);
        stage.show();
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
        Scene scene = new Scene(userPageController.getRoot());

        stage.setScene(scene);
        stage.show();
    }

    public void loadPatientPage(PatientDTO patient) {
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

    public void loadTestResultsPage(AllTests test, PatientDTO patient) {
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