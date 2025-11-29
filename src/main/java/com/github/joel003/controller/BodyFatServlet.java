package com.github.joel003.controller;

import com.github.joel003.model.BodyFat;
import com.github.joel003.service.BodyFatService;
import com.github.joel003.util.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="bodyfat" ,value ="/bodyfat-calc")
public class BodyFatServlet extends HttpServlet {

    private final BodyFatService bodyFatService;
    public BodyFatServlet(){
        this.bodyFatService = new BodyFatService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            BodyFat bodyFat = new BodyFat();

            bodyFat.setGender(req.getParameter("gender"));
            if("female".equalsIgnoreCase(req.getParameter("gender"))){
                bodyFat.setHipSize(Double.parseDouble(req.getParameter("hip")));
            }

            bodyFat.setHeight(InputValidator.parseDouble((req.getParameter("height")),"Height"));
            bodyFat.setWaistSize(InputValidator.parseDouble(req.getParameter("waist"), "Waist"));
            bodyFat.setNeckSize(InputValidator.parseDouble(req.getParameter("neck"),"Neck"));

            double bodyFatValue = bodyFatService.getBodyFat(bodyFat);
            String category = bodyFatService.getBodyFatCategory(bodyFatValue,bodyFat);

            req.setAttribute("bodyFatValue", String.format("%.1f", bodyFatValue));
            req.setAttribute("category", category);

        } catch(Exception e){
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("bodyfat.jsp").forward(req,resp);

    }
}
