package com.github.joel003.service;

import com.github.joel003.model.BMIAdult;
import com.github.joel003.util.MeasurementValidator;
import com.github.joel003.util.UnitConverter;

public class BMIAdultService {

    public double getBMIValueForAdult(BMIAdult bmiAdult) throws Exception {

        MeasurementValidator.validateHeight(bmiAdult.getHeight(), bmiAdult.getHeightUnit());
        MeasurementValidator.validateWeight(bmiAdult.getWeight(), bmiAdult.getWeightUnit());

        double height = UnitConverter.convertHeightToMeters(bmiAdult.getHeight(), bmiAdult.getHeightUnit());
        double weight = UnitConverter.convertWeightToKg(bmiAdult.getWeight(), bmiAdult.getWeightUnit());

        double bmi = weight / (height * height);

        if (Double.isNaN(bmi) || bmi < 5 || bmi > 80) {
            throw new Exception("Invalid BMI value. Please recheck your inputs.");
        }

        return bmi;
    }

    public String getBMICategoryForAdult(double bmi) {
        if(bmi < 18.5) {
            return  "Underweight";
        }else if(bmi < 25) {
            return  "Normal";
        }else if(bmi < 30) {
            return  "Overweight";
        }else{
            return  "Obese";
        }
    }

    public String[] getTipsForCategoryForAdult(String category) {
        switch (category) {
            case "Underweight":
                return new String[]{
                        "🥜 Eat calorie-rich snacks like nuts & cheese.",
                        "🍚 Have small meals more often.",
                        "🏋️‍♂️ Lift weights to build muscle."
                };
            case "Normal":
                return new String[]{
                        "🥗 Keep meals balanced & colorful.",
                        "🚶‍♂️ Stay active daily — walk, jog, stretch.",
                        "🩺 Get regular health checkups."
                };
            case "Overweight":
                return new String[]{
                        "🥤 Cut sugary drinks & junk food.",
                        "🏃‍♀️ Move 30+ mins daily — walk, dance, cycle.",
                        "🍽️ Watch portions & eat mindfully."
                };
            default:
                return new String[]{
                        "👩‍⚕️ Talk to a doctor for a plan.",
                        "🔄 Make slow, steady lifestyle changes.",
                        "📓 Track meals & progress in a journal."
                };
        }
    }

}

