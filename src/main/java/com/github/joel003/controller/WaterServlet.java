package com.github.joel003.controller;

import com.github.joel003.model.Water;
import com.github.joel003.service.WaterService;
import com.github.joel003.util.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "water", value = "/water-calc")
public class WaterServlet extends HttpServlet {
    private final WaterService waterService;
    public WaterServlet() {
        this.waterService = new WaterService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            Water water = new Water();

            water.setWeight(InputValidator.parseDouble(req.getParameter("weight"),"Weight"));
            water.setCategory(InputValidator.parseDouble(req.getParameter("activity"),"Category"));

            double total = waterService.getTotalWaterNeeded(water);

            req.setAttribute("waterLiters", String.format("%.2f", total));

        } catch(Exception e){
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("water.jsp").forward(req, resp);

    }
}
