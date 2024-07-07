package com.dddryinside.controllers;

import com.dddryinside.PageLoader;
import com.dddryinside.service.DataBaseAccess;
import com.dddryinside.service.PatientDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.concurrent.ExecutionException;

public class EditProfileController extends PageLoader implements Controller {
    BorderPane root;
    @Override
    public void initializeUI() {
        root = new BorderPane();
        loadMenuBar(root);

        Label secondNameLabel = new Label("Фамилия");
        TextField secondNameTextField = new TextField(DataBaseAccess.patient.getSecondName());

        Label nameLabel = new Label("Имя");
        TextField nameTextField = new TextField(DataBaseAccess.patient.getName());

        Label thirdNameLabel = new Label("Отчество");
        TextField thirdNameTextField = new TextField(DataBaseAccess.patient.getAdditionalName());

        Label birthDateLabel = new Label("Дата рождения");
        DatePicker birthDatePicker = new DatePicker(DataBaseAccess.patient.getBirthDate());
        birthDatePicker.setPrefWidth(300);

        ToggleGroup gender = new ToggleGroup();
        RadioButton maleOption = new RadioButton("Мужчина");
        RadioButton femaleOption = new RadioButton("Женщина");
        maleOption.setToggleGroup(gender);
        femaleOption.setToggleGroup(gender);

        if (DataBaseAccess.patient.getSex()) {
            maleOption.setSelected(true);
        } else {
            femaleOption.setSelected(true);
        }

        HBox buttons = new HBox();
        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> {
            PatientDTO user = new PatientDTO(DataBaseAccess.patient.getId(), nameTextField.getText(), secondNameTextField.getText(),
                    thirdNameTextField.getText(), birthDatePicker.getValue(), maleOption.isSelected());

            try {

                    DataBaseAccess.updateUser(user);
                    loadUserPage();

            } catch (Exception e) {
                errorNotification(e.getMessage());
            }
        });
        Button canselButton = new Button("Отмена");
        canselButton.setOnAction(event -> {
            loadUserPage();
        });
        buttons.getChildren().addAll(saveButton, canselButton);
        buttons.setSpacing(10);

        VBox container = new VBox();
        container.getChildren().addAll(secondNameLabel, secondNameTextField,
                nameLabel, nameTextField, thirdNameLabel, thirdNameTextField,
                birthDateLabel, birthDatePicker, maleOption, femaleOption,
                buttons);
        container.setSpacing(10);
        container.setMaxWidth(300);
        container.setMaxHeight(350);

        root.setCenter(container);
    }

    public Parent getRoot() {
        return root;
    }
}
