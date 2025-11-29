package com.github.joel003.util;

import com.github.joel003.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @ParameterizedTest
    @DisplayName("parseDouble with valid numbers")
    @CsvSource({
            "12.5, 12.5",
            "0.0, 0.0",
            "-3.14, -3.14"
    })
    void testParseDoubleValid(String input, double expected) throws InvalidInputException {
        assertEquals(expected, InputValidator.parseDouble(input, "Field"));
    }

    @ParameterizedTest
    @DisplayName("parseDouble with invalid inputs throws exception")
    @ValueSource(strings = {"", "   ", "abc"})
    void testParseDoubleInvalid(String input) {
        Exception ex = assertThrows(Exception.class,
                () -> InputValidator.parseDouble(input, "Field"));
        assertTrue(ex.getMessage().contains("Field"));
    }

    @ParameterizedTest
    @DisplayName("parseInt with valid integers")
    @CsvSource({
            "25, 25",
            "0, 0",
            "-10, -10"
    })
    void testParseIntValid(String input, int expected) throws InvalidInputException {
        assertEquals(expected, InputValidator.parseInt(input, "Field"));
    }

    @ParameterizedTest
    @DisplayName("parseInt with invalid inputs throws exception")
    @ValueSource(strings = {"", "   ", "12.5", "abc"})
    void testParseIntInvalid(String input) {
        Exception ex = assertThrows(Exception.class,
                () -> InputValidator.parseInt(input, "Field"));
        assertTrue(ex.getMessage().contains("Field"));
    }

    @ParameterizedTest
    @DisplayName("validatePositive with invalid values throws exception")
    @ValueSource(doubles = {0.0, -1.0, -5.5})
    void testValidatePositiveInvalid(double value) {
        Exception ex = assertThrows(Exception.class,
                () -> InputValidator.validatePositive(value, "Field"));
        assertEquals("Field must be greater than zero.", ex.getMessage());
    }

    @ParameterizedTest
    @DisplayName("validatePositive with valid values passes")
    @ValueSource(doubles = {0.1, 1.0, 100.0})
    void testValidatePositiveValid(double value) {
        assertDoesNotThrow(() -> InputValidator.validatePositive(value, "Field"));
    }
}
