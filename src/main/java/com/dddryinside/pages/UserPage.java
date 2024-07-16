package com.dddryinside.pages;

import com.dddryinside.contracts.Page;
import com.dddryinside.elements.Profile;
import com.dddryinside.elements.Root;
import com.dddryinside.models.User;
import com.dddryinside.service.SecurityManager;
import javafx.scene.Scene;

public class UserPage implements Page {
    private final User user = SecurityManager.getUser();
    @Override
    public Scene getInterface() {
        Profile profile = new Profile(user);

        Root root = new Root();
        root.setToTopCenter(profile);
        root.setMenuBar();

        return new Scene(root);
    }
}
