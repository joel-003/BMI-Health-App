package com.github.joel003.service;

import com.github.joel003.exception.InvalidInputException;
import com.github.joel003.model.BodyFat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyFatServiceTest {

    private BodyFatService service;

    @BeforeEach
    void setUp() {
        service = new BodyFatService();
    }

    @Test
    @DisplayName("Valid male body fat calculation")
    void testValidMaleBodyFat() throws InvalidInputException {
        BodyFat bodyFat = new BodyFat("male", 0, 175, 85, 40);
        double result = service.getBodyFat(bodyFat);
        assertTrue(result > 1 && result < 70, "Result should be realistic");
        String category = service.getBodyFatCategory(result, bodyFat);
        assertNotNull(category);
    }

    @Test
    @DisplayName("Valid female body fat calculation")
    void testValidFemaleBodyFat() throws InvalidInputException {
        BodyFat bodyFat = new BodyFat("female", 95, 165, 70, 30);
        double result = service.getBodyFat(bodyFat);
        assertTrue(result > 1 && result < 70, "Result should be realistic");
        String category = service.getBodyFatCategory(result, bodyFat);
        assertNotNull(category);
    }

    @Test
    @DisplayName("Waist smaller than neck for male throws exception")
    void testMaleWaistSmallerThanNeck() {
        BodyFat bodyFat = new BodyFat("male", 0, 175, 35, 40);
        Exception ex = assertThrows(Exception.class, () -> service.getBodyFat(bodyFat));
        assertEquals("Waist size must be between 40 cm and 200 cm.", ex.getMessage());
    }

    @Test
    @DisplayName("Hip out of range for female throws exception")
    void testFemaleHipOutOfRange() {
        BodyFat bodyFat = new BodyFat("female", 30, 165, 70, 30);
        Exception ex = assertThrows(Exception.class, () -> service.getBodyFat(bodyFat));
        assertEquals("Hip size must be between 50 cm and 200 cm.", ex.getMessage());
    }

    @Test
    @DisplayName("Unrealistic body fat percentage throws exception")
    void testUnrealisticBodyFat() {
        // Force unrealistic values by manipulating inputs
        BodyFat bodyFat = new BodyFat("male", 0, 100, 200, 20);
        Exception ex = assertThrows(Exception.class, () -> service.getBodyFat(bodyFat));
        assertEquals("Calculated body fat % is unrealistic. Please check your inputs.", ex.getMessage());
    }

    @Test
    @DisplayName("Category classification for male")
    void testMaleCategoryClassification() {
        BodyFat bodyFat = new BodyFat("male", 0, 175, 85, 40);
        assertEquals("Essential Fat", service.getBodyFatCategory(5, bodyFat));
        assertEquals("Athlete", service.getBodyFatCategory(10, bodyFat));
        assertEquals("Fitness", service.getBodyFatCategory(15, bodyFat));
        assertEquals("Average", service.getBodyFatCategory(20, bodyFat));
        assertEquals("Obese", service.getBodyFatCategory(30, bodyFat));
    }

    @Test
    @DisplayName("Category classification for female")
    void testFemaleCategoryClassification() {
        BodyFat bodyFat = new BodyFat("female", 95, 165, 70, 30);
        assertEquals("Essential Fat", service.getBodyFatCategory(10, bodyFat));
        assertEquals("Athlete", service.getBodyFatCategory(18, bodyFat));
        assertEquals("Fitness", service.getBodyFatCategory(22, bodyFat));
        assertEquals("Average", service.getBodyFatCategory(28, bodyFat));
        assertEquals("Obese", service.getBodyFatCategory(35, bodyFat));
    }

}