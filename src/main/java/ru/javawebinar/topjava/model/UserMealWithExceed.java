package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExceed {

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public String toString() {
        return "Date: " + dateTime + " Desc: " + description + " Cal: " + calories + " Exceed: "  +exceed;
    }
}
