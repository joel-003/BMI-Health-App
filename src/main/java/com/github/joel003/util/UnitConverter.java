package com.github.joel003.util;

public class UnitConverter {

    public static double convertHeightToCm(double value, String unit) {

        switch (unit.toLowerCase()) {
            case "cm": return value;
            case "m":  return value * 100;
            case "ft": return value * 30.48;
            default:   return value;
        }
    }

    public static double convertHeightToMeters(double value, String unit) {
        switch (unit.toLowerCase()) {
            case "cm": return value / 100.0;
            case "m":  return value;
            case "ft": return value * 0.3048;
            default:   return value;
        }
    }

    public static double convertWeightToKg(double value, String unit) {

        switch (unit.toLowerCase()) {
            case "kg": return value;
            case "lb": return value * 0.45359237;
            default:   return value;
        }
    }

}
