package com.dddryinside.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class MoodDTO {
    int mood;
    String date;

    public String getShortStringDate() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(this.date, inputFormatter);

        // Форматируем дату в нужный формат
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM.dd");
        return date.format(outputFormatter);
    }
}
