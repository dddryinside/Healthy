package com.dddryinside;

import com.dddryinside.controllers.*;
import com.dddryinside.service.PatientDTO;
import com.dddryinside.service.AllTests;
import com.dddryinside.tests.Test;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
        loadFXMLPage("/security-page.fxml");
    }

    public void loadPage(Controller controller) {
        controller.initializeUI();
        Scene scene = new Scene(controller.getRoot());
        stage.setScene(scene);
        stage.show();
    }

    public void loadTestPage(Test test) {
        test.initializeUI();
        Scene scene = new Scene(test.getRoot());
        stage.setScene(scene);
        stage.show();
    }

    public void loadEditProfilePage() {
        Controller controller = new EditProfileController();
        controller.initializeUI();
        Scene scene = new Scene(controller.getRoot());

        stage.setScene(scene);
        stage.show();
    }

    public void loadAllTestsPage() {
        //loadPage("/main-page.fxml");

        AllTestsPageController allTestsPageController = new AllTestsPageController();
        allTestsPageController.initializeUI();
        Scene scene = new Scene(allTestsPageController.getRoot());

        stage.setScene(scene);
        stage.show();
    }

    public void loadPatientsPage() {
        loadFXMLPage("/patients.fxml");
    }

    public void loadAddNewPatientPage() {
        loadFXMLPage("/add-new-patient.fxml");
    }

    public void loadDASS21TestPage() {
        loadFXMLPage("/tests/dass21/dass21-test.fxml");
    }

    public void loadAboutPage() {
        loadFXMLPage("/about.fxml");
    }

/*    public void loadTestPage(AllTests test) {
        TestController testController = new TestController();
        testController.initializeUI(test);
        Scene scene = new Scene(testController.getRoot());

        stage.setScene(scene);
        stage.show();
    }*/

    private void loadFXMLPage(String FXMLFileName) {
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

    protected boolean confirmation(String message, String description) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Внимание!");
        alert.setHeaderText(message);
        alert.setContentText(description);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}