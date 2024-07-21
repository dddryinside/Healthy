package com.dddryinside.pages;

import com.dddryinside.Mental;
import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Root;
import com.dddryinside.elements.SuperLabel;
import com.dddryinside.models.Locale;
import com.dddryinside.models.User;
import com.dddryinside.service.AccountManager;
import com.dddryinside.service.PageManager;
import com.dddryinside.service.SettingsManager;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class SettingsPage extends Page {
    private final VBox languageBox = new VBox(10);
    private final VBox securityBox = new VBox(10);
    private final VBox notificationsBox = new VBox(10);

    @Override
    public Scene getInterface() {
        SuperLabel title = new SuperLabel(localeRes.getString("settings"));
        title.makeTitle();
        Hyperlink languageButton = new Hyperlink(localeRes.getString("language"));
        languageButton.setOnAction(event -> {
            deactivateAll();
            getLanguageBox();
        });

        Hyperlink securityButton = new Hyperlink(localeRes.getString("security"));
        securityButton.setOnAction(event -> {
            deactivateAll();
            getSecurityBox();
        });

        Hyperlink notificationButton = new Hyperlink(localeRes.getString("notifications"));
        notificationButton.setOnAction(event -> {
            deactivateAll();
            getNotificationsBox();
        });

        SuperLabel explanation = new SuperLabel(localeRes.getString("settings_explanation"));
        explanation.setWrapText(true);

        VBox menuBox = new VBox(5);
        menuBox.setMinWidth(300);
        menuBox.getChildren().addAll(title, languageButton, securityButton,
                notificationButton, explanation);

        VBox settingsBox = new VBox(languageBox, securityBox, notificationsBox);
        settingsBox.setMinWidth(300);

        HBox container = new HBox(menuBox, settingsBox);
        container.setSpacing(20);

        Root root = new Root();
        root.setToTopCenter(container);
        root.setMenuBar();

        return new Scene(root);
    }

    private void getLanguageBox() {
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

        Hyperlink saveButton = new Hyperlink(localeRes.getString("save"));
        saveButton.setOnAction(event -> {
            if (chooseLanguage.getSelectionModel().getSelectedItem() != null) {
                SettingsManager.setLocale(chooseLanguage.getValue());
                PageManager.showNotification(localeRes.getString("language_has_been_changed"));
            }
        });

        languageBox.getChildren().addAll(title, chooseLanguage, saveButton);
    }

    public void getSecurityBox() {
        SuperLabel currentPasswordLabel = new SuperLabel(localeRes.getString("current_password"));
        PasswordField currentPassword = new PasswordField();

        SuperLabel newPasswordLabel = new SuperLabel(localeRes.getString("new_password"));
        TextField newPassword = new TextField();

        Hyperlink saveButton = new Hyperlink(localeRes.getString("save"));
        saveButton.setOnAction(event -> {
            if (currentPassword.getText().equals(AccountManager.getUser().getPassword())) {
                if (newPassword.getText().length() >= 8) {
                    User user = AccountManager.getUser();
                    user.setPassword(newPassword.getText());
                    AccountManager.updatePassword(newPassword.getText());

                    currentPassword.setText(null);
                    newPassword.setText(null);
                    PageManager.showNotification(localeRes.getString("password_has_been_updated"));
                } else {
                    PageManager.showNotification(localeRes.getString("password_too_short"));
                }
            } else {
                PageManager.showNotification(localeRes.getString("password_is_not_correct"));
            }
        });

        securityBox.getChildren().addAll(currentPasswordLabel, currentPassword,
                newPasswordLabel, newPassword, saveButton);
    }

    public void getNotificationsBox() {
        CheckBox notifyMe = new CheckBox(localeRes.getString("notify_me"));
        notifyMe.setSelected(SettingsManager.getShowNotification());

        Hyperlink saveButton = new Hyperlink(localeRes.getString("save"));
        saveButton.setOnAction(event -> {
            SettingsManager.setShowNotification(notifyMe.isSelected());

            if (notifyMe.isSelected()) {
                PageManager.showNotification(localeRes.getString("you_will_receive_reminder"));
            } else {
                PageManager.showNotification(localeRes.getString("you_will_not_receive_reminder"));
            }
        });

        notificationsBox.getChildren().addAll(notifyMe, saveButton);
    }

    private void deactivateAll() {
        languageBox.getChildren().clear();
        securityBox.getChildren().clear();
        notificationsBox.getChildren().clear();
    }
}
