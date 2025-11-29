package com.github.joel003.util;

import com.github.joel003.exception.InvalidInputException;

public class InputValidator {

    // Validate and parse double
    public static double parseDouble(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " is required.");
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid number.");
        }
    }

    // Validate and parse integer
    public static int parseInt(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " is required.");
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid whole number.");
        }
    }

    // Validate positive values
    public static void validatePositive(double value, String fieldName)
            throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be greater than zero.");
        }
    }
}
