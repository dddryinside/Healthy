package com.dddryinside.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Service {
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
                .withLocale(new Locale("ru", "RU"));
        return dateTime.format(formatter);
    }
}
