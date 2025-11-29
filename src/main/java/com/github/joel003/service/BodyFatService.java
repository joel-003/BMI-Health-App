package com.github.joel003.service;

import com.github.joel003.exception.InvalidInputException;
import com.github.joel003.model.BodyFat;
import com.github.joel003.util.InputValidator;
import com.github.joel003.util.MeasurementValidator;

public class BodyFatService {
    public double getBodyFat(BodyFat bodyFat) throws InvalidInputException {

        double height = bodyFat.getHeight();
        double waist  = bodyFat.getWaistSize();
        double neck   = bodyFat.getNeckSize();

        MeasurementValidator.validateHeight(height,"cm");

        if (waist < 40 || waist > 200) {
            throw new InvalidInputException("Waist size must be between 40 cm and 200 cm.");
        }
        if (neck < 20 || neck > 60) {
            throw new InvalidInputException("Neck size must be between 20 cm and 60 cm.");
        }

        if ("male".equalsIgnoreCase(bodyFat.getGender())) {
            if (waist <= neck)
                throw new InvalidInputException("Waist must be larger than neck for male formula.");

            double A = Math.log10(waist - neck);
            double B = Math.log10(height);

            double denomi = 1.0324 - 0.19077 * A + 0.15456 * B;
            if (denomi == 0)
                throw new InvalidInputException("Invalid measurement values.");

            double result = 495.0 / denomi - 450.0;

            validateBodyFatRange(result);
            return result;
        }

        double hip = bodyFat.getHipSize();
        InputValidator.validatePositive(hip, "Hip");
        if (hip < 50 || hip > 200) {
            throw new InvalidInputException("Hip size must be between 50 cm and 200 cm.");
        }

        if ((waist + hip) <= neck)
            throw new InvalidInputException("Waist + Hip must be greater than neck for female formula.");

        double A = Math.log10(waist + hip - neck);
        double B = Math.log10(height);

        double denomi = 1.29579 - 0.35004 * A + 0.22100 * B;
        if (denomi == 0)
            throw new InvalidInputException("Invalid measurement values.");

        double result = 495.0 / denomi - 450.0;

        validateBodyFatRange(result);
        return result;

    }

    private void validateBodyFatRange(double value) throws InvalidInputException {
        if (Double.isNaN(value) || value < 1 || value > 70) {
            throw new InvalidInputException("Calculated body fat % is unrealistic. Please check your inputs.");
        }
    }

    public String getBodyFatCategory(double bodyFatValue,BodyFat bodyFat ) {

        if ("male".equalsIgnoreCase(bodyFat.getGender())) {

            if (bodyFatValue <= 5) return "Essential Fat";
            else if (bodyFatValue <= 13) return "Athlete";
            else if (bodyFatValue <= 17) return "Fitness";
            else if (bodyFatValue <= 24) return "Average";
            else return "Obese";

        } else {

            if (bodyFatValue <= 13) return "Essential Fat";
            else if (bodyFatValue <= 20) return "Athlete";
            else if (bodyFatValue <= 24) return "Fitness";
            else if (bodyFatValue <= 31) return "Average";
            else return "Obese";
        }
    }

}
