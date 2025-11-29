package com.github.joel003.controller;

import com.github.joel003.model.BMR;
import com.github.joel003.service.BMRService;
import com.github.joel003.util.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BMR" ,value ="/bmr-calc")
public class BMRServlet extends HttpServlet {

    private final BMRService bmrService;
    public BMRServlet() {
        this.bmrService = new BMRService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BMR bmr = new BMR();

            bmr.setAge(InputValidator.parseInt(req.getParameter("age"), "Age"));
            bmr.setGender(req.getParameter("gender"));

            bmr.setHeight(InputValidator.parseDouble(req.getParameter("heightValue"), "Height"));
            bmr.setHeightUnit(req.getParameter("heightUnit"));

            bmr.setWeight(InputValidator.parseDouble(req.getParameter("weightValue"), "Weight"));
            bmr.setWeightUnit(req.getParameter("weightUnit"));

            bmr.setActivity(InputValidator.parseDouble(req.getParameter("activity"), "Activity Level"));

            double bmrValue = bmrService.calculateBMR(bmr);
            double tdee = bmrService.calculateTDEE(bmrValue, bmr);

            req.setAttribute("bmrValue", String.format("%.0f", bmrValue));
            req.setAttribute("tdee", String.format("%.0f", tdee));

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("bmr.jsp").forward(req, resp);
    }
}
