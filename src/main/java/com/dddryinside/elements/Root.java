package com.dddryinside.elements;

import com.dddryinside.pages.AboutPage;
import com.dddryinside.pages.SettingsPage;
import com.dddryinside.pages.UserPage;
import com.dddryinside.service.PageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Root extends BorderPane {
    public void setToCenter(Node... children) {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);

        container.setMaxWidth(900);
        container.setMinWidth(900);

        container.getChildren().addAll(children);

        super.setCenter(container);
    }

    public void setToTopCenter(Node... children) {
        VBox container = new VBox(10);

        container.setMaxWidth(900);
        container.setMinWidth(900);
        container.setPadding(new Insets(10, 0, 10, 0));
        VBox.setVgrow(container, Priority.ALWAYS);

        container.getChildren().addAll(children);

/*        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTGREEN, null, null));
        container.setBackground(DEFAULT_BACKGROUND);*/

        super.setCenter(container);
    }

    public void setMenuBar() {
        Menu menu = new Menu("Меню");
        MenuItem main = new MenuItem("Главная");
        main.setOnAction(event -> PageManager.loadPage(new UserPage()));
        MenuItem tests = new MenuItem("Настройки");
        tests.setOnAction(event -> PageManager.loadPage(new SettingsPage()));
        menu.getItems().addAll(main, tests);

        Menu information = new Menu("Информация");
        MenuItem about = new MenuItem("О приложении");
        about.setOnAction(event -> PageManager.loadPage(new AboutPage()));
        information.getItems().add(about);

        MenuBar menuBar = new MenuBar(menu, information);
        menuBar.setMinHeight(25);

        this.setTop(menuBar);
    }
}
