package com.dddryinside.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String secondName;
    private String additionalName;
    private LocalDate birthDate;
    private int gender;
    private String username;
    private String password;

    public User(String name, String secondName, String additionalName,
                LocalDate birthDate, int gender, String username, String password) {
        this.name = name;
        this.secondName = secondName;
        this.additionalName = additionalName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    public String getFio() {
        return secondName + " " + name + " " + additionalName;
    }

    public String getStringBirthDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return this.birthDate.format(formatter);
    }

    public String getStringGender() {
        if (gender == 1) {
            return "мужской";
        } else {
            return "женский";
        }
    }

    @Override
    public String toString() {
        return "id = " + id + "\nname = " + name +
                "\nsecond_name = " + secondName + "\nadditional_name = " + additionalName +
                "\nbirth_date = " + birthDate.toString() + "\ngender = " + gender +
                "\nusername = " + username + "\npassword = " + password;
    }
}
