package com.dddryinside.DTO;

public enum Tests {
    DASS21("Шкала депрессии, тревоги и стресса (DASS-21)", "dass21", new DASS21());

    private final String fullName;
    private final String name;
    private final Test test;

    Tests(String fullName, String name, Test test) {
        this.fullName = fullName;
        this.name = name;
        this.test = test;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }

    public Test getTest() {
        return test;
    }
}
