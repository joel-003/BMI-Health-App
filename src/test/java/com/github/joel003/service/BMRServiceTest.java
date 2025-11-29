package com.github.joel003.service;

import com.github.joel003.exception.InvalidInputException;
import com.github.joel003.model.BMR;
import com.github.joel003.util.MeasurementValidator;
import com.github.joel003.util.UnitConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BMRServiceTest {
    BMRService service;

    @BeforeEach
    void setUp() {
        service = new BMRService();
    }

    @ParameterizedTest
    @DisplayName("Valid Age test")
    @ValueSource(ints ={12,43,34,67,22,80,100} )
    void validateAgeTest(int age){
        assertFalse(age < 10 || age > 120);
    }

    @ParameterizedTest
    @DisplayName("Invalid Age Exception test")
    @ValueSource(ints = {0,4,8,9,122,130,200,300,350})
    void validateAgeExceptionTest(int age) {
        Exception exception = assertThrows(Exception.class, () -> MeasurementValidator.validateAge(age));
        String expectedMessage = "Age must be between 10 and 120.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Invalid Height Exception test")
    @CsvSource({
            "40, cm, Height must be between 50 cm and 250 cm.",
            "300, cm, Height must be between 50 cm and 250 cm.",
            "0, m, Height must be between 0.5 m and 2.5 m.",
            "3, m, Height must be between 0.5 m and 2.5 m.",
            "1, ft, Height must be between 1.5 ft and 8 ft.",
            "9, ft, Height must be between 1.5 ft and 8 ft.",
            "170, inch, Invalid height unit."
    })
    void validateHeightExceptionTest(int height,String unit,String expectedMessage)  {
        Exception exception = assertThrows(Exception.class, () -> MeasurementValidator.validateHeight(height,unit));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Invalid Weight Exception test")
    @CsvSource({
            "10, kg, Weight must be between 20 kg and 300 kg.",
            "400, kg, Weight must be between 20 kg and 300 kg.",
            "30, lb, Weight must be between 44 lb and 660 lb.",
            "700, lb, Weight must be between 44 lb and 660 lb.",
            "70, stone, Invalid weight unit."
    })
    void validateWeightExceptionTest(int weight,String unit,String expectedMessage)  {
        Exception exception = assertThrows(Exception.class, () -> MeasurementValidator.validateWeight(weight,unit));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Validation of cm Conversion test")
    @CsvSource({
            "170, cm, 170",
            "200, cm, 200",
            "1.75, m, 175",
            "2.0, m, 200",
            "5, ft, 152.4",
            "6.5, ft, 198.12"
    })
    void validateConvertHeightToCmTest(double height,String unit,double expectedHeight) {
        double actualHeight = UnitConverter.convertHeightToCm(height,unit);
        assertEquals(expectedHeight, actualHeight,0.1);
    }

    @ParameterizedTest
    @DisplayName("Validation of kg Conversion test")
    @CsvSource({
            "220, lb, 99.79",
            "150, lb, 68.03",
            "70, kg, 70",
            "100, kg, 100"
    })
    void validateConvertWeightToKgTest(double weight,String unit,double expectedWeight) {
        double actualWeight = UnitConverter.convertWeightToKg(weight,unit);
        assertEquals(expectedWeight, actualWeight,0.01);
    }

    @ParameterizedTest
    @DisplayName("Calculation to test BMR")
    @MethodSource("BmrValueProvider")
    void calculateBMRTest(BMR bmr, double expectedBMR) throws InvalidInputException {
        double result = service.calculateBMR(bmr);
        assertEquals(expectedBMR, result,0.1);
    }
    static Stream<Arguments> BmrValueProvider() {
        return Stream.of(
                Arguments.of(new BMR(25, "male", 175, "cm", 70, "kg", 1.2), 1673.75),
                Arguments.of(new BMR(25, "female", 165, "cm", 60, "kg", 1.2), 1345.25),
                Arguments.of(new BMR(40, "male", 180, "cm", 80, "kg", 1.2), 1730.0)
        );
    }

    @ParameterizedTest
    @DisplayName("Calculation to test TDEE")
    @MethodSource("TdeeValueProvider")
    void calculateTDEETest(double bmrValue, double activity, double expectedTDEE) {
        BMR bmr = new BMR(25, "male", 175, "cm", 70, "kg", activity);
        double result = service.calculateTDEE(bmrValue, bmr);
        assertEquals(expectedTDEE, result, 0.1);
    }

    static Stream<Arguments> TdeeValueProvider() {
        return Stream.of(
                Arguments.of(1673.75, 1.2, 2008.5),
                Arguments.of(1673.75, 1.375, 2301.4),
                Arguments.of(1673.75, 1.55, 2594.3),
                Arguments.of(1673.75, 1.725, 2887.2),
                Arguments.of(1673.75, 1.9, 3180.1)
        );
    }

}