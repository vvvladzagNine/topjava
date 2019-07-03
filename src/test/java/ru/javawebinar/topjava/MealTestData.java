package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int ID1 = START_SEQ+2;
    public static final int ID2 = START_SEQ+3;
    public static final int ID3 = START_SEQ+4;
    public static final int ID4 = START_SEQ+5;
    public static final int ID5 = START_SEQ+6;
    public static final int ID6 = START_SEQ+7;



    public static final Meal meal1 = new Meal(ID1, LocalDateTime.of(2011,5,16,10,36,37), "Breakfast", 500);
    public static final Meal meal2 = new Meal(ID2, LocalDateTime.of(2011,5,16,15,36,37), "Dinner", 500);
    public static final Meal meal3 = new Meal(ID3, LocalDateTime.of(2011,5,16,19,36,37), "Supper", 500);
    public static final Meal meal4 = new Meal(ID4, LocalDateTime.of(2011,5,17,10,36,38), "Breakfast2", 500);
    public static final Meal meal5 = new Meal(ID5, LocalDateTime.of(2011,5,17,15,36,38), "supper2", 500);
    public static final Meal meal6 = new Meal(ID6, LocalDateTime.of(2011,5,17,19,36,38), "dinner2", 500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }


    public static void assertMatch(List<Meal> actual, List<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
