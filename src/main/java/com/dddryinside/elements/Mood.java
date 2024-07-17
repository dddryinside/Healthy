package com.dddryinside.elements;

import com.dddryinside.models.User;
import com.dddryinside.service.DataBaseAccess;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Mood extends VBox {
    public Mood(User user) {
        SuperLabel title = new SuperLabel("Среднее настроение");
        title.makeSpecial();

        double avgMood = DataBaseAccess.getAverageMood();
        SuperLabel avgMoodLabel = new SuperLabel(String.valueOf(avgMood));
        avgMoodLabel.makeSpecial();

        if (avgMood > 7) {
            avgMoodLabel.makeGreen();
        } else if (avgMood > 5) {
            avgMoodLabel.makeYellow();
        } else {
            avgMoodLabel.makeRed();
        }

        this.getChildren().addAll(title, avgMoodLabel);
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
