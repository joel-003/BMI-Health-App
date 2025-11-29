package com.github.joel003.service;

import com.github.joel003.exception.InvalidInputException;
import com.github.joel003.model.BMR;
import com.github.joel003.util.MeasurementValidator;
import com.github.joel003.util.UnitConverter;

public class BMRService {

    public double calculateBMR(BMR bmr) throws InvalidInputException {

        MeasurementValidator.validateAge(bmr.getAge());
        MeasurementValidator.validateHeight(bmr.getHeight(), bmr.getHeightUnit());
        MeasurementValidator.validateWeight(bmr.getWeight(), bmr.getWeightUnit());

        double height = UnitConverter.convertHeightToCm(bmr.getHeight(), bmr.getHeightUnit());
        double weight = UnitConverter.convertWeightToKg(bmr.getWeight(), bmr.getWeightUnit());

        int age = bmr.getAge();

        double bmrValue;
        if("male".equalsIgnoreCase(bmr.getGender())) {
            bmrValue= (10 * weight) + (6.25 * height) - (5 * age) + 5;
        }
        else {
            bmrValue= (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }

        if (Double.isNaN(bmrValue) || bmrValue < 500 || bmrValue > 4000){
            throw new InvalidInputException("Calculated BMR is out of valid range (500–4000).");
        }

        return bmrValue;
    }

    public double calculateTDEE(double bmrValue, BMR bmr) {
        return bmrValue * bmr.getActivity();
    }

}
