package com.dddryinside.DTO;

import java.time.LocalDate;

public class Patient {
    private String name;
    private String secondName;
    private String additionalName;
    private LocalDate birthDate;
    private String sex;

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
        return name + " " + secondName + " " + additionalName + " " + birthDate + " " + sex;
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
}
