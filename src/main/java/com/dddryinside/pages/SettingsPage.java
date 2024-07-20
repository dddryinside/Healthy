package com.dddryinside.pages;

import com.dddryinside.Mental;
import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.elements.SuperLabel;
import com.dddryinside.models.Locale;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class SettingsPage extends Page {
    @Override
    public Scene getInterface() {
        SuperLabel title = new SuperLabel(localeRes.getString("app_language"));

        ComboBox<Locale> chooseLanguage = new ComboBox<>();
        chooseLanguage.getItems().addAll(Locale.values());
        chooseLanguage.setConverter(new StringConverter<>() {
            @Override
            public String toString(Locale locale) {
                return locale.getName();
            }

            @Override
            public Locale fromString(String string) {
                return null;
            }
        });
        chooseLanguage.setValue(Mental.APP_LOCALE);
        chooseLanguage.setMinWidth(300);

        CheckBox notifyMe = new CheckBox(localeRes.getString("notify_me"));

        SuperLabel explanation = new SuperLabel(localeRes.getString("settings_explanation"));

        Hyperlink saveButton = new Hyperlink(localeRes.getString("save"));
        Hyperlink exitButton = new Hyperlink(localeRes.getString("exit"));
        HBox buttons = new HBox(saveButton, exitButton);
        buttons.setSpacing(10);

        VBox container = new VBox(title, chooseLanguage, notifyMe, explanation, buttons);
        container.setSpacing(10);
        container.setMaxWidth(300);

        Root root = new Root();
        root.setToCenter(container);
        root.setMenuBar();

        return new Scene(root);
    }
}
