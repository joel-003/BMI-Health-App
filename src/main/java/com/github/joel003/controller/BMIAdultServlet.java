package com.github.joel003.controller;

import com.github.joel003.exception.InvalidInputException;
import com.github.joel003.model.BMIAdult;
import com.github.joel003.service.BMIAdultService;
import com.github.joel003.util.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BMI-Adult", value = "/bmi-adult")
public class BMIAdultServlet extends HttpServlet {

    private final BMIAdultService bmiAdultService;

    public BMIAdultServlet() {
        this.bmiAdultService = new BMIAdultService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            try {
                BMIAdult bmiAdult = new BMIAdult();

                bmiAdult.setHeight(
                        InputValidator.parseDouble(req.getParameter("heightValue"), "Height")
                );
                bmiAdult.setHeightUnit(req.getParameter("heightUnit"));

                bmiAdult.setWeight(
                        InputValidator.parseDouble(req.getParameter("weightValue"), "Weight")
                );
                bmiAdult.setWeightUnit(req.getParameter("weightUnit"));

                double bmi = bmiAdultService.getBMIValueForAdult(bmiAdult);
                String category = bmiAdultService.getBMICategoryForAdult(bmi);
                String[] tips = bmiAdultService.getTipsForCategoryForAdult(category);

                req.setAttribute("bmi", String.format("%.1f", bmi));
                req.setAttribute("category", category);
                req.setAttribute("tips", tips);

            } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            }

            req.getRequestDispatcher("bmi-adult.jsp").forward(req, resp);
    }
}
