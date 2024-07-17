package com.dddryinside.models;

import lombok.Getter;

@Getter
public enum Tests {
    PSQI("Питтсбургский опросник на определение индекса качества сна (PSQI)", "psqi", "Анкета, которая оценивает" +
            "качество сна в течении одного месяца", 22),
    BDI2( "Шкала депрессии Бека-2 (BDI-2)", "bdi2", "Шкала депрессии Бека (Beck Depression Inventory, BDI) - " +
            "методика диагностики депрессивных состояний, разработанная американским психотерапевтом Аароном Беком на основе клинических наблюдений, " +
            "позволивших выявить ограниченный набор наиболее релевантных и значимых симптомов депрессии и наиболее часто предъявляемых " +
            "пациентами жалоб.", 21),
    GAD7("Тест на тревожность (GAD-7)", "gad7", "Шкала GAD-7 (ГТР-7) для оценки уровня тревожности и выявления " +
            "симптомов тревожных расстройств, в частности генерализированного тревожного расстройства.", 10),
    YBOCS("Шкала Йеля-Брауна для оценки симптомов ОКР (Y-BOCS)", "ybocs", "(Yale-Brown Obsessive Compulsive Scale) " +
            "обсессивно-компульсивная шкала Йеля — Брауна - клиническое пособие для определения степени тяжести обсессивных и компульсивных симптомов." +
            " Разработана Вейном Гудманом (англ. Wayne Goodman) и его коллегами в Йельском и Брауновском университетах", 10);

    private final String fullName;
    private final String name;
    private final String description;
    private final Integer questionsAmount;

    Tests(String fullName, String name, String description, Integer questionsAmount) {
        this.fullName = fullName;
        this.name = name;
        this.description = description;
        this.questionsAmount = questionsAmount;
    }
}
