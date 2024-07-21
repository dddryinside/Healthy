package com.dddryinside.service;

import java.io.InputStream;
import java.util.Objects;

public class ResourceManager {
    public static InputStream loadImage(String uri) {
        return ResourceManager.class.getResourceAsStream(uri);
    }

    public static String loadStyle(String uri) {
        return Objects.requireNonNull(PageManager.class.getResource(uri)).toExternalForm();
    }
}
