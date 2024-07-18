package com.dddryinside.elements;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Panel extends VBox {
    private static final BorderStroke greyStroke = new BorderStroke(
            Color.rgb(186, 186, 186),
            BorderStrokeStyle.SOLID,
            new CornerRadii(2.0),
            new BorderWidths(1.0)
    );
    private static final Border greyBorder = new Border(greyStroke);

    public Panel() {
        this.setBorder(greyBorder);
    }
}
