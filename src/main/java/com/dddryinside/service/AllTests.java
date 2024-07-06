package com.dddryinside.service;

import com.dddryinside.tests.*;

public enum AllTests {
    DASS21("Шкала депрессии, тревоги и стресса (DASS-21)", "dass21", new DASS21(), "(Depression, Anxiety " +
            "and Stress Scales) — это психологический инструмент для измерения уровня депрессии, тревоги и стресса у человека", 12),
    PSQI("Тест на генерализованное тревожное расстройство (GAD-7)", "psqi", new GAD7(), "(General Anxiety Disorder-7) - " +
            "шкала оценки уровня тревожности и выявления симптомов тревожных расстройств, в частности " +
            "генерализированного тревожного расстройства", 7),
    BDI2("Шкала депрессии Бека-2 (BDI-2)", "bdi2", new BDI2(), "Шкала депрессии Бека (Beck Depression Inventory, BDI) - " +
            "методика диагностики депрессивных состояний, разработанная американским психотерапевтом Аароном Беком на основе клинических наблюдений, " +
            "позволивших выявить ограниченный набор наиболее релевантных и значимых симптомов депрессии и наиболее часто предъявляемых " +
            "пациентами жалоб.", 21),
    YBOCS("Шкала Йеля-Брауна для оценки симптомов ОКР (Y-BOCS)", "ybocs", new YBOCS(), "(Yale-Brown Obsessive Compulsive Scale) " +
            "обсессивно-компульсивная шкала Йеля — Брауна - клиническое пособие для определения степени тяжести обсессивных и компульсивных симптомов." +
            " Разработана Вейном Гудманом (англ. Wayne Goodman) и его коллегами в Йельском и Брауновском университетах", 10);

    private final String fullName;
    private final String name;
    private final Test test;
    private final String description;
    private final Integer questionsAmount;

    AllTests(String fullName, String name, Test test, String description, Integer questionsAmount) {
        this.fullName = fullName;
        this.name = name;
        this.test = test;
        this.description = description;
        this.questionsAmount = questionsAmount;
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
    public String getDescription() {
        return description;
    }

    public Integer getQuestionsAmount() {
        return questionsAmount;
    }
}
