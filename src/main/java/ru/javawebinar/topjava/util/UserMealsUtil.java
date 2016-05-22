package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Ужин", 410),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,11,0), "Ужин", 310),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Ужин", 810)
        );
        List<UserMealWithExceed> rt = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();

        for (UserMealWithExceed m :rt ){
            System.out.println(m.getCalories());
        }
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        LocalDateTime lt;
        LocalTime hour;
        String desc;
        List <UserMealWithExceed> calories = new ArrayList<>();

        for (UserMeal um : mealList){
            // System.out.println(um.getDescription() + " " + um.getCalories());
            lt = um.getDateTime();
            hour = lt.toLocalTime();
            desc = um.getDescription();

            if (TimeUtil.isBetween(hour, startTime, endTime)) {
                int call = um.getCalories();
                calories.add(new UserMealWithExceed(lt, desc, call, caloriesPerDay > call));
            }

        }
        Collections.sort(calories, (o1, o2) -> {
            if (o1.getCalories() > o2.getCalories())
                return 1;
            else if (o1.getCalories() < o2.getCalories())
                return -1;
            else return 0;
        });

        return calories;
    }
}
