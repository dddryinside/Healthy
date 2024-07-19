package com.dddryinside.elements;

import com.dddryinside.models.Tests;
import com.dddryinside.service.DataBaseAccess;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;

import java.util.List;

public class Researches extends Box {
    public Researches() {
        SuperLabel title = new SuperLabel("Текущие исследования");
        title.makeTitle();
        this.getChildren().add(title);

        List<Tests> currentResearches = DataBaseAccess.getCurrentResearches();
        for (Tests test : currentResearches) {
            Hyperlink name = new Hyperlink(test.getFullName());
            this.getChildren().add(name);
        }

        this.setPadding(new Insets(10));
        this.setMinWidth(330);
    }
}
