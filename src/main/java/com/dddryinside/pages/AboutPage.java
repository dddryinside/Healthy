package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.elements.SuperLabel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class AboutPage extends Page {
    @Override
    public Scene getInterface() {
        Image icon = new Image("icon.png");
        ImageView imageView = new ImageView(icon);
        SuperLabel title = new SuperLabel("Mental 1.0.2024");
        title.makeTitle();

        VBox container = new VBox(imageView, title);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);

        Root root = new Root();
        root.setMenuBar();
        root.setToCenter(container);

        return new Scene(root);
    }
}
