package com.github.joel003.service;

import com.github.joel003.exception.InvalidInputException;
import com.github.joel003.model.Water;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterServiceTest {

    private final WaterService service = new WaterService();

    @Test
    @DisplayName("Valid water intake calculation")
    void testValidWaterIntake() throws InvalidInputException {
        Water water = new Water(70, 1.0);
        double result = service.getTotalWaterNeeded(water);
        assertEquals(3.45, result, 0.01);
    }

    @Test
    @DisplayName("Calculated water intake too low throws exception")
    void testTooLowWaterIntake() {
        Water water = new Water(20, 0.0);
        Exception ex = assertThrows(Exception.class,
                () -> service.getTotalWaterNeeded(water));
        assertEquals("Calculated water intake is unrealistic. Please check input.", ex.getMessage());
    }

    @Test
    @DisplayName("Calculated water intake too high throws exception")
    void testTooHighWaterIntake() {
        Water water = new Water(300, 5.0);
        Exception ex = assertThrows(Exception.class,
                () -> service.getTotalWaterNeeded(water));
        assertEquals("Calculated water intake is unrealistic. Please check input.", ex.getMessage());
    }

    @Test
    @DisplayName("Boundary case: exactly 1 liter is valid")
    void testBoundaryOneLiter() throws InvalidInputException {
        Water water = new Water(20, 0.3);
        double result = service.getTotalWaterNeeded(water);
        assertEquals(1.0, result, 0.01);
    }

    @Test
    @DisplayName("Boundary case: exactly 10 liters is valid")
    void testBoundaryTenLiters() throws InvalidInputException {
        Water water = new Water(250, 1.25);
        double result = service.getTotalWaterNeeded(water);
        assertEquals(10.0, result, 0.01);
    }
}
