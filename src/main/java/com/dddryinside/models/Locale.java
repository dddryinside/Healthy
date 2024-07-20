package com.dddryinside.models;

import lombok.Getter;

@Getter
public enum Locale {
    ENG( "English", "eng_locale"),
    RU( "Русский", "ru_locale"),
    BY( "Беларуская", "by_locale"),
    UA( "Украінская", "ua_locale");

    private final String name;
    private final String file;

    Locale(String name, String file) {
        this.name = name;
        this.file = file;
    }
}
