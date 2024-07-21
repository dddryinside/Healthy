package com.dddryinside.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String secondName;
    private String additionalName;
    private String username;
    private String password;

    public User(String name, String secondName, String additionalName,
                String username, String password) {
        this.name = name;
        this.secondName = secondName;
        this.additionalName = additionalName;
        this.username = username;
        this.password = password;
    }

    public String getFio() {
        StringBuilder fio = new StringBuilder();

        if (!secondName.isEmpty()) {
            fio.append(secondName).append(" ");
        }

        fio.append(name);

        if (!additionalName.isEmpty()) {
            fio.append(" ").append(additionalName);
        }

        return String.valueOf(fio);
    }
}
