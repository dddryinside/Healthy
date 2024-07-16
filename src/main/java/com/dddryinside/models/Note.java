package com.dddryinside.models;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

@Data
public class Note {
    int id;
    int user_id;
    String content;
    LocalDate date;

    public Note(int id, User user, String content, LocalDate date) {
        this.id = id;
        this.user_id = user.getId();
        this.content = content;
        this.date = date;
    }

    public String getShortTitle() {
        return StringUtils.abbreviate(this.content, 50);
    }
}
