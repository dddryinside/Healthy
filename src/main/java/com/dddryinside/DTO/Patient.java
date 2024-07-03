package com.dddryinside.DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    private int id;
    private String name;
    private String secondName;
    private String additionalName;
    private LocalDate birthDate;
    private String sex;

    public Patient(int id, String name, String secondName, String additionalName, LocalDate birthDate, String sex) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.additionalName = additionalName;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public Patient(String name, String secondName, String additionalName, LocalDate birthDate, String sex) {
        this.name = name;
        this.secondName = secondName;
        this.additionalName = additionalName;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public boolean isCorrect() throws IllegalArgumentException{
        if (this.secondName.isEmpty() || this.secondName.trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия введена не верно!");
        } else if (this.name.isEmpty() || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя введено неверно!");
        } else if (this.additionalName.isEmpty() || this.additionalName.trim().isEmpty()) {
            throw new IllegalArgumentException("Отчество введено неверно!");
        } else if (this.birthDate == null) {
            throw new IllegalArgumentException("Дата рождения вверена неверно!");
        } else if (this.sex == null) {
            throw new IllegalArgumentException("Пол не выбран!");
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return secondName + " " + name + " " + additionalName + ", " + getStringBirthDate() + ", " + getStringSex();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getAdditionalName() {
        return additionalName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public String getStringSex() {
        if (this.sex.equals("Male")) {
            return "Мужчина";
        } else {
            return "Женщина";
        }
    }

    public String getStringBirthDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return this.birthDate.format(formatter);
    }

    public String getFio() {
        return secondName + " " + name + " " + additionalName + " ";
    }
}
