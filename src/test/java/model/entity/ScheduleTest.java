package model.entity;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScheduleTest {

    @Test
    void testGetterAndSetterMethods() {
        // Create an instance of the Schedule class
        Schedule schedule = new Schedule();

        // Set values using setter methods
        int idTherapist = 1;
        Date date = Date.valueOf("2022-01-01");
        String timeSlot = "10:00 AM - 11:00 AM";
        int reserved = 2;

        schedule.setIdTherapist(idTherapist);
        schedule.setDate(date);
        schedule.setTimeSlot(timeSlot);
        schedule.setReserved(reserved);

        // Test getter methods
        assertEquals(idTherapist, schedule.getIdTherapist(), "getIdTherapist() should return the correct value");
        assertEquals(date, schedule.getDate(), "getDate() should return the correct value");
        assertEquals(timeSlot, schedule.getTimeSlot(), "getTimeSlot() should return the correct value");
        assertEquals(reserved, schedule.getReserved(), "getReserved() should return the correct value");
    }

    @Test
    void testNotNull() {
        // Create an instance of the Schedule class
        Schedule schedule = new Schedule();

        // Test that the object is not null
        assertNotNull(schedule, "Schedule object should not be null");
    }
}

