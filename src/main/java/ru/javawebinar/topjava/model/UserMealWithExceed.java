package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExceed extends  UserMeal {


    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        super(dateTime,description,calories);
        this.exceed = exceed;
    }

    public boolean isExceed() {
        return exceed;
    }
}
