package com.github.joel003.util;

import com.github.joel003.exception.InvalidInputException;

public class MeasurementValidator {

    public static void validateAge(int age) throws InvalidInputException {
        if (age < 10 || age > 120)
            throw new InvalidInputException("Age must be between 10 and 120.");
    }

    public static void validateHeight(double value, String unit) throws InvalidInputException {

        switch (unit.toLowerCase()) {
            case "cm":
                if (value < 50 || value > 250)
                    throw new InvalidInputException("Height must be between 50 cm and 250 cm.");
                break;

            case "m":
                if (value < 0.5 || value > 2.5)
                    throw new InvalidInputException("Height must be between 0.5 m and 2.5 m.");
                break;

            case "ft":
                if (value < 1.5 || value > 8.0)
                    throw new InvalidInputException("Height must be between 1.5 ft and 8 ft.");
                break;

            default:
                throw new InvalidInputException("Invalid height unit.");
        }
    }

    public static void validateWeight(double value, String unit) throws InvalidInputException {

        switch (unit.toLowerCase()) {
            case "kg":
                if (value < 20 || value > 300)
                    throw new InvalidInputException("Weight must be between 20 kg and 300 kg.");
                break;

            case "lb":
                if (value < 44 || value > 660)
                    throw new InvalidInputException("Weight must be between 44 lb and 660 lb.");
                break;

            default:
                throw new InvalidInputException("Invalid weight unit.");
        }
    }

}
