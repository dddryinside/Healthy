package com.dddryinside.service;

import com.dddryinside.service.DataBaseAccess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private int id;
    private String name;
    private String secondName;
    private String additionalName;
    private LocalDate birthDate;
    private String sex;
    private String password;

    public Patient(int id, String name, String secondName, String additionalName, LocalDate birthDate, String sex) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.additionalName = additionalName;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public Patient(String name, String secondName, String additionalName, LocalDate birthDate, String sex, String password) {
        this.name = name;
        this.secondName = secondName;
        this.additionalName = additionalName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.password = password;
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
        } else if (this.password == null || this.password.trim().isEmpty() || this.password.length() < 5 || DataBaseAccess.checkPasswordExist(password)) {
            if (this.password == null || this.password.trim().isEmpty() || this.password.length() < 8) {
                throw new IllegalArgumentException("Пароль слишком короткий! Должно быть не меньше 5 символов!");
            } else {
                throw new IllegalArgumentException("Пароль недоступен. Попробуйте другой");
            }
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return secondName + " " + name + " " + additionalName + ", " + getStringBirthDate() + ", " + getStringSex();
    }

    public String getStringSex() {
        if (this.sex.equals("Male")) {
            return "мужской";
        } else {
            return "женский";
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
