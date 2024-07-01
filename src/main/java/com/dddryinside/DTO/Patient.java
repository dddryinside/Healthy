package com.dddryinside.DTO;

public class Patient {
    private String name;
    private String secondName;
    private String additionalName;

    public Patient(String name, String secondName, String additionalName) {
        this.name = name;
        this.secondName = secondName;
        this.additionalName = additionalName;
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
}
