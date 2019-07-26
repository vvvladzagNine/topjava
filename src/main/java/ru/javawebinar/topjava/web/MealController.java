package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class MealController {
    @Autowired
    private MealService service;

    @GetMapping("/meals")
    public String meals(Model model, HttpServletRequest request) {
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                service.delete(id,SecurityUtil.authUserId());
                model.addAttribute("meals", MealsUtil.getWithExcess(service.getAll(SecurityUtil.authUserId()),SecurityUtil.authUserCaloriesPerDay()));
                return "meals";
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        service.get(Integer.parseInt(request.getParameter("id")),SecurityUtil.authUserId());
                model.addAttribute("meal", meal);
                return "mealForm";

            case "filter":
                model.addAttribute("meals", MealsUtil.getFilteredWithExcess(
                        service.getBetweenDates(
                                LocalDate.parse(request.getParameter("startDate")),
                                LocalDate.parse(request.getParameter("endDate")),
                                SecurityUtil.authUserId()),
                        SecurityUtil.authUserCaloriesPerDay(),
                        LocalTime.parse(request.getParameter("startTime")),
                        LocalTime.parse(request.getParameter("endTime"))));
                return "meals";

            case "all":
            default:
                model.addAttribute("meals", MealsUtil.getWithExcess(service.getAll(SecurityUtil.authUserId()),SecurityUtil.authUserCaloriesPerDay()));
                return "meals";

        }



    }



    @PostMapping("/meals")
    public String mealsPost(HttpServletRequest request) {

        String ids = request.getParameter("id");
        if(ids==null) {
            Meal m = new Meal(LocalDateTime.parse(request.getParameter("dateTime")), request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
            service.create(m, SecurityUtil.authUserId());
        }

        else {
            Meal m = service.get(Integer.parseInt(ids),SecurityUtil.authUserId());
            m.setDescription(request.getParameter("description"));
            m.setCalories(Integer.parseInt(request.getParameter("calories")));
            m.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
            service.update(m, SecurityUtil.authUserId());
        }

        //model.addAttribute("meals", MealsUtil.getWithExcess(service.getAll(SecurityUtil.authUserId()),SecurityUtil.authUserCaloriesPerDay()));
        return "redirect:meals";
    }
}
