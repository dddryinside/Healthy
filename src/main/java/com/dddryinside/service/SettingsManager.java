package com.dddryinside.service;

import com.dddryinside.models.Locale;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class SettingsManager {
    private static final String SETTINGS_FILE = "app.properties";
    private static final String LOCALE = "locale";
    private static final String SHOW_NOTIFICATION = "show_notification";

    private static final Properties settings;
    private static final Map<String, Locale> LOCALE_MAP = new HashMap<>();

    static {
        settings = new Properties();
        try {
            settings.load(new FileInputStream(SETTINGS_FILE));
        } catch (IOException e) {
            // Create file if not exist
            settings.setProperty(LOCALE, Locale.EN.getFile());
            settings.setProperty(SHOW_NOTIFICATION, String.valueOf(true));
            saveSettings();
        }

        for (Locale locale : Locale.values()) {
            LOCALE_MAP.put(locale.getFile(), locale);
        }
    }

    private SettingsManager() {
        // Private constructor to prevent instantiation
    }

    public static Locale getLocale() {
        return LOCALE_MAP.getOrDefault(settings.getProperty(LOCALE), Locale.EN);
    }

    public static void setLocale(Locale locale) {
        settings.setProperty(LOCALE, locale.getFile());
        saveSettings();
    }

    public static boolean getShowNotification() {
        return Boolean.parseBoolean(settings.getProperty(SHOW_NOTIFICATION));
    }

    public static void setShowNotification(boolean value) {
        settings.setProperty(SHOW_NOTIFICATION, String.valueOf(value));
        saveSettings();
    }

    private static void saveSettings() {
        try {
            File settingsFile = new File(SETTINGS_FILE);
            if (!settingsFile.exists()) {
                settingsFile.createNewFile();
            }
            settings.store(new FileOutputStream(settingsFile), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
