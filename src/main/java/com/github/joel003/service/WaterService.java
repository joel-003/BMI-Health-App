package com.github.joel003.service;

import com.github.joel003.exception.InvalidInputException;
import com.github.joel003.model.Water;
import com.github.joel003.util.MeasurementValidator;

public class WaterService {

    public double getTotalWaterNeeded(Water water) throws InvalidInputException {

        double weight = water.getWeight();
        double activity = water.getCategory();

        MeasurementValidator.validateWeight(weight, "kg");

        double baseLiters = (weight * 35) / 1000.0;
        double total = baseLiters + activity;

        if (total < 1 || total > 10) {
            throw new InvalidInputException("Calculated water intake is unrealistic. Please check input.");
        }

        return total;
    }
}
