module com.dddryinside.weightchecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens com.dddryinside to javafx.fxml;
    exports com.dddryinside;
    exports com.dddryinside.controllers;
    opens com.dddryinside.controllers to javafx.fxml;
}