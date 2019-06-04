package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 11, 0), "Завтрак", 530),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2014, Month.JUNE, 1, 8, 0), "Завтрак2", 600),
                new UserMeal(LocalDateTime.of(2014, Month.JUNE, 1, 17, 0), "Завтрак2", 10)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredWithExceeded
                .forEach(
                        System.out::println
                );
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesInTheDate = mealList
                .stream()
                .collect(Collectors.toMap(p -> p.getDateTime().toLocalDate(), UserMeal::getCalories,
                        Integer::sum));
        return mealList
                .stream()
                .filter(o -> (TimeUtil.isBetween(o.getDateTime().toLocalTime(), startTime, endTime)))
                .map(o -> {
                    boolean exceed = caloriesInTheDate.get(o.getDateTime().toLocalDate()) > caloriesPerDay;
                    return new UserMealWithExceed(o.getDateTime(), o.getDescription(), o.getCalories(), exceed);
                })
                .collect(Collectors.toList());
    }
}
