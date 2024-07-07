package com.dddryinside.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private int id;
    private String name;
    private String secondName;
    private String additionalName;
    private LocalDate birthDate;
    private boolean sex;
    private String password;

    public PatientDTO(int id, String name, String secondName, String additionalName, LocalDate birthDate, boolean sex) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.additionalName = additionalName;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public PatientDTO(String name, String secondName, String additionalName, LocalDate birthDate, boolean sex, String password) {
        this.name = name;
        this.secondName = secondName;
        this.additionalName = additionalName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.password = password;
    }

    public PatientDTO(String name, String secondName, String additionalName, LocalDate birthDate, boolean sex) {
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
        } else if (this.password == null || this.password.trim().isEmpty() || this.password.length() < 5 || DataBaseAccess.checkPasswordExist(password)) {
            if (this.password == null || this.password.trim().isEmpty() || this.password.length() < 5) {
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
        return id + " " + secondName + " " + name + " " + additionalName + ", " + getStringBirthDate() + ", " + getStringSex();
    }

    public boolean getSex() {
        return this.sex;
    }

    public String getStringSex() {
        if (this.sex) {
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
