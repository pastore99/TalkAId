package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PatientConditionTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the PatientCondition class
        PatientCondition patientCondition = new PatientCondition();

        // Set values using setter methods
        int idCondition = 1;
        int idPatient = 2;
        int severity = 3;

        patientCondition.setIdCondition(idCondition);
        patientCondition.setIdPatient(idPatient);
        patientCondition.setSeverity(severity);

        // Test getter methods
        assertEquals(idCondition, patientCondition.getIdCondition(), "getIdCondition() should return the correct value");
        assertEquals(idPatient, patientCondition.getIdPatient(), "getIdPatient() should return the correct value");
        assertEquals(severity, patientCondition.getSeverity(), "getSeverity() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the PatientCondition class
        PatientCondition patientCondition = new PatientCondition();

        // Test that the object is not null
        assertNotNull(patientCondition, "PatientCondition object should not be null");
    }
}

