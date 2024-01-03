package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConditionTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the Condition class
        Condition condition = new Condition();

        // Set values using setter methods
        int idCondition = 1;
        String disorderDescription = "Test Disorder Description";
        String disorderName = "Test Disorder Name";

        condition.setIdCondition(idCondition);
        condition.setDisorderDescription(disorderDescription);
        condition.setDisorderName(disorderName);

        // Test getter methods
        assertEquals(idCondition, condition.getIdCondition(), "getIdCondition() should return the correct value");
        assertEquals(disorderDescription, condition.getDisorderDescription(), "getDisorderDescription() should return the correct value");
        assertEquals(disorderName, condition.getDisorderName(), "getDisorderName() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the Condition class
        Condition condition = new Condition();

        // Test that the object is not null
        assertNotNull(condition, "Condition object should not be null");
    }
}
