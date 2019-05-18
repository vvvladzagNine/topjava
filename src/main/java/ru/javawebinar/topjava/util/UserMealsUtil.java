package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed>  filteredWithExceeded=getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        mealList.sort((o1, o2) -> {
            if(o1.getDateTime().equals(o2.getDateTime()))
                return  0;

            if(o1.getDateTime().isAfter(o2.getDateTime())){
                return 1;
            }
            else return -1;
        });
        LocalDate d = LocalDate.of(1970,Month.JANUARY,1);
        int currentDayCallories=0;
        UserMeal mealToAdd=null;
        boolean exceed = false;
        List<UserMealWithExceed>  filteredWithExceeded = new ArrayList<>();
        for(UserMeal meal : mealList){

            if(!meal.getDateTime().toLocalDate().equals(d)){
                exceed=(currentDayCallories>caloriesPerDay);
                d=meal.getDateTime().toLocalDate();
                currentDayCallories=0;
                if(mealToAdd!=null){
                    filteredWithExceeded.add(new UserMealWithExceed(mealToAdd.getDateTime(),mealToAdd.getDescription(),mealToAdd.getCalories(),exceed));
                    mealToAdd=null;
                }
                currentDayCallories+=meal.getCalories();
                if(TimeUtil.isBetween(meal.getDateTime().toLocalTime(),startTime,endTime)){
                    mealToAdd=meal;
                }
            }
            else {
                currentDayCallories+=meal.getCalories();
                if(TimeUtil.isBetween(meal.getDateTime().toLocalTime(),startTime,endTime)){
                    mealToAdd=meal;
                }
            }
        }

        if(mealToAdd!=null){
            exceed=(currentDayCallories>caloriesPerDay);
            filteredWithExceeded.add(new UserMealWithExceed(mealToAdd.getDateTime(),mealToAdd.getDescription(),mealToAdd.getCalories(),exceed));
        }
        return filteredWithExceeded;
    }
}
