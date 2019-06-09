package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.repos.MealsRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("meals", MealsRepo.loadMeals());
        req.getRequestDispatcher("meals.jsp").forward(req,resp);
    }
}
