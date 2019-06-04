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
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 490),
                new UserMeal(LocalDateTime.of(2014, Month.JUNE, 1, 8, 0), "Завтрак2", 600),
                new UserMeal(LocalDateTime.of(2014, Month.JUNE, 1, 17, 0), "Завтрак2", 10)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredWithExceeded.forEach(System.out::println);
//        .toLocalDate();
//        .toLocalTime();
    }


    /*
    Реализация с forEach
     */
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesInTheDate = new HashMap<>();
        for (UserMeal meal : mealList) {
            if (!caloriesInTheDate.containsKey(meal.getDateTime().toLocalDate()))
                caloriesInTheDate.put(meal.getDateTime().toLocalDate(), meal.getCalories());
            else {
                caloriesInTheDate.replace(meal.getDateTime().toLocalDate(),
                        caloriesInTheDate.get(meal.getDateTime().toLocalDate()) + meal.getCalories());
            }
        }
        List<UserMealWithExceed> mealsWithExceed = new ArrayList<>();
        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                boolean exceed = caloriesInTheDate.get(meal.getDateTime().toLocalDate()) > caloriesPerDay;
                mealsWithExceed.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed));
            }
        }
        return mealsWithExceed;
    }
}
