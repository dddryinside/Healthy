package com.dddryinside.elements;

import com.dddryinside.pages.About;
import com.dddryinside.pages.UserPage;
import com.dddryinside.service.PageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Root extends BorderPane {
    public void setToCenter(Node... children) {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);

        container.setMaxWidth(900);
        container.setMinWidth(900);

        container.getChildren().addAll(children);

/*        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
        container.setBackground(DEFAULT_BACKGROUND);*/

        super.setCenter(container);
    }

    public void setToTopCenter(Node... children) {
        VBox container = new VBox(10);

        container.setMaxWidth(900);
        container.setMinWidth(900);
        container.setPadding(new Insets(10, 0, 0, 0));

        container.getChildren().addAll(children);

/*        Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTGREEN, null, null));
        container.setBackground(DEFAULT_BACKGROUND);*/

        super.setCenter(container);
    }

    public void setMenuBar() {
        Menu menu = new Menu("Меню");
        MenuItem main = new MenuItem("Главная");
        main.setOnAction(event -> PageManager.loadPage(new UserPage()));
        MenuItem tests = new MenuItem("Тесты");
        //tests.setOnAction(event -> PageManager.loadPage(new AllTestsPage()));
        menu.getItems().addAll(main, tests);

        Menu information = new Menu("Информация");
        MenuItem about = new MenuItem("О приложении");
        about.setOnAction(event -> PageManager.loadPage(new About()));
        information.getItems().add(about);

        MenuBar menuBar = new MenuBar(menu, information);
        menuBar.setMinHeight(25);

        this.setTop(menuBar);
    }
}
