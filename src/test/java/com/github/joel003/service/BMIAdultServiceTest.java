package com.github.joel003.service;

import com.github.joel003.model.BMIAdult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BMIAdultServiceTest {

    BMIAdultService service ;

    @BeforeEach
    void setUP(){
        service = new BMIAdultService();
    }

    @ParameterizedTest
    @DisplayName("BMI Test with Kg And CM")
    @MethodSource("BMIWithKgCmProvider")
    void testBMIWithKgCm(BMIAdult bmi,double expectedResult) throws Exception {
        double result = service.getBMIValueForAdult(bmi);
        assertEquals(expectedResult, result,0.1);
    }
    static Stream<Arguments> BMIWithKgCmProvider() {
        return Stream.of(
                Arguments.of(new BMIAdult(180,"cm",75,"kg"),23.15),
                Arguments.of(new BMIAdult(165,"cm",60,"kg"),22.03),
                Arguments.of(new BMIAdult(150,"cm",50,"kg"),22.22),
                Arguments.of(new BMIAdult(170,"cm",90,"kg"),31.14),
                Arguments.of(new BMIAdult(160,"cm",40,"kg"),15.62),
                Arguments.of(new BMIAdult(190,"cm",120,"kg"),33.24),
                Arguments.of(new BMIAdult(200,"cm",70,"kg"),17.50),
                Arguments.of(new BMIAdult(175,"cm",68,"kg"),22.120)
                );
    }

    @ParameterizedTest
    @DisplayName("BMI Test with LB And FT")
    @MethodSource("BMIWithFtLbProvider")
    void testBMIWithLbFt(BMIAdult bmi,double expectedResult) throws Exception {

        double result = service.getBMIValueForAdult(bmi);
        assertEquals(expectedResult, result,0.1);
    }
    static Stream<Arguments> BMIWithFtLbProvider() {
        return Stream.of(
                Arguments.of(new BMIAdult(5.9, "ft", 165, "lb"), 23.14),
                Arguments.of(new BMIAdult(6.0, "ft", 200, "lb"), 27.12),
                Arguments.of(new BMIAdult(5.5, "ft", 140, "lb"), 22.60),
                Arguments.of(new BMIAdult(5.2, "ft", 120, "lb"), 21.67),
                Arguments.of(new BMIAdult(6.2, "ft", 180, "lb"), 22.86),
                Arguments.of(new BMIAdult(5.0, "ft", 100, "lb"), 19.53),
                Arguments.of(new BMIAdult(5.8, "ft", 220, "lb"), 31.93),
                Arguments.of(new BMIAdult(6.1, "ft", 150, "lb"), 19.68)

        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {14.0, 15.5, 16.8, 17.0, 18.0, 18.49})
    void testUnderweightCategory(double bmiValue) {
        String category = service.getBMICategoryForAdult(bmiValue);
        assertEquals("Underweight", category);
    }

    @ParameterizedTest
    @ValueSource(doubles = {18.5, 19.0, 21.3, 22.8, 24.0, 24.9})
    void testNormalCategory(double bmiValue) {
        String category = service.getBMICategoryForAdult(bmiValue);
        assertEquals("Normal", category);
    }

    @ParameterizedTest
    @ValueSource(doubles = {25.0, 26.2, 27.5, 28.9, 29.0, 29.9})
    void testOverweightCategory(double bmiValue) {
        String category = service.getBMICategoryForAdult(bmiValue);
        assertEquals("Overweight", category);
    }

    @ParameterizedTest
    @ValueSource(doubles = {32.5, 35.8, 38.0, 42.3, 50.0})
    void testObeseCategory(double bmiValue) {
        String category = service.getBMICategoryForAdult(bmiValue);
        assertEquals("Obese", category);
    }

    @ParameterizedTest
    @MethodSource("invalidBMIProvider")
    @DisplayName("To check the generated bmi is valid or not")
    void testBMIValueThrowsException(BMIAdult bmi) {

        Exception ex= assertThrows(Exception.class, () -> service.getBMIValueForAdult(bmi));
        assertEquals("Invalid BMI value. Please recheck your inputs.",ex.getMessage());
    }
    static Stream<Arguments> invalidBMIProvider() {
        return Stream.of(
                Arguments.of(new BMIAdult(250, "cm", 20, "kg")),
                Arguments.of(new BMIAdult(120, "cm", 120, "kg"))
        );
    }

    @Test
    void testInvalidHeightThrowsException() {
        BMIAdult bmi = new BMIAdult();
        bmi.setHeight(0);
        bmi.setHeightUnit("cm");
        bmi.setWeight(60);
        bmi.setWeightUnit("kg");

        Exception exception = assertThrows(Exception.class, () ->
                service.getBMIValueForAdult(bmi)
        );

        assertEquals("Height must be between 50 cm and 250 cm.", exception.getMessage());
    }

    @Test
    void testInvalidWeightThrowsException() {
        BMIAdult bmi = new BMIAdult();
        bmi.setHeight(170);
        bmi.setHeightUnit("cm");
        bmi.setWeight(-5);
        bmi.setWeightUnit("kg");

        Exception ex = assertThrows(Exception.class, () ->
                service.getBMIValueForAdult(bmi)
        );

        assertEquals("Weight must be between 20 kg and 300 kg.", ex.getMessage());
    }

}