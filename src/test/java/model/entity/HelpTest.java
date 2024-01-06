package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelpTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the Help class
        Help help = new Help();

        // Set values using setter methods
        int idHelp = 1;
        int idExercise = 2;
        String helpText = "Test Help Text";

        help.setIdHelp(idHelp);
        help.setIdExercise(idExercise);
        help.setHelpText(helpText);

        // Test getter methods
        assertEquals(idHelp, help.getIdHelp(), "getIdHelp() should return the correct value");
        assertEquals(idExercise, help.getIdExercise(), "getIdExercise() should return the correct value");
        assertEquals(helpText, help.getHelpText(), "getHelpText() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the Help class
        Help help = new Help();

        // Test that the object is not null
        assertNotNull(help, "Help object should not be null");
    }
}

