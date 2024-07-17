package com.dddryinside.elements;

import com.dddryinside.models.Tests;
import com.dddryinside.service.DataBaseAccess;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class Researches extends VBox {
    public Researches() {
        SuperLabel title = new SuperLabel("Текущие исследования");
        title.makeSpecial();
        this.getChildren().add(title);

        List<Tests> currentResearches = DataBaseAccess.getCurrentResearches();
        for (Tests test : currentResearches) {
            Hyperlink name = new Hyperlink(test.getFullName());
            this.getChildren().add(name);
        }

        this.setPadding(new Insets(10));
        this.setMinWidth(330);

        BorderStroke borderStroke = new BorderStroke(
                Color.rgb(186, 186, 186),
                BorderStrokeStyle.SOLID,
                new CornerRadii(2.0),
                new BorderWidths(1.0)
        );

        Border border = new Border(borderStroke);

        this.setBorder(border);
    }
}
