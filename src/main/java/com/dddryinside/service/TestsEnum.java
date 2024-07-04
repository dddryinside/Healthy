package com.dddryinside.service;

public enum TestsEnum {
    DASS21("Шкала депрессии, тревоги и стресса (DASS-21)", "dass21");

    private final String fullName;
    private final String dbName;

    TestsEnum(String fullName, String dbName) {
        this.fullName = fullName;
        this.dbName = dbName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDbName() {
        return dbName;
    }
}
