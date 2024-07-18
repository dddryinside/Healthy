package com.dddryinside.elements;

import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Box extends VBox {
    private static final BorderStroke greyStroke = new BorderStroke(
            Color.rgb(186, 186, 186),
            BorderStrokeStyle.SOLID,
            new CornerRadii(2.0),
            new BorderWidths(1.0)
    );
    private static final Border greyBorder = new Border(greyStroke);

    public Box() {
        this.setBorder(greyBorder);
    }

    public Box(Node node) {
        super(node);
        this.setBorder(greyBorder);
    }
}
